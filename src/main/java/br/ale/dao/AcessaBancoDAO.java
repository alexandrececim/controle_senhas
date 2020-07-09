package br.ale.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import br.ale.config.DB;
import br.ale.entidades.Senhas;

public class AcessaBancoDAO {

    public int quantidadeAtendidas(String tipo) throws Exception{
        Connection conX = DB.getConnection();
        int resultado = 0;
        String sql = "select COUNT(*) from fila_de_chamadas where tipo="+tipo;
        PreparedStatement acessaBanco = conX.prepareStatement(sql);
        ResultSet executaAcesso = acessaBanco.executeQuery();

        if(executaAcesso.next()){
            resultado = executaAcesso.getInt(1);
        }
        conX.close(); 
        return resultado;
    }

    public String ultimoAtendidoLista() throws Exception{
        Connection conX = DB.getConnection();
        String sql = "SELECT * FROM fila_de_chamadas where status='true'";
 
        PreparedStatement acessaBanco = conX.prepareStatement(sql);
        ResultSet rs = acessaBanco.executeQuery();
        String resultado = "";
        while (rs.next()) {
           resultado = (rs.getString("senha"));
            
        }
        conX.close(); 
        return resultado;
    }
   

    public String add(Senhas senha) throws Exception {
        String resultado = null;
        Connection conX = DB.getConnection();
 
        String sql = "insert into fila_de_chamadas (senha, tipo, status) values (?, ?, ?)";
 
        PreparedStatement acessaBanco = conX.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        if(senha.getTipo() == 1){
            acessaBanco.setString(1, senha.getSenhasTexto());
        }
            
        else{
            acessaBanco.setString(1, senha.getSenhasTexto());
        }
            

        acessaBanco.setInt(2, senha.getTipo());
        acessaBanco.setBoolean(3, false);
        
        acessaBanco.execute();
         
        ResultSet rs = acessaBanco.getGeneratedKeys();
        if (rs.next()) {
            resultado = rs.getString("senha");
        }
        conX.close(); 
        return resultado;
    }

    public List<Senhas> carregaLista() throws Exception{
        List<Senhas> lista = new ArrayList<>();
        Connection conX = DB.getConnection();
        String sql = "SELECT * FROM fila_de_chamadas";
 
        PreparedStatement acessaBanco = conX.prepareStatement(sql);
        ResultSet rs = acessaBanco.executeQuery();
        while (rs.next()) {
            Senhas senha = new Senhas();
            senha.setSenhasTexto(rs.getString("senha"));
            senha.setTipo(rs.getInt("tipo"));
            senha.setAtendido(rs.getBoolean("status"));
            
 
            lista.add(senha);
        }
        conX.close(); 
        return lista;
    }
    public void editar(Senhas senha) throws Exception {
        Connection conX = DB.getConnection();
 
        String sql = "UPDATE fila_de_chamadas SET status = ? WHERE senha = ?";
 
        PreparedStatement acessaBanco = conX.prepareStatement(sql);
        acessaBanco.setBoolean(1, true);
        acessaBanco.setString(2, senha.getSenhasTexto());
        acessaBanco.execute();
        conX.close(); 
    }

    public void deletar() throws Exception {
        Connection conX = DB.getConnection();
 
        String sql = "DELETE FROM fila_de_chamadas";
 
        PreparedStatement acessaBanco = conX.prepareStatement(sql);
        
        acessaBanco.execute();
        conX.close(); 
    }
    
}