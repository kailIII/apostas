appMain.run(function($rootScope) {
    limparMensagens($rootScope);
})

appMain.directive('mensagem', function () {
        return {
                restrict: 'E',
                scope: {
                        valor: "="
                },
                link: function (scope, element, attrs, controller) {
                        scope.$watch('valor', addMensagem, true);
                        
                        function addMensagem (msg) {
                                if (msg != null && ((msg.mensagens != null && msg.mensagens.length > 0) || (msg.mensagem != null && msg.mensagem != ''))) {
                                        if (msg.status == 403) {
                                                addMsgInterna(scope, msg, "alert-warning", true);
                                        } 
                                        else if (msg.status == 200) {
                                                addMsgInterna(scope, msg, "alert-success", false);
                                        }
                                        else {
                                                addMsgInterna(scope, msg, "alert-danger", false);
                                        }
                                }
                        };
                },
                template:
                        '<div id="div_mensagem" class="hide">'+
                        '{{mensagem}}'+
                        '</div>',
                replace: true
        };
});

/**
 * FUNCOES INTERNAS DA DIRECTIVA
 */
function addMsgInterna(scope, msg, alertcss, chaveValor){
        scope.mensagem = '';
        
        $("#div_mensagem").removeClass();
        $("#div_mensagem").addClass("alert "+alertcss);
        var arr = msg.mensagens;
        var txt = msg.mensagem;

        if(arr != null && arr.length > 0){
                jQuery.each(arr, function (i, msg) {
                        if(chaveValor){
                                scope.mensagem = scope.mensagem + " " + msg.chave + " - " + msg.valor;
                        }else{
                                scope.mensagem = scope.mensagem + " " + msg.valor;
                        }
                });
        }else{
                scope.mensagem = txt;
        }
}

/**
 * FUNCOES EXTERNAS
 */
function addMensagemValidacao($scope, mensagem){
        limparMensagens($scope);
        $scope.mensagem.status = 403;
        $scope.mensagem.mensagem = mensagem;
}

function addMensagemSucesso($scope, mensagem){
        limparMensagens($scope);
        $scope.mensagem.status = 200;
        $scope.mensagem.mensagem = mensagem;
}

function addMensagemErro($scope, mensagem){
        limparMensagens($scope);
        $scope.mensagem.status = 500;
        $scope.mensagem.mensagem = mensagem;
}

function addMensagemRetornoValidacao($scope, result){
        limparMensagens($scope);
        $scope.mensagem.status = result.status;
        if(result.status == 404){
                $scope.mensagem.mensagem = "404 - Recurso nao encontrado. Verifique com o RH.";
        }
        else{
                $scope.mensagem.mensagens = result.data.mensagens;
        }
}

function limparMensagens($scope){
        $scope.mensagem = {status: '', mensagem: '', mensagens: []};
}