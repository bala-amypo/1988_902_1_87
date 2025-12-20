@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI carbonFootprintOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Carbon Footprint Estimator API")
                        .description("API for tracking and estimating carbon emissions")
                        .version("1.0"));
        // no security items or schemes needed for now
    }
}
