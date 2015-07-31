appMain.controller("DashboardController", ["$scope", "$location", "Aposta",
        function ($scope, $location, Aposta) {
                
                $scope.deletar = function (id) {
                        Aposta.deletar(id).then(function (result) {
                                addMensagemRetorno($scope, result);
                                $scope.apostas = result.lista;
                        }, function (result) {
                                addMensagemRetorno($scope, result);
                        });
                };
                
                $scope.editar = function(id){
                        $location.path("/aposta/").search({id: id});
                };

                $scope.init = function (pagina) {
                        Aposta.buscar(pagina).then(function (result) {
                                $scope.apostas = result.lista;
                        }, function (result) {
                                $scope.msg = result
                        });
                }
                $scope.init();
        }
]);


