/**
 * ChatController
 */

console.log("chatcontroller.js loaded .... ")

app.controller('ChatController' , ['$rootScope' , 'socket' , '$scope' , function($rootScope , socket , $scope) {
	
	alert('Entering Chat Controller .... ')
	
	$scope.chats = [] ;
	
	$scope.stompClient = socket.stompClient ;
	
	$scope.users = [] ;
	
	$scope.$on('sockConnected' , function(event , frame) {
		
		alert('sockConnected .... ')
		
		$scope.userName = $rootScope.currentUser.username ;
		
	//	console.log("Username - chatcontroller.js : " + user)
		
		$scope.stompClient.subscribe("/topic/join" , function(message) {
			
			
			user = JSON.parse(message.body) ;
			console.log("chatcontroller.js - stompClientSubscribe - new user added - topic/join : " + user)
			
			/*
			 *	if user is already in users array or name of the user is same as logged in username 
			 *						then no need to add that user in users
			 */
			if(user != $scope.userName && $.inArray(user , $scope.users) == -1) {
				
				$scope.addUser(user) ;
				$scope.latestUser = user ;
				$scope.$apply() ;
				$('#joinedChat').fadeIn(120).delay(5000).fadeOut(200) ;
				
			}
			
		}) ;
		
		
		/*
		
		$scope.addUser(user) {
			$scope.users.push(user) ;
			$scope.$apply()
		}
		
		*/
		
		//////
		
		$scope.addUser = function(user) {
			
			$scope.users.push(user) ;
			
			$scope.$apply() ;
		} ;
		
		
		//////
		
		/*
		 *	when new user joins the chatroom , username will be sent to the sockcontroller
		 *	list of users - callback function
		 *	message.body = list of users
		 *
		 *	new user joins the chat room , username has to be sent to the application
		 *		receive list of users already there in the chat room
		 */
		$scope.stompClient.subscribe('/app/join/'+$scope.userName , function(message) {
			
			$scope.users = JSON.parse(message.body) ;
			console.log("chatcontroller.js - stompClientSubscribe - new user added - app/join : " + message)
			$scope.$apply() ;
			
		}) ;
		
	}) ;
	
	
	
	/*
	 * message is from client to controller
	 */
	$scope.sendMessage = function(chat) {
		
		chat.from = $scope.userName ;
		
		$scope.stompClient.send("/app/chat" , {} , JSON.stringify(chat)) ;
		$rootScope.$broadcast('sendingChat' , chat) ;
		$scope.chat.message = '' ;
		
	} ;
	
	
//	$rootScope.capitalize = function(str) {
	$scope.capitalize = function(str) {	
		return str.charAt(0).toUpperCase() + str.slice(1) ;
		
	} ;
	
	///
	/*
	$scope.addUser = function(user) {
		
		$scope.users.push(user) ;
		
		$scope.$apply() ;
	} ;
	*/
	///
	
	$scope.$on('sockConnected' , function(event , frame) {
		
		$scope.userName = $rootScope.currentUser.username ;
		
	//	$scope.user = $rootScope.currentUser.username ;
		
		/*
		
		$scope.stompClient.subscribe("/queue/chats" + queueSuffix , function(message) {
			
				alert('Queue Suffix : ' + queueSuffix)
				alert(message)
			
			$scope.processIncommingMessage(message , false) ;	
			
		}) ;
		
		*/
		
		
		
		/* private chat */
		$scope.stompClient.subscribe("/queue/chats/" + $scope.userName , function(message) {
			
			//$scope.processIncommingMessage(message , false) ;
			
			console.log("chatcontroller.js - stompClientSubscribe - queue/chats - private chat - before : " + message)
			console.log("chatcontroller.js - stompClientSubscribe - queue/chats - private chat - before2 : " + $scope.userName)
			
			$scope.processIncommingMessage(message , false) ;
			
			console.log("chatcontroller.js - stompClientSubscribe - queue/chats - private chat - after : " + message)
			
		}) ;
		
		
		
		
		/* group chats */
		$scope.stompClient.subscribe("/queue/chats" , function(message) {
			
			$scope.processIncommingMessage(message , true) ;
			console.log("chatcontroller.js - stompClientSubscribe - queue/chats - group chat : " + message)
			
		}) ;
		
		
	}) ;
	
	
	
	$scope.$on('sendingChat' , function(event , sentChat) {
		
		chat = angular.copy(sentChat) ;
		chat.from = 'Me' ;
		chat.direction = 'outgoing' ;
		
		$scope.addChat(chat) ;
		
	}) ;
	
	
	$scope.processIncommingMessage = function(message , isBroadcast) {
		
		console.log("chatcontroller.js - Broadcast chat : ")
		console.log(isBroadcast)
		message = JSON.parse(message.body) ;
		message.direction = 'incomming' ;
		
		if(message.from != $scope.userName) {
			
			$scope.addChat(message) ;
			$scope.$apply() ; // since inside subscribe closure
			
		}
		
	} ;
	
	
	$scope.addChat = function(chat) {
		
		$scope.chats.push(chat) ;
		
	} ;
	
	
}]) ;