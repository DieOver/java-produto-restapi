package com.example.apirest.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_12)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.apirest"))
                .paths(regex("/api.*"))
                .build()
                .pathMapping("/")
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        return new ApiInfo("Produtos API Rest", "API Rest", "1.0", "Terms of Service",
                new Contact("Lennon", "lennonmilicic@gmail.com", "lennonmilicic@gmail.com"),
                "Apache License Version 2.0", "https://www.apache.org/license.html", new ArrayList<VendorExtension>());
    }
}
