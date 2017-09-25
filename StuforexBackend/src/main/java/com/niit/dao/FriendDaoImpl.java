package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Friend;
import com.niit.model.User;



@Repository
@Transactional
public class FriendDaoImpl implements FriendDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	
	// to get a list of suggested users :
	//@Override
	public List<User> getListOfSuggestedUsers(String username) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("FriendDaoImpl.java - getListOfSuggestedUsers()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		
		//Session session = sessionFactory.openSession() ;
		
		// if toId rejects fromId friend request : fromId will be displayed in suggested users' list
		String queryString = "select * from User_Detail where username in (select username from User_Detail where username != ? minus (select fromId from Friend_Detail where toId = ? and status != 'D' union select toId from Friend_Detail where fromId=? and status != 'D'))" ;
		
		SQLQuery query = session.createSQLQuery(queryString) ;
		
		/*
		 * first query - inner query : (select fromId from Friend_Detail where toId=? union select toId from Friend_Detail where fromId=?)
		 * second query - outer query : select * from User_Detail where username in (select username from User_Detail where username!=? minus
		 * 
		 * ? - loggedIn username
		 * 
		 * 	union : combines result of two queries which eliminates duplicate selected rows
		 * 	minus : combines results of two queries which returns only unique rows returned by the first query but not by the second 
		 */
		
		query.setString(0, username) ;
		query.setString(1, username) ;
		query.setString(2, username) ;
		
		query.addEntity(User.class) ; // specifies which type of record is returned by SQL query
		
		List<User> suggestedUsers = query.list();
		
		//session.close() ;
		
		return suggestedUsers ;
		
	}


	// to send friend request to other users :
	//@Override
	public void sendFriendRequest(String username, String toId) {
		// TODO Auto-generated method stub
		System.out.println("FriendDaoImpl.java - sendFriendRequest()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		
		Friend friend = new Friend() ;
		
		// friend request is from username(i.e.,fromId) to "toId"
		friend.setFromId(username);
		friend.setToId(toId);
		friend.setStatus('P'); // initially friend request status will be pending
		
		session.save(friend) ;
		
	}


	// to get list of pending requests :
	//@Override
	public List<Friend> getPendingRequests(String username) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("FriendDaoImpl.java - getPendingRequests()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		
		Query query = session.createQuery("from Friend where toId = ? and status = 'P'") ;
		
		query.setString(0, username) ;
		
		return query.list();
		
	}


	// to accept or deny the request : update pending request from 'P' to 'A' or 'D'
	//@Override
	public void updatePendingRequest(Friend pendingRequest) {
		// TODO Auto-generated method stub
		System.out.println("FriendDaoImpl.java - updatePendingRequest()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		
		session.update(pendingRequest); // updates status to 'A' or 'D'
		
		/* to delete denied or rejected request records from database :
		 *		
		 *			if(pendingRequest.getStatus() == 'D')
		 *				session.delete(pendingRequest); // delete query - delete from friend where id = ?
		 *			else
		 *				session.update(pendingRequest); // update query - status = 'A' : update frieend set status = 'A' where id = ?
		 *
		 * change for getting list of suggested user :
		 * 		String queryString = "select * from User_Detail where username in (select username from User_Detail where username != ? minus (select fromId from Friend_Detail where toId = ? union select toId from Friend_Detail where fromId=?))" ;
		 */
		
	}


	// to get list of friends :
	//@Override
	public List<Friend> listOfFriends(String username) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("FriendDaoImpl.java - listOfFriends()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		
		//Query query = session.createQuery("select Friend where ( fromId = ? or toId = ? ) and status = 'A'") ;
		Query query = session.createQuery("from Friend where ( fromId = ? or toId = ? ) and status = ?") ;
		
		query.setString(0, username) ;
		query.setString(1, username) ;
		query.setCharacter(2, 'A') ;
		
		return query.list() ;
	
	}

}
