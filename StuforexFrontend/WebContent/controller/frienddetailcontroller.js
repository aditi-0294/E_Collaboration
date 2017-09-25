/**
 * FriendDetailController
 */

console.log("frienddetailcontroller.js loaded .... ")

app.controller('FriendDetailController' , function($scope , FriendService , $location , $routeParams) {
	
	var fromId = $routeParams.fromId // takes id dynamically
	
	FriendService.viewUserDetails(fromId).then(function(response) {
		
		console.log("frienddetailcontroller.js - FriendService.getUserDetails() - first function - success : " + response.status)
		console.log("frienddetailcontroller.js - FriendService.getUserDetails() - first function - success : " + response.data)
		
		$scope.user = response.data
		
	} , function(response) {
		
		console.log("frienddetailcontroller.js - FriendService.getUserDetails() - second function - error / failure : " + response.status)
		console.log("frienddetailcontroller.js - FriendService.getUserDetails() - second function - error / failure : " + response.data)
		
		if(response.status == 401)
			$location.path('/login')
			
	})
})