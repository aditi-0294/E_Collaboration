/**
 * BlogPostController
 */

console.log("blogpostcontroller.js loaded .... ")

app.controller('BlogPostController' , function($scope , BlogPostService , $location) {
	
	
	// to check list of blog_posts requiring admin approval 
	BlogPostService.getBlogPostsWaitingForApproval().then(function(response) {
		
		console.log("blogpostcontroller.js - BlogPostService.getBlogPostsWaitingForApproval() statement - first function - success : " + response.status)
		console.log("blogpostcontroller.js - BlogPostService.getBlogPostsWaitingForApproval() statement - first function - success : " + response.data)
		
		$scope.getBlogPostsWaitingForApproval = response.data
		
	 } , function(response) {
		
		console.log("blogpostcontroller.js - BlogPostService.getBlogPostsWaitingForApproval() statement - second function - error : " + response.status)
		console.log("blogpostcontroller.js - BlogPostService.getBlogPostsWaitingForApproval() statement - second function - error : " + response.data)
		
		if(response.status == 401)
			$location.path('/login')
		
	})
	
	
	// to check list of blog_posts approved by admin 
	BlogPostService.getApprovedListOfBlogPosts().then(function(response) {
		
		console.log("blogpostcontroller.js - BlogPostService.getApprovedListOfBlogPosts() statement - first function - success : " + response.status)
		console.log("blogpostcontroller.js - BlogPostService.getApprovedListOfBlogPosts() statement - first function - success : " + response.data)
		
		$scope.getApprovedListOfBlogPosts = response.data
		
	} , function(response) {
		
		console.log("blogpostcontroller.js - BlogPostService.getApprovedListOfBlogPosts() statement - second function - error : " + response.status)
		console.log("blogpostcontroller.js - BlogPostService.getApprovedListOfBlogPosts() statement - second function - error : " + response.data)
		
		if(response.status == 401)
			$location.path('/login')
		
	})
	
	
	// to insert and save blop_post - user & admin
	$scope.addBlogPost = function() {
		
		BlogPostService.addBlogPost($scope.blogPost).then(function(response) {
			
			console.log("blogpostcontroller.js - BlogPostService.addBlogPost() statement - first function - success : " + response.status)
			console.log("blogpostcontroller.js - BlogPostService.addBlogPost() statement - first function - success : " + response.data)
			
			alert('BlogPost added successfully .... Waiting for admin approval .... ')
			
			$location.path('/getAllBlogs')

		} , function(response) {
			
			console.log("blogpostcontroller.js - BlogPostService.addBlogPost() statement - first function - success : " + response.status)
			console.log("blogpostcontroller.js - BlogPostService.addBlogPost() statement - first function - success : " + response.data)
			
			if(response.status == 401)
				$location.path('/login')
				
			$location.path('/saveBlogPost')
			
		})
	}
	
	//
})