package br.ale.entidades;

import java.util.ArrayList;
import java.util.List;

import br.ale.dao.AcessaBancoDAO;

/**
 * Classe que emprega as regras de negocio dentro do que foi 
 * repassado pela requisição Rest tratada na classe MyResource
 */
public class RegrasChamadas extends Senhas {

    private int ultimoAtendido(int tipo) {
        AcessaBancoDAO banco = new AcessaBancoDAO();
        String tipoSenha = String.valueOf(tipo);
        int resultado = 0;
        try {
            resultado = banco.quantidadeAtendidas(tipoSenha);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }

    /**
     * Metodo que adiciona uma senha na fila Normal no formato 'NXXXX' para usuario
     * normal (Onde cada 'X' é um digito numérico).
     */
    public String addFilaNormal() {
        Senhas senha = new Senhas();
        String resultado = "";
        int totalFila = ultimoAtendido(0) + 1;// Adicionado evitando o 'N0000'
        String str = String.format("%04d", totalFila);
        String senhaFormatada = "N" + str;
        System.out.println("**** " + senhaFormatada + " **** | " + ultimoAtendido(0));
        senha.setSenhasTexto(senhaFormatada);
        senha.setTipo(0);
        senha.getAtendido();
        AcessaBancoDAO banco = new AcessaBancoDAO();
        try {
            resultado = banco.add(senha);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }

    /**
     * Metodo que adiciona o ticket na fila Preferencial gerar uma senha(ticket) no
     * formato 'PXXXX' para usuario Preferencial (Onde cada 'X' é um digito
     * numérico).
     */
    public String addFilaPreferencial() {
        Senhas senha = new Senhas();
        String resultado = "";
        int totalFila = ultimoAtendido(1) + 1;// Adicionado evitando $
        String str = String.format("%04d", totalFila);
        String senhaFormatada = "P" + str;
        senha.setSenhasTexto(senhaFormatada);
        senha.setTipo(1);
        senha.getAtendido();
        AcessaBancoDAO banco = new AcessaBancoDAO();
        try {
            resultado = banco.add(senha);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }

    /**
     * Esse metodo é onde se aplica a regra de negocio: O sistema terá que atender
     * somente uma senha de usuario normal depois que todos os usuarios
     * Preferenciais estiverem sido atendidos.
     */
    public String atendeSenha() {
        AcessaBancoDAO banco = new AcessaBancoDAO();
        List<Senhas> fila = new ArrayList<>();
        String resultado = "";
        try {
            fila = banco.carregaLista();
        } catch (Exception e) {
            System.out.println(e);
        }
        int tamanhoFila = fila.size();
        int contaFila = 0;
        int regraPreferencial = 0;// usado para dar inio ao atendimento normal

        do {
            boolean status = fila.get(contaFila).getAtendido();
            String tipoTicket = fila.get(contaFila).getSenhasTexto().substring(0, 1);
            // condição de atendimento preferencial
            if (status == false && tipoTicket.equals("P")) {
                Senhas senha = new Senhas();
                senha.setSenhasTexto(fila.get(contaFila).getSenhasTexto());
                senha.setTipo(fila.get(contaFila).getTipo());
                senha.setAtendido(true);
                // contabiliza o atendimento
                try {
                    banco.editar(senha);
                } catch (Exception e) {
                    System.out.println(e);
                }
                regraPreferencial = 1;// anula o atendimento normal
                break;
            }
            contaFila++;
        } while (tamanhoFila > contaFila);
        if (regraPreferencial == 0) {

            for (int i = 0; tamanhoFila > i; i++) {
                boolean status = fila.get(i).getAtendido();
                String tipoTicket = fila.get(i).getSenhasTexto().substring(0, 1);
                if (status == false && tipoTicket.equals("N")) {
                    Senhas senha = new Senhas();
                    senha.setSenhasTexto(fila.get(i).getSenhasTexto());
                    senha.setTipo(fila.get(i).getTipo());
                    senha.setAtendido(true);
                    // contabiliza o atendimento
                    try {
                        banco.editar(senha);
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    break;
                }
            }
            
      
    }
    try {
        resultado = banco.ultimoAtendidoLista();
    } catch (Exception e) {
        System.out.println(e);
    }
    return resultado;

  }

    
}