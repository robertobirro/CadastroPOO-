package model;

import java.io.Serializable;


/**
 *
 * @author Roberto Birro Alves Filho
 */
public class PessoaFisica extends Pessoa implements Serializable{
  private String cpf;
    private int idade;

    // Construtor padrão
    public PessoaFisica() {
        super(); // Chama o construtor padrão da classe Pessoa
    }

    // Construtor completo
    public PessoaFisica(int id, String nome, String cpf, int idade) {
        super(id, nome); // Chama o construtor da classe Pessoa
        this.cpf = cpf;
        this.idade = idade;
    }

    // Método exibir polimórfico (sobrescreve o método da classe Pessoa)
    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe Pessoa
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade);
    }

    // Getters
    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    // Setters
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    
}