appMain.controller("UsuarioController", ["$scope", "$routeParams", "Usuario",
        function ($scope, $routeParams, Usuario) {
                limparTela($scope);
                
                $scope.salvar = function () {
                        Usuario.salvar($scope.usuario).then(function (result) {
                                addMensagemRetorno($scope, result);
                                limparTela($scope);
                        }, function (result) {
                                addMensagemRetorno($scope, result);
                        });
                };

                var acao = $routeParams.acao;
                if (acao == 'buscar') {
                        $scope.init = function () {
                                Usuario.buscarLogado().then(function (result) {
                                        $scope.usuario = result.modelo;
                                }, function (result) {
                                        addMensagemRetorno($scope, result);
                                        limparTela($scope);
                                });
                        }
                        $scope.init();
                }
                limparMensagens($scope);
        }
]);


function limparTela($scope){
        $scope.usuario = {nome: '', login: '', senha: ''};
}

