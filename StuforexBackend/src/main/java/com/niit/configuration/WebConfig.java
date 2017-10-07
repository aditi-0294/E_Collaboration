package com.niit.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc	// same as <mvc:annotation-driven /> - dtects @RequestMapping
@ComponentScan(basePackages = "com.niit")	// same as <context:component-scan base-package = "com.niit" />

public class WebConfig extends WebMvcConfigurerAdapter {
	
		public WebConfig() {
			
			System.out.println("Default Constructor of WebConfig.java");
			System.out.println();
		}
	
		
		
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
			System.out.println("WebConfig.java - addResourceHandlers()");
			System.out.println();
			
			
			// To write <mvc:resources location = "/WEB-INF/resources/" mapping = "/resources/**" />

			registry.addResourceHandler("/resources/**")  
					.addResourceLocations("/WEB-INF/resources/") ; 
		
	}

		
		// for profile_pic upload - multipart file upload :
		@Bean(name = "multipartResolver")
		public CommonsMultipartResolver getCommonsMultipartResolver() {
			
			System.out.println("WebConfig.java - getCommonsMultipartResolver()");
			System.out.println();
			
			CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver() ;
			
			commonsMultipartResolver.setMaxUploadSize(10485760); // for 10MB ( 10 * 1024 * 1024 = 10485760 bytes)
	//		commonsMultipartResolver.setMaxInMemorySize(1048576); // for 1MB ( 1024 * 1024 = 1048576 bytes)
			
			return commonsMultipartResolver ;
		}
}
