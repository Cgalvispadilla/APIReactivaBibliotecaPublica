package com.sofkau.apibibliotecareactiva.routers.resource;

import com.sofkau.apibibliotecareactiva.usecases.ReturnResourceUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ReturnResourceRouter {
    @Bean
    public RouterFunction<ServerResponse> returnResource(ReturnResourceUseCase returnResourceUseCase) {
        return route(
                PUT("/recursos/devolver/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(returnResourceUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }
}
