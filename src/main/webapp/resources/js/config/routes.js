appMain.config(["RestangularProvider", "$routeProvider", "$httpProvider", "CONST", function (RestangularProvider, $routeProvider, $httpProvider, CONST) {

                RestangularProvider.setErrorInterceptor(function (response, deferred, responseHandler) {
                        if (response.status === 401) {
                                $('#login_modal').modal({
                                        keyboard: false,
                                        backdrop: 'static'
                                });
                                return false;
                        }
                        return true;
                });
                
                RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json'});

                $routeProvider.when("/dashboard/", {
                        templateUrl: getContexto(CONST) + "/pages/dashboard/",
                        controller: "DashboardController"
                });

                $routeProvider.when("/usuario/", {
                        templateUrl: getContexto(CONST) + "/pages/usuario/",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/usuario/:acao", {
                        templateUrl: getContexto(CONST) + "/pages/usuario/",
                        controller: "UsuarioController"
                });

                $routeProvider.when("/aposta/", {
                        templateUrl: getContexto(CONST) + "/pages/aposta/",
                        controller: "ApostaController"
                });
                $routeProvider.when("/aposta/:id", {
                        templateUrl: getContexto(CONST) + "/pages/aposta/{id}",
                        controller: "ApostaController"
                });

        }
]);
