package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.collections.Resource;
import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import org.springframework.cglib.core.internal.Function;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Supplier;

@Service
@Validated
public class CheckAvailabilityUseCase implements Function<String, Mono<String>> {
    private final ResourceRepository resourceRepository;

    public CheckAvailabilityUseCase(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }


    @Override
    public Mono<String> apply(String id) {
        Objects.requireNonNull(id, "El id no puede ser nulo");
        return resourceRepository.findById(id)
                .map(resource ->
                        resource.getQuantityAvailable() > resource.getQuantityBorrowed()
                                ? String.valueOf("El recurso " + resource.getName() + " disponible y cuenta con " + (resource.getQuantityAvailable() - resource.getQuantityBorrowed()) + " unidad(es) disponible(es)")
                                : String.valueOf("el recurso " + resource.getName() + " no esta disponible " + " fue prestado " + resource.getLoanDate())
                );
    }
}
