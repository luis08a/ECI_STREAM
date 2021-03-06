package edu.eci.arsw.eci_stream.mvcConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/main").setViewName("main.html");
        registry.addViewController("/register").setViewName("register.html");
        registry.addViewController("/index").setViewName("index.html");
        registry.addViewController("/room").setViewName("room.html");
        registry.addViewController("/login").setViewName("login.html");
    }

}