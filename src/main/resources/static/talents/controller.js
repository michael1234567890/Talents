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
     		$scope.errortext = "The item is already in your shopping list.";
     	}
     	
     };
     
     $scope.removeItem = function(x){
    	var index = $scope.products.indexOf(x);
    	alert("Deleting items : " + index);
     	$scope.products.splice(index,1);
     };

     $scope.editItem = function(x){
    	$scope.current = x;
     };
     
     $scope.save = function(x){
    	 $scope.current = {};
     };
})

/*$scope.showEmployee = function(){
	var id = $routeParams.id;
	$http.get("/hello/users", {'id':id})
	.then(function(result){
		var emp = response.data;
		$scope.employee = emp[0];
	});
}*/

.controller("DetailController", function($scope, $http, $routeParams){
	var id = $routeParams.id;
	$http.get("/hello/users/" +id)
	.then(function(result){
		$scope.userDetails = result.data;
		console.log(result.data);
	});
	
	$scope.updateEmp = function(formdata){		
		if(confirm("Are You Sure You Want TO Update Employee Id : " + formdata.id)){
			$http.put("/hello/users/" +formdata.id, formdata)
			.then(function(){
				alert("successfully Updated");
				GetAll();
			},function(){
				alert("Failed Update")
			});
		}
	};
	
	function GetAll(){
		$http.get("/hello/users")
		.then(function(result){
			$scope.userDetails = result.data;
		});
	};
		
})



.controller("HttpController", function($scope, $http, $routeParams) {
	$http.get("http://jsonplaceholder.typicode.com/posts")
	.then(function(result) {
		$scope.posts = result.data;
    });
	
	$scope.handleChange = function(postId){
		$http.get("http://jsonplaceholder.typicode.com/comments?postId=" + postId)
		.then(function(result){
			$scope.comments = result.data;
		});
	};
	
	$http.get("http://jsonplaceholder.typicode.com/users")
	.then(function(result) {
		$scope.users = result.data;
    })
    .catch(function(result){
    	console.error('post error', result.status, result.data);
    })
	.finally(function(result){
		console.log('finally finished post');
	});
	
	$scope.editedUser = {name:""};
	
	$scope.save = function(u){
		$scope.currentUser.name = u.name;
	};
	
	$scope.edit = function(u){
		$scope.currentUser = u;
		$scope.editedUser.name = u.name;
	};
	
	/*$http.get("/hello/users")
	.then(function(result){
		$scope.user = result.data;
	})
	.catch(function(result){
		console.error('error', result.status, result.data);
	})
	.finally(function(result){
		console.log('finally finished');
	});*/
	
	/*$scope.getEmployees = function(){
		alert("Get employee method invoked");
		$http.get("/hello/users")
		.then(function(result){
			$scope.user = result.data;
		})
		.catch(function(result){
			console.error('error', result.status, result.data);
		})
		.finally(function(result){
			console.log('finally finished');
		});
	};*/
	
	GetAll();
	function GetAll(){
		$http.get("/hello/users")
		.then(function(result){
			$scope.user = result.data;
		});
		/*.catch(function(result){
			console.error('error', result.status, result.data);
		})
		.finally(function(result){
			console.log('finally finished');
		});*/
	}
		
	$scope.addEmployee = function(user){
		var dataPost = {};
		dataPost.username = user.username;
		dataPost.password = user.password;
		dataPost.email = user.email;
		dataPost.firstName = user.firstName;
		dataPost.lastName = user.lastName;
		dataPost.company = user.company;
		
//		alert(user.username);
//		alert(user.password);
//		alert(user.email);
//		alert(user.firstName);
		var data = JSON.stringify(dataPost);
		
		$http.post("/hello/users", data)
		.then(function(response){ 
			alert("successfully inserted");
			GetAll();
		}, 
		function(){
			alert("failed to insert")
		});
	}
	
	$scope.empDelById = function(id){
		if(confirm("Are you sure ?")){
			$http.delete("/hello/users/" +id)
			.then(function(result){
				$scope.colr = "#349d0a";
				$scope.status = "Deleted Successfully";
				GetAll();
			}, function(){
				$scope.colr = "red";
				$scope.status = "Failed to Delete";
			});
		}
		
	}
	
//	$scope.userDetails = { id: "", username: "", password: "", email: "", firstName: "", lastName: ""}
	$scope.empGetById = function(id){
		$http.get("/hello/users/" +id)
		.then(function(result){
			$scope.userDetails = result.data;
			console.log(result.data);
		});
		//alert(id);
	}
	
	$scope.updateEmp = function(formdata){		
		if(confirm("Are You Sure You Want TO Update Employee Id : " + formdata.id)){
			$http.put("/hello/users/" +formdata.id, formdata)
			.then(function(){
				alert("successfully Updated");
				GetAll();
			},function(){
				alert("Failed Update")
			});
		}
		
	}
	
	/*$scope.showEmployee = function(){
		var id = $routeParams.id;
		$http.get("/hello/users", {'id':id})
		.then(function(result){
			var emp = response.data;
			$scope.employee = emp[0];
		});
	}*/
	
	
	$http.get("/hello/company")
	.then(function(result){
		$scope.company = result.data;
	})
	.catch(function(result){
		console.error('error', result.status, result.data);
	})
	.finally(function(result){
		console.log('finally finished');
	});
	
	$http.get("/hello/historysync")
	.then(function(result){
		$scope.historysync = result.data;
	});
	
})

