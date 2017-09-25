package com.niit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BlogComment_Detail")
public class BlogComment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@Column (name = "BlogComment_Id")
	private int id ;
	
	@ManyToOne
	@JoinColumn(name = "blogpost_id")
	private BlogPost blogpost ;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private User commentedBy ;
	
	private Date commentedOn ;
	
	private String commentText ;

	
	// getter-setter method
	
	/* for blog_comment id */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/* for blogpost details */
	public BlogPost getBlogpost() {
		return blogpost;
	}

	public void setBlogpost(BlogPost blogpost) {
		this.blogpost = blogpost;
	}

	/* for name of the commentator */
	public User getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}

	/* for date of comment */
	public Date getCommentedOn() {
		return commentedOn;
	}

	public void setCommentedOn(Date commentedOn) {
		this.commentedOn = commentedOn;
	}

	/* for writing comments */
	public String getCommentText() {
		return commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	
}
