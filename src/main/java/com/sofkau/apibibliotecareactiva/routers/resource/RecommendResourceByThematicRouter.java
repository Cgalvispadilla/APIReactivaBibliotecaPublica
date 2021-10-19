package com.sofkau.apibibliotecareactiva.routers.resource;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.usecases.RecommendByThematicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecommendResourceByThematicRouter {
    @Bean
    public RouterFunction<ServerResponse> recommendByThematic(RecommendByThematicUseCase recommendByThematicUseCase) {
        return route(
                GET("/recursos/recomendarxtema/{tema}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recommendByThematicUseCase.get(request.pathVariable("tema")), ResourceDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
}
