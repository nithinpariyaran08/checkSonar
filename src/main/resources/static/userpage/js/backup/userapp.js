var app = angular.module('userCrudApp',['ui.router','ngStorage']);
app.constant('urls', {
    BASE: 'http://localhost:9999/projectmanager',
    ADD_USER_SERVICE_API :    '/api/v1/addUser',
    GET_ALL_USER_SERVICE_API :'/api/v1/getAllUsers',
    UPDATE_TASK_SERVICE_API :'/api/v1/updateTask/',
    DELETE_TASK_SERVICE_API :'/api/v1/deleteTask/'
});
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('home', {
                url: '/',
                controller:'UserController',
                templateUrl: '/projectmanager',
                controllerAs:'userCtrl',
                resolve: {
                    users: function ($q, UserService) {
                        console.log('Load all users');
                        var deferred = $q.defer();
                       UserService.loadAllUsers().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/');
    }]);

app.controller('ctrl', function($scope, $http) {
	$http.get(BASE+GET_ALL_USER_SERVICE_API).
    then(function(response) {
        $scope.tasks = response.data;
        console.log('testing parent task'+scope.tasks);
    });
});