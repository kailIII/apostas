appMain.service("Usuario", ["Restangular", "CONST", function (Restangular, CONST) {
                this.salvar = function (usuario) {
                        return Restangular.one( getContexto(CONST)+"pages/usuario/").customPOST(usuario);
                };

                this.buscarTodos = function () {
                        return Restangular.one( getContexto(CONST)+"pages/usuario/todos").get();
                };

                this.buscarLogado = function () {
                        return Restangular.one( getContexto(CONST)+"pages/usuario/logado").get();
                }
        }
]);
