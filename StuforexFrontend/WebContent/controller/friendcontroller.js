/**
 * FriendController
 */

console.log("friendcontroller.js loaded .... ")

app.controller('FriendController' , function($scope , FriendService , $location) {
	
	// to get a list of suggested users 
	function listOfSuggestedUsers() {
		
		console.log("friendcontroller.js - listOfSuggestedUsers()")
		
		FriendService.listOfSuggestedUsers().then(function(response) {
			
			console.log("friendcontroller.js - FriendService.listOfSuggestedUsers() - first function - success : " + response.status)
			console.log("friendcontroller.js - FriendService.listOfSuggestedUsers() - first function - success : " + response.data)
			
			$scope.suggestedUserList = response.data

		} , function(response) {
			
			console.log("friendcontroller.js - FriendService.listOfSuggestedUsers() - second function - error : " + response.status)
			console.log("friendcontroller.js - FriendService.listOfSuggestedUsers() - second function - error : " + response.data)
			
			if(response.status == 401)
				$location.path('/login')
				
		})
	}

	
	// to get list of pending requests : data is for listOfPendingRequests.html 
	function listOfPendingRequests() {
		
		console.log("friendcontroller.js - listOfPendingRequests()")
		
		FriendService.listOfPendingRequests().then(function(response) {
			
			console.log("friendcontroller.js - FriendService.listOfPendingRequests() - first function - success : " + response.status)
			console.log("friendcontroller.js - FriendService.listOfPendingRequests() - first function - success : " + response.data)
			
			$scope.pendingRequests = response.data

		} , function(response) {
			
			console.log("friendcontroller.js - FriendService.listOfPendingRequests() - second function - error : " + response.status)
			console.log("friendcontroller.js - FriendService.listOfPendingRequests() - second function - error : " + response.data)
			
			if(response.status == 401)
				$location.path('/login')
			
		})
	}
	
	
	// to get list of friends 
	function listOfFriends() {
		
		console.log("friendcontroller.js - listOfFriends()")
		
		FriendService.listOfFriends().then(function(response) {
			
			console.log("friendcontroller.js - FriendService.listOfFriends() - first function - success : " + response.status)
			console.log("friendcontroller.js - FriendService.listOfFriends() - first function - success : " + response.data)
			
			$scope.friendList = response.data 

		} , function(response) {
			
			console.log("friendcontroller.js - FriendService.listOfFriends() - first function - error / failure : " + response.status)
			console.log("friendcontroller.js - FriendService.listOfFriends() - first function - error / failure : " + response.data)
			
			if(response.status == 401) 
				$location.path('/login')
				
		})
		
	}
	
	
	// to send friend request to other users : toId = user.username
	$scope.friendRequest = function(toId) {
		
		console.log("friendcontroller.js - friendrequest()")
		
		FriendService.sendFriendRequest(toId).then(function(response) {
			
			console.log("friendcontroller.js - FriendService.sendFriendRequest() - first function - success : " + response.status)
			console.log("friendcontroller.js - FriendService.sendFriendRequest() - first function - success : " + response.data)
			
			alert('Friend Request sent successfully .... ')
			listOfSuggestedUsers()
			
			$location.path('/suggestedUsersList')
			
		} , function(response) {
			
			console.log("friendcontroller.js - FriendService.sendFriendRequest() - second function - error / failure : " + response.status)
			console.log("friendcontroller.js - FriendService.sendFriendRequest() - second function - error / failure : " + response.data)
		
			if(response.status == 401)
				$location.path('/login')
				
		})
	}

	
	// to accept or deny the request : update pending request from 'P' to 'A' or 'D'
	/*
	 * from HTML page to controller
	 */
	$scope.updatePendingRequest = function(pendingRequest,status) {
		
		console.log("friendcontroller.js - updatePendingRequest()")
		
		console.log("friendcontroller.js - updatePendingRequest() - initial status : " + pendingRequest.status)
		/*
		 * Initially : pendingRequests.status = 'P'
		 * So to assign values 'A' or 'D' :
		 * 	pendingRequest.status = status
		 */
		pendingRequest.status = status
		console.log("friendcontroller.js - updatePendingRequest() - after accept / deny status : " + pendingRequest.status)
		
		/*
		 * pendingRequest is an object of type friend - id , fromId , toId and status 
		 * status obtained is updated from 'P' to 'A' or 'D'
		 */
		
		FriendService.updatePendingRequest(pendingRequest).then(function(response) {
			
			console.log("friendcontroller.js - FriendService.updatePendingRequest() - first function - success : " + response.status)
			console.log("friendcontroller.js - FriendService.updatePendingRequest() - first function - success : " + response.data)
			
			listOfPendingRequests()
			
			alert('Successfully executed friend request operation .... ')
			
			$location.path('/pendingRequests')
			
		} , function(response) {
			
			console.log("friendcontroller.js - FriendService.sendFriendRequest() - second function - error / failure : " + response.status)
			console.log("friendcontroller.js - FriendService.sendFriendRequest() - second function - error / failure : " + response.data)
		
			if(response.status == 401)
				$location.path('/login')
			
		})
	}
	
	/*
	 * for assigning data to $scope property
	 * data will be dislayed in html page
	 */
	listOfSuggestedUsers()
	listOfPendingRequests()
	listOfFriends()
	
})