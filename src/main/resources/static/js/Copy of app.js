angular.module("myApp", ['ngRoute'])

.config(function($stateProvider, $urlRouterProvider){
	
	$stateProvider

	.state('test', {
		url: '/test',
		abstract: true,
		templateUrl: 'templates/pages-login.html'
	})

	.state('login', {
		url: '/login',
		views: {
			'login': {
				templateUrl: 'templates/pages-login.html'
			}
		}
	})

	.state('welcome', {
		url: '/welcome',
		views: {
			'welcome': {
				templateUrl: 'templates/index.html'
			}
		}
	})

	.state('registration', {
		url: '/registration',
		views: {
			'registration': {
				templateUrl: 'templates/registration.html'
			}
		}
	})



	$urlRouterProvider.otherwise('/login');

});




	