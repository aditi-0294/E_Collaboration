/**
 * Job Controller
 */

console.log("jobcontroller.js loaded .... ")

app.controller('JobController' , function($scope , JobService , $location , $routeParams) {
	
	//var id = $routeParams.id ;
	
	$scope.showJobDetails = false
	// to get a list of all job details - user and admin
	function getAllJobs() {
		
		JobService.getAllJobs().then(function(response) {
			
			console.log("jobcontroller.js - JobService.getAllJobs() statement - first function - success : " + response.status)
			console.log("jobcontroller.js - JobService.getAllJobs() statement - first function - success : " + response.data)
			
			$scope.jobs = response.data
			
		} , function(response) {
			
			console.log("jobcontroller.js - JobService.getAllJobs() statement - first function - success : " + response.status)
			console.log("jobcontroller.js - JobService.getAllJobs() statement - first function - success : " + response.data)
			
			$location.path('/login')
			
		})
	}
	
	
	// to insert and save job - admin 
	$scope.saveJob = function() {
		
		JobService.saveJob($scope.job).then(function(response) {
			
			console.log("jobcontroller.js - JobService.saveJob() method - first function - success : " + response.status)
			console.log("jobcontroller.js - JobService.saveJob() method - first function - success : " + response.data)
			
			alert('Job Details successfully submitted .... ')
		//	 getAllJobs()
			$location.path('/getAllJobs')
			
		} , function(response) {
			
			console.log("jobcontroller.js - JobService.saveJob() method - second function - failure : " + response.status)
			console.log("jobcontroller.js - JobService.saveJob() method - second function - failure : " + response.data)
			
			// UnAuthorized User or Access Denied Error
			if(response.status == 401) {
				
			//	alert("401 - Access Denied - UnAuthorized User")
				$scope.error = response.data
				$location.path('/login')
				
			}
				
			// Unable to insert job details - Internal Server Error	
			if(response.status == 500) {
				
				$scope.error = response.data
				$location.path('/savejob')
				
			} 
				
			$location.path('/home')
			
		})
	}
	
	
	// to get job details depending on job_id - user & admin 
	$scope.getJobDetails = function(id) {
		
		$scope.showJobDetails = true
		
		JobService.getJobDetails(id).then(function(response) {
			
			console.log("jobcontroller.js - JobService.getJobDetails(id) - first function - success : " + response.status)
			console.log("jobcontroller.js - JobService.getJobDetails(id) - first function - success : " + response.data)
			
			$scope.job = response.data // from JobController.java - getJobById() job object
			
		//	$location.path('/getJobDetails')
			
		} , function(response) {

			console.log("jobcontroller.js - JobService.getJobDetails(id) - second function - error : " + response.status)
			console.log("jobcontroller.js - JobService.getJobDetails(id) - second function - error : " + response.data)
		
			$location.path('/login')
			
		})
	}
	////////
	
	
	////////
	// to delete job details by job_id 
	$scope.deleteJobById = function(id) {
		
		console.log("jobcontroller.js - deleteJobDetails() .... ")
		
		JobService.deleteJobById(id).then(function(response){
			
			console.log("jobcontroller.js - JobService.deleteJobDetails() - first function - success : " + response.status)
			console.log("jobcontroller.js - JobService.deleteJobDetails() - first function - success : " + response.data)
		
			alert('Job details deleted successfully ... ')

			getAllJobs()
			$location.path('/getAllJobs')
			
		} , function(response) {
			
			console.log("jobcontroller.js - JobService.deleteJobDetails() - second function - error / failure : " + response.status)
			console.log("jobcontroller.js - JobService.deleteJobDetails() - second function - error / failure : " + response.data)
			
			// for unauthorised / access denied error
			if(response.status == 401) {
				
				$scope.error = response.data
				$location.path('/login')
				
			}
			
			/*
			// unable to delete job details - internal server error 
			if(response.status == 500) {
				
				$scope.error = response.data
				$location.path('/getJobDetails/' + id)
				
			}
			*/
			
			$location.path('/home')
		})
		
	}
	
	getAllJobs()
})
