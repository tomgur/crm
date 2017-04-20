/**
 * Created by gurt on 4/20/2017.
 */
app.controller('clientsController', ['$scope', 'clientsFactory', '$mdToast', '$mdDialog', function ($scope, clientsFactory, $mdToast, $mdDialog) {
    $scope.readClients = function () {
        clientsFactory.getClients().then(function (responseData) {
            $scope.clients = responseData.data;
        });
    }
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

    $scope.readClients();
}]);