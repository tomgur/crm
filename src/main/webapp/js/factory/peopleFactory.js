/**
 * Created by gurt on 4/20/2017.
 */
app.factory('peopleFactory', function ($http) {
    var factory = {};

    factory.getPeople = function () {
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/rest/getPeople'
        });
    };

    factory.addPerson = function ($scope) {
        return $http({
            method : 'POST',
            data : {
                'firstName' : $scope.user.firstName,
                'lastName'  : $scope.user.lastName,
                'phone'     : $scope.user.phone,
                'email'     : $scope.user.email,
                'company'   : $scope.user.company,
                'tz'        : $scope.user.tz
            },
            url: 'http://localhost:8080/rest/addPerson'
        });
    };

    return factory;
});