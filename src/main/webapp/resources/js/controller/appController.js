appMain.controller("AppController", ["$scope", "$location",
        function ($scope, $location) {

                $scope.init = function () {
                        $location.path("/dashboard/");
                };
                $scope.init();
        }
]);


