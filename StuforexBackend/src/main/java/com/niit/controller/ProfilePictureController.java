package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.niit.dao.ProfilePictureDao;

import com.niit.model.ProfilePicture;
import com.niit.model.User;
import com.niit.model.Error ;

@Controller
public class ProfilePictureController {
	
	@Autowired
	private ProfilePictureDao profilePictureDao ;
	
	public ProfilePictureController() {
		
		System.out.println("Default Constructor of ProfilePictureController.java .... ") ;
		System.out.println();
		
	}
	
	
	// to insert and save profile picture :
	@RequestMapping(value = "/uploadProfilePicture" , method = RequestMethod.POST)
	public ResponseEntity<?> uploadProfilePicture(@RequestParam CommonsMultipartFile image , HttpSession session) {
		
		System.out.println("ProfilePictureController.java - uploadProfilePicture()");
		System.out.println();
		
		System.out.println("CurrentUser : " + session.getAttribute("username"));
		
		// check if user is LoggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
		ProfilePicture profilePicture = new ProfilePicture() ;
		
		profilePicture.setUsername(username);
		
		profilePicture.setImage(image.getBytes());
		
		try {
			
			profilePictureDao.saveProfilePicture(profilePicture);
			return new ResponseEntity<ProfilePicture>(profilePicture , HttpStatus.OK) ;
			
		} catch(Exception e) {
			
			Error error = new Error(7 , "unable to upload image ... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
		
	}
	
	
	// to display profile picture :
	@RequestMapping(value = "/getImage/{username}" , method = RequestMethod.GET)
	public @ResponseBody byte[] getImage(@PathVariable String username , HttpSession session) {
		
		System.out.println("ProfilePictureController.java - getImage()");
		System.out.println();
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			return null ;
			
		}
		
		else {
			
			ProfilePicture profilePicture = profilePictureDao.getProfilePicture(username) ;
			
			if(profilePicture == null) {
				
				return null ;
				
			}
		
			else {
				
				return profilePicture.getImage() ;
			}
			
			//return profilePicture.getImage() ;
		}
		

		/*
		User user = (User)session.getAttribute("user") ;
		
		if(user == null) {
			return null ;
		}
		
		else {
			ProfilePicture profilePicture = profilePictureDao.getProfilePicture(username);
			
			if(profilePicture == null)
				return null ;
			else
				return profilePicture.getImage() ;
		}
		*/
	}

}
