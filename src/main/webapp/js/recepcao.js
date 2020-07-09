$(document).ready(function(){

    $("#bt_normalFila").click(function(){

        mensagemAjax("0");
               
    });

    $("#bt_preferencialFila").click(function(){

        mensagemAjax("1");
       
    });

   
}); 

function mensagemAjax(msn){
    let senha_chamada = "";
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            let obj = (this.responseText);
            
             $("#lb_atendimentoFila").html(obj);
         }
    };
    xhttp.open("POST", "http://localhost:9090/rest/teste/add", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send('{"tipo": "'+msn+'"}');
    /*
    $.get("../model/callMsn.jsp?msnCliente="+msn, function(retornoDoServidor){
        $("#lb_atendimentoFila").html(retornoDoServidor);
    });
    setTimeout(function(){
        $.get("../model/callMsn.jsp?msnCliente="+msn, function(retornoDoServidor){
            $("#lb_atendimentoFila").html(retornoDoServidor);
        });
    }, 3000);
    */
}