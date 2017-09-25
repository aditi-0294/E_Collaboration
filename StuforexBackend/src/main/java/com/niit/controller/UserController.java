package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserDao;
import com.niit.model.User;

import com.niit.model.Error; // imported explicitly 

@Controller
//@RestController
public class UserController {

	@Autowired 
	private UserDao userDao ;
	
	public UserController() {
		
		System.out.println("Default constructor of UserController.java .... ");
		System.out.println();
		
	}
	
	
	// for user registration :
	@RequestMapping(value = "/registeruser" , method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		// ? - any type of object , ResponseEntity - represents entire Http response
		
		System.out.println("UserController.java - registerUser()");
		System.out.println();
		
		try {
			
			// check for duplicate username :
			User duplicateUser = userDao.validateUsername(user.getUsername()) ;
			
			if(duplicateUser != null) {
				
				// response.data is error in frontend
				// response.status is 406 NOT_ACCEPTABLE
				Error error = new Error(2,"username already exists .... please enter different usernmae ...  ") ;
				return new ResponseEntity<Error>(error , HttpStatus.NOT_ACCEPTABLE) ;
				
			}
			
			// check for duplicate email_id :
			User duplicateEmail = userDao.validateEmail(user.getEmail()) ;
			
			if(duplicateEmail != null) {
				
				// response.data is error in frontend
				// response.status is 406 NOT_ACCEPTABLE
				Error error = new Error(3,"email_id already exists .... please enter different email_id ... ") ;
				return new ResponseEntity<Error>(error , HttpStatus.NOT_ACCEPTABLE) ;
				
			}
			
			userDao.registerUser(user);
			
			// response.data is user
			// response.status is 200 OK
			return new ResponseEntity<User>(user , HttpStatus.OK) ;
			
		} catch(Exception e) {
			
			// response.data is error in frontend
			// response.status is 500 INTERNAL_SERVER_ERROR
			Error error = new Error(1 , "Unable to register user details .... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		//userDao.registerUser(user);
		
	}
	
	
	// for login :
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	//Http RequestBody : {"username" : "test1" , "password" : "test1" , .... , "online" : "false"}
	public ResponseEntity<?> login(@RequestBody User user , HttpSession session){
		// HttpSession added for frontend part
		
		System.out.println("UserController.java - login()");
		System.out.println();
		
		// check for invalid credentials :
		User validuser = userDao.login(user) ;
		
		if(validuser == null) {
			
			Error error = new Error(4 , "Invalid username and password ..... Please enter valid username and password .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
		
		}
		
		validuser.setOnline(true) ;
		userDao.update(validuser); // update only the online status from 0 to 1
		session.setAttribute("username", validuser.getUsername()) ;
		
		//Http ResponseBody : {"username" : "test1" , "password" : "test1" , .... , "online" : "true"} 
		return new ResponseEntity<User>(validuser , HttpStatus.OK) ;
		
	}
	
	
	// for logout :
	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public ResponseEntity<?> logout(HttpSession session) {
		
		System.out.println("UserController.java - logout()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorized User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
		System.out.println("UserController.java - logout username : " + username);
		
		User user = userDao.getUserByUsername(username) ;
		user.setOnline(false) ;
		userDao.update(user) ;
		session.removeAttribute("username");
		session.invalidate() ;
		
		System.out.println("UserController.java - after session invalidate : " + username) ;
		
		return new ResponseEntity<Void>(HttpStatus.OK) ;
	}
	
	
	// for getting user details - editing user profile :
	@RequestMapping(value = "/getuser" , method = RequestMethod.GET)
	public ResponseEntity<?> getUser(HttpSession session) {
		
		System.out.println("UserController.java - getUser()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorized User .... ") ;
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
		User user = userDao.getUserByUsername(username) ;
		
		return new ResponseEntity<User>(user,HttpStatus.OK) ;
	}
	
	/*
	 * Error - 401 - UnAuthorized Access
	 * Error - 500 - Exception
	 * Void - 200 - Successful updation
	 */
	
	// for updating user details - editing user profile
	@RequestMapping(value = "/updateuser" , method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody User user , HttpSession session ) {
		
		System.out.println("UserController.java - updateUser()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
					
			Error error = new Error(5 , "UnAuthorized User .... ") ;
			return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED) ;
					
		}
		
		try {
			
			userDao.update(user);
			return new ResponseEntity<Void>(HttpStatus.OK) ; 
			
		} catch(Exception e) {
			
			Error error = new Error(6 , "Unable to edit user profile .... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
						
	}
}


