/**
 * Created by gurt on 4/20/2017.
 */
app.controller('companiesController', ['$scope', 'companiesFactory', '$mdToast', '$mdDialog', function ($scope, companiesFactory, $mdToast, $mdDialog) {
    $scope.readCompanies = function () {
        companiesFactory.getCompanies().then(function (responseData) {
            $scope.companies = responseData.data;
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

    $scope.readCompanies();
}]);