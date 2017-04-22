/**
 * Created by gurt on 4/20/2017.
 */
app.controller('peopleController', ['$scope', '$mdToast', 'peopleFactory', '$mdDialog', function ($scope, $mdToast, peopleFactory, $mdDialog) {
    $scope.readPeople = function () {
        peopleFactory.getPeople().then(function successCallback(responseData) {
            $scope.people = responseData.data;
        }, function errorCallback(responseData) {
            $scope.showToast("Something didn't work as expected....");
        });
        $scope.title = "People";
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

    $scope.addPerson = function () {
        peopleFactory.addPerson($scope).then(function successCallback(response) {
            // tell the user new product was created
            // $scope.showToast(response.data);

            // close the dialog
            $scope.cancel();

            $scope.readPeople();
            // remove form values
            $scope.clearPeopleForm();
        }, function errorCallback(response) {
            $scope.showToast("Unable to create record.");
        });
    }

    $scope.clearPeopleForm = function () {
        $scope.user.firstName = "";
        $scope.user.lastName = "";
        $scope.user.phone = "";
        $scope.user.email = "";
        $scope.user.client = "";
        $scope.user.id = "";
        $scope.user.tz = "";
    };

    $scope.showRowId = function (rowId) {
        $mdDialog.show(
            $mdDialog.alert()
                .title('Row Clicked!')
                .textContent('You clicked row # [' + rowId + ']')
                .ok('Close')
                .targetEvent(event)
        )
    };

    $scope.confirmDeletePerson = function (event) {

        var confirm = $mdDialog.confirm()
            .title('Are you sure?')
            .textContent('Person [ ' + $scope.user.firstName + ' ' + $scope.user.lastName + ' - ' + $scope.user.tz + ' ] will be deleted permanently')
            .parent(angular.element(document.body))
            .targetEvent(event)
            .clickOutsideToClose(true)
            .ok('Yes')
            .cancel('No');

        $mdDialog.show(confirm).then(function() {
            // delete the person
            peopleFactory.deletePerson($scope.user.id).then(function successCallback() {
                $scope.showToast("Person [" + $scope.user.firstName + " " + $scope.user.lastName + "] was deleted successfully");
                // close the dialog
                $scope.cancel();
                // remove form values
                $scope.clearPeopleForm();
                $scope.readPeople();
            });
        }, function() {
            $scope.showToast("Person was NOT deleted");
            $scope.clearPeopleForm();
        });
    };

    $scope.getPersonAndPopulateFormFields = function (rowId) {
        $scope.user.id = rowId;
        peopleFactory.getPerson(rowId).then(function successCallback(responseData) {
            $scope.user.firstName = responseData.data.firstName;
            $scope.user.lastName = responseData.data.lastName;
            $scope.user.client = responseData.data.client;
            $scope.user.phone = responseData.data.phone;
            $scope.user.tz = responseData.data.tz;
            $scope.user.email = responseData.data.email;
        });
    };

    $scope.showPersonInfoDialog = function (rowId) {
        $scope.getPersonAndPopulateFormFields(rowId);
        $mdDialog.show({
            controller: 'peopleController',
            templateUrl: 'templates/person.info.template.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            scope: $scope,
            preserveScope: true
        });
    };

    $scope.updatePerson = function () {
        peopleFactory.updatePerson($scope).then(function successCallback() {
            $scope.showToast("Updated person [" + $scope.user.firstName + " " + $scope.user.lastName + "]");
            $scope.cancel();
            $scope.clearPeopleForm();
            $scope.readPeople();
        }, function errorCallback() {
            $scope.showToast("Unable to update record");
        });
    };

    // methods for dialog box
    $scope.cancel = function () {
        $mdDialog.cancel();
        $scope.clearPeopleForm();
    };

    $scope.showToast = function (message) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(message)
                .hideDelay(5000)
        );
    }

    $scope.readPeople();
}]);