package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Service
@Validated
public class RecommendByTypeAndThematicUseCase implements RecommendByTypeAndThematic {
    private final ResourceRepository resourceRepository;
    private final MapperUtils mapperUtils;

    public RecommendByTypeAndThematicUseCase(ResourceRepository resourceRepository, MapperUtils mapperUtils) {
        this.resourceRepository = resourceRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Flux<ResourceDTO> get(String type, String thematic) {
        Objects.requireNonNull(type, "el tipo no puede esta vacio");
        Objects.requireNonNull(thematic, "la tematica no puede esta vacia");
        return resourceRepository.findAllByTypeAndThematic(type, thematic).map(mapperUtils.mapEntityToResource()).distinct();
    }
}