.controller("WorkflowController", function($scope, $http, $routeParams){
	GetAll();
	function GetAll(){
		$http.get("/hello/workflow")
		.then(function(result){
			$scope.workflowDetails = result.data;
		});
	}
	
	$scope.addWorkflow = function(workflow){
		var dataPost = {};
		dataPost.code = workflow.code;
		dataPost.task = workflow.task;
		dataPost.name = workflow.name;
		dataPost.days = workflow.days;
		dataPost.description = workflow.descirption;
		dataPost.operation = workflow.operation;
		dataPost.approvalCodeLevelOne = workflow.approvalCodeLevelOne;
		dataPost.approvalCodeLevelTwo = workflow.approvalCodeLevelTwo;
		dataPost.approvalCodeLevelThree = workflow.approvalCodeLevelThree;
		var data = JSON.stringify(dataPost);
		
		$http.post("/hello/workflow", data)
		.then(function(result){ 
			alert("successfully inserted");
			GetAll();
		}, 
		function(){
			alert("failed to insert")
		});
	}
	
	$scope.delWorkflow = function(id){
		if(confirm("Are you sure ?")){
			$http.delete("/hello/workflow/" +id)
			.then(function(result){
				$scope.colr = "#349d0a";
				$scope.status = "Deleted Successfully";
				GetAll();
			}, function(){
				$scope.colr = "red";
				$scope.status = "Failed to Delete";
			});
		}
		
	}
	
	$scope.workflowGetById = function(id){
		$http.get("/hello/workflow/" +id)
		.then(function(result){
			$scope.workflowDetails = result.data;
			console.log(result.data);
		});
		//alert(id);
	}
	
})

.controller("DetailWorkflowController", function($scope, $http, $routeParams){
	var id = $routeParams.id;
	$http.get("/hello/workflow/" +id)
	.then(function(result){
		$scope.workflowDetails = result.data;
		console.log(result.data);
	});
	
	$scope.updateWorkflow = function(formdata){		
		if(confirm("Are You Sure You Want TO Update Workflow Id : " + formdata.id)){
			$http.put("/hello/workflow/" +formdata.id, formdata)
			.then(function(){
				alert("successfully Updated");
				GetAll();
			},function(){
				alert("Failed Update")
			});
		}
	};
	
	function GetAll(){
		$http.get("/hello/workflow")
		.then(function(result){
			$scope.workflowDetails = result.data;
		});
	};
		
})

