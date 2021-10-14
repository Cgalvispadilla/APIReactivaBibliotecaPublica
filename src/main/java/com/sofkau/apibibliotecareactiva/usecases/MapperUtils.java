package com.sofkau.apibibliotecareactiva.usecases;

import com.sofkau.apibibliotecareactiva.collections.Resource;
import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperUtils {
    public Function<ResourceDTO, Resource> mapperToResource() {
        return updateResource -> {
            var resource = new Resource();
            resource.setId(updateResource.getId());
            resource.setName(updateResource.getName());
            resource.setQuantityAvailable(updateResource.getQuantityAvailable());
            resource.setType(updateResource.getType());
            resource.setThematic(updateResource.getThematic());
            resource.setLoanDate(updateResource.getLoanDate());
            resource.setQuantityBorrowed(updateResource.getQuantityBorrowed());
            return resource;
        };
    }

    public Function<Resource, ResourceDTO> mapEntityToResource() {
        return entity -> new ResourceDTO(
                entity.getId(),
                entity.getName(),
                entity.getLoanDate(),
                entity.getQuantityAvailable(),
                entity.getQuantityBorrowed(),
                entity.getType(),
                entity.getThematic()
        );
    }
}
