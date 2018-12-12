/*package com.my.travel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("WebMvcConfigurer - addResourceHandlers() function get loaded..."+  );
        registry.addResourceHandler("/resources/static/**")
                .addResourceLocations("/resources/");
   

        registry
            .addResourceHandler("/js/**")
            .addResourceLocations("/js/")
            .setCachePeriod(3600)
            .resourceChain(true);
           // .addResolver(new GzipResourceResolver())
           // .addResolver(new PathResourceResolver());
    }
    
    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}*/