var appMain = angular.module("appMain", ["ngRoute", "ngCookies", "ngResource", "restangular", "ui.bootstrap"]);

//constante
appMain.constant("CONST", {
        //CONTEXTO: "http://localhost:8084/pages"
        CONTEXTO: "",
        QTDREGISTROPAGINACAO: 3
});

appMain.run(function ($rootScope, $templateCache, $route, Login) {
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
                //a toda rota verifica sessao expirada
                Login.verificar().then(function (result) {
                        if(result == null || result.mensagem=='NOK'){
                                //guarda a rota anterior para pegar os dados novamente em caso de sucesso.
                                globalRotaAnterior = $route;
                                return abrirLogin();
                        }
                });
        });
});

function getContexto(constant) {
        return constant.CONTEXTO;
}

function getParamCache() {
        return "?" + new Date().getTime();
}

function abrirLogin(){
        limparMensagensLogin();
        $('#login_modal').modal({
                keyboard: false,
                backdrop: 'static'
        });
        return false;
}