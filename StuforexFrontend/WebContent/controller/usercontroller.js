/**
 * 	UserController
 * 
 * $scope.error = {"code":2, "message" : "Username already exists ..... "}
 * 
 * $rootScope.currentUser = {"username" : "aditi" , "firstname" : "aditi" ......}
 * 
 */

console.log("usercontroller.js loaded .... ")

app.controller('UserController' , function($scope,$rootScope,UserService,$location,$cookieStore) {
	
	$scope.user = {} // called from service
	
	// for editing user profile - if commented no details are retrieved on page 	
	if($rootScope.currentUser != undefined) {
		

		UserService.getUser().then(function(response) { // called from service
			
			console.log("usercontroller.js - UserService.getUser() method - first function - success : " + response.status)
			console.log("usercontroller.js - UserService.getUser() method - first function - success : " + response.data)
			
			$scope.user = response.data 
			
		} , function(response) {
			
			console.log("usercontroller.js - UserService.getUser() method - second function - failure / error : " + response.status)
			console.log("usercontroller.js - UserService.getUser() method - second function - failure /error : " + response.data)
			
			if(response.status == 401)
				$location.path('/login')
			
			$location.path('/home')
			
		})
		
		
	}
	
	
	// for new user registration
	$scope.registerUser = function() {
		
		console.log("usercontroller.js - $scope.registerUser()")
		
		UserService.registerUser($scope.user).then(function(response) {
			
			console.log("usercontroller.js - UserService.registerUser() method - first function - success : " + response.status)
			console.log("usercontroller.js - UserService.registerUser() method - first function - success : " + response.data)
			
		//	$scope.message = "Registered successfully .... please login .... "
			alert('Registered successfully ..... Proceed to login .... ')
			
			$location.path('/login')	
						
		},function(response) {
				
				console.log("usercontroller.js - UserService.registerUser() method - second function - failure / error : " + response.status)
				console.log("usercontroller.js - UserService.registerUser() method - second function - failure /error : " + response.data)
			
				$scope.error = response.data
				
				$location.path('/register')
		
		})
	
	}
	
	
	
	// for login
	$scope.validateUser = function() {
		
		console.log("usercontroller.js - $scope.validateUser()")
		
		UserService.validateUser($scope.user).then(function(response) {
			
			console.log("usercontroller.js - UserService.validateUser() method - first function - success : " + response.status)
			console.log("usercontroller.js - UserService.validateUser() method - first function - success : " + response.data)
			
			$rootScope.currentUser = response.data // for logout part - displays logged-in user - index.html 
			
			$cookieStore.put("currentUser" , response.data)
			
			$location.path('/home')
			
		} , function(response) {
			
			$scope.error = response.data
			
			console.log("usercontroller.js - UserService.registerUser() method - second function - failure / error : " + response.status)
			console.log("usercontroller.js - UserService.registerUser() method - second function - failure /error : " + response.data)
		
			$location.path('/login')
		
		})
	}
		
	
	
	// for logout
	
//	$scope.logout = function() { // $scope - local variable - but not view so doesnt' work 
		
	$rootScope.logout = function() { // $rootScope - global variable - can be accessed from any view 
		
		console.log("usercontroller.js - $scope.logout()")
		
		UserService.logout().then(function(response) { // no model to bind
			
			console.log("usercontroller.js - UserService.logout() method - first function - success : " + response.status)
			console.log("usercontroller.js - UserService.logout() method - first function - success : " + response.data)
			
			//$scope.logoutSuccess = "LoggedOut Successfully .... "
			$rootScope.logoutSuccess = "LoggedOut Successfully ..... "
				delete $rootScope.currentUser
				$cookieStore.remove("currentUser")
			
			$location.path('/login')
			
		} , function(response) {
			
			$scope.error = response.data
			
			console.log("usercontroller.js - UserService.logout() method - second function - failure / error : " + response.status)
			console.log("usercontroller.js - UserService.logout() method - second function - failure /error : " + response.data)
		
			$location.path('/login')
					
		})
	}
	
	
	// for updating user details 
	$scope.updateUser = function() {
		
		console.log("usercontroller.js - $scope.updateUser()")
		
		UserService.updateUser($scope.user).then(function(response) {
			
			console.log("usercontroller.js - UserService.updateUser() method - first function - success : " + response.status)
			console.log("usercontroller.js - UserService.updateUser() method - first function - success : " + response.data)
			
			alert("Details updated successfully ..... ")
			
			$location.path('/home')
			
		} , function(response) {
			
			//$scope.error = response.data
			
			console.log("usercontroller.js - UserService.updateUser() method - second function - failure / error : " + response.status)
			console.log("usercontroller.js - UserService.updateUser() method - second function - failure /error : " + response.data)
		
			/*
			 * for unauthorized access - 401 - redirect the user to login page
			 * for exception - 500 - redirect the user to update profile page
			 */
			if(response.status == 401)
				$location.path('/login')
			
			$location.path('/editprofile')	
		
		})
	}
	
	
})