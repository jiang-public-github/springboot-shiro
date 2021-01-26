package com.shiro.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author jianglanglang
 * @Date 2020/11/16 15:09
 * @Description 配置中心
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    /**
     * 全局跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}

