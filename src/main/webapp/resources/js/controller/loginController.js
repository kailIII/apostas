appMain.controller("LoginController", ["$scope", "$location", "$cookies", "Login",
    function ($scope, $location, $cookies, Login) {
        $scope.login = {j_username: '', j_password: ''};
        
        $scope.logar = function () {
                Login.logar($scope.login).then(function (result) {
                        $('#login_modal').modal('hide');
                }, function (result) {
                        alert("erro!");
                        addMensagemRetorno($scope, result);
                });
        };
        
    }
]);

