'use strict';
angular.module('userCrudApp').controller('UserController',
	    ['UserService', '$scope',  function( UserService, $scope) {
	  
	    	var self = this;
	        self.user = {};
	        self.users=[];
	 
	        self.submit = submit;
	        self.getAllUsers = getAllUsers;
	        self.createUser = createUser;
	      /*  self.updateUser = updateUser;
	        self.removeUser = removeUser;
	        self.editUser = editUser;
	        self.endTask= endTask;
	        self.reset = reset;*/
	 
	        self.successMessage = '';
	        self.errorMessage = '';
	        self.done = false;
	        function submit() {
	            console.log('Submitting');
	            if (self.user === undefined || self.user === null) {
	                console.log('Saving New Task', self.user);
	                createUser(self.user);
	            } else {
	            	console.log('updating');
	            	updateUser(self.user);
	                console.log('Task updated with id ',self.task);
	            }
	        }
	        
	        
	        function createUser(user) {
	            console.log('About to create user');
	            TaskService.createUser(user)
	                .then(
	                    function (response) {
	                        console.log('User created successfully');
	                        self.successMessage = 'User created successfully';
	                        self.errorMessage='';
	                        self.done = true;
	                        self.user={};
	                        $scope.myForm.$setPristine();
	                    },
	                    function (errResponse) {
	                        console.error('Error while creating user');
	                        self.errorMessage = 'Error while creating user: ' + errResponse.data.errorMessage;
	                        self.successMessage='';
	                    }
	                );
	        }
	        
	        function getAllUsers(){
	        	 console.log('Get all the users');
	            return UserService.getAllUsers();
	        }
	        
	       /* function updateTask(task,id){
	            console.log('About to update Task');
	            TaskService.updateTask(task,id)
	                .then(
	                    function (response){
	                        console.log('Task updated successfully');
	                        self.successMessage='Task updated successfully';
	                        self.errorMessage='';
	                        self.done = true;
	                        $scope.myForm.$setPristine();
	                    },
	                    function(errResponse){
	                        console.error('Error while updating task');
	                        self.errorMessage='Error while updating Task '+errResponse.data;
	                        self.successMessage='';
	                    }
	                );
	        }
	        
	        function removeTask(id){
	            console.log('About to remove task with task '+id);
	            TaskService.removeTask(id)
	                .then(
	                    function(){
	                        console.log('Task removed successfully');
	                    },
	                    function(errResponse){
	                        console.error('Error while removing Task');
	                    }
	                );
	        }
	        
	        function getAllTasks(){
	            return TaskService.getAllTasks();
	        }
	        
	        function getTaskList(){
	        	console.log('Inside the get task list');
	        	console.log('The value is '+TaskService.getTaskList());
	            return TaskService.getTaskList();
	        }
	        
	        function editTask(id) {
	        	console.log('About to edit the task with id '+id);
	            self.successMessage='';
	            self.errorMessage='';
	            TaskService.getTask(id).then(
	                function (task) {
	                    self.task = task;
	                },
	                function (errResponse) {
	                    console.error('Error while removing task ' + id + ', Error :' + errResponse.data);
	                }
	            );
	        }
	        
	        function endTask(id) {
	        	console.log('About to end the task with id '+id);
	            self.successMessage='';
	            self.errorMessage='';
	            TaskService.getTask(id).then(
	                function (task) {
	                    self.task = task;
	                },
	                function (errResponse) {
	                    console.error('Error while removing task ' + id + ', Error :' + errResponse.data);
	                }
	            );
	        }
	        */
	        function reset(){
	            self.successMessage='';
	            self.errorMessage='';
	            self.task={};
	            $scope.myForm.$setPristine(); //reset Form
	        }
	    }
	    ]);