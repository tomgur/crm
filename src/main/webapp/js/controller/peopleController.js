/**
 * Created by gurt on 4/20/2017.
 */
app.controller('peopleController', ['$scope', '$mdToast', 'peopleFactory', '$mdDialog', function ($scope, $mdToast, peopleFactory, $mdDialog) {
    $scope.readPeople = function() {
        peopleFactory.getPeople().then(function (responseData) {
            $scope.people = responseData.data;
        });
        $scope.title = "People";
    };

    $scope.addPerson = function() {
        peopleFactory.addPerson($scope).then(function successCallback(response) {
            // tell the user new product was created
            // $scope.showToast(response.data);

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
        $scope.user.client = "";
        $scope.user.id = "";
        $scope.user.tz = "";
    };

    $scope.cancel = function () {
        $mdDialog.cancel();
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

    $scope.confirmDeletePerson = function () {

        var confirm = $mdDialog.confirm()
            .title('Are you sure?')
            .textContent('Person ' + $scope.user.firstName + ' ' + $scope.user.lastName + ' (' + $scope.user.tz + ') will be deleted permanently')
            .parent(angular.element(document.body))
            .clickOutsideToClose(true)
            .ok('Yes')
            .cancel('No');

        $mdDialog.show(confirm).then(function() {
            // delete the person
            peopleFactory.deletePerson($scope.user.id);
            // close the dialog
            $scope.cancel();
            // remove form values
            $scope.clearPeopleForm();
            $scope.showToast("Person was deleted");
        }, function() {
            $scope.showToast("Person was NOT deleted");
            $scope.clearPeopleForm() ;
        });
    };

    $scope.getPersonAndPopulateFormFields = function (rowId) {
        $scope.user.id = rowId
        peopleFactory.getPerson(rowId).then( function successCallback(responseData) {
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
            controller: DialogController,
            templateUrl: 'templates/person.info.template.html',
            parent : angular.element(document.body),
            clickOutsideToClose : true,
            scope : $scope,
            preserveScope : true
        }).then(
            function () {},
            // user clicked cancel
            function () {
                //clear model content
            }
        );
    };

    // methods for dialog box
    function DialogController($scope, $mdDialog) {
        $scope.cancel = function () {
            $mdDialog.cancel();
        };
    };

    $scope.showToast = function (message) {
        $mdToast.show(
            $mdToast.simple()
                .textContent(message)
                .hideDelay(3000)
                .position("top right")
        );
    }

    $scope.readPeople();
}]);