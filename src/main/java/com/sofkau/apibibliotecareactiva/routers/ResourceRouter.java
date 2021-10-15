package com.sofkau.apibibliotecareactiva.routers;

import com.sofkau.apibibliotecareactiva.models.ResourceDTO;
import com.sofkau.apibibliotecareactiva.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ResourceRouter {
    @Bean
    public RouterFunction<ServerResponse> getAll(ListResourceUseCase listResourceUseCase) {
        return route(GET("/recursos"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listResourceUseCase.get(), ResourceDTO.class)
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> save(SaveResourceUseCase saveResourceUseCase) {
        Function<ResourceDTO, Mono<ServerResponse>> executor = resourceDTO -> saveResourceUseCase.apply(resourceDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/recursos/agregar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ResourceDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> update(UpdateResourceUseCase updateResourceUseCase) {
        Function<ResourceDTO, Mono<ServerResponse>> executor = resourceDTO -> updateResourceUseCase.apply(resourceDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/recursos/editar")
                        .and(accept(MediaType.APPLICATION_JSON)), request -> request
                        .bodyToMono(ResourceDTO.class)
                        .flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> delete(DeleteResourceUseCase deleteResourceUseCase) {
        return route(
                DELETE("/recursos/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(deleteResourceUseCase.apply(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> availability(ChechAvailabilityUseCase chechAvailabilityUseCase) {
        return route(
                GET("/recursos/disponibilidad/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(chechAvailabilityUseCase.apply(request.pathVariable("id")), String.class)
                        ).onErrorResume((Error) -> ServerResponse.badRequest().build())

        );
    }

    @Bean
    public RouterFunction<ServerResponse> lend(LendUseCase lendUseCase) {
        return route(
                PUT("/recursos/prestar/{id}"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(lendUseCase.apply(request.pathVariable("id")), String.class))
                        .onErrorResume((error) -> ServerResponse.badRequest().build())
        );
    }

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
