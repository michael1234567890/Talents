angular.module('project.controller', [])

.controller('ProjectListController', function(projects) {
	console.log("ProjectListController");
  var projectList = this;
  projectList.projects = projects;
})

.controller('CompanyController', function($scope,DTOptionsBuilder) {
	console.log("CompanyController");
	  $scope.dtOptions = DTOptionsBuilder.newOptions()
	  .withDisplayLength(10)
	  .withOption('bLengthChange', false);
	  $scope.projects = [
	                          	  {
	                         	    name: 'AngularJS',
	                         	    site: 'http://angularjs.org',
	                         	    description: 'HTML enhanced for web apps!'
	                         	  },
	                         	  {
	                         	    name: 'Angular',
	                         	    site: 'http://angular.io',
	                         	    description: 'One framework. Mobile and desktop.'
	                         	  },
	                         	  {
	                         	    name: 'AngularJS',
	                         	    site: 'http://angularjs.org',
	                         	    description: 'HTML enhanced for web apps!'
		                          },
		                          {
	                         	    name: 'Angular',
	                         	    site: 'http://angular.io',
	                         	    description: 'One framework. Mobile and desktop.'
		                          },
		                          {
	                         	    name: 'AngularJS',
	                         	    site: 'http://angularjs.org',
	                         	    description: 'HTML enhanced for web apps!'
			                      },
	                         	  {
	                         	    name: 'Angular',
	                         	    site: 'http://angular.io',
	                         	    description: 'One framework. Mobile and desktop.'
	                         	  },
			                      {
	                         	    name: 'AngularJS',
	                         	    site: 'http://angularjs.org',
	                         	    description: 'HTML enhanced for web apps!'
	                         	  },
	                         	  {
	                         	    name: 'Angular',
	                         	    site: 'http://angular.io',
	                         	    description: 'One framework. Mobile and desktop.'
	                         	  },
				                  {
	                         	    name: 'AngularJS',
	                         	    site: 'http://angularjs.org',
	                         	    description: 'HTML enhanced for web apps!'
	                         	  },
	                         	  {
	                         	    name: 'Angular',
	                         	    site: 'http://angular.io',
	                         	    description: 'One framework. Mobile and desktop.'
	                         	  },
					              {
	                         	    name: 'AngularJS',
	                         	    site: 'http://angularjs.org',
	                         	    description: 'HTML enhanced for web apps!'
	                         	  },
	                         	  {
	                         	    name: 'Angular',
	                         	    site: 'http://angular.io',
	                         	    description: 'One framework. Mobile and desktop.'
	                         	  }	
	                         ];
	      
	
})

.controller('AboutController', function() {
  console.log('AboutController');
})

.controller('ListController', function(projects,DTOptionsBuilder,$scope) {
  console.log('RoleController 1');
 
  
  var projectList = this;
  projectList.dtOptions = DTOptionsBuilder.newOptions()
  .withDisplayLength(10)
  .withOption('bLengthChange', false);
  
  // projectList.projects = projects;
  projectList.projects = [
                     {
                    	    name: 'AngularJS',
                    	    site: 'http://angularjs.org',
                    	    description: 'HTML enhanced for web apps!'
                    	  },
                    	  {
                    	    name: 'Angular',
                    	    site: 'http://angular.io',
                    	    description: 'One framework. Mobile and desktop.'
                    	  }
                    ];
 
  
})
 
.controller('NewProjectController', function($location, projects) {
  var editProject = this;
  editProject.save = function() {
      projects.$add(editProject.project).then(function(data) {
          $location.path('/');
      });
  };
})

.controller('EditProjectController',
  function($location, $routeParams, projects) {
    var editProject = this;
    var projectId = $routeParams.projectId,
        projectIndex;
 
    editProject.projects = projects;
    projectIndex = editProject.projects.$indexFor(projectId);
    editProject.project = editProject.projects[projectIndex];
 
    editProject.destroy = function() {
        editProject.projects.$remove(editProject.project).then(function(data) {
            $location.path('/');
        });
    };
 
    editProject.save = function() {
        editProject.projects.$save(editProject.project).then(function(data) {
           $location.path('/');
        });
    };
})

.controller('GridController', function($scope) {
	$scope.myData = [
         {
             firstName: 'Cox',
             lastName: 'Carney',
             company: 'Enormo',
             employed: true
         },
         {
             firstName: 'Lorraine',
             lastName: 'Wise',
             company: 'Comveyer',
             employed: false
         },
         {
             firstName: 'Nancy',
             lastName: 'Waters',
             company: 'Fuelton',
             employed: false
         }
     ];

     $scope.products = ["Bread", "Milk", "Cheese"];
     $scope.addItem = function(){
     	$scope.errortext = "";
     	if($scope.products.indexOf($scope.addMe) == -1){
     		$scope.products.push($scope.addMe);	
     	}else{
     		$scope.errortext = "The item is already in your shopping list."
     	}
     	
     }
     $scope.removeItem = function(x){
     	$scope.products.splice(x,1);
     }
});