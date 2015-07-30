appMain.config(["RestangularProvider", "$routeProvider", "CONST", function (RestangularProvider, $routeProvider, CONST) {

                RestangularProvider.setRestangularFields({
                        id: "id"
                });

                $routeProvider.when("/dashboard/", {
                        templateUrl: getContextoPages(CONST)+"/dashboard/",
                        controller: "DashboardController"
                });

                $routeProvider.when("/usuario/", {
                        templateUrl: getContextoPages(CONST)+"/usuario/",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/usuario/:acao", {
                        templateUrl: getContextoPages(CONST)+"/usuario/",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/aposta/", {
                        templateUrl: getContextoPages(CONST)+"/aposta/",
                        controller: "ApostaController"
                });
        }
]);
