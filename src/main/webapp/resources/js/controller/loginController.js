//-------------------------------
// PARA QUEDA DE SESSAO COM AJAX
//-------------------------------
appMain.controller("LoginController", function (Login) {
        var ctrl = this;
        ctrl.login = {j_username: '', j_password: ''};

        ctrl.logar = function () {
                Login.logar(ctrl.login).then(function (result) {
                        if(result !=null){
                                //o retorno sera uma pagina de login com a mensagem setada pelo spring sec.
                                var msgSecurity = $($.parseHTML( result )).find("#msg_security");
                                
                                //caso contenha mensagem, exibe no modal.
                                if(msgSecurity!=null && msgSecurity.length > 0){
                                        var msg = msgSecurity.text();
                                        if(msg!=null && msg!=''){
                                                $("#div_mensagem_login").removeClass("hide");
                                                $("#div_mensagem_login").html(msg);
                                                return;
                                        }
                                }
                        }
                        $('#login_modal').modal('hide');
                }, function (result) {
                        $("#div_mensagem_login").removeClass("hide");
                        $("#div_mensagem_login").html(result);
                });
        };
});

function limparMensagensLogin(){
        $("#div_mensagem_login").html("");
        $("#div_mensagem_login").addClass("hide");
}
