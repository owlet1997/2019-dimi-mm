package com.ncedu.eventx.configs;

import org.springframework.context.annotation.Configuration;
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
                            "classpath:/static/js/",
                            "classpath:/static/version-selector/",
                            "classpath:/static/css/",
                            "classpath:/static/scss/");
        }


}
