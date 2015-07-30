var moduloLogin = angular.module("loginApp", ["ngRoute", "ngResource", "restangular"]);

// CONSTANTES
moduloLogin.constant("CONST", {
        //CONTEXTO: "http://localhost:8084/pages"
        CONTEXTO: ""
});

// ROTAS
moduloLogin.config(["RestangularProvider", "$routeProvider", function (RestangularProvider, $routeProvider) {
                RestangularProvider.setRestangularFields({
                        id: "id"
                });
        }
]);

moduloLogin.service("Login", ["Restangular", "CONST", function (Restangular, CONST) {
                this.logar = function (usuario) {
                        return Restangular.all( CONST.CONTEXTO+"pages/login").customPOST(usuario);
                };
        }
]);

moduloLogin.controller("LoginController", ["$scope", "CONST", "Login",
        function ($scope, CONST, Login) {

                $scope.usuario = {login: '', senha: ''};

                $scope.logar = function () {
                        $scope.mensagemErro = ""
                        Login.logar($scope.usuario).then(function (data) {
                                window.location.href = CONST.CONTEXTO+"/pages/principal/";
                        }, function (data) {
                                $scope.mensagemErro = "Usuário ou senha inválido."
                        });
                };

        }
]);