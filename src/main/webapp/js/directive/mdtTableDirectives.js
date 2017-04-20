/**
 * Created by gurt on 4/20/2017.
 */
app.directive('mdtCustomCellEditButton', function () {
    return {
        template: '<md-button aria-label="Edit"><ng-md-icon icon="mode_edit"></ng-md-icon></md-button>'
    };
});
app.directive('mdtCustomCellDeleteButton', function () {
    return {
        template: '<md-button aria-label="Delete"><ng-md-icon icon="delete"></ng-md-icon></md-button>'
    };
});