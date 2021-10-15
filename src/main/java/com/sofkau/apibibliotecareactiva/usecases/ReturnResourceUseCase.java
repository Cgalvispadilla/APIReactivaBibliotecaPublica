package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

@Service
@Validated
public class ReturnResourceUseCase implements Function<String, Mono<String>> {
    private final ResourceRepository resourceRepository;
    private final UpdateResourceUseCase updateResourceUseCase;
    private final MapperUtils mapperUtils;

    public ReturnResourceUseCase(ResourceRepository resourceRepository, MapperUtils mapperUtils) {
        this.resourceRepository = resourceRepository;
        this.updateResourceUseCase = new UpdateResourceUseCase(resourceRepository, mapperUtils);
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "el id no puede ser nullo");
        return resourceRepository.findById(id).flatMap(
                resource -> {
                    if (resource.getQuantityBorrowed() > 0) {
                        resource.setQuantityBorrowed(resource.getQuantityBorrowed() - 1);
                        return updateResourceUseCase.apply(mapperUtils.mapEntityToResource().apply(resource)).thenReturn("El recurso " + resource.getName() + " se ha devuelto");
                    }
                    return Mono.just("el recurso " + resource.getName() + " no se puede devolver, porque no se ha prestado");
                }
        );
    }
}
