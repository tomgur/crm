/**
 * Created by gurt on 4/20/2017.
 */
app.factory('companiesFactory', function ($http) {
    var factory = {};

    factory.getCompanies = function () {
        return $http({
            method: 'GET',
            url: 'http://localhost:8080/rest/getCompanies'
        });
    }

    return factory
});