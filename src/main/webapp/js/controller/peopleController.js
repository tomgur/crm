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
            $scope.showToast(response.data);

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
    }

    $scope.readPeople();
}])