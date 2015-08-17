var appMain = angular.module("appMain", ["ngRoute", "ngCookies", "ngResource", "restangular", "ui.bootstrap"]);

//constante
appMain.constant("CONST", {
        //CONTEXTO: "http://localhost:8084/pages"
        CONTEXTO: "",
        QTDREGISTROPAGINACAO: 3
});

appMain.run(function ($rootScope, $templateCache, Login) {
        $rootScope.$on('$routeChangeStart', function (event, next, current) {
                
                //forca o angular a reinvocar os templates, sempre forcando a verificacao de sessao logada nas rotas.
                if (typeof (current) !== 'undefined') {
                        $templateCache.remove(current.templateUrl);
                }
                
                //a toda rota verifica sessao expirada
                Login.verificar().then(function (result) {
                        if(result == null || result.mensagem=='NOK'){
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