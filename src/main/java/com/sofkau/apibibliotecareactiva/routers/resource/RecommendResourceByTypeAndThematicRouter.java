package com.sofkau.apibibliotecareactiva.routers.resource;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.usecases.RecommendByTypeAndThematicUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class RecommendResourceByTypeAndThematicRouter {
    @Bean
    public RouterFunction<ServerResponse> recommendByTypeAndThematic(RecommendByTypeAndThematicUseCase recommendByTypeAndThematicUseCase) {
        return route(
                GET("/recursos/recomendarxtipoxtema/{tipo}/{tema}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recommendByTypeAndThematicUseCase.get(request.pathVariable("tipo"), request.pathVariable("tema")), ResourceDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
}
