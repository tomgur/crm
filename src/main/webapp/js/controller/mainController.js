/**
 * Created by gurt on 4/20/2017.
 */
app.controller('mainController', ['$scope', '$mdBottomSheet', '$mdSidenav', '$mdDialog', '$location', '$mdToast', function ($scope, $mdBottomSheet, $mdSidenav, $mdDialog, $location, $mdToast) {
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
            link: '/clients',
            title: 'Clients',
            icon: 'account_balance'
        },
        {
            link: '/courses',
            title: 'Courses',
            icon: 'work'
        },
        {
            link: '/quotas',
            title: 'Quotas',
            icon: 'list'
        },
        {
            link: '/Invoices',
            title: 'Invoices',
            icon: 'receipt'
        },

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
    $scope.user = [];

    $scope.showAdd = function (ev) {
        switch ( $scope.title ) {
            case "People" :
                $scope.showAddPersonDialog(ev)
                break;
            case "Clients" :
                $scope.showAddClientDialog(ev)
                break;
            default :
                $scope.showToast("Add Button clicked, but no dialog has been written for [" + $scope.title + "] yet...");
        }
    };

    $scope.showAddPersonDialog = function (ev) {
        $mdDialog.show({
            controller: 'peopleController',
            templateUrl: 'templates/add.person.template.html',
            targetEvent: ev,
            parent : angular.element(document.body),
            clickOutsideToClose : true,
            scope : $scope,
            preserveScope : true
        });
    };
    $scope.showAddClientDialog = function (ev) {
        $mdDialog.show({
            controller: 'clientsController',
            templateUrl: 'templates/add.client.template.html',
            targetEvent: ev,
            parent : angular.element(document.body),
            clickOutsideToClose : true,
            scope : $scope,
            preserveScope : true
        });
    };
    $scope.showToast = function (message) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(message)
                .hideDelay(5000)
        );
    }
}]);