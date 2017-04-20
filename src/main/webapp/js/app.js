var app = angular.module("StarterApp", ["ngRoute", "ngMaterial", "ngMdIcons", "ngSanitize", "mdDataTable", "ngMdIcons"]);

app.controller('AppCtrl', ['$scope', '$mdBottomSheet', '$mdSidenav', '$mdDialog', '$location', '$mdToast', function ($scope, $mdBottomSheet, $mdSidenav, $mdDialog, $location, $mdToast) {
    $scope.changePath = function (path, title) {
        $scope.title = title;
        $location.path(path);
    };
    $scope.toggleSidenav = function (menuId) {
        $mdSidenav(menuId).toggle();
    };
    $scope.menu = [
        {
            link: '/calendar',
            title: 'Calendar',
            icon: 'event'
        },
        {
            link: '/people',
            title: 'People',
            icon: 'contacts'
        },
        {
            link: '/companies',
            title: 'Companies',
            icon: 'account_balance'
        },
        {
            link: '/courses',
            title: 'Courses',
            icon: 'work'
        }
    ];
    $scope.admin = [
        {
            link: '',
            title: 'Trash',
            icon: 'delete'
        },
        {
            link: 'showListBottomSheet($event)',
            title: 'Settings',
            icon: 'settings'
        }
    ];

    $scope.title = "";

    $scope.showAdd = function (ev) {
        switch ( $scope.title ) {
            case "People" :
                $scope.showAddPersonDialog(ev)
                break;
            default :
                $scope.showToast("Add Button clicked, but no dialog has been written for [' + $scope.title + '] yet...");
        }
    };

    $scope.showAddPersonDialog = function (ev) {
        $mdDialog.show({
            controller: DialogController,
            templateUrl: 'templates/add.person.template.html',
            targetEvent: ev,
            parent : angular.element(document.body),
            clickOutsideToClose : true,
            scope : $scope,
            preserveScope : true,
        });
    }

    function DialogController($scope, $mdDialog) {
        $scope.cancel = function () {
            $mdDialog.cancel();
        };


    };
}]);

app.controller('ListBottomSheetCtrl', function ($scope, $mdBottomSheet) {
    $scope.items = [
        {name: 'Share', icon: 'share'},
        {name: 'Upload', icon: 'upload'},
        {name: 'Copy', icon: 'copy'},
        {name: 'Print this page', icon: 'print'},
    ];

    $scope.listItemClick = function ($index) {
        var clickedItem = $scope.items[$index];
        $mdBottomSheet.hide(clickedItem);
    };
});

function DialogController($scope, $mdDialog) {
    $scope.hide = function () {
        $mdDialog.hide();
    };
    $scope.cancel = function () {
        $mdDialog.cancel();
    };
    $scope.answer = function (answer) {
        $mdDialog.hide(answer);
    };
};


app.config(function ($mdThemingProvider, $routeProvider) {
    // Color theme
    $mdThemingProvider.theme('default')
        .primaryPalette('teal')
        .accentPalette('orange');
    $mdThemingProvider.theme('input', 'default')
        .primaryPalette('grey');

    // Routing
    $routeProvider
        .when("/", {
            templateUrl: "templates/main.html"
        })
        .when("/people", {
            templateUrl: "templates/people.template.html",
            controller: "peopleController"
        })
        .when("/companies", {
            templateUrl: "templates/companies.template.html",
            controller: "companiesController"
        })
});

app.controller('peopleController', ['$scope', 'peopleFactory', '$mdToast', '$mdDialog', function ($scope, peopleFactory, $mdToast, $mdDialog) {
    $scope.readPeople = function() {
        peopleFactory.getPeople().then(function (responseData) {
            $scope.people = responseData.data;
        });
    };

    $scope.addPerson = function() {
        peopleFactory.addPerson($scope).then(function successCallback(response) {
            // tell the user new product was created
            $scope.showToast(response.data);

            // refresh the list
            $scope.readPeople();

            // close the dialog
            $scope.cancel();

            // remove form values
            $scope.clearPeopleForm();
        }, function errorCallback (response) {
            $scope.showToast("Unable to create record.");
        });
    }

    $scope.clearPeopleForm = function() {
        $scope.user.firstName = "";
        $scope.user.lastName = "";
        $scope.user.phone = "";
        $scope.user.email = "";
        $scope.user.company = "";
    };

    $scope.showToast = function(message) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(message)
                .hideDelay(3000)
                .position("top right")
        );
    };

    $scope.cancel = function () {
        $mdDialog.cancel();
    };

    $scope.readPeople();
}]);

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
                'company'   : $scope.user.company
            },
            url: 'http://localhost:8080/rest/addPerson'
        });
    };

    return factory;
});

app.controller('companiesController', ['$scope', 'companiesFactory', '$mdToast', '$mdDialog', function ($scope, companiesFactory, $mdToast, $mdDialog) {
    companiesFactory.getCompanies().then(function (responseData) {
        $scope.companies = responseData.data;
    });
    $scope.selectedRowCallback = function (rows) {
        $mdToast.show(
            $mdToast.simple()
                .content('Selected row id(s): ' + rows)
                .hideDelay(3000)
        );
    };
    $scope.showRowId = function(rowId) {
        $mdDialog.show(
            $mdDialog.alert()
                .clickOutsideToClose(true)
                .textContent(rowId)
                .ariaLabel('Alert Dialog Demo')
                .ok('Got it!')
        )
    };
}]);

app.factory('companiesFactory', function ($http) {
    return {
        getCompanies: function () {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/rest/getCompanies'
            });
        }
    }
});

app.directive('mdtCustomCellEditButton', function () {
    return {
        template: '<md-button aria-label="Edit"><ng-md-icon icon="mode_edit"></ng-md-icon></md-button>'
    };
});
app.directive('mdtCustomCellDeleteButton', function () {
    return {
        template: '<md-button aria-label="Delete"><ng-md-icon icon="delete"></ng-md-icon></md-button>'
    };
});