/**
 * Chat Service
 */

console.log("chatservice.js loaded .... ")


app.filter('reverse' , function() {
	
	return function(items) {
		
		console.log("chatservice.js - app.filter - reverse() .... ")
		return items.slice().reverse() ;
		
	} ;
}) ;


app.directive('ngFocus' , function() {
	
	console.log("chatservice.js - app.directive - ngFocus() .... ")
	return function(scope , element , attrs) {
	
		element.bind('click' , function() {
	
			console.log("chatservice.js - app.directive - element.bind() .... ")
			$('.' + attrs.ngFocus)[0].focus() ;
			
		}) ;
	} ;
}) ;


app.factory('socket' , function($rootScope) {

	alert('Entering chat app factory .... ')
	
	var socket = new SockJS('/StuforexBackend/portfolio') ;	// from register STOMP end point - websocketconfiguration.java
	var stompClient = Stomp.over(socket) ;
	
	stompClient.connect('' , '' , function(frame) {
		
		console.log("chatservice.js - app.factory - stompClient() - connected .... ")
		
		$rootScope.$broadcast('sockConnected' , frame) ;
		
	}) ;
	
	return {
		
		stompClient : stompClient
		
	} ;
	
	
}) ;