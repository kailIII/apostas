appMain.controller("DashboardController", ["$scope", "$location", "Aposta", "CONST",
    function ($scope, $location, Aposta, CONST) {
        
        $scope.apostaForm = {paginaAtual: '', dataInicial: null, dataFinal: null, quantidadeRegistro: CONST.QTDREGISTROPAGINACAO, totalRegistro:''};
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
        
        $scope.buscar = function(){
            Aposta.buscarPaginaAtual($scope.apostaForm).then(function (result) {
                $scope.apostas = result.lista;
                $scope.totalItems = result.apostaForm.totalRegistro;
            });
            
        }
        
        $scope.buscarTotalItems = function () {
            Aposta.buscar().then(function (result) {

                //configura a paginacao
                $scope.totalItems = result.lista.length;
                console.log($scope.apostas);
            }, function (result) {
                $scope.msg = result;
            });
        };
        
        $scope.$watch('apostaForm.paginaAtual', function () {
            Aposta.buscarPaginaAtual($scope.apostaForm).then(function (result) {
                $scope.apostas = result.lista;
            });
        });

        $scope.init = function () {

            $scope.buscarTotalItems();
            $scope.apostaForm.paginaAtual = 1;

        }

        $scope.init();
    }
]);


