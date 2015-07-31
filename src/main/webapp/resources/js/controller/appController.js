appMain.controller("AppController", ["$scope", "$location",
        function ($scope, $location) {

                $scope.init = function () {
                        $location.path("/dashboard/");
                };
                $scope.init();
        }
]);


appMain.controller('PaginationController', function ($scope) {
    $scope.totalItems = 64;
    $scope.currentPage = 4;

    $scope.setPage = function (pageNo) {
      $scope.currentPage = pageNo;
    };

    $scope.pageChanged = function() {
      console.log('Page changed to: ' + $scope.currentPage);
    };

    $scope.maxSize = 5;
    $scope.bigTotalItems = 175;
    $scope.bigCurrentPage = 1;
});