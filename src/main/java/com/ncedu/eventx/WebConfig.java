package com.ncedu.eventx;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler(
                    "/img/**",
                    "/bootstrap/**",
                    "/external/**",
                    "/fonts/**",
                    "/icons/**",
                    "/img/**",
                    "/js/**",
                    "/version-selector/**",
                    "/css/**",
                    "/scss/**")
                    .addResourceLocations(
                            "classpath:/static/img/",
                            "classpath:/static/bootstrap/",
                            "classpath:/static/external/",
                            "classpath:/static/fonts/",
                            "classpath:/static/icons/",
                            "classpath:/static/img/",
                            "classpath:/static/js/",
                            "classpath:/static/version-selector/",
                            "classpath:/static/css/",
                            "classpath:/static/scss/");
        }


}
