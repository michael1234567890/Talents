angular.module('project', ['ngTouch', 'ui.grid', 'ngRoute', 'firebase','datatables', 'project.controller', 'project.service'])
 
 
.config(function($routeProvider) {
  var resolveProjects = {
    projects: function (Projects) {
      return Projects.fetch();
    }
  };
 
  $routeProvider
    .when('/', {
      controller:'ProjectListController as projectList',
      templateUrl:'home.html',
      resolve: resolveProjects
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
    .otherwise({
      redirectTo:'/'
    });
});
 

 
