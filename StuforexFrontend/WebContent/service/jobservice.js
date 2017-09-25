/**
 * JobService
 */

console.log("jobservice.js loaded .... ")

app.factory('JobService' , function($http) {
	
	var jobService = {} ; // instantiation
	
	var BASE_URL = "http://localhost:9090/StuforexBackend"
		
		
	// to insert and save job details - admin :
		jobService.saveJob = function(job) {
		
			console.log("jobservice.js - jobService.savejob()")
			return $http.post(BASE_URL + "/savejob" , job)
			
		}	
	
	
	// to get list of all job details - user & admin :
		jobService.getAllJobs = function() {
			
			console.log("jobservice.js - jobservice.getAllJobs()")
			return $http.get(BASE_URL + "/getAllJobs")
			
		}
	
		
	// to get job details depending on job_id - user & admin :
		jobService.getJobDetails = function(id) {
			
			console.log("jobservice.js - jobService.getJobDetails(id)")
			return $http.get(BASE_URL + "/getJobById/" + id)
		
		}
		
		
	// to edit and update job details - admin :
		jobService.editUpdateJobDetails = function(job) {
			
			console.log("jobservice.js - jobService.editUpdateJobDetails(job)")
			return $http.put(BASE_URL + "/editJobDetails" , job)
			
		}
		
		
	// to delete job by job_id - admin :
		jobService.deleteJobById = function(id) {
			
			console.log("jobservice.js - jobService.deleteJobById(id) .... ")
			return $http['delete'](BASE_URL + "/deleteJobById/" + id)
			
		}
		
	
		
	return jobService ;
	
})