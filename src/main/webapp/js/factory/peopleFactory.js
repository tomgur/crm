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
                'client'    : $scope.user.client,
                'tz'        : $scope.user.tz,
                'imageBlob' : $scope.myCroppedImage
            },
            url: 'http://localhost:8080/rest/addPerson'
        });
    };

    factory.updatePerson = function ($scope) {
        return $http({
            method : 'POST',
            data : {
                'personId'  : $scope.user.id,
                'firstName' : $scope.user.firstName,
                'lastName'  : $scope.user.lastName,
                'phone'     : $scope.user.phone,
                'email'     : $scope.user.email,
                'client'    : $scope.user.client,
                'tz'        : $scope.user.tz,
                'imageBlob' : $scope.myCroppedImage
            },
            url : 'http://localhost:8080/rest/updatePerson'
        });
    };

    factory.getPerson = function (personId) {
        return $http({
            method : 'GET',
            url: 'http://localhost:8080/rest/getPerson?personId=' + personId
        });
    };

    factory.deletePerson = function (personId) {
        return $http({
            method  : 'POST',
            data    : {
                'personId' : personId
            },
            url     : 'http://localhost:8080/rest/deletePerson'
        });
    };



    return factory;
});