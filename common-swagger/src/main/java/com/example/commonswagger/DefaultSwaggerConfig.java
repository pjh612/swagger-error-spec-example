package com.example.commonswagger;

import com.example.commonapi.exception.converter.ExceptionCodeConverter;
import com.example.commonapi.exception.strategy.StatusDetermineStrategyFactory;
import com.example.commonapi.exception.strategy.impl.SpringStatusDetermineStrategyFactory;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.utils.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.util.ObjectUtils;


public class DefaultSwaggerConfig {
    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${spring.profiles.active}")
    private String activeProfile;

    private final ApplicationContext applicationContext;

    public DefaultSwaggerConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public OpenAPI openAPI(Info info) {
        if (ObjectUtils.isEmpty(info.getTitle())) {
            info.setTitle(this.applicationName);
        }
        if (ObjectUtils.isEmpty(info.getDescription())) {
            info.setDescription("Environment - " + this.activeProfile);
        }

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }

    @Bean
    @ConfigurationProperties(value = "springdoc.info")
    public Info info() {
        return new Info();
    }

    @Bean
    @ConfigurationProperties(prefix = "springdoc.swagger-ui")
    @ConditionalOnProperty(name = Constants.SPRINGDOC_SWAGGER_UI_ENABLED, matchIfMissing = true)
    @Primary
    public SwaggerUiConfigProperties swaggerUiConfigProperties() {
        SwaggerUiConfigProperties properties = new SwaggerUiConfigProperties();
        properties.setDocExpansion("none");
        properties.setFilter("true");
        properties.setDefaultModelExpandDepth(2);
        properties.setTagsSorter("alpha");
        properties.setTryItOutEnabled(true);
        properties.setDisplayRequestDuration(true);
        properties.setDisableSwaggerDefaultUrl(false);
        properties.setDeepLinking(true);

        return properties;
    }

    @Bean
    @ConditionalOnMissingBean(StatusDetermineStrategyFactory.class)
    StatusDetermineStrategyFactory statusDetermineStrategyFactory() {
        return new SpringStatusDetermineStrategyFactory(applicationContext);
    }

    @Bean
    public OperationCustomizer customize(StatusDetermineStrategyFactory statusDetermineStrategyFactory, ExceptionCodeConverter exceptionCodeConverter) {
        return new ErrorResponseOperationCustomizer(statusDetermineStrategyFactory, exceptionCodeConverter);
    }
}
