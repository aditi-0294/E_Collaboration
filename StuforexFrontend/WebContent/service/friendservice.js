/**
 * FriendService
 */

console.log("friendservice.js loaded .... ")

app.factory('FriendService' , function($http) {
	
	var friendService = {} ; // instantiation
	
	var BASE_URL = "http://localhost:9090/StuforexBackend"
		
	
		
	// to get a list of suggested users :	
		friendService.listOfSuggestedUsers = function() {
		
			console.log("friendservice.js - friendService.listOfSuggestedUsers()")
			return $http.get(BASE_URL + "/suggestedUsersList")
			
	}
		
		
	// to send friend request to other users :
		friendService.sendFriendRequest = function(toId) {
			
			console.log("friendservice.js - friendService.sendFriendRequest()")
			return $http.post(BASE_URL + "/sendFriendRequest/" + toId)
			
		}
		
		
	// to get list of pending requests :
		friendService.listOfPendingRequests = function() {
			
			console.log("friendservice.js - friendService.listOfPendingRequests()")
			return $http.get(BASE_URL + "/getPendingRequests")
			
		}
		
		
	// to view user details before accepting request :
		friendService.viewUserDetails = function(fromId) {
			
			console.log("friendservice.js - friendservice.viewUserDetails()")
			return $http.get(BASE_URL + "/viewUserDetails/" + fromId)
			
		}
		
		
	// to accept or deny request - update pending request from 'P' to 'A' or 'D' : 
		friendService.updatePendingRequest = function(pendingRequest) { // status property is assigned in friendcontroller.js
			
			/*
			 * If accepted : {id,'fromId','toId','A'}
			 * If Rejected : {id,'fromId','toId','D'} 
			 */
			
			console.log("friendservice.js - friendservice.updatePendingRequest()")
			return $http.put(BASE_URL + "/updatePendingRequest" , pendingRequest)
			
		}
		
		
	// to get list of friends :
		friendService.listOfFriends = function() {
			
			console.log("friendservice.js - friendService.listOfFriends()")
			return $http.get(BASE_URL + "/friendList")
			
		}
		
		
		
		
	return friendService ;	
})