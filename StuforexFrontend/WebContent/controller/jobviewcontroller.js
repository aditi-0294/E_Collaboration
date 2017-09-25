/**
 * to view and edit job details 
 */

console.log("jobviewcontroller.js loaded .... ")

app.controller('JobViewController' , function($scope , JobService , $location , $routeParams) {
	
	var id = $routeParams.id ;
	
	
	// to get job details depending on job_id 
	JobService.getJobDetails(id).then(function(response){
			
			console.log("jobviewcontroller.js - getJobDetails() - first function - success : " + response.status)
			console.log("jobviewcontroller.js - getJobDetails() - first function - success : " + response.data)
			
			$scope.job = response.data
			
		} , function(response){
			
			console.log("jobviewcontroller.js - getJobDetails() - second function - error / failure : " + response.status)
			console.log("jobviewcontroller.js - getJobDetails() - second function - error / failure : " + response.data)
			
			$location.path('/login')
			
		})	
		
		
	// to edit and update job details 
	$scope.editUpdateJobDetails = function() {
		
		console.log("jobviewcontroller.js - editUpdateJobDetails() .... ")
		
		JobService.editUpdateJobDetails($scope.job).then(function(response){
			
			console.log("jobviewcontroller.js - editUpdateJobDetails() - first function - success : " + response.status)
			console.log("jobviewcontroller.js - editUpdateJobDetails() - first function - success : " + response.data)
			
			alert('Job Details updated successfully .... ')
			
			$location.path('/getJobDetails/' + id)
			
		//	$location.path('/getAllJobs')
			
		} , function(response){
			
			console.log("jobviewcontroller.js - editUpdateJobDetails() - second function - error / failure : " + response.status)
			console.log("jobviewcontroller.js - editUpdateJobDetails() - second function - error / failure : " + response.data)
			
			// for unAuthorised / Access denied error
			if(response.status == 401) {
				
				$scope.error = response.data
				$location.path('/login')
				
			}
			
			// unable to edit or update job details - internal server error
			if(response.status == 500) {
				
				$scope.error = response.data
				$location.path('/editUpdateJobById/' + id)
				
			}
			
			$location.path('/home')
			
		})
		
	}	
		

})