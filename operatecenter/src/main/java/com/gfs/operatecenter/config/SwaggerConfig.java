package com.gfs.operatecenter.config;

import com.dw.sc.common.annotation.EnableBeanValidator;
import com.dw.sc.common.constant.TokenConstant;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableKnife4j
@EnableSwagger2
@EnableBeanValidator
public class SwaggerConfig {

    @Autowired
    private ServletContext servletContext;

    @Bean(value = "operatecenterApi")
    @Order(value = 1)
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/operatecenter";
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gfs.operatecenter.controller"))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(getHeader());
                .securityContexts(newArrayList(securityContext()))
                .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("运营中心接口")
                .description("运营中心接口swagger")
                .termsOfServiceUrl("http://localhost:8915/")
                .contact(new Contact("liaodewen", "" , "675619672@qq.com"))
                .version("1.0") .build();
    }

    private List<Parameter> getHeader(){
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = newArrayList();
        parameterBuilder.name(TokenConstant.TOKEN_NAME)
                .description("By Access Token")
                .modelRef(new ModelRef("String"))
                .parameterType("header")
                .required(false)
                .build();
        parameters.add(parameterBuilder.build());
        return parameters;
    }

    private ApiKey apiKey() {
        return new ApiKey(TokenConstant.TOKEN_NAME, TokenConstant.TOKEN_NAME, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(new SecurityReference("BearerToken", authorizationScopes));
    }

}
