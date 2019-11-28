package com.test.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author
 * @create 2019-11-19 14:14
 */
@Configuration
@EnableWebMvc
@EnableSwagger
@ComponentScan(basePackages = {"com.test.sys.*"})
public class SwaggerConfig {

    @Autowired
    private SpringSwaggerConfig springSwaggerConfig;


    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*?");
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Beamtech接口文档",
                "功能接口一览",
                "SwaggerConfig-Url",
                "@Gmail.com",
                "SwaggerConfig-license",
                "SwaggerConfig-licenseurl");
        return apiInfo;
    }
}
