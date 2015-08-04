var appMain = angular.module("appMain", ["ngRoute", "ngCookies", "ngResource", "restangular", "ui.bootstrap"]);

// CONSTANTES
appMain.constant("CONST", {
    //CONTEXTO: "http://localhost:8084/pages"
    CONTEXTO: "",
    QTDREGISTROPAGINACAO: 3
});

function getContexto(constant) {
    return constant.CONTEXTO;
}

