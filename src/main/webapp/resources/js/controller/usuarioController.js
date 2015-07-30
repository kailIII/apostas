appMain.controller("UsuarioController", ["$scope", "$routeParams", "Usuario",
        function ($scope, $routeParams, Usuario) {
                $scope.usuario = {nome: '', login: '', senha: ''};
                $scope.salvar = function () {
                        Usuario.salvar($scope.usuario).then(function (result) {
                                addMensagemSucesso($scope, "Usuário cadastrado com sucesso!");
                                delete $scope.usuario;
                        }, function (result) {
                                addMensagemRetornoValidacao($scope, result);
                        });
                };

                var acao = $routeParams.acao;
                if (acao == 'buscar') {
                        $scope.init = function () {
                                Usuario.buscarLogado().then(function (result) {
                                        $scope.usuario = result.modelo;
                                }, function (result) {
                                        addMensagemRetornoValidacao($scope, result);
                                        delete $scope.usuario;
                                });
                        }
                        $scope.init();
                }
                limparMensagens($scope);
        }
]);


