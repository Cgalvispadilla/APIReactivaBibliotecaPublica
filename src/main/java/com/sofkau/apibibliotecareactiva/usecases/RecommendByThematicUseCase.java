package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.function.Function;

@Service
@Validated
public class RecommendByThematicUseCase implements RecommedByThematic {
    private final ResourceRepository resourceRepository;
    private final MapperUtils mapperUtils;

    public RecommendByThematicUseCase(ResourceRepository resourceRepository, MapperUtils mapperUtils) {
        this.resourceRepository = resourceRepository;
        this.mapperUtils = mapperUtils;
    }




    @Override
    public Flux<ResourceDTO> get(String thematic) {
        return resourceRepository.findAllByThematic(thematic).map(mapperUtils.mapEntityToResource());
    }
}