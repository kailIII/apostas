appMain.service("Usuario", ["Restangular", "CONST", function (Restangular, CONST) {
	this.salvar = function (usuario) {
		return Restangular.one("pages/usuario/salvar").customPOST(usuario);
	};
}]);

appMain.controller("UsuarioController", ["$scope", "CONST", "Usuario", 
	function($scope, CONST, Usuario) {
                $scope.usuario = {nome:'',login:'', senha:''};
                $scope.salvar = function (){
                        $scope.mensagem = "";
                        Usuario.salvar($scope.usuario).then(function(result){
                                $scope.mensagem = "Usuário cadastrado com sucesso!";
                                $("#div_mensagem").removeClass();
                                $("#div_mensagem").addClass("alert alert-success");
                        }, function(result){
                                if(result.status == 403){
                                        $("#div_mensagem").removeClass();
                                        $("#div_mensagem").addClass("alert alert-warning");
                                        var arr = result.data.mensagens;
                                        jQuery.each(arr, function(i, msg) {
                                                $scope.mensagem = $scope.mensagem + " " + msg.chave +" - "+ msg.valor;
                                        });
                                        
                                }else{
                                        $("#div_mensagem").removeClass();
                                        $("#div_mensagem").addClass("alert alert-danger");
                                        var arr = result.data.mensagens;
                                        jQuery.each(arr, function(i, msg) {
                                                $scope.mensagem = $scope.mensagem + " " + msg.valor;
                                        });
                                }
                        });
                };
}]);    


