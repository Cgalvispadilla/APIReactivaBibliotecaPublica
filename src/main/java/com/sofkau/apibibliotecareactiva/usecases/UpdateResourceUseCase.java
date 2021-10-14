package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class UpdateResourceUseCase implements SaveResource {
    private final ResourceRepository resourceRepository;
    private final MapperUtils mapperUtils;

    public UpdateResourceUseCase(ResourceRepository resourceRepository, MapperUtils mapperUtils) {
        this.resourceRepository = resourceRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Mono<ResourceDTO> apply(ResourceDTO resourceDTO) {
        Objects.requireNonNull(resourceDTO.getId(), "El id del recurso es requerido");
        return resourceRepository.save(mapperUtils.mapperToResource().apply(resourceDTO));
    }
}
