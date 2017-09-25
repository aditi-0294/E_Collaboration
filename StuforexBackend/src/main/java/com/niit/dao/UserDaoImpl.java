package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	
	@Autowired
	private SessionFactory sessionFactory ;

	public UserDaoImpl() {
		
		System.out.println("Default Constructor of UserDaoImpl.java .... ");
		System.out.println();
		
	}
	
	
	// for new user registration :
	//@Override
	public void registerUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("UserDaoImpl.java - registerUser() .... ");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.save(user) ;
		
		/*
		Session session = sessionFactory.openSession() ;
		session.save(user) ;
		session.flush();
		session.close() ;
		*/
	}

	
	// check for duplicate username :
	//@Override
	public User validateUsername(String username) {
		// TODO Auto-generated method stub
		//return null;
		//Session session = sessionFactory.getCurrentSession() ;
		
		System.out.println("UserDaoImpl.java - validateUsername() .... ");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		
		/*
		Query query = session.createQuery("from User where username = ?") ;
		query.setString(0 , username) ;
		User user = (User) query.uniqueResult() ;
		*/
		
		//Query query = session.createQuery("from User where username = '"+username+"'");
		
		User user = (User)session.get(User.class , username) ; // applicable only if field is a primary key
		
		return user ;
		
		/*
		Session session = sessionFactory.openSession();
		User user = (User)session.get(User.class , username) ; // applicable only if field is a primary key
		session.close();
		return user ;
		*/
		
		
		
		
	}
	

	// check for duplicate email_id :
	//@Override
	public User validateEmail(String email) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("UserDaoImpl.java - validateEmail() .... ");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("from User where email = ?") ;
		query.setString(0 , email) ;
		User user = (User) query.uniqueResult() ;
		return user ;
		
		
		/*
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User where email = ?") ;
		query.setString(0 , email) ;
		User user = (User) query.uniqueResult() ;
		session.close();
		return user ;
		*/
		
	}


	// for login :
	//@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("UserDaoImpl.java - login(User user) .... ");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("from User where username = ? and password = ?") ;
		query.setString(0, user.getUsername()) ;
		query.setString(1, user.getPassword()) ;
		/*
		User user_login = (User) query.uniqueResult() ;
		return user_login ;
		*/
		return (User)query.uniqueResult() ;
		
		/*
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("from User where username = ? and password = ?") ;
		query.setString(0, user.getUsername()) ;
		query.setString(1, user.getPassword()) ;
		
		User user_login = (User) query.uniqueResult() ;
		
		session.close() ;
		
		return user_login ;
		*/
		
	}


	// for user update (online_status update) :
	//@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		System.out.println("UserDaoImpl.java - update(User user) .... ");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.update(user); // updates the online_status value from 0(false) to 1(true) 
		
		
		/*
		Session session = sessionFactory.openSession() ;
		session.update(user);
		session.flush();
		session.close();
		*/
	
	}


	// to get username for logout function :
	//@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("UserDaoImpl.java - getUserByUsername() .... ");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		User user = (User)session.get(User.class,username) ;
		return user ;
	
	}
	
	
	
}
