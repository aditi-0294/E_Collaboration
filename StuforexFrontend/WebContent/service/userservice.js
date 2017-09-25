/**
 * 	UserService
 */


console.log("userservice.js loaded .... ")

app.factory('UserService' , function($http) {
	
	var userService = {} ; // instantiation
	
	var BASE_URL = "http://localhost:9090/StuforexBackend"
		
	
	// for new user registration :
		userService.registerUser = function(user) {
		
			console.log("userservice.js - userService.registerUser()")
			return $http.post(BASE_URL + "/registeruser" , user)
		
		}
	
	
	// for login :
		userService.validateUser = function(user) {
			
			console.log("userservice.js - userService.registerUser()")
			return $http.post(BASE_URL + "/login" , user)
		
		}
		
	
	// for logout :	
		userService.logout = function() {
			
			console.log("userservice.js - userService.logout()")
			return $http.get(BASE_URL + "/logout")
		
		}
	
		
	// for getting user details - editing user profile :
		userService.getUser = function() {
			
			console.log("userservice.js - userService.getUser()")
			return $http.get(BASE_URL + "/getuser")
			
		}
	
		
	// for updating user details - editing user profile :
		userService.updateUser = function(user) {
			
			console.log("userservice.js - userService.updateUser()")
			return $http.put(BASE_URL + "/updateuser" , user)
		
		}
		
		
	return userService ; // returning the instance
	
})