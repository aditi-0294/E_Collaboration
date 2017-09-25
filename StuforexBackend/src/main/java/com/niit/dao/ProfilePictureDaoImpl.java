package com.niit.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.ProfilePicture;

@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	
	
	// to insert and save profile picture :
	//@Override
	public void saveProfilePicture(ProfilePicture profilePicture) {
		// TODO Auto-generated method stub
		System.out.println("ProfilePictureDaoImpl.java - saveProfilePicture()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.saveOrUpdate(profilePicture);
		
	}


	// to display profile picture :
	//@Override
	public ProfilePicture getProfilePicture(String username) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("ProfilePictureDaoImpl.java - getProfilePicture()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		ProfilePicture profilePicture = (ProfilePicture)session.get(ProfilePicture.class, username) ;
		
		return profilePicture ;
		
	}

}
