package com.niit.dao;

import com.niit.model.User;

public interface UserDao {

	// user registration :
	void registerUser(User user) ;
	
	// check for duplicate username :
	User validateUsername(String username) ;
	
	// check for duplicate email_id :
	User validateEmail(String email) ;
	
	// for login :
	User login(User user) ;
	
	// for user update :
	void update(User user) ;
	
	// to get username so as to logout
	User getUserByUsername(String username) ;
 
}
