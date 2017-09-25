package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "BlogPost_Detail")
public class BlogPost {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
	
	@NotEmpty
	private String blogTitle ;
	
	@Lob // Large Object
	@NotEmpty
	private String blogDescription ; // CLOB - Character Large OBject
	
	@ManyToOne // Many blogs by One user
	@JoinColumn(name = "author") // foreign key
	private User postedBy ;
	
	private Date postedOn ;

	private boolean approved ; // initially the value is false - admin_approval
	
	
	// getter-setter
	
	
	/* for blog_id */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	/* for blog_title */
	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	
	/* for blog_description */
	public String getBlogDescription() {
		return blogDescription;
	}

	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}

	
	/* for blog_author - blog written by */
	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	
	/* for date_postedOn */
	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	
	/* for blog_approved */
	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
}
