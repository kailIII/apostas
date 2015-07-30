appMain.controller("DashboardController", ["$scope", "Dashboard",
        function ($scope, Dashboard) {

                $scope.init = function () {
                        Dashboard.buscar().then(function (result) {
                                $scope.apostas = result.lista;
                        }, function (result) {
                                $scope.msg = result
                        });
                }
                $scope.init();
        }
]);


