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
    $scope.client = [];
}]);