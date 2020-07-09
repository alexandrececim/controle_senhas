$(document).ready(function(){

    $("#bt_resetarFila").click(function(){
      
      mensagemAjax(1);
        
    });

    $("#bt_chamarFila").click(function(){
       
       mensagemAjax(2);
       

    });

   

}); 

function mensagemAjax(msn){
    let senha_chamada = "";
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            let obj = (this.responseText);
            
            // $("#lb_atendimentoFila").html(obj);
            ultimoAtendido();
         }
    };
    if(msn == 2)
        xhttp.open("PUT", "http://localhost:9090/rest/teste/atender/1", true);
    else
        xhttp.open("DELETE", "http://localhost:9090/rest/teste/delete/1", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send('{}');
    
}

function ultimoAtendido(){
    let senha_chamada = "";
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
            let obj = JSON.parse(this.responseText);
            
            obj.forEach(function(o, obj){ //senhasTexto
              if(o.atendido == true)
                senha_chamada = o.senhasTexto;
            });
             $("#lb_atendimentoFila").html(senha_chamada);
         }
    };
    xhttp.open("GET", "http://localhost:9090/rest/teste/lista", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send("{}");
    
}
