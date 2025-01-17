package pe.gob.munisullana.sistrado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/webapp/api/**")
                        .or(PathSelectors.ant("/common/api/**"))
                )
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Sistrado REST API",
                "Documentación de servicios rest",
                "v1",
                "terms",
                new Contact("Hugo Ángeles", "https://github.com/hugoangeles0810", "hangelesch@alumnos.unp.edu.pe"),
                "MIT", "https://opensource.org/licenses/MIT", Collections.emptyList());
    }
}
