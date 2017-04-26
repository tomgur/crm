/**
 * Created by gurt on 4/23/2017.
 */
app.directive('upload', function($rootScope) {
    return {
        restrict: 'EA',
        link: function(scope, elem, attrs) {
            elem.on("change" ,function(evt) {
                var file = evt.currentTarget.files[0];
                var reader = new FileReader();
                reader.onload = function(evt) {
                    scope.$apply(function($scope) {
                        $scope.myImage = evt.target.result;
                    });
                };
                reader.readAsDataURL(file);
            });
        }
    };
});