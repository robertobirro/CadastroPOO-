package model;

import java.io.Serializable;


/**
 *
 * @author Roberto Birro Alves Filho
 */
public class PessoaJuridica extends Pessoa implements Serializable{
    private String cnpj;
    
            

    // Construtor padrão
    public PessoaJuridica() {
        super(); // Chama o construtor padrão da classe Pessoa
    }

    // Construtor completo
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome); // Chama o construtor da classe Pessoa
        this.cnpj = cnpj;
    }

    // Método exibir polimórfico (sobrescreve o método da classe Pessoa)
    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe Pessoa
        System.out.println("CNPJ: " + cnpj);
    }

    // Getter
    public String getCnpj() {
        return cnpj;
    }

    // Setter
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

   
}
