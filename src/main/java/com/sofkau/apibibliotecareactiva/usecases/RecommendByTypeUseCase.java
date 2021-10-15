package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.repositories.ResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class RecommendByTypeUseCase implements RecommedByType{
    private final ResourceRepository resourceRepository;
    private final MapperUtils mapperUtils;

    public RecommendByTypeUseCase(ResourceRepository resourceRepository, MapperUtils mapperUtils) {
        this.resourceRepository = resourceRepository;
        this.mapperUtils = mapperUtils;
    }




    @Override
    public Flux<ResourceDTO> get(String type) {
        return resourceRepository.findAllByType(type).map(mapperUtils.mapEntityToResource());
    }
}