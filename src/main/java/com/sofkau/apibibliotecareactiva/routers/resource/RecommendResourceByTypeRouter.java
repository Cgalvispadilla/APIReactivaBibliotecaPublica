package com.sofkau.apibibliotecareactiva.routers.resource;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.usecases.RecommendByTypeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecommendResourceByTypeRouter {
    @Bean
    public RouterFunction<ServerResponse> recommendByType(RecommendByTypeUseCase recommendByTypeUseCase) {
        return route(
                GET("/recursos/recomendarxtipo/{tipo}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(recommendByTypeUseCase.get(request.pathVariable("tipo")), ResourceDTO.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }
}
