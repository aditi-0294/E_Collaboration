package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.dao.FriendDao;
import com.niit.dao.UserDao;
import com.niit.model.User;
import com.niit.model.Error;
import com.niit.model.Friend;

@Controller
public class FriendController {
	
	@Autowired
	private FriendDao friendDao ;
	
	@Autowired
	private UserDao userDao ;
	
	
	public FriendController() {
		
		System.out.println("Default Constructor of FriendController.java .... ") ;
		System.out.println();
		
	}
	
	
	// to get a list of suggested users :
	@RequestMapping(value = "/suggestedUsersList" , method = RequestMethod.GET)
	public ResponseEntity<?> getListOfSuggestedUsers(HttpSession session) {
		
		System.out.println("FriendController.java - getListOfSuggestedUsers()");
		System.out.println();
		
	 // check if user is LoggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
	//	List<User> suggestedUsers = friendDao.getListOfSuggestedUsers("test1") ;
		
		List<User> suggestedUsers = friendDao.getListOfSuggestedUsers(username) ;
		return new ResponseEntity<List<User>>(suggestedUsers , HttpStatus.OK) ;
	
	}
	
	
	// to send friend request to other users :
	@RequestMapping(value = "/sendFriendRequest/{toId}" , method = RequestMethod.POST)
	public ResponseEntity<?> sendFriendRequest(@PathVariable String toId , HttpSession session) {
		
		System.out.println("FriendController.java - sendFriendRequest()");
		System.out.println();
		
		System.out.println("CurrentUser : " + session.getAttribute("username"));
		
		// check if the user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User ..... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ; // 401 - 2nd callback function will be called
			
		}
		
		String username = (String)session.getAttribute("username") ; // username is same as fromId
		
		try {
			
	//		friendDao.sendFriendRequest("test1", toId); // to check via postman
			
			friendDao.sendFriendRequest(username, toId);
			return new ResponseEntity<Void>(HttpStatus.OK) ;
			
		}catch(Exception e) {
			
			Error error = new Error(7 , "Unable to send friend request ..... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
	}

	
	// to get list of pending requests :
	@RequestMapping(value = "/getPendingRequests" , method = RequestMethod.GET)
	public ResponseEntity<?> getPendingRequests(HttpSession session) {
		
		System.out.println("FriendController.java - getPendingRequests()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
		try {
			
			List<Friend> pendingRequests = friendDao.getPendingRequests(username) ;
			return new ResponseEntity<List<Friend>>(pendingRequests , HttpStatus.OK) ;
			
		} catch(Exception e) {
			
			Error error = new Error(7 , "Unable to get pending requests list ..... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
	}
	
	
	// to view user details before accepting request
	@RequestMapping(value = "/viewUserDetails/{fromId}" , method = RequestMethod.GET)
	public ResponseEntity<?> viewUserDetails(@PathVariable String fromId , HttpSession session) {
		
		System.out.println("FriendController.java - viewUserDetails()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
		}
		
		try {
		
			User user = userDao.getUserByUsername(fromId) ;
			return new ResponseEntity<User>(user , HttpStatus.OK) ;
		
		} catch(Exception e) {
			
			Error error = new Error(7 , "Unable to get / view user details .... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
		
	}
	
	
	// to accept or deny the request : update pending request from 'P' to 'A' or 'D'
	@RequestMapping(value = "/updatePendingRequest" , method = RequestMethod.PUT)
	public ResponseEntity<?> updatePendingRequest(@RequestBody Friend pendingRequest , HttpSession session) {
		
		/*
		 *  Friend pendingRequest - pendingRequest is a friend object with status updated to 'A' / 'D' 
		 */
		
		System.out.println("FriendController.java - updatePendingRequest()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		try {
			
			friendDao.updatePendingRequest(pendingRequest);
			return new ResponseEntity<Friend>(pendingRequest , HttpStatus.OK) ;
			
		} catch(Exception e) {
			
			Error error = new Error(7 , "Unable to accept or deny the request .... " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
	}
	
	
	// to get list of friends :
	@RequestMapping(value = "/friendList" , method = RequestMethod.GET)
	public ResponseEntity<?> listOfFriends(HttpSession session) {
		
		System.out.println("FriendController.java - listOfFriends()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
		try {
			
			List<Friend> friendList = friendDao.listOfFriends(username) ;
			return new ResponseEntity<List<Friend>>(friendList , HttpStatus.OK) ;
			
		} catch(Exception e) {
			
			Error error = new Error(7 , "Unable to fetch list of friends .... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
		
	}
}
