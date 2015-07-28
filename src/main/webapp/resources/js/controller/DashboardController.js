appMain.service("Dashboard", ["Restangular", "CONST", function (Restangular, CONST) {
	this.buscar = function () {
		return Restangular.one("pages/dashboard/apostas/").get();
	};
}]);

appMain.controller("DashboardController", ["$scope", "CONST", "Dashboard", 
	function($scope, CONST, Dashboard) {
        
        $scope.init = function(){
                Dashboard.buscar().then(function(result) {
                        $scope.msg = result.nome
                }, function(result) {
                        $scope.msg = result
                });
        }
        $scope.init();
}]);


