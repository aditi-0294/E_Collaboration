/**
 * BlogPostService
 */


console.log("blogpostservice.js loaded .... ")

app.factory('BlogPostService' , function($http) {
	
	var blogPostService = {} ; // instantiation
	
	var BASE_URL = "http://localhost:9090/StuforexBackend"
		
		
	// to insert and save blog_post - user and admin :
		blogPostService.addBlogPost = function(blogPost) {
		
			console.log("blogpostservice.js - blogPostService.addBlogPost()")
			return $http.post(BASE_URL + "/saveBlogPost" , blogPost)
			
		}
	
	
	// to check list of blog_posts requiring admin approval :
		// blogs waiting for approval
			blogPostService.getBlogPostsWaitingForApproval = function() {
				
				console.log("blogpostservice.js - blogPostService.getBlogPostsWaitingForApproval()")
				return $http.get(BASE_URL + "/getBlogPosts/" + 0)
				
			}
			
		// blogs approved by admin 
			blogPostService.getApprovedListOfBlogPosts = function() {
				
				console.log("blogpostservice.js - blogPostService.getApprovedListOfBlogPosts()")
				return $http.get(BASE_URL + "/getBlogPosts/" + 1)
				
			}
			
			
	// to get blog_posts depending on id :
			blogPostService.getBlogPostById = function(id) {
				
				console.log("blogpostservice.js - blogPostService.getBlogPostById(id)")
				return $http.get(BASE_URL + "/getBlogPostById/" + id)
				
			}
		
		
	// to approve blog_post :
			blogPostService.approveBlogPost = function(blogPost) {
				
				console.log("blogpostservice.js - blogPostService.approveBlogPost()") 
				return $http.put(BASE_URL + "/approveBlogPost" , blogPost)
			
			}
			
			
	// to insert and save blog_post comments - user and admin :		
			blogPostService.addBlogComment = function(blogComment) {
				
				console.log("blogpostservice.js - blogPostService.addBlogComment()") 
				return $http.post(BASE_URL + "/addBlogComment" , blogComment)
			
			}
			
			
	// to retrive all blog_post comments depending on blogpost_id :		
			blogPostService.getBlogComments = function(blogPostId) {
				
				console.log("blogpostservice.js - blogPostService.addBlogComment()") 
				return $http.get(BASE_URL + "/getBlogComments/" + blogPostId)
			
			}
			
			
			
	return blogPostService ;	
	
})