/**
 * BlogPostDetailController
 * /*
		 * Assignment statement which will assign blogPost object to blogPost property in blogComment object
		 * $scope.blogPost - JSON Object of type BlogPost
		 * $scope.blogComment - JSON Object of type BlogComment
		 * blogComment has {id , blogPost , commentedBy , commentedOn , commentText }
		 * $scope.blogComment.blogPost = $scope.blogPost
		 
		 *
		 * blogComment : {blogPost : {id , blogTitle , postedBy , postedOn} , commentText : 'Good'}
		 *
 */

console.log("blogpostdetailcontroller.js loaded .... ")

app.controller('BlogPostDetailController' , function($scope , BlogPostService , $location , $routeParams) {
	
	var id = $routeParams.id // takes id dynamically
	$scope.showComments = false
	
	
	// to get blog_post depending on id - select * from BlogPost_Detail where id = 246 [BlogPost Object]
	$scope.blogPost = BlogPostService.getBlogPostById(id).then(function(response) {
		
		console.log("blogpostdetailcontroller.js - BlogPostService.getBlogPostById(id) - first function - success : " + response.status)
		console.log("blogpostdetailcontroller.js - BlogPostService.getBlogPostById(id) - first function - success : " + response.data)
	
		$scope.blogPost = response.data
		
	} , function(response) {
		
		console.log("blogpostdetailcontroller.js - BlogPostService.getBlogPostById(id) - second function - error / failure : " + response.status)
		console.log("blogpostdetailcontroller.js - BlogPostService.getBlogPostById(id) - second function - error / failure : " + response.data)
	
		if(response.status == 401)
			$location.path('/login')
		
	})
	
	
	// to retrive all blog_post comments depending on blogpost_id - select * from BlogComment_Detail where blogpost_id = 246 [listOfBlogComments] 
	function getBlogComments() {
		
		BlogPostService.getBlogComments(id).then(function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - first function - success : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - first function - success : " + response.data)
			
			$scope.getBlogComments = response.data
			
		} , function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - second function - error / failure : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - first function - error / failure : " + response.data)
	
			if(response.status == 401)
				$location.path('/login')
	
		})
	}
	
	$scope.getComments = function() {
		
		$scope.showComments = true 
	}
	
	getBlogComments()
	
	
	// to approve blog_post by admin 
	$scope.approveBlogPost = function() {
		
		BlogPostService.approveBlogPost($scope.blogPost).then(function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.approveBlogPost() - first function - success : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.approveBlogPost() - first function - success : " + response.data)
			
			$location.path('/getAllBlogs')
			
		} , function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.approveBlogPost() - second function - error / failure : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.approveBlogPost() - second function - error / failure : " + response.data)
			
			if(response.status == 401)
				$location.path('/login')
					
			
		})
	}
	
	
	// to insert and save blog_post comments - user and admin :
	$scope.addBlogComment = function() {
		
		/*
		 * Assignment statement which will assign blogPost object to blogPost property in blogComment object
		 * $scope.blogPost - JSON Object of type BlogPost
		 * $scope.blogComment - JSON Object of type BlogComment
		 * blogComment has {id , blogPost , commentedBy , commentedOn , commentText }
		 * $scope.blogComment.blogPost = $scope.blogPost
		 */
		
		$scope.blogComment.blogpost = $scope.blogPost
		
		/*
		 * blogComment : {blogPost : {id , blogTitle , postedBy , postedOn} , commentText : 'Good'}
		 */
		
		BlogPostService.addBlogComment($scope.blogComment).then(function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.addBlogComment() - first function - success : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.addBlogComment() - first function - success : " + response.data)
			
			$scope.blogComment.commentText = ''
			
			getBlogComments()	
				
		}, function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.addBlogComment() - second function - error / failure : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.addBlogComment() - first function - error / failure : " + response.data)
	
			if(response.status == 401)
				$location.path('/login')
				
			$scope.error = response.data
			
			$location.path('/getBlogPostById/' + id)	
		})
	}
	
	/*
	// to retrive all blog_post comments depending on blogpost_id 
	function getBlogComments() {
		
		BlogPostService.getBlogComments(id).then(function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - first function - success : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - first function - success : " + response.data)
			
			$scope.getBlogComments = response.data
			
		} , function(response) {
			
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - second function - error / failure : " + response.status)
			console.log("blogpostdetailcontroller.js - BlogPostService.getBlogComments() - first function - error / failure : " + response.data)
	
			if(response.status == 401)
				$location.path('/login')
	
		})
	}
	
	getBlogComments()
	*/
})