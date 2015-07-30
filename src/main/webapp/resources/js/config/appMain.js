var appMain = angular.module("appMain", ["ngRoute", "ngResource", "restangular"]);

// CONSTANTES
appMain.constant("CONST", {
        //CONTEXTO: "http://localhost:8084/pages"
        CONTEXTO: ""
});
appMain.directive('datepicker', function() {
    return {
        restrict: 'A',
        require : 'ngModel',
        link : function (scope, element, attrs, ngModelCtrl) {
            $(function(){
                element.datepicker({
                    dateFormat:'dd/mm/yy',
                    onSelect:function (date) {
                        scope.$apply(function () {
                            ngModelCtrl.$setViewValue(date);
                        });
                    }
                });
            });
        }
    };
});
function getContexto(constant){
        return constant.CONTEXTO;
}
