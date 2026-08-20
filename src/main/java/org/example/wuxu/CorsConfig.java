package org.example.wuxu;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")                     // 允许所有接口
                .allowedOriginPatterns("*")           // 允许所有来源（关键：用 pattern 支持 *）
                .allowedMethods("*")                  // 允许所有请求方法
                .allowedHeaders("*")                  // 允许所有请求头
                .allowCredentials(true)               // 允许携带凭证（cookie）
                .maxAge(3600);                        // 预检请求缓存时间（秒）
    }
}