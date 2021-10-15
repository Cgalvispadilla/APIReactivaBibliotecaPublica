package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import reactor.core.publisher.Flux;

import javax.validation.Valid;

@FunctionalInterface
public interface RecommedByThematic {
    Flux<ResourceDTO> get(@Valid String thematic);
}
