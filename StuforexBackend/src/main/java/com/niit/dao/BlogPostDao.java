package com.niit.dao;

import java.util.List;

import com.niit.model.BlogComment;
import com.niit.model.BlogPost;

public interface BlogPostDao {

	// to insert and save the blog posted - user and admin :
	void saveBlogPost(BlogPost blogPost) ;
	
	// to check list of blog_posts requiring admin approval :
	List<BlogPost> getBlogPosts(int approved) ;
	
	// to get blog_posts depending on id :
	BlogPost getBlogPostById(int id) ;
	
	// to approve blog_post by admin :
	void approveBlogPost(BlogPost blogPost) ;
	
	// to insert and save blog_post comments :
	void addBlogComment(BlogComment blogComment) ;
	
	// to retrieve blog_post comments depending on blogpost_id :
	List<BlogComment> getAllBlogComments(int blogPostId) ;
	
}
