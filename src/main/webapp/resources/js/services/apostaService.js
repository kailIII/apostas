appMain.service("Aposta", ["Restangular", "CONST", function (Restangular, CONST) {
                this.salvar = function (aposta) {
                        return Restangular.one( getContexto(CONST)+"pages/aposta/").customPOST(aposta);
                };
        }
]);