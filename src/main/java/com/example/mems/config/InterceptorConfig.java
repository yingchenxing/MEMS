package com.example.mems.config;

import com.example.mems.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }

    //Open interceptor of token
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor())
//                .addPathPatterns("/**")//拦截所有请求
//                .excludePathPatterns("/user/login","/user/register","/file/**","/","/swagger-ui.html","/webjars/**","/v2/**","/swagger-resources/**");
//    }
}