package com.my.travel.controller;
/*package com.creditone.creditone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.creditone.creditone")
public class WebConfig  implements WebMvcConfigurer{
  @Autowired
  @Qualifier("jstlViewResolver")
  private ViewResolver jstlViewResolver;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/someurl/resources/**").addResourceLocations("/resources/");

  }

  @Bean
  @DependsOn({ "jstlViewResolver" })
  public ViewResolver viewResolver() {
    return jstlViewResolver;
  }

  @Bean(name = "jstlViewResolver")
  public ViewResolver jstlViewResolver() {
    UrlBasedViewResolver resolver = new UrlBasedViewResolver();
    resolver.setPrefix("/*"); // NOTE: no preffix here
    resolver.setViewClass(JstlView.class);
    resolver.setSuffix(""); // NOTE: no suffix here
    return resolver;
  }

// NOTE: you can use InternalResourceViewResolver it does not matter 
//  @Bean(name = "internalResolver")
//  public ViewResolver internalViewResolver() {
//    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//    resolver.setPrefix("");
//    resolver.setSuffix("");
//    return resolver;
//  }
}*/