.controller("NewsController", function($scope, $http, $routeParams, $location, $rootScope){
	GetAll();
	function GetAll(){
		$http.get("/hello/news")
		.then(function(result){
			$scope.news = result.data;
		});
	}
	
	$scope.addNews = function(news){
		var dataPost = {};
		dataPost.title = news.title;
		dataPost.content = news.content;
		dataPost.publishedDate = news.publishedDate;
		dataPost.endDate = news.endDate;
		dataPost.active = news.active;
		dataPost.company = news.company;
		var data = JSON.stringify(dataPost);
		
		$http.post("/hello/news", data)
		.then(function(result){ 
			alert("successfully inserted");
			GetAll();
		}, 
		function(){
			alert("failed to insert")
		});
	}
	
	$scope.delNews = function(id){
		if(confirm("Are you sure ?")){
			$http.delete("/hello/news/" +id)
			.then(function(result){
				$scope.colr = "#349d0a";
				$scope.status = "Deleted Successfully";
				GetAll();
			}, function(){
				$scope.colr = "red";
				$scope.status = "Failed to Delete";
			});
		}
		
	}
	
	$scope.newsGetById = function(id){
		$http.get("/hello/news/" +id)
		.then(function(result){
			$scope.workflowDetails = result.data;
			console.log(result.data);
		});
		//alert(id);
	}
	
	$scope.submit = function(){
		
		if($scope.username == 'admin' && $scope.password == 'admin'){
			$rootScope.loggedIn = true;
			$location.path('/news');
		}else{
			alert('Wrong Stuff');
		}
	}
	
})

.controller("DetailNewsController", function($scope, $http, $routeParams){
	var id = $routeParams.id;
	$http.get("/hello/news/" +id)
	.then(function(result){
		$scope.newsDetails = result.data;
		console.log(result.data);
	});
	
	$scope.updateNews = function(formdata){		
		if(confirm("Are You Sure You Want TO Update News Id : " + formdata.id)){
			$http.put("/hello/news/" +formdata.id, formdata)
			.then(function(){
				alert("successfully Updated");
				GetAll();
			},function(){
				alert("Failed Update")
			});
		}
	};
	
	function GetAll(){
		$http.get("/hello/news")
		.then(function(result){
			$scope.newsDetails = result.data;
		});
	};
		
})

.controller("HistorySyncController", function($scope, $http, $routeParams){
	GetAll();
	function GetAll(){
		$http.get("/hello/historysync")
		.then(function(result){
			$scope.historysync = result.data;
		});
	}
	
	$scope.addHistorySync = function(historySync){
		var dataPost = {};
		dataPost.moduleName = historySync.moduleName;
		dataPost.lastSync = historySync.lastSync;
		dataPost.company = historySync.company;
		var data = JSON.stringify(dataPost);
		
		$http.post("/hello/historysync", data)
		.then(function(result){ 
			alert("successfully inserted");
			GetAll();
		}, 
		function(){
			alert("failed to insert")
		});
	}
	
	$scope.delHistorySync = function(id){
		if(confirm("Are you sure ?")){
			$http.delete("/hello/historysync/" +id)
			.then(function(result){
				$scope.colr = "#349d0a";
				$scope.status = "Deleted Successfully";
				GetAll();
			}, function(){
				$scope.colr = "red";
				$scope.status = "Failed to Delete";
			});
		}
		
	}
	
	$scope.historySyncGetById = function(id){
		$http.get("/hello/historysync/" +id)
		.then(function(result){
			$scope.historySyncDetails = result.data;
			console.log(result.data);
		});
		//alert(id);
	}
	
})

.controller("DetailHistorySyncController", function($scope, $http, $routeParams){
	var id = $routeParams.id;
	$http.get("/hello/historysync/" +id)
	.then(function(result){
		$scope.historySyncDetails = result.data;
		console.log(result.data);
	});
	
	$scope.updateHistorySync = function(formdata){		
		if(confirm("Are You Sure You Want TO Update History Sync Id : " + formdata.id)){
			$http.put("/hello/historysync/" +formdata.id, formdata)
			.then(function(){
				alert("successfully Updated");
				GetAll();
			},function(){
				alert("Failed Update")
			});
		}
	};
	
	function GetAll(){
		$http.get("/hello/historysync")
		.then(function(result){
			$scope.historySyncDetails = result.data;
		});
	};
	
})

