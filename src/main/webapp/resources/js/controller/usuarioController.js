appMain.controller("UsuarioController", ["$scope", "$routeParams", "Usuario",
        function ($scope, $routeParams, Usuario) {
                $scope.usuario = {nome: '', login: '', senha: ''};
                $scope.salvar = function () {
                        Usuario.salvar($scope.usuario).then(function (result) {
                                addMensagemRetorno($scope, result);
                                delete $scope.usuario;
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
                                        delete $scope.usuario;
                                });
                        }
                        $scope.init();
                }
                limparMensagens($scope);
        }
]);


