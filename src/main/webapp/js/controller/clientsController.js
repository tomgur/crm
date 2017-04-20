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

    $scope.addClient = function() {
        clientsFactory.addClient($scope).then(function successCallback(response) {
            // tell the user new product was created
            $scope.showToast("Added client [" + $scope.client.name + "]");

            // close the dialog
            $scope.cancel();

            // remove form values
            $scope.clearClientsForm();
        }, function errorCallback (response) {
            $scope.showToast("Unable to create record.");
        });
    }

    $scope.clearClientsForm = function() {
        $scope.client.name = "";
        $scope.client.email = "";
        $scope.client.phone = "";
        $scope.client.fax = "";
        $scope.client.address = "";
        $scope.client.contactPerson = "";
    };

    $scope.showToast = function (message) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(message)
                .hideDelay(5000)
        );
    }

    // cancel for new Client dialog
    $scope.cancel = function () {
        $mdDialog.cancel();
    };


    $scope.readClients();
}]);