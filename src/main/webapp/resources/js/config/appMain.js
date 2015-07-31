var appMain = angular.module("appMain", ["ngRoute", "ngResource", "restangular"]);

// CONSTANTES
appMain.constant("CONST", {
    //CONTEXTO: "http://localhost:8084/pages"
    CONTEXTO: ""
});

function getContexto(constant) {
    return constant.CONTEXTO;
}