.controller("CompanySettingController", function($scope, $http, $routeParams){
	GetAll();
	function GetAll(){
		$http.get("/hello/companysetting")
		.then(function(result){
			$scope.companysetting = result.data;
		});
	};
	
	$scope.addCompanySettings = function(companysetting){
		var dataPost = {};
		dataPost.company = companysetting.company;
		dataPost.payslipDisclaimer = companysetting.payslipDisclaimer;
		dataPost.isRegexPasswordActive = companysetting.isRegexPasswordActive;
		dataPost.regexPassword = companysetting.regexPassword;
		dataPost.msgErrorRegexPassword = companysetting.msgErrorRegexPassword;
		
		var data = JSON.stringify(dataPost);
		
		$http.post("/hello/companysetting", data)
		.then(function(result){
			alert("successfully inserted");
			GetAll();
		},
		function(result){
			alert("failed to insert");
		});
	};
	
	$scope.delCompanysetting = function(id){
		if(confirm("Are you sure ?")){
			$http.delete("/hello/companysetting/" +id)
			.then(function(result){
				$scope.colr = "#349d0a";
				$scope.status = "Deleted Successfully";
				GetAll();
			},
			function(result){
				$scope.colr = "red";
				$scope.status = "Failed to Delete";
			});
		};
	};
	
	$scope.getById = function(id){
		$http.get("/hello/companysetting/" +id)
		.then(function(result){
			$scope.companysettingDetails = result.data;
		});
	};
})

.controller("DetailCompanySettingController", function($scope, $http, $routeParams){
	var id = $routeParams.id;
	$http.get("/hello/companysetting/" +id)
	.then(function(result){
		$scope.companysettingDetails = result.data;
	});
	
	
	$scope.updateCompanysetting = function(formdata){
		if(confirm("Are You Sure You Want TO Update Setting Id : " + formdata.id)){
			$http.put("/hello/companysetting/" +formdata.id, formdata)
			.then(function(result){
				alert("successfully updated");
				GetAll();
			},
			function(result){
				alert("failed to update");
			});
		}
			
	};
	
	
	function GetAll(){
		$http.get("/hello/companysetting")
		.then(function(result){
			$scope.companysettingDetails = result.data;
		});
	};
	
})

.controller("CompanyReferenceController", function($scope, $http, $routeParams){
	GetAll();
	function GetAll(){
		$http.get("/hello/companyreference")
		.then(function(result){
			$scope.companyreference = result.data;
		});
	};
	
	$scope.addCompanyReference = function(companyreference){
		var dataPost = {};
		dataPost.category = companyreference.category;
		dataPost.subCategory = companyreference.subCategory;
		dataPost.field = companyreference.field;
		dataPost.value = companyreference.value;
		dataPost.company = companyreference.company;
		
		var data = JSON.stringify(dataPost);
		
		$http.post("hello/companyreference", data)
		.then(function(result){
			alert("successfully inserted");
			GetAll();
		},
		function(result){
			alert("failed to insert");
		});
	};
	
	$scope.delCompanyReference = function(id){
		if(confirm("Are you sure ?")){
			$http.delete("/hello/companyreference/" +id)
			.then(function(result){
				$scope.colr = "#349d0a";
				$scope.status = "Deleted Successfully";
				GetAll();
			},
			function(result){
				$scope.colr = "red";
				$scope.status = "Failed to Delete";
			});
		};
	};
	
	$scope.getById = function(id){
		$http.get("/hello/companyreference/" +id)
		.then(function(result){
			$scope.companyreferenceDetail = result.data;
		});
	};
	
})

