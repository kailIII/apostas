appMain.config(["RestangularProvider", "$routeProvider", "CONST", function (RestangularProvider, $routeProvider, CONST) {

                RestangularProvider.setRestangularFields({
                        id: "id"
                });

                $routeProvider.when("/dashboard/", {
                        templateUrl: getContexto(CONST)+"/pages/dashboard/",
                        controller: "DashboardController"
                });

                $routeProvider.when("/usuario/", {
                        templateUrl: getContexto(CONST)+"/pages/usuario/",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/usuario/:acao", {
                        templateUrl: getContexto(CONST)+"/pages/usuario/",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/aposta/", {
                        templateUrl: getContexto(CONST)+"/pages/aposta/",
                        controller: "ApostaController"
                });
                $routeProvider.when("/aposta/:id", {
                        templateUrl: getContexto(CONST)+"/pages/aposta/{id}",
                        controller: "ApostaController"
                });
        }
]);
