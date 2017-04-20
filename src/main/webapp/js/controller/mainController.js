/**
 * Created by gurt on 4/20/2017.
 */
app.controller('mainController', ['$scope', '$mdBottomSheet', '$mdSidenav', '$mdDialog', '$location', function ($scope, $mdBottomSheet, $mdSidenav, $mdDialog, $location) {
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
    $scope.user = [];

    $scope.showAdd = function (ev) {
        switch ( $scope.title ) {
            case "People" :
                $scope.showAddPersonDialog(ev)
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
    }
}]);