/**
 * Created by gurt on 4/20/2017.
 */
app.controller('clientsController', ['$scope', 'clientsFactory', '$mdToast', '$mdDialog', function ($scope, clientsFactory, $mdToast, $mdDialog) {
    $scope.readClients = function () {
        clientsFactory.getClients().then(function (responseData) {
            $scope.clients = responseData.data;
        });
    };

    $scope.showAddClientDialog = function (ev) {
        $mdDialog.show({
            controller : 'clientsController',
            templateUrl: 'templates/add.client.template.html',
            targetEvent: ev,
            parent : angular.element(document.body),
            clickOutsideToClose : true,
            scope : $scope,
            preserveScope : true
        });
    };

    $scope.addClient = function() {
        clientsFactory.addClient($scope).then(function successCallback(response) {
            // tell the user new product was created
            $scope.showToast("Added client [" + $scope.client.name + "]");

            // close the dialog
            $scope.cancel();

            // remove form values
            $scope.clearClientsForm();

            //refresh the table
            $scope.readClients()
        }, function errorCallback (response) {
            $scope.showToast("Unable to create record.");
        });
    };

    $scope.getClientAndPopulateFormFields = function (rowId) {
        $scope.client.id = rowId;
        clientsFactory.getClient(rowId).then( function successCallback(responseData) {
            $scope.client.name = responseData.data.name;
            $scope.client.email = responseData.data.email;
            $scope.client.phone = responseData.data.phone;
            $scope.client.fax = responseData.data.fax;
            $scope.client.address = responseData.data.address;
            $scope.client.contactPerson = responseData.data.contactPerson;
        });
    };

    // methods for dialog box
    $scope.cancel = function () {
        $mdDialog.cancel();
        $scope.clearClientsForm();
    };

    $scope.showClientInfoDialog = function (rowId) {
        $scope.getClientAndPopulateFormFields(rowId);
        $mdDialog.show({
            controller: 'clientsController',
            templateUrl: 'templates/client.info.template.html',
            parent : angular.element(document.body),
            clickOutsideToClose : true,
            scope : $scope,
            preserveScope : true
        });
    };

    $scope.confirmDeleteClient = function () {

        var confirm = $mdDialog.confirm()
            .title('Are you sure?')
            .textContent('Client ' + $scope.client.name + ' will be deleted permanently')
            .parent(angular.element(document.body))
            .clickOutsideToClose(true)
            .ok('Yes')
            .cancel('No');

        $mdDialog.show(confirm).then(function () {
            // delete the client
            clientsFactory.deleteClient($scope.client.id);
            // close the dialog
            $scope.cancel();
            // remove form values
            $scope.clearClientsForm();
            $scope.showToast("Client was deleted");
            $scope.clients = null;
            $scope.readClients();
        }, function () {
            $scope.showToast("Client was NOT deleted");
            $scope.clearClientsForm();
        });
    };

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

    $scope.readClients();
}]);