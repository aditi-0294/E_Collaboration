package com.niit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import com.niit.model.Chat;
import com.niit.dao.UserDao;

@Controller
public class SockController {
	
	private final SimpMessagingTemplate messagingTemplate ;
	
	// username will be added to the list whenever user joins the chat room
	private List<String> users = new ArrayList<String>() ;
	
	// Object of type messaging template will be injected [autowired] by spring container
	@Autowired
	public SockController(SimpMessagingTemplate messagingTemplate) {	// MessagingTemplate is a dependency that is used to convert message into any format.
		super();
		// TODO Auto-generated constructor stub
		System.out.println("Default Constructor of SockController.java .... ");
		System.out.println();
		
		this.messagingTemplate = messagingTemplate ;
		
	}
	
	
	// when user joins the chat room - $scope.stompClient.subscribe("app/join/$scope.username) :
	@SubscribeMapping("/join/{username}") // sends message directly to the client
	public List<String> join(@DestinationVariable("username") String username) {
		
		System.out.println("SockController.java (ChatController) - join() .... ");
		System.out.println();
		
		System.out.println("username in SockController.java : " + username);
		System.out.println();
		
		/*	check if username exists in chat list 	*/
		// if username doesnt' exist - add to the list :
		if(!users.contains(username)) {
			
			users.add(username) ;
						
		}
		
		System.out.println("Newly joined username : " + username);
		System.out.println();
			
		// notify all subscribers of new user - notifies that the user has joined the chatroom :
		messagingTemplate.convertAndSend("/topic/join" , username); 
		
		return users ; // users list - existing as well as newly joined
			
	}
	
	
	// when users' send and receive messages ( chat ) to each other :
	/*
	 * 	@MessageMapping - handles STOMP (Simple Text Oriented Message Protocol) messages within the springMvc
	 * 				and is enabled by @EnableWebSocketMessageBroker
	 */
	@MessageMapping(value = "/chat") // sends message to message broker
	public void chatReceived(Chat chat) {
		
		System.out.println("SockController.java - chatReceived() .... ");
		System.out.println();
		
		// user sends message to all others :
		if("all".equals(chat.getTo())) {
		
			System.out.println("SockController.java - all users - chatReceived : " + chat.getMessage() + "  from : " + chat.getFrom() + " to : " + chat.getTo() );
			System.out.println();
			
			messagingTemplate.convertAndSend("/queue/chats" , chat);
			
		}
		
		// user sends and receives message to and from a particular user respectively :
		else {
			
			System.out.println("SockController.java - particular user (else part ) - chatReceived : " + chat.getMessage() + "  from : " + chat.getFrom() + " to : " + chat.getTo() );
			System.out.println();
			
			
			System.out.println("To is : " + chat.getTo() + "From is : " + chat.getFrom());
			System.out.println();
			
			
			messagingTemplate.convertAndSend("/queue/chats/" + chat.getTo() , chat);
			messagingTemplate.convertAndSend("/queue/chats/" + chat.getFrom() , chat);
		
		}
	}
}
