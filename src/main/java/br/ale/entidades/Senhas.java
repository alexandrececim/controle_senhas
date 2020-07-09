package br.ale.entidades;

public class Senhas {
    private String senhasTexto;
    private int tipo;
    private boolean atendido;

    public boolean getAtendido() {
        return this.atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }


    public String getSenhasTexto() {
        return this.senhasTexto;
    }

    public void setSenhasTexto(String senhasTexto) {
        this.senhasTexto = senhasTexto;
    }

    

}