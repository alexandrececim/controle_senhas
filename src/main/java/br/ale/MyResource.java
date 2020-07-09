
package br.ale;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.ale.entidades.Senhas;
import br.ale.entidades.RegrasChamadas;
import br.ale.dao.AcessaBancoDAO;

/**
 * Classe responsavel por receber as requisições REST bem
 * como direcionar paara o serviço correspondente
 */
@Path("/teste")
public class MyResource {
    private RegrasChamadas banco_regras;
    private AcessaBancoDAO banco_lista;

    @PostConstruct
    private void init() {
        banco_regras = new RegrasChamadas();
        banco_lista = new AcessaBancoDAO();
    }

    @GET
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Senhas> getIt() {

        List<Senhas> lista = null;
        try {
            lista = banco_lista.carregaLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces("text/plain")
    public String addPost(Senhas senha) {
        String msg = "";
 
         try {
            if(senha.getTipo() == 1)
                msg = banco_regras.addFilaPreferencial();
            else
                msg = banco_regras.addFilaNormal();
            
        } catch (Exception e) {
            msg = "Erro ao add a Senha de chamada!";
            e.printStackTrace();
        }
 
        return msg;
    }

    
    @PUT
    @Path("/atender/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String editarNota(Senhas senha, @PathParam("id") int id) {
        String msg = "";
         
        try {
            if(id == 1)
            msg = banco_regras.atendeSenha();             
                
        } catch (Exception e) {
            msg = "Erro ao chamar senha!";
            e.printStackTrace();
        }
         
        return msg;
    }
     
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String removerNota(@PathParam("id") int id) {
        String msg = "";
         
        try {
            if(id == 1)
            banco_lista.deletar();;
             
            msg = "Zerado com sucesso!";
        } catch (Exception e) {
            msg = "Erro ao remover a fila!";
            e.printStackTrace();
        }
         
        return msg;
    }
}
