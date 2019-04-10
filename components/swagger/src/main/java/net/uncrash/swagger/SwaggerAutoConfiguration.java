package net.uncrash.swagger;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;


/**
 * Swagger 2 configurations
 *
 * @author Acris
 * @date 2019/04/03
 */
@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerAutoConfiguration {
    private final SwaggerProperties properties;

    @Bean
    public Docket petApi() {
        if (!properties.isEnabled()) {
            return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.none())
                .paths(PathSelectors.none())
                .build();
        }

        return new Docket(DocumentationType.SWAGGER_2)
            .host(properties.getHost())
            .select()
            .apis(StringUtils.isEmpty(properties.getBasePackage()) ?
                RequestHandlerSelectors.any() :
                RequestHandlerSelectors.basePackage(properties.getBasePackage()))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(properties.getTitle(), properties.getDescription(),
            properties.getVersion(), properties.getTosUrl(),
            new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactEmail()),
            properties.getLicense(), properties.getLicenseUrl(), Collections.emptyList());
    }
}
