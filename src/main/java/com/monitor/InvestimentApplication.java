package com.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestimentApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestimentApplication.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/v1/list").allowedOrigins("http://127.0.0.1:3000");
//			}
//		};
//	}

}
