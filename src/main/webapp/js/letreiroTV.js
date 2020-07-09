$(document).ready(function(){

    //setInterval(() => mensagemAjax("chamado") , 3000);
    setInterval(() => mensagemAjax() , 3000);
   
      document.onkeyup=function(e){

        if(e.which == 17){//tecla 'Ctrl'
          window.location.href = "../control/gerente.jsp";
          return false;
        }

        if(e.which == 18){//tecla 'Alt'
          window.location.href = "../control/recepcao.jsp";
       return false;
     }
     
     }
});


//function mensagemAjax(msn){
  function mensagemAjax(){
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

