/**
 * Created by gurt on 4/10/2017.
 */
var app = angular.module("app", ["ngRoute"])
    .config(function ($routeProvider) {
        $routeProvider
            .when("/people", {
                templateUrl: "templates/people.html",
                controller: "peopleController"
            })
            .when("/companies", {
                templateUrl: "templates/companies.html",
                controller: "companiesController"
            })
            .when("/courses", {
                templateUrl: "templates/courses.html",
                controller: "coursesController"
            })
    })
    .controller("peopleController", function ($scope, $http) {
        $http.get('http://localhost:8080/crm/rest/getPeople')
            .then(function (response) {
                $scope.people = response.data;
            })
    })
    .controller("companiesController", function ($scope, $http) {
        $http.get('http://localhost:8080/crm/rest/getCompanies')
            .then(function (response) {
                $scope.companies = response.data;
            })
    })
    .controller("coursesController", function ($scope, $http) {
        $http.get('http://localhost:8080/crm/rest/getPeople')
            .then(function (response) {
                $scope.people = response.data;
            })
    });




