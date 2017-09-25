package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.model.Job;


@Repository
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	private SessionFactory sessionFactory ;
	
	
	// to insert and save job details - admin :
	//@Override
	public void saveJob(Job job) {
		// TODO Auto-generated method stub
		System.out.println("JobDaoImpl.java - saveJob()");
		System.out.println(); 
		
		Session session = sessionFactory.getCurrentSession() ;
		session.save(job) ;
	}


	// to get list of all saved job details - user & admin :
	//@Override
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("JobDaoImpl.java - getAllJobs()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		Query query = session.createQuery("from Job") ;
		return query.list() ;
		
	}


	// to get list of job details by job_id - user & admin :
	//@Override
	public Job getJobById(int id) {
		// TODO Auto-generated method stub
		//return null;
		System.out.println("JobDaoImpl.java - getJobById()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		Job job = (Job)session.get(Job.class, id) ; // Id is primary key
		
		return job ;
		
	}


	// to edit job details - admin : 
	//@Override
	public void editJob(Job job) {
		// TODO Auto-generated method stub
		System.out.println("JobDaoImpl.java - editJob()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.update(job);
		
	}

	
	// to delete job by job_id - admin : 
	//@Override
	public void deleteJobById(Job job) {
		// TODO Auto-generated method stub
		System.out.println("JobDaoImpl.java - deleteJobById()");
		System.out.println();
		
		Session session = sessionFactory.getCurrentSession() ;
		session.delete(job);
		
	}
	
	

}
