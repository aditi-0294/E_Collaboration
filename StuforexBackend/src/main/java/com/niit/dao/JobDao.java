package com.niit.dao;

import java.util.List;

import com.niit.model.Job;

public interface JobDao {

	// to insert and save job details - admin :
	void saveJob(Job job) ;
	
	// to get list of all saved job details - user & admin :
	List<Job> getAllJobs() ;
	
	// to get job details by job_id - user & admin :
	Job getJobById(int id) ;
	
	// to edit and update job details - admin : 
	void editJob(Job job) ;
	
	// to delete job by job_id - admin :
	void deleteJobById(Job job) ;
	
}
