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

import com.niit.dao.JobDao;
import com.niit.dao.UserDao;
import com.niit.model.Job;
import com.niit.model.User;
import com.niit.model.Error;

@Controller
public class JobController {
	
	@Autowired
	private JobDao jobDao ;
	
	@Autowired
	private UserDao userDao ;
	
	public JobController() {
		
		System.out.println("Default Constructor of JobController.java .... ");
		System.out.println();
		
	}
	
	
	// for inserting and saving job details - admin :
	@RequestMapping(value = "/savejob" , method = RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job , HttpSession session) {
		
		System.out.println("JobController.java - saveJob() .... ");
		System.out.println();
		
		// check if user is loggedIn :
	//	String username = "admin" ; // to test via POSTMAN
		
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorized User ..... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
		
		}
		
		String username = (String)session.getAttribute("username") ;
		User user = userDao.getUserByUsername(username) ;
		
		// Only Admin is authorized :
		if(user.getRole().equals("ADMIN")) {
			
			try {
				
				job.setPostedOn(new Date());
				
				jobDao.saveJob(job);
				
				return new ResponseEntity<Job>(job , HttpStatus.OK) ;
								
			} catch(Exception e) {
				
				Error error = new Error(7 , "Unable to insert Job Details .... : " + e.getMessage()) ;
				
				return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
				
			}
			
		}
		
		// if user is other than Admin : 
		else {
			
			Error error = new Error(6 , "Access Denied .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED ) ;
		}
		
		
	}
	
	
	// to get list of all saved job details - user :
	@RequestMapping(value = "/getAllJobs" , method = RequestMethod.GET)
	public ResponseEntity<?> getAllJobs(HttpSession session) {
		
		System.out.println("JobController.java - getAllJobs()");
		System.out.println();
		
	//	String username = "test1" ; // to test via postman
		
		// check for empty username (i.e., if username exists / user is logged in)
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorized User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		List<Job> jobs = jobDao.getAllJobs() ;
		
		return new ResponseEntity<List<Job>>(jobs , HttpStatus.OK) ;
	}
	

	// to get job details depending on job_id - user & admin :
	@RequestMapping(value = "/getJobById/{id}" , method = RequestMethod.GET)
	public ResponseEntity<?> getJobById(@PathVariable int id , HttpSession session) {
		
		System.out.println("JobController.java - getJobById()");
		System.out.println();
		
//		String username = "test1" ; // to check via postman
	
		// check for empty username (i.e., if username exists / user is logged in)
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorized User .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		Job job = jobDao.getJobById(id) ;
		
		return new ResponseEntity<Job>(job , HttpStatus.OK) ;
		
	}
	
	
	// to edit and update job details - admin :
	@RequestMapping(value = "/editJobDetails" , method = RequestMethod.PUT)
	public ResponseEntity<?> editJob(@RequestBody Job job , HttpSession session) {
		
		System.out.println("JobController.java - editJob()");
		System.out.println();
		
		// check if user is loggedIn
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorized user .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
		User user = userDao.getUserByUsername(username) ;
		
		// only admin is authorized to edit details 
		if(user.getRole().equals("ADMIN")) {
			
			try {
				
				jobDao.editJob(job);
				return new ResponseEntity<Void>(HttpStatus.OK) ;
				
			} catch(Exception e) {
				
				Error error = new Error(7 , "Unable to edit and update job details ... " + e.getMessage()) ;
				return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			}
		}
		
		// if user is other than admin
		else {
			
			Error error = new Error(6 , "Access Denied .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
		}
		
	}
	
	
	// to delete job by job_id - admin :
	@RequestMapping(value = "/deleteJobById/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteJobById(@PathVariable int id , HttpSession session) {
		
		System.out.println("JobController.java - deleteJobById()");
		System.out.println();
		
		// check if user is loggedIn 
		if(session.getAttribute("username") == null) {
			
			Error error = new Error(5 , "UnAuthorised user .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
		String username = (String)session.getAttribute("username") ;
		
		User user = userDao.getUserByUsername(username) ;
		
		// only admin is authorised to delete job details
		if(user.getRole().equals("ADMIN")) {
			
			try {
				
				Job job = jobDao.getJobById(id) ;
				jobDao.deleteJobById(job);
				
				return new ResponseEntity<Void>(HttpStatus.OK) ;
			
			} catch(Exception e) {
				
				Error error = new Error(7 , "Unable to delete job details ... " + e.getMessage()) ;
				return new ResponseEntity<Error>(error , HttpStatus.INTERNAL_SERVER_ERROR) ;
			}
		}
		
		// if user is other than admin
		else {
			
			Error error = new Error(6 , "Access denied .... ") ;
			return new ResponseEntity<Error>(error , HttpStatus.UNAUTHORIZED) ;
			
		}
		
	}
}