.controller("DetailCompanyReferenceController", function($scope, $http, $routeParams){
	var id = $routeParams.id;
	$http.get("/hello/companyreference/" +id)
	.then(function(result){
		$scope.companyreferenceDetails = result.data;
	});
	
	$scope.updateCompanyReference = function(formdata){
		if(confirm("Are You Sure You Want TO Update Setting Id : " + formdata.id)){
			$http.put("hello/companyreference/" +formdata.id, formdata)
			.then(function(result){
				alert("successfully updated");
				GetAll();
			},
			function(result){
				alert("failed to update");
			});
		};
	};
	
	function GetAll(){
		$http.get("/hello/companyreference")
		.then(function(result){
			$scope.companyreferenceDetails = result.data;
		});
	};
	
})

.controller("ConnectedAppController", function($scope, $http, $routeParams){
	GetAll();
	function GetAll(){
		$http.get("/hello/connectedapp")
		.then(function(result){
			$scope.connectedapp = result.data;
		});
	};
	
	GetCompany();
	function GetCompany(){
		$http.get("/hello/company")
		.then(function(result){
			$scope.company = result.data;
		});
	};
	
	$scope.addConnectedApp = function(connectedapp){
		var dataPost = {};
		dataPost.name = connectedapp.name;
		dataPost.instanceUrl = connectedapp.instanceUrl;
		dataPost.loginUrl = connectedapp.loginUrl;
		dataPost.username = connectedapp.username;
		dataPost.password = connectedapp.password;
		dataPost.securityToken = connectedapp.securityToken;
		dataPost.consumerKey = connectedapp.consumerKey;
		dataPost.consumerSecret = connectedapp.consumerSecret;
		dataPost.company = connectedapp.company;
		
		var data = JSON.stringify(dataPost);
		
		$http.post("hello/connectedapp", data)
		.then(function(result){
			alert("successfully inserted");
			GetAll();
		},
		function(result){
			alert("failed to insert");
		});
	};
	
	$scope.delConnectedApp = function(id){
		if(confirm("Are you sure ?")){
			$http.delete("/hello/connectedapp/" +id)
			.then(function(result){
				$scope.colr = "#349d0a";
				$scope.status = "Deleted Successfully";
				GetAll();
			},
			function(result){
				$scope.colr = "red";
				$scope.status = "Failed to Delete";
			});
		};
	};
	
	$scope.getById = function(id){
		$http.get("hello/connectedapp/" +id)
		.then(function(result){
			$scope.connectedappDetail = result.data;
		});
	};
	
})

.controller("DetailConnectedAppController", function($scope, $http, $routeParams){
	var id = $routeParams.id;
	$http.get("hello/connectedapp/" +id)
	.then(function(result){
		$scope.connectedappDetails = result.data;
	});
	
	GetCompany();
	function GetCompany(){
		$http.get("/hello/company")
		.then(function(result){
			$scope.companies = result.data;
		});
	};
	
	$scope.updateConnectedApp = function(formdata){
		if(confirm("Are You Sure You Want TO Update Setting Id : " + formdata.id)){
			$http.put("hello/connectedapp/" +formdata.id, formdata)
			.then(function(result){
				alert("successfully updated");
				GetAll();
			},
			function(result){
				alert("failed to update");
			});
		};
	};
	
	function GetAll(){
		$http.get("/hello/connectedapp")
		.then(function(result){
			$scope.connectedappDetails = result.data;
		});
	};
})

.controller("ChangePasswordController", function($scope, $http, $routeParams, $location){
	$scope.changePassword = function(password){
		var dataPost = {};
		dataPost.newPassword = password.newPassword;
		dataPost.confirmPassword = password.confirmPassword;
		dataPost.token = document.getElementById("tokentoken").innerHTML;
		dataPost.userId = document.getElementById("userId").innerHTML;
		var data = JSON.stringify(dataPost);
		
		$http.post("/api/actionresetpassword", data)
		.then(function(result){
			alert("success");
		},
		function(){
			alert("failed");
		});
	}
})

.controller("loginController", function($scope, $location, $rootScope){
	$scope.submit = function(){
	
		if($scope.username == 'admin' && $scope.password == 'admin'){
			$rootScope.loggedIn = true;
			$location.path('/dashboard');
		}else{
			alert('Wrong Stuff');
		}
	}
})




