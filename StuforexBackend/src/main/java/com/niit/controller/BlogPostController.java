package com.niit.controller;

import java.util.Date;
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

import com.niit.dao.BlogPostDao;
import com.niit.dao.UserDao;
import com.niit.model.BlogComment;
import com.niit.model.BlogPost;
import com.niit.model.User;
import com.niit.model.Error;

@Controller
public class BlogPostController {

	@Autowired
	private BlogPostDao blogPostDao ;
	
	@Autowired
	private UserDao userDao ;
	
	public BlogPostController() {
		
		System.out.println("Default Constructor of BlogPostController.java .... ") ;
		System.out.println();
		
	}
	
	
	// to insert and save blogpost - user and admin :
	@RequestMapping(value = "/saveBlogPost" , method = RequestMethod.POST)
	public ResponseEntity<?> saveBlogPost(@RequestBody BlogPost blogPost , HttpSession session) {
		
		System.out.println("BlogPostController.java - saveBlogPost()");
		System.out.println();
		
	//	String username = "test1" ; // to check via postman
	
		String username = (String)session.getAttribute("username") ;
		
		// check for null / unAuthorized user - if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5, "UnAuthorised user .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
				
		User user = userDao.getUserByUsername(username) ;
		
		// check for UnAuthorized user / null user via postman check - if user is loggedIn :
		/*
		if(user == null) {
			
			Error error = new Error(5 , "UnAuthorised User ..... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ; // 401 - 2nd callback function will be called
			
		}
		*/
		
		blogPost.setPostedOn(new Date());
		
		blogPost.setPostedBy(user);
		
		try {
			
			blogPostDao.saveBlogPost(blogPost); // checks if required details are mentioned - title and description
			
			return new ResponseEntity<BlogPost>(blogPost , HttpStatus.OK) ; // 200 - 1st callback function will be called
		
		} catch(Exception e) {
			
			Error error = new Error(7 , "Unable to insert blog details .... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ; // 500 - 2nd callback function will be called
		
		}
	}
	
	
	// to check list of blog_posts requiring admin approval :
	@RequestMapping(value = "/getBlogPosts/{approved}" , method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPosts(@PathVariable int approved , HttpSession session) {
		
		System.out.println("BlogPostController.java - getBlogPosts()");
		System.out.println();
		
		// check for null / UnAuthorised user - user must be loggedIn :
		
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised user ..... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ; // 401 - 2nd callback function will be called
			
		}
		
		List<BlogPost> blogPosts = blogPostDao.getBlogPosts(approved) ;
		
		return new ResponseEntity<List<BlogPost>>(blogPosts , HttpStatus.OK) ;
		
	}
	
	
	// to get blog_post depending on id :
	@RequestMapping(value = "/getBlogPostById/{id}" , method = RequestMethod.GET)
	public ResponseEntity<?> getBlogPostById(@PathVariable int id , HttpSession session) {
		
		System.out.println("BlogPostController.java - getBlogPostById()");
		System.out.println();
		
//		String username = "test1" ; // to check via postman
		
		// check if user is loggedIn :
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User ..... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ; // 401 - 2nd callback function will be called
		
		}
		
		/* for postman check
		User user = userDao.getUserByUsername(username) ;
		
		// check if user is loggedIn :
		if(user == null) {
			
			Error error = new Error(5 , "UnAuthorised User ..... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ; // 401 - 2nd callback function will be called
		}
		*/
		
		BlogPost blogPost = blogPostDao.getBlogPostById(id) ;
		
		return new ResponseEntity<BlogPost>(blogPost , HttpStatus.OK) ;
		
	}
	
	
	// to approve blog_post by admin :
	@RequestMapping(value = "/approveBlogPost" , method = RequestMethod.PUT)
	public ResponseEntity<?> approveBlogPost(@RequestBody BlogPost blogPost , HttpSession session) {
		
		System.out.println("BlogPostController.java - updateBlogPost()");
		System.out.println();
		
		System.out.println("Current username : " + session.getAttribute("username"));

		//	String username = "admin" ; // to check via postman
		
		// check if the user is loggedIn :
		/*
		User user = userDao.getUserByUsername(username) ;
		if(user == null) {
			
			Error error = new Error(5 , "UnAuthorised User ..... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		*/
		
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .....") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		try {
		
			blogPostDao.approveBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost , HttpStatus.OK) ;
		
		} catch (Exception e) {
			
			Error error = new Error(7 , "Unable to update blog post ..... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
	}
	
	
	// to insert and save blog_post comments - user and admin :
	@RequestMapping(value = "/addBlogComment" , method = RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment , HttpSession session) {
		
		System.out.println("BlogPostController.java - addBlogComment()");
		System.out.println();
		
	//	String username = "test1" ; // to check via postman
		System.out.println("Current User : " + session.getAttribute("username")) ;
		
		// check if user is loggedIn :
		/*
		User user = userDao.getUserByUsername(username) ;
		if(user == null) {
			
			Error error = new Error(5 , "UnAuthorised User ....  ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		*/
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User ....  ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ; // 401 - 2nd callback function will be executed
			
		}
	
		String username = (String)session.getAttribute("username") ;
		
		User user = userDao.getUserByUsername(username) ;
		
		blogComment.setCommentedBy(user); // sets the value for foreign key "username" in blogcomment table
		blogComment.setCommentedOn(new Date()); // sets the value for commentedOn
		
		try {
			
			blogPostDao.addBlogComment(blogComment);
			return new ResponseEntity<BlogComment>(blogComment , HttpStatus.OK) ;
			
		} catch(Exception e) {
			
			Error error = new Error(7 , "Unable to insert blog comment .... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
		
		
	}
	
	
	// to retrive all blog_post comments depending on blogpost_id :
	@RequestMapping(value = "/getBlogComments/{blogPostId}" , method = RequestMethod.GET) 
	public ResponseEntity<?> getAllBlogComments(@PathVariable int blogPostId , HttpSession session) {
		
		System.out.println("BlogPostController.java - getAllBlogComments()");
		System.out.println();
		
		System.out.println("Current User : " + session.getAttribute("username"));
	//	String username = "test1" ; // to check via postman
		
	//	User user = userDao.getUserByUsername(username) ;
		
		// check if user is loggedIn :
		/*
		if(user == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		*/
		
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ; // 401 - 2nd callback function will be executed
			
		}
		
		try {
		
			List<BlogComment> blogComments = blogPostDao.getAllBlogComments(blogPostId) ;
			return new ResponseEntity<List<BlogComment>>(blogComments , HttpStatus.OK) ;
		
		} catch(Exception e) {
			
			Error error = new Error(7 , "Unable to load blog_post comments .... : " + e.getMessage()) ;
			return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			
		}
	}
	
}	