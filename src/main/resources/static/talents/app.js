angular.module('project', ['ngTouch', 'ngRoute', 'firebase', 'project.controller'])
 
 
.config(function($routeProvider) {
  var resolveProjects = {
    projects: function (Projects) {
      return Projects.fetch();
    }
  };
 
  $routeProvider
    .when('/', {
      controller:'loginController',
      templateUrl:'home.html',
      resolve: resolveProjects
    })
    .when('/dashboard', {
    	resolve: {
    		"check": function($location, $rootScope){
    			if(!$rootScope.loggedIn){
    				$location.path('/login');
    			}
    		}
    	},
    	controller:'loginController',
    	templateUrl: 'change-password.html'
    })
    .when('/edit/:projectId', {
      controller:'EditProjectController as editProject',
      templateUrl:'detail.html',
      resolve: resolveProjects
    })
    .when('/about', {
      controller:'AboutController',
      templateUrl:'detail.html'
    })
    .when('/company', {
      controller:'CompanyController',
      templateUrl:'company.html'
    })
    .when('/user', {
      controller:'UserController',
      templateUrl:'user.html'
    })
     .when('/list', {
      controller:'ListController as projectList',
      templateUrl:'list.html',
      resolve: resolveProjects
    })
    .when('/new', {
      controller:'NewProjectController as editProject',
      templateUrl:'detail.html',
      resolve: resolveProjects
    })
    .when('/grid', {
      controller:'GridController',
      templateUrl: 'ui-grid.html'
    })
    .when('/test', {
    	controller:'HttpController',
    	templateUrl:'test.html'
    })
    .when('/employees', {
    	controller: 'HttpController',
    	templateUrl: 'lists.html'
    })
    .when('/employees/create', {
		templateUrl:'create.html',
		controller:'HttpController'
	})
	.when('/employees/:id/edit', {
		templateUrl: 'edit.html',
		controller: 'DetailController'
	})
	.when('/workflow', {
		templateUrl: 'workflow.html',
		controller: 'WorkflowController'
	})
	.when('/workflow/create', {
		templateUrl: 'create-workflow.html',
		controller: 'WorkflowController'
	})
	.when('/workflow/:id/edit', {
		templateUrl: 'edit-workflow.html',
		controller : 'DetailWorkflowController'
	})
	.when('/companysetting', {
		templateUrl: 'company-setting.html',
		controller: 'CompanySettingController'
	})
	.when('/companysetting/create', {
		templateUrl: 'create-company-setting.html',
		controller: 'CompanySettingController'
	})
	.when('/companysetting/:id/edit', {
		templateUrl: 'edit-company-setting.html',
		controller: 'DetailCompanySettingController'
	})
	.when('/news', {
		templateUrl: 'news.html',
		controller: 'NewsController'
	})
	.when('/news/create', {
		templateUrl: 'create-news.html',
		controller : 'NewsController'
	})
	.when('/news/:id/edit', {
		templateUrl: 'edit-news.html',
		controller: 'DetailNewsController'
	})
	.when('/historysync', {
		templateUrl: 'historysync.html',
		controller: 'HistorySyncController'
	})
	.when('/historySync/create', {
		templateUrl: 'create-historysync.html',
		controller: 'HistorySyncController'
	})
	.when('/historysync/:id/edit', {
		templateUrl: 'edit-historysync.html',
		controller: 'DetailHistorySyncController'
	})
	.when('/changepassword', {
		templateUrl: 'change-password.html',
		controller: 'ChangePasswordController'
	})
	.when('/companyreference', {
		templateUrl: 'company-reference.html',
		controller: 'CompanyReferenceController'
	})
	.when('/companyreference/create', {
		templateUrl: 'create-company-reference.html',
		controller: 'CompanyReferenceController'
	})
	.when('/companyreference/:id/edit', {
		templateUrl: 'edit-company-reference.html',
		controller: 'DetailCompanyReferenceController'
	})
	.when('/connectedapp', {
		templateUrl: 'connected-app.html',
		controller: 'ConnectedAppController'
	})
	.when('/connectedapp/create', {
		templateUrl: 'create-conntected-app.html',
		controller: 'ConnectedAppController'
	})
	.when('/connectedapp/:id/edit', {
		templateUrl: 'edit-connected-app.html',
		controller: 'DetailConnectedAppController'
	})
    .otherwise({
      redirectTo:'/'
    });
});
 

 
