package com.example.demo.config;

// CorsConfig.java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // อนุญาตทุกเส้นทาง
                        .allowedOrigins("http://localhost:5173") // อนุญาต Origin ที่ต้องการ
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // อนุญาต Methods ที่ต้องการ
                        .allowedHeaders("*") // อนุญาต Headers ทุกประเภท
                        .allowCredentials(true); // อนุญาต Credentials
            }
        };
    }
}
