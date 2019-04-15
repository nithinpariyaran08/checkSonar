var app = angular.module("userCrudApp", []);

app.value('urls', {
	BASE : 'http://localhost:9999/projectmanager',
	ADD_USER_SERVICE_API : '/api/v1/addUser',
	GET_ALL_USER_SERVICE_API : '/api/v1/getAllUsers',
	UPDATE_USER_SERVICE_API : '/api/v1/updateUser/',
	DELETE_USER_SERVICE_API : '/api/v1/deleteUser/'
});


app.controller("UserController", ['$scope','$http','urls',function($scope, $http,urls) {
	 console.log(urls.BASE);
	$scope.users = []
	
	var self = this;
    self.user = {};
    
    loadAllUsers();
	console.log("inside the method");
	$scope.submitUser = function() {
		console.log("inside the submituser");
		var method = "";
		var url = "";
			method = "POST";
			url : urls.BASE + urls.ADD_USER_SERVICE_API
			console.log("inside the submituser for add user");
			console.log(urls.BASE + urls.ADD_USER_SERVICE_API);
			console.log($scope.user);
			
			
			
			if ($scope.user.empId === undefined) {
				console.log("inside the add user");
	            method = "POST";
	            url : urls.BASE + urls.ADD_USER_SERVICE_API
	            $http.post(urls.BASE+urls.ADD_USER_SERVICE_API, $scope.user)
	            .then(
	                function (response) {
	               	 loadAllUsers();
	                   
	                },
	                function (errResponse) {
	                   console.error('Error while creating project : '+errResponse.data.errorMessage);
	                
	                }
	            );
	        } else {
	            method = "PUT";
	            console.log("inside the update user");
	            url : urls.BASE + urls.UPDATE_USER_SERVICE_API
	            $http.post( urls.BASE + urls.UPDATE_USER_SERVICE_API, $scope.user)
	            .then(
	                function (response) {
	                	loadAllUsers();
	                   
	                },
	                function (errResponse) {
	                   console.error('Error while creating project : '+errResponse.data.errorMessage);
	                
	                }
	            );
	        }
			
			
/*			$http.post(urls.BASE+urls.ADD_USER_SERVICE_API, $scope.user)
             .then(
                 function (response) {
                	 loadAllUsers();
                    
                 },
                 function (errResponse) {
                    console.error('Error while creating user : '+errResponse.data.errorMessage);
                 
                 }
             );*/

	};

	$scope.createEmployee = function() {
		_clearFormData();
	}
	
	
	function _success(res) {
		loadAllUsers();
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
		$scope.myForm.$setPristine(); //reset Form
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


//In case of edit
$scope.editUser = function(user) {
	console.log("inside the edit user");
	console.log(user);
	console.log(user.empId);
	console.log($scope.user.empId);
	$scope.user.empId = user.empId;
	$scope.user.name = user.name;
    $scope.user.dateOfBirth = new Date(user.dateOfBirth);
  
};

$scope.deleteUser = function(user) {
	console.log("inside the delete user");
	  $http.delete(urls.BASE + urls.DELETE_TASK_SERVICE_API+user.empId)
	   .then(function (response) {
	     oadAllUsers();
	      },
	     function (errResponse) {
	     console.error('Error while removing user with id :'+user.empId);
	     }
	     )};



}]);