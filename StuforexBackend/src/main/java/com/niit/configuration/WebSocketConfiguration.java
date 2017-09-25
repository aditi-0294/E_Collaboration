package com.niit.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker	// enable broker based stomp messaging - @MessageMapping
@EnableScheduling	// to schedule beans 
@ComponentScan(basePackages = "com.niit")
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	public WebSocketConfiguration() {
		
		System.out.println("Default constructor of WebSocketConfiguration");
		System.out.println();
		
	}
	
	
	// to register stomp end points :
	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry)
	 * 
	 * STOMP endpoint is to establish a communication channel [ websocket connection - connection with server only once ] 
	 */
	//@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		System.out.println("WebSocketConfiguration.java - registerStompEndpoints()");
		System.out.println();
		
		registry.addEndpoint("/portfolio").withSockJS() ; // using this ("/portfolio") client establishes the connection
	}
	
	
	//@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}

	
	//@Override
	public void configureClientOutboundChannel(ChannelRegistration registration) {
		// TODO Auto-generated method stub
		
	}
	
	
	// configure message broker :
		/*
		 * (non-Javadoc)
		 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer#configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry)
		 * 
		 * message broker gets the message from the server to the clients
		 * queue and topic are end points to the client [ from server to the client ]
		 * 
		 * queue - message sent to client by server
		 * 
		 * controller has to send message to the client using the endpoint /queue , /topic
		 * 
		 * if client has to send message to the application[controller] prefix app will be used 
		 * 
		 * data from client to server /app/chat [/app is a prefix]
		 */
		//@Override
		public void configureMessageBroker(MessageBrokerRegistry configurer) {
			// TODO Auto-generated method stub
			System.out.println("WebSocketConfiguration.java - confiureMessageBroker()");
			System.out.println();
		
			// to send message from spring controller to client :
			configurer.enableSimpleBroker("/queue/" , "/topic/") ;
			
			// to send message from client to spring controller :
			configurer.setApplicationDestinationPrefixes("/app") ;
			
		}




	
	
	
}
