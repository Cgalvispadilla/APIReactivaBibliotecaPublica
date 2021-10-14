package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListResourceUseCase implements Supplier<Flux<ResourceDTO>> {
    private final ResourceRepository resourceRepository;
    private final MapperUtils mapperUtils;

    public ListResourceUseCase(ResourceRepository resourceRepository, MapperUtils mapperUtils) {
        this.resourceRepository = resourceRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Flux<ResourceDTO> get() {
        return resourceRepository.findAll().map(mapperUtils.mapEntityToResource());
    }
}
