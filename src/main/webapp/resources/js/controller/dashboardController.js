appMain.controller("DashboardController", ["$scope", "$location", "Aposta", "CONST",
        function ($scope, $location, Aposta, CONST) {

                $scope.apostaForm = {paginaAtual: '', dataInicial: null, dataFinal: null, quantidadeRegistro: CONST.QTDREGISTROPAGINACAO, totalRegistro: ''};
                $scope.deletar = function (id) {
                        Aposta.deletar(id).then(function (result) {
                                addMensagemRetorno($scope, result);
                                $scope.apostas = result.lista;
                        }, function (result) {
                                addMensagemRetorno($scope, result);
                        });
                };

                $scope.editar = function (id) {
                        $location.path("/aposta/").search({id: id});
                };

                $scope.buscar = function () {
                        Aposta.buscarPaginaAtual($scope.apostaForm).then(function (result) {
                                $scope.apostas = result.lista;
                                $scope.totalItems = result.form.totalRegistros;
                        }, function (result) {
                                $scope.msg = result;
                        });

                }

                $scope.$watch('apostaForm.paginaAtual', function () {
                        $scope.buscar();
                });

                $scope.init = function () {
                        $scope.apostaForm.paginaAtual = 1;
                        $scope.buscar();
                }

                $scope.init();
        }
]);


