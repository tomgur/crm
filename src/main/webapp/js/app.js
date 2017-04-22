var app = angular.module("crm", ["ngRoute", "ngMaterial", "ngMdIcons", "ngSanitize", "mdDataTable", "ngMdIcons", 'mwl.calendar', 'ui.bootstrap']);

app.config(function ($mdThemingProvider, $routeProvider) {
    // Routing
    $routeProvider
        .when("/people", {
            templateUrl: "templates/people.template.html",
            controller: "peopleController"
        })
        .when("/clients", {
            templateUrl: "templates/clients.template.html",
            controller: "clientsController"
        })
        .when("/calendar", {
            templateUrl: "templates/calendar.template.html",
            controller: "calendarController"
        })
        .otherwise({
            templateUrl: "templates/calendar.template.html",
            controller: "calendarController"
        });

    // Color theme
    $mdThemingProvider.theme('default')
        .primaryPalette('teal')
        .accentPalette('orange');
    $mdThemingProvider.theme('input', 'default')
        .primaryPalette('grey');
});