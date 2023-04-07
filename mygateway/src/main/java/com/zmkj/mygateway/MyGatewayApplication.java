package com.zmkj.mygateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
@EnableDiscoveryClient
public class MyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyGatewayApplication.class, args);
    }
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("path_route", r -> r.predicate(serverWebExchange -> serverWebExchange.getRequest().getPath().subPath(0).toString().startsWith("/customer")).filters(gatewayFilterSpec -> gatewayFilterSpec.filter((exchange, chain) -> {
                    System.out.println(exchange.getRequest().getPath());
                    return chain.filter(exchange);
                })).uri("http://localhost:8074"))
                .route("path_route", r -> r.predicate(serverWebExchange -> serverWebExchange.getRequest().getPath().subPath(0).toString().startsWith("/address")).filters(gatewayFilterSpec -> gatewayFilterSpec.filter((exchange, chain) -> {
                    System.out.println(exchange.getRequest().getPath());
                    return chain.filter(exchange);
                })).uri("http://localhost:8072"))
                .build();
    }
}
