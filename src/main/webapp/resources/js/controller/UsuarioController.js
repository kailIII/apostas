appMain.service("Usuario", ["Restangular", "CONST", function (Restangular, CONST) {
	this.salvar = function (usuario) {
		return Restangular.one("pages/usuario/salvar").customPOST(usuario);
	};
        
        this.buscarTodos = function () {
		return Restangular.one("pages/usuario/buscar-todos").get();
	};
        
        this.buscarLogado = function(){
                return Restangular.one("pages/usuario/buscar-logado").get();
        }
}]);

appMain.controller("UsuarioController", ["$scope", "$routeParams", "CONST", "Usuario", 
	function($scope, $routeParams, CONST, Usuario) {
                $scope.usuario = {nome:'',login:'', senha:''};
                $scope.salvar = function (){
                        Usuario.salvar($scope.usuario).then(function(result){
                                addMensagemSucesso($scope, "Usuário cadastrado com sucesso!");
                                delete $scope.usuario;
                        }, function(result){
                                addMensagemRetornoValidacao($scope, result);
                        });
                };
                
                var acao = $routeParams.acao;
                if(acao == 'buscar'){
                        $scope.init = function(){
                                Usuario.buscarLogado().then(function(result) {
                                        $scope.usuario = result.modelo;
                                }, function(result) {
                                        addMensagemRetornoValidacao($scope, result);
                                        delete $scope.usuario;
                                });
                        }
                        $scope.init();
                }
                limparMensagens($scope);
}]);    


