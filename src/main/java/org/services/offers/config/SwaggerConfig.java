package org.services.offers.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Value(value = "${swagger.server.routes.local}")
  private String localServerRoute;

  @Value(value = "${swagger.server.routes.global}")
  private String globalServerRoute;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Offers API").version("v1").description("Offers API"))
        .addServersItem(new Server().url(localServerRoute))
        .addServersItem(new Server().url(globalServerRoute));
  }
}
