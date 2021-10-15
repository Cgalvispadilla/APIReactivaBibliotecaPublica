package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@FunctionalInterface
public interface RecommendByTypeAndThematic {
    Flux<ResourceDTO> get(@Valid String type, @Valid String thematic);
}
