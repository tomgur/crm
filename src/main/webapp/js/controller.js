/**
 * Created by gurt on 4/10/2017.
 */
angular.module('app', [])
    .controller('MyCtrl1', function($scope, $http) {
        $http.get('http://localhost:8080/crm/rest/getPeople').
        then(function(response) {
            $scope.people = response.data;
        });
    });
