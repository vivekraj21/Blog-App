package com.masai.springngblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.AppCacheManifestTransformer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @SuppressWarnings("deprecation")
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
            .resourceChain(false)
            .addTransformer(new AppCacheManifestTransformer());
    }
}

