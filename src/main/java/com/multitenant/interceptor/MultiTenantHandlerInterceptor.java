package com.multitenant.interceptor;

import com.multitenant.TenantHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;


@Component
public class MultiTenantHandlerInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TenantHolder tenantHolder;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        setTenantId(request, tenantHolder);
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        tenantHolder.clear();
    }

    private void setTenantId(HttpServletRequest request, TenantHolder tenantHolder) {
        // Header that would be attached to every request made to identify tenant DB.
        String tenantId = request.getHeader("x-tenant-id");
        //OR can look into request parameter that can help identify tenant DB
        if(request.getParameterMap() != null){
            String[] clientCodes = request.getParameterMap().get("planetName");
            if(clientCodes != null && clientCodes.length > 0){
                tenantId = clientCodes[0];
            }
        }
        // or can look into PATH Variables/attribute.
        Object o = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if (o instanceof Map) {
            var map = new TreeMap<>((Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            tenantId =  map.get("planetName");
        }
        tenantHolder.setTenantId(tenantId);
    }
}

