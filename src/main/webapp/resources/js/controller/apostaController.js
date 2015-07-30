appMain.controller("ApostaController", ["$scope","$routeParams", "Aposta", "Usuario",
        function ($scope, $routeParams, Aposta, Usuario) {
                $scope.aposta = {descricao: '', dateFinalizacao: null, palpites: []};
                $scope.palpite = {descricao: '', usuario: ''};
                
                $scope.adicionarPalpite = function () {
                        if ($scope.palpite.descricao != '' && $scope.palpite.usuario != '') {
                                $scope.aposta.palpites.push($scope.palpite);
                                delete $scope.palpite;
                        } else {
                                addMensagemValidacao($scope, "Verifique os campos obrigatórios: Usuário e Palpite");
                        }
                };

                $scope.salvar = function () {
                        if ($scope.aposta.palpites.length == 0) {
                                addMensagemValidacao($scope, "Adicione pelo menos um palpite");
                        } else {
                                Aposta.salvar($scope.aposta).then(function (result) {
                                        addMensagemRetorno($scope, result);
                                        delete $scope.aposta;
                                        delete $scope.palpite;
                                }, function (result) {
                                        addMensagemRetorno($scope, result);
                                });
                        }
                };

                $scope.init = function () {
                        var id = $routeParams.id;
                        if(angular.isDefined(id)){
                                Aposta.editar(id).then(function (result) {
                                        $scope.aposta = result.modelo;
                                }, function (result) {
                                        addMensagemRetorno($scope, result);
                                        delete $scope.aposta;
                                });
                        }
                        //carrega a combo de usuarios utilizadas no cadastro de apostas
                        Usuario.buscarTodos().then(function (result) {
                                $scope.usuarios = result.lista;
                        }, function (result) {
                                addMensagemRetorno($scope, result);
                                delete $scope.usuarios;
                        });
                };
                $scope.init();
                limparMensagens($scope);
        }
]);


