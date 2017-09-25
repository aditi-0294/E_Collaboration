/**
 * 	Angular JS Module and Route Provider Information
 */

console.log("app.js loaded .... ")
var app = angular.module("app" , ['ngRoute' , 'ngCookies'])

app.config(function($routeProvider) {
	
	$routeProvider
	
	// for welcome page ( all users )
	.when('/welcome' , {
		templateUrl : 'views/welcome.html'
	})
	
	// for home page 
	.when('/home' , { 
		templateUrl : 'views/home.html' ,
		controller : 'BlogPostController'
	})
	
	// for aboutUs page
	.when('/aboutUs' , { 
		templateUrl : 'views/aboutUs.html'
	})
	
	// for contactUs page
	.when('/contactUs' , { 
		templateUrl : 'views/contactUs.html'
	})
	
	// for registeration page
	.when('/register' , {  
		templateUrl : 'views/registerForm.html' ,
		controller : 'UserController'
	})

	// for login page
	.when('/login' , { 
		templateUrl : 'views/loginForm.html' ,
		//templateUrl : 'views/login1.html' ,
		controller : 'UserController'
	})
	
	// for editing user profile
	.when('/editprofile' , {
		//templateUrl : 'views/updateProfile.html' ,
		templateUrl : 'views/editUpdateProfile.html' ,
		controller : 'UserController'
	})
	
	// to insert and save job details - admin
	.when('/savejob' , {
		templateUrl : 'views/jobForm.html' ,
		controller : 'JobController'
	})
	
	// to get list of all saved job details - user and admin
	.when('/getAllJobs' , {
		templateUrl : 'views/jobTitle.html' ,
		controller : 'JobController' ,
	//	controller : 'JobViewController'
	})
	
	// job details page - to apply for new jobs
	.when('/getJobDetails/:id' , {
		templateUrl : 'views/jobDetail.html' ,
		controller : 'JobViewController' 
		//controller : 'JobController'
	})
	
	// to edit or update job details - admin
	.when('/editUpdateJobById/:id' , {
		templateUrl : 'views/editUpdateJobForm.html' ,
		controller : 'JobViewController'
		//controller : 'JobController'
	})
	
	// to insert and save blog post details - user and admin
	.when('/saveBlogPost' , {
		templateUrl : 'views/blogpostForm.html' , 
		controller : 'BlogPostController'
	})
	
	// to get list of all blogs - approved as well as waiting for approval 
	.when('/getAllBlogs' , {
		templateUrl : 'views/listOfBlogPosts.html' ,
		controller : 'BlogPostController'	
	})
	
	// to get blog_posts depending on id
	.when('/getBlogPostById/:id' , {
		templateUrl : 'views/blogPostDetail.html' ,
		controller : 'BlogPostDetailController'
	})
	
	// to approve blog_post ( depending on id ) by admin 
	.when('/approveBlogPost/:id' , {
		templateUrl : 'views/blogPostApprovalForm.html' ,
		controller : 'BlogPostDetailController'		
	})
	
	// to upload profile picture (save , insert and display)
	.when('/uploadProfilePicture' , {
		templateUrl : 'views/profilePicture.html'
	})
	
	// for list of suggested users 
	.when('/suggestedUsersList' , {
		templateUrl : 'views/listOfSuggestedUsers.html' ,
		controller : 'FriendController'
	})
	
	// for pending requests
	.when('/pendingRequests' , {
		templateUrl : 'views/listOfPendingRequests.html' ,
		controller : 'FriendController'
	})
	
	// view user details before accepting friend request
	.when('/getUserDetails/:fromId' , {
		templateUrl : 'views/userDetails(friend).html' ,
		controller : 'FriendDetailController'
	})
	
	// to get list of friends
	.when('/listOfFriends' , {
		templateUrl : 'views/listOfFriends.html' ,
		controller : 'FriendController'
	})
	
	// chat room section
	.when('/chat' , {
		templateUrl : 'views/chats.html' ,
		controller : 'ChatController'
	})
	
	.otherwise({
		//templateUrl : 'views/home.html'
		//templateUrl : 'views/loginForm.html'
		templateUrl : 'views/welcome.html'
	})
	
})


app.run(function($rootScope , $cookieStore , UserService , $location ) {
	
	if($rootScope.currentUser == undefined) {
		
		$rootScope.currentUser = $cookieStore.get("currentUser")
		
		$rootScope.logout = function() {
			
			console.log("app.js - app.run() - $rootScope.logout()")
				
				UserService.logout().then(function(response) {
					
					console.log("app.js - UserService.logout() - first function - success : ")
					
					UserService.logoutSuccess = "LoggedOut Successfully .... "
				
						delete $rootScope.currentUser
						$cookieStore.remove("currentUser")
						
						$location.path('/login')
					
				} , function(response) {
					
					console.log("app.js - UserService.logout() - second function - error : ")
					
					$scope.error = response.data
					
					$location.path('/login')
					
				})
		}
	}

})