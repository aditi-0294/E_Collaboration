package com.niit.dao;

import com.niit.model.ProfilePicture;

public interface ProfilePictureDao {

	// to insert and save profile picture :
	void saveProfilePicture(ProfilePicture profilePicture) ;
	
	// to display profile picture :
	ProfilePicture getProfilePicture(String username) ;
	
}
