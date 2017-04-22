/**
 * Created by gurt on 4/20/2017.
 */
app.factory('clientsFactory', function ($http) {
    var factory = {};

    factory.getClients = function () {
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/rest/getClients'
        });
    };

    factory.addClient = function ($scope) {
        return $http({
            method : 'POST',
            data : {
                'name'          : $scope.client.name,
                'email'         : $scope.client.email,
                'phone'         : $scope.client.phone,
                'fax'           : $scope.client.fax,
                'address'       : $scope.client.address,
                'contactPerson' : $scope.client.contactPerson
            },
            url: 'http://localhost:8080/rest/addClient'
        });
    };

    factory.getClient = function (id) {
        return $http({
            method  : 'GET',
            url     : 'http://localhost:8080/rest/getClient?id=' + id
        });
    };

    factory.deleteClient = function (id) {
        return $http({
            method  : 'POST',
            data    : {
                'id' : id
            },
            url     : 'http://localhost:8080/rest/deleteClient'
        })
    };
    
    factory.updateClient = function ($scope) {
        return $http({
            method : 'POST',
            data : {
                'id'            : $scope.client.id,
                'name'          : $scope.client.name,
                'email'         : $scope.client.email,
                'phone'         : $scope.client.phone,
                'fax'           : $scope.client.fax,
                'address'       : $scope.client.address,
                'contactPerson' : $scope.client.contactPerson
            },
            url: 'http://localhost:8080/rest/updateClient'
        });
    };

    return factory
});