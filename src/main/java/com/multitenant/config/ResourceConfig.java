package com.multitenant.config;

import com.multitenant.interceptor.MultiTenantHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Autowired
    private MultiTenantHandlerInterceptor webServiceTenantInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(webServiceTenantInterceptor);
    }
}
