package com.sofkau.apibibliotecareactiva.routers.resource;

import com.sofkau.apibibliotecareactiva.usecases.CheckAvailabilityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
@Configuration
public class CheckAvailabilityResourceRouter {
    @Bean
    public RouterFunction<ServerResponse> availability(CheckAvailabilityUseCase checkAvailabilityUseCase) {
        return route(
                GET("/recursos/disponibilidad/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(checkAvailabilityUseCase.apply(request.pathVariable("id")), String.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())

        );
    }
}
