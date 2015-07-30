appMain.service("Dashboard", ["Restangular", "CONST", function (Restangular, CONST) {
                this.buscar = function () {
                        return Restangular.one( getContexto(CONST)+"pages/dashboard/apostas/").get();
                };
        }
]);