appMain.service("Aposta", ["Restangular", "CONST", function (Restangular, CONST) {
                this.salvar = function (aposta) {
                        return Restangular.one( getContextoPages(CONST)+"/aposta/salvar").customPOST(aposta);
                };
        }
]);