appMain.controller("DashboardController", ["$scope", "Dashboard",
        function ($scope, Dashboard) {

                $scope.init = function () {
                        Dashboard.buscar().then(function (result) {
                                $scope.msg = result.nome
                        }, function (result) {
                                $scope.msg = result
                        });
                }
                $scope.init();
        }
]);


