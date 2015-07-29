appMain.service("Aposta", ["Restangular", "CONST", function (Restangular, CONST) {
                this.salvar = function (aposta) {
                        return Restangular.one("pages/aposta/salvar").customPOST(aposta);
                };
        }]);

appMain.controller("ApostaController", ["$scope", "CONST", "Aposta", "Usuario",
        function ($scope, CONST, Aposta, Usuario) {
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
                                        addMensagemSucesso($scope, "Aposta cadastrada com sucesso!");
                                        delete $scope.aposta;
                                        delete $scope.palpite;
                                }, function (result) {
                                        addMensagemRetornoValidacao($scope, result);
                                });
                        }
                };

                $scope.init = function () {
                        Usuario.buscarTodos().then(function (result) {
                                $scope.usuarios = result.lista;
                        }, function (result) {
                                delete $scope.usuarios;
                        });
                }
                $scope.init();
                limparMensagens($scope);
        }]);


