appMain.service("Usuario", ["Restangular", "CONST", function (Restangular, CONST) {
                this.salvar = function (usuario) {
                        return Restangular.one( getContextoPages(CONST)+"/usuario/").customPOST(usuario);
                };

                this.buscarTodos = function () {
                        return Restangular.one( getContextoPages(CONST)+"/usuario/todos").get();
                };

                this.buscarLogado = function () {
                        return Restangular.one( getContextoPages(CONST)+"/usuario/logado").get();
                }
        }
]);
