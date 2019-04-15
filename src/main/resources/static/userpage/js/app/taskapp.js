var app = angular.module("taskCrudApp", []);

app.value('urls', {
	BASE : 'http://localhost:9999/projectmanager',
	ADD_TASK_SERVICE_API : '/api/v1/addTask',
	UPDATE_TASK_SERVICE_API : '/api/v1/updateTask',
	GET_ALL_TASKS : '/api/v1/getAllTasks',
	GET_ALL_PARENT_TASKS : '/api/v1/getAllParentTasks',
	GET_ALL_PROJECTS : '/api/v1/getAllProjects/',
	GET_ALL_USER_SERVICE_API : '/api/v1/getAllUsers',
	DELETE_TASK_SERVICE_API : '/api/v1/deleteTask/'
});


app.controller("TaskController", ['$scope','$http','urls',function($scope, $http,urls) {
	 console.log(urls.BASE);
	$scope.users = []
	$scope.projects = []
	$scope.tasks = []
	
	var self = this;
    self.user = {};
	
	
    loadAllUsers();
    loadAllProjects();
    loadAllTasks();
    loadAllParentTasks();
	console.log("inside the method");
	$scope.submitTask = function() {
		console.log("inside the submit task");
		var method = "";
		var url = "";
		console.log($scope.individualTask.id);
		if ($scope.individualTask.id === undefined) {
			console.log("inside the add task");
            method = "POST";
            url : urls.BASE + urls.ADD_TASK_SERVICE_API
            $http.post(urls.BASE+urls.ADD_TASK_SERVICE_API, $scope.individualTask)
            .then(
                function (response) {
               	 loadAllUsers();
               	loadAllTasks();
                   
                },
                function (errResponse) {
                   console.error('Error while creating project : '+errResponse.data.errorMessage);
                
                }
            );
        } else {
            method = "PUT";
            console.log("inside the update user");
            url : urls.BASE + urls.UPDATE_TASK_SERVICE_API
            $http.post( urls.BASE + urls.UPDATE_TASK_SERVICE_API, $scope.individualTask)
            .then(
                function (response) {
                	loadAllTasks();
                   
                },
                function (errResponse) {
                   console.error('Error while creating project : '+errResponse.data.errorMessage);
                
                }
            );
        }

			//url : urls.BASE + urls.ADD_PROJECT_SERVICE_API
			console.log("inside the submitproject for add task");
			console.log(urls.BASE + urls.ADD_TASK_SERVICE_API);
			console.log($scope.individualTask);

	};

	$scope.createProject = function() {
		_clearFormData();
	}
	
	
	function _success(res) {
		loadAllTasks();
        _clearFormData();
    }
	
	 function _error(res) {
	        var data = res.data;
	        var status = res.status;
	        var header = res.header;
	        var config = res.config;
	        alert("Error: " + status + ":" + data);
	    }

	function reset() {
		self.successMessage = '';
		self.errorMessage = '';
		self.task = {};
		$scope.projForm.$setPristine(); //reset Form
	}

function loadAllTasks() {
		console.log("inside the _refreshAllTasks");
		$http({
			method : 'GET',
			url : urls.BASE + urls.GET_ALL_TASKS
		}).then(function(res) { // success
			$scope.individualTasks = res.data;
		}, function(res) { // error
			console.log("Error: no data"); 
		});
	}


function loadAllParentTasks() {
	console.log("inside the _refreshAllParentTasks");
	$http({
		method : 'GET',
		url : urls.BASE + urls.GET_ALL_PARENT_TASKS
	}).then(function(res) { // success
		$scope.parentTasks = res.data;
		console.log($scope.parentTasks);
	}, function(res) { // error
		console.log("Error: no data"); 
	});
}

function loadAllUsers() {
	console.log("inside the _refreshUserData");
	$http({
		method : 'GET',
		url : urls.BASE + urls.GET_ALL_USER_SERVICE_API
	}).then(function(res) { // success
		$scope.users = res.data;
	}, function(res) { // error
		console.log("Error: no data"); 
	});
}


function loadAllProjects() {
	console.log("inside the _refreshProjectData");
	$http({
		method : 'GET',
		url : urls.BASE + urls.GET_ALL_PROJECTS
	}).then(function(res) { // success
		$scope.projects = res.data;
	}, function(res) { // error
		console.log("Error: no data"); 
	});
}


// In case of edit
$scope.editIndividualTask = function(individualTask) {
	console.log("inside the edit individualTask");
	console.log(individualTask);
	$scope.individualTask.id = individualTask.id;
    $scope.individualTask.taskName = individualTask.taskName;
    $scope.individualTask.priority = individualTask.priority;
    $scope.individualTask.startDate = new Date(individualTask.startDate)
    $scope.individualTask.endDate = new Date(individualTask.endDate) 
  
};

$scope.deleteIndividualTask = function(individualTask) {
	console.log("inside the delete task");
	  $http.delete(urls.BASE + urls.DELETE_TASK_SERVICE_API+individualTask.id)
	   .then(function (response) {
	     loadAllTasks();
	      },
	     function (errResponse) {
	     console.error('Error while removing user with id :'+individualTask.id);
	     })
};



function loadActions() {
	console.log("inside the _refreshActions");
	$http({
		method : 'GET',
		url : urls.BASE + urls.GET_ALL_PROJECTS
	}).then(function(res) { // success
		$scope.projects = res.data;
	}, function(res) { // error
		console.log("Error: no data"); 
	});
}

}]);