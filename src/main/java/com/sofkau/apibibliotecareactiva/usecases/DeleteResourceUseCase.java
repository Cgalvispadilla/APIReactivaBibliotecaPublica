package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

public class DeleteResourceUseCase implements Function<String, Mono<Void>> {
    private final ResourceRepository resourceRepository;

    public DeleteResourceUseCase(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;

    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "el id es requerido");
        return resourceRepository.deleteById(id);
    }
}
