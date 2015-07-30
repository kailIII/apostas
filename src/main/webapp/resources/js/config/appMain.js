var appMain = angular.module("appMain", ["ngRoute", "ngResource", "restangular"]);

// CONSTANTES
appMain.constant("CONST", {
        //CONTEXTO: "http://localhost:8084/pages"
        CONTEXTO: "/pages"
});

function getContextoPages(constant){
        return constant.CONTEXTO;
}
