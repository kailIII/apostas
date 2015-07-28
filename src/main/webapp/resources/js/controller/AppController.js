appMain.controller("AppController", ["$scope", "$location", "CONST", 
	function($scope, $location, CONST) {
        
        $scope.init = function(){
                $location.path("/dashboard/");
        };
        $scope.init();
}]);


