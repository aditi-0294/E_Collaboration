package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

@Repository
@Transactional
public class BlogPostDaoImpl implements BlogPostDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	
	// to insert and save blogpost - user and admin :
	//@Override
	public void saveBlogPost(BlogPost blogPost) {
		// TODO Auto-generated method stub
		System.out.println("BlogPostDaoImpl.java - saveBlogPost()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.save(blogPost) ;
		
	}


	// to check list of blog_posts requiring admin approval :
	//@Override
	public List<BlogPost> getBlogPosts(int approved) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("BlogPostDaoImpl.java - getBlogPosts()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		
		// if approved , method parameter = 0 : select * from BlogPost_Detail wgere approved = 0 - waiting for admin approval
		// if approved , method parameter = 1 : select * from BlogPost_Detail wgere approved = 1 - posts approved by admin
		Query query = session.createQuery("from BlogPost where approved = " + approved) ;
		return query.list() ;
		
	}


	// to get blog_posts depending on id :
	//@Override
	public BlogPost getBlogPostById(int id) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("BlogPostDaoImpl.java - getBlogPostById()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		BlogPost blogPost = (BlogPost)session.get(BlogPost.class , id) ;
		return blogPost ;
		
	}


	// to approve blog_post by admin :
	//@Override
	public void approveBlogPost(BlogPost blogPost) {
		// TODO Auto-generated method stub
		System.out.println("BlogPostDaoImpl.java - updateBlogPost()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.update(blogPost);
		
	}

	
	// to insert and save blog_post comments - user and admin :
	//@Override
	public void addBlogComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		System.out.println("BlogPostDaoImpl.java - addBlogComment()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.save(blogComment) ; 
		
	}


	// to retrieve blog_post comments depending on blogpost_id :
	//@Override
	public List<BlogComment> getAllBlogComments(int blogPostId) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("BlogPostDaoImpl.java - getAllBlogComments()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("from BlogComment where blogpost_id = ?") ;
		query.setInteger(0, blogPostId) ;
		return query.list() ;
		
	}

	
}
