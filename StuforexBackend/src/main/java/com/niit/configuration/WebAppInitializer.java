package com.niit.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	public WebAppInitializer() {
		
		System.out.println("Default Constructor of WebAppInitializer.java");
		System.out.println();
		
	}
	
	//To load beans for application-context.xml
	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		//return null;
		return new Class[] {DBConfig.class} ;
	}

	
	// To load beans for dispatcher-servlet.xml
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		//return null;
		return new Class[] {WebConfig.class} ;
	}

	
	// To forward request to dispatcher-servlet.xml
	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		//return null;
		return new String[] {"/"} ;
	}

}
