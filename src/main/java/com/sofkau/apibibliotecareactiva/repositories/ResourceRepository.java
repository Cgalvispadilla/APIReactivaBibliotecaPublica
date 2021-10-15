package com.sofkau.apibibliotecareactiva.repositories;

import com.sofkau.apibibliotecareactiva.collections.Resource;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ResourceRepository extends ReactiveCrudRepository<Resource, String> {
    Flux<Resource> findAllByType(final String type);
    Flux<Resource> findAllByThematic(final String thematic);
    Flux<Resource> findAllByTypeAndThematic (final String type, final String thematic);
}
