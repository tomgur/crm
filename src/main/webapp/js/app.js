var app = angular.module("crm", ["ngRoute", "ngMaterial", "ngMdIcons", "ngSanitize", "mdDataTable", "ngMdIcons"]);

app.config(function ($mdThemingProvider, $routeProvider) {
    // Routing
    $routeProvider
        .when("/", {
            templateUrl: "templates/main.html"
        })
        .when("/people", {
            templateUrl: "templates/people.template.html",
            controller: "peopleController",
        })
        .when("/companies", {
            templateUrl: "templates/companies.template.html",
            controller: "companiesController"
        })

    // Color theme
    $mdThemingProvider.theme('default')
        .primaryPalette('teal')
        .accentPalette('orange');
    $mdThemingProvider.theme('input', 'default')
        .primaryPalette('grey');
});