package com.iswAcademy.Voucherz.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


// this Config allows for cross origin requests from react client
@Configuration
public class WebMMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH","DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
