appMain.service("Aposta", ["Restangular", "CONST", function (Restangular, CONST) {
                this.buscar = function () {
                        return Restangular.one( getContexto(CONST)+"pages/apostas/").get();
                };
                this.salvar = function (aposta) {
                        return Restangular.one( getContexto(CONST)+"pages/aposta/").customPOST(aposta);
                };
                this.editar = function (id) {
                        return Restangular.one( getContexto(CONST)+"pages/aposta", id).get();
                };
                this.deletar = function (id) {
                        return Restangular.one( getContexto(CONST)+"pages/aposta", id).remove();
                };
        }
]);