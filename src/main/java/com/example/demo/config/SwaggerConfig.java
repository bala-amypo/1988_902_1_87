package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carbonFootprintOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Carbon Footprint Estimator API")
                        .description("API for tracking and estimating carbon emissions")
                        .version("1.0"));
    }
}
