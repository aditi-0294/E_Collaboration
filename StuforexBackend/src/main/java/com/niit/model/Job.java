package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Job_Detail")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id ;
		
	@NotEmpty
	private String jobTitle ;
		
	@Lob // Large Object
	private String jobDescription ; // CLOB - Character Large Object
		
	@Lob
	private String skillRequired ;
	
	//@Lob
	private String yrsOfExp ;
		
	private String salary ;
		
	private String companyname ;
		
	private String location ;
	
	private Date postedOn ;

		
	// getter-setter :
		
	/* for job_id */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/* for job_title */
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/* for job_description */
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	/* for skills_required */
	public String getSkillRequired() {
		return skillRequired;
	}

	public void setSkillRequired(String skillRequired) {
		this.skillRequired = skillRequired;
	}

	/* for experienced_required */
	public String getYrsOfExp() {
		return yrsOfExp;
	}

	public void setYrsOfExp(String yrsOfExp) {
		this.yrsOfExp = yrsOfExp;
	}

	/* for salary */
	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	/* for companyname */
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	/* for location */
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	/* for date_postedOn */
	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
			this.postedOn = postedOn;
	}
		
		
		


}
