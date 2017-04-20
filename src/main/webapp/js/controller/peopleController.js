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
        $scope.user.company = "";
        $scope.user.id = "";
        $scope.user.zt = "";
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

    $scope.confirmDeletePerson = function (rowId) {
        $scope.showConfirm($scope, rowId);
    };

    $scope.showConfirm = function($scope, rowId) {
        $scope.user.id = rowId;
        var confirm = $mdDialog.confirm()
            .title('Are you sure?')
            .textContent('Record will be deleted permanently.')
            .ariaLabel('Delete Dialog')
            .targetEvent(event)
            .scope($scope)
            .preserveScope(true)
            .ok('Yes')
            .cancel('No');
        $mdDialog.show(confirm).then(function successCallback(response) {
            // delete the person
            peopleFactory.deletePerson($scope);
            // close the dialog
            $scope.cancel();
            // remove form values
            $scope.clearPeopleForm();
        }, function() {

        });
    };

    $scope.showPersonInfoDialog = function (rowId) {
        $scope.user.id = rowId
        peopleFactory.getPerson(rowId).then( function successCallback(responseData) {
            $scope.user.firstName = responseData.data.firstName;
            $scope.user.lastName = responseData.data.lastName;
            $scope.user.company = responseData.data.company;
            $scope.user.phone = responseData.data.phone;
            $scope.user.tz = responseData.data.tz;
            $scope.user.email = responseData.data.email;

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
                    $scope.clearPeopleForm();
                }
            );
        }), function errorCallback(responseData) {
            $scope.showToast('Unable to retrieve record.')
        }
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