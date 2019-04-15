var app = angular.module("projCrudApp", []);

app.value('urls', {
	BASE : 'http://localhost:9999/projectmanager',
	ADD_PROJECT_SERVICE_API : '/api/v1/addProject',
	UPDATE_PROJECT_SERVICE_API : '/api/v1/updateProject',
	GET_ALL_USER_SERVICE_API : '/api/v1/getAllUsers',
	GET_ALL_PROJECTS : '/api/v1/getAllProjects/',
	DELETE_PROJECT_SERVICE_API : '/api/v1/deleteProject/'
});


app.controller("ProjController", ['$scope','$http','urls',function($scope, $http,urls) {
	 console.log(urls.BASE);
	$scope.users = []
	$scope.projects = []
	
	var self = this;
    self.user = {};
	
	
    loadAllUsers();
    loadAllProjects();
	console.log("inside the method");
	$scope.submitProject = function() {
		console.log("inside the submitproject");
		var method = "";
		var url = "";
		console.log($scope.project.projectId);
		if ($scope.project.projectId === undefined) {
			console.log("inside the add project");
            method = "POST";
            url : urls.BASE + urls.ADD_PROJECT_SERVICE_API
            $http.post(urls.BASE+urls.ADD_PROJECT_SERVICE_API, $scope.project)
            .then(
                function (response) {
               	 loadAllProjects();
                   
                },
                function (errResponse) {
                   console.error('Error while creating project : '+errResponse.data.errorMessage);
                
                }
            );
        } else {
            method = "PUT";
            console.log("inside the update project");
            url : urls.BASE + urls.UPDATE_PROJECT_SERVICE_API
            $http.post( urls.BASE + urls.UPDATE_PROJECT_SERVICE_API, $scope.project)
            .then(
                function (response) {
               	 loadAllProjects();
                   
                },
                function (errResponse) {
                   console.error('Error while creating project : '+errResponse.data.errorMessage);
                
                }
            );
        }

			//url : urls.BASE + urls.ADD_PROJECT_SERVICE_API
			console.log("inside the submitproject for add project");
			console.log(urls.BASE + urls.ADD_PROJECT_SERVICE_API);
			console.log($scope.project);
/*			
			$http.post(urls.BASE+urls.ADD_PROJECT_SERVICE_API, $scope.project)
             .then(
                 function (response) {
                	 loadAllProjects();
                    
                 },
                 function (errResponse) {
                    console.error('Error while creating project : '+errResponse.data.errorMessage);
                 
                 }
             );*/

	};

	$scope.createProject = function() {
		_clearFormData();
	}
	
	
	function _success(res) {
		loadAllProjects();
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



// In case of edit
$scope.editProject = function(project) {
	console.log("inside the edit project");
	console.log(project);
	$scope.project.projectId = project.projectId;
    $scope.project.projectName = project.projectName;
    $scope.project.projectPriority = project.projectPriority;
    $scope.project.projectStatus= project.projectStatus;
    $scope.project.projectStartDate = new Date(project.projectStartDate)
    $scope.project.projectEndDate = new Date(project.projectEndDate) 
    $scope.project.userDTO = project.project.userDTO
    console.log($scope.projectForm);
  
};

$scope.deleteProject = function(project) {
	console.log("inside the delete project");
	  $http.delete(urls.BASE + urls.DELETE_PROJECT_SERVICE_API+project.projectId)
	   .then(function (response) {
	     loadAllProjects();
	      },
	     function (errResponse) {
	     console.error('Error while removing user with id :'+project.projectId);
	     })
};


}]);