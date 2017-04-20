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
    }

    return factory
});