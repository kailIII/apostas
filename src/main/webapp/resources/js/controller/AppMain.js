var appMain = angular.module("appMain", ["ngRoute", "ngResource", "restangular"]);

// CONSTANTES
appMain.constant("CONST", {
        CONTEXTO: "http://localhost:8084/pages"
});

// ROTAS
appMain.config(["RestangularProvider", "$routeProvider", "CONST", function (RestangularProvider, $routeProvider, CONST) {

                RestangularProvider.setRestangularFields({
                        id: "id"
                });

                $routeProvider.when("/dashboard/", {
                        templateUrl: "/views/dashboard.html",
                        controller: "DashboardController"
                });

                $routeProvider.when("/usuario/", {
                        templateUrl: "/views/usuario/cadastro.html",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/usuario/:acao", {
                        templateUrl: "/views/usuario/cadastro.html",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/aposta/", {
                        templateUrl: "/views/aposta/cadastro.html",
                        controller: "ApostaController"
                });
        }]);

