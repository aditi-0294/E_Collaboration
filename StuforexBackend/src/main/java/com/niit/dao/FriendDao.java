package com.niit.dao;

import java.util.List;

import com.niit.model.Friend;
import com.niit.model.User;

public interface FriendDao {

	// to get a list of suggested users :
	List<User> getListOfSuggestedUsers(String username) ; // find list of suggested user for a username
	
	// to send friend request to other users :
	void sendFriendRequest(String username , String toId) ;
	
	// to get list of pending requests :
	List<Friend> getPendingRequests(String username) ;
	
	// to accept or deny the request : update pending request from 'P' to 'A' or 'D'
	void updatePendingRequest(Friend pendingRequest) ;
	
	// to get list of friends :
	List<Friend> listOfFriends(String username) ;
	
}
