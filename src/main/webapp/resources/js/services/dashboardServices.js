appMain.service("Dashboard", ["Restangular", "CONST", function (Restangular, CONST) {
                this.buscar = function () {
                        return Restangular.one( getContextoPages(CONST)+"/dashboard/apostas/").get();
                };
        }
]);