'use strict';
angular.module('userCrudApp').factory('UserService',
	    ['$localStorage', '$http', '$q', 'urls',
	        function ($localStorage, $http, $q, urls) {
	 
	            var factory = {
	               /* loadAllTasks: loadAllTasks,
	                getTaskList: getTaskList,
	                getAllTasks: getAllTasks,
	                getTask: getTask,*/
	            	loadAllUsers: loadAllUsers,
	                createUser: createUser
	               // updateTask: updateTask,
	               // removeTask: removeTask
	            };
	 
	            return factory;
	            
	          /*  function loadAllUsers() {
	                console.log('Fetching all tasks');
	                var deferred = $q.defer();
	                console.log(urls.USER_SERVICE_API);
	                $http.get(urls.USER_SERVICE_API)
	                    .then(
	                        function (response) {
	                            console.log('Fetched successfully all tasks');
	                            $localStorage.tasks = response.data;
	                            deferred.resolve(response);
	                        },
	                        function (errResponse) {
	                            console.error('Error while loading tasks');
	                            deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }
	            
	            function getAllTasks(){
	                return $localStorage.tasks;
	            }
	            
	            
	            function getTaskList(){
	            	console.log('Fetching all tasks list');
	            	var deferred = $q.defer();
	            	console.log(urls.GET_TASK_LIST_SERVICE_API);
	            	 $http.get(urls.GET_TASK_LIST_SERVICE_API)
	                    .then(
	                        function (response) {
	                            console.log('Fetched successfully all tasks for parent task');
	                            $localStorage.parentTasks = response.data;
	                            console.log(response.data);
	                            deferred.resolve(response);
	                        },
	                        function (errResponse) {
	                            console.error('Error while loading tasks');
	                            deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }
	            
	            function getTask(id) {
	                console.log('Fetching Task with id :'+id);
	                var deferred = $q.defer();
	                $http.get(urls.GET_TASK_SERVICE_API+id)
	                    .then(
	                        function (response) {
	                            console.log('Fetched successfully Task with id :'+id);
	                            deferred.resolve(response.data);
	                        },
	                        function (errResponse) {
	                            console.error('Error while loading Task with id :'+id);
	                            deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }*/
	            
	            
	            function loadAllUsers() {
	                console.log('Fetching all users');
	                var deferred = $q.defer();
	                console.log(urls.BASE+urls.GET_ALL_USER_SERVICE_API);
	                $http.get(urls.BASE+urls.GET_ALL_USER_SERVICE_API)
	                    .then(
	                        function (response) {
	                            console.log('Fetched successfully all users');
	                            $localStorage.users = response.data;
	                            deferred.resolve(response);
	                        },
	                        function (errResponse) {
	                            console.error('Error while loading tasks');
	                            deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }
	            
	            function createUser(user) {
	                console.log('Creating user');
	                var deferred = $q.defer();
	                $http.post(urls.BASE+urls.ADD_USER_SERVICE_API, user)
	                    .then(
	                        function (response) {
	                        	loadAllUsers();
	                            deferred.resolve(response.data);
	                        },
	                        function (errResponse) {
	                           console.error('Error while creating user : '+errResponse.data.errorMessage);
	                           deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }
	            
	          /*  function updateTask(task,id) {
	                console.log('Updating Task with id '+task.taskId);
	                var deferred = $q.defer();
	                $http.put(urls.UPDATE_TASK_SERVICE_API +task.taskId, task)
	                    .then(
	                        function (response) {
	                        	loadAllTasks();
	                            deferred.resolve(response.data);
	                        },
	                        function (errResponse) {
	                            console.error('Error while updating Task with id :'+task.taskId);
	                            deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }
	            
	            function removeTask(id) {
	                console.log('Removing Task with id '+id);
	                var deferred = $q.defer();
	                $http.delete(urls.DELETE_TASK_SERVICE_API+id)
	                    .then(
	                        function (response) {
	                        	loadAllTasks();
	                            deferred.resolve(response.data);
	                        },
	                        function (errResponse) {
	                            console.error('Error while removing Task with id :'+id);
	                            deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }
	            
	            function endTask(task,id) {
	                console.log('End task Task with id '+task.taskId);
	                var deferred = $q.defer();
	                $http.put(urls.END_TASK_SERVICE_API +task.taskId, task)
	                    .then(
	                        function (response) {
	                        	loadAllTasks();
	                            deferred.resolve(response.data);
	                        },
	                        function (errResponse) {
	                            console.error('Error while updating Task with id :'+task.taskId);
	                            deferred.reject(errResponse);
	                        }
	                    );
	                return deferred.promise;
	            }*/
	    }
	    ]);