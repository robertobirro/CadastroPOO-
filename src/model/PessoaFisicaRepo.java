package model;


/**
 *
 * @author Roberto Birro Alves Filho
 */
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        this.pessoasFisicas = new ArrayList<>();
    }

    
     public void persistir(String nomeArquivo) throws  IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasFisicas);
             System.out.println("Dados de Pessoa Física Armazenados. " );
         
        } catch (IOException e) {
            System.err.println("Erro ao persistir dados de Pessoa Física: " + e.getMessage());
        }
    }
     
     
     
    public void recuperar(String nomeArquivo) throws  IOException, ClassNotFoundException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            this.pessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
            System.out.println("Dados de Pessoa Física recuperados. " );
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar dados de Pessoa Física: " + e.getMessage());
        }
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

   public void alterar(PessoaFisica pessoaFisicaAtualizada) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            if (pessoasFisicas.get(i).getId() == pessoaFisicaAtualizada.getId()) {
                pessoasFisicas.set(i, pessoaFisicaAtualizada);
                return;
            }
        }
        System.out.println("Pessoa Física com ID " + pessoaFisicaAtualizada.getId() + " não encontrada.");
    }
    public void excluir(int id) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            if (pessoasFisicas.get(i).getId() == id) {
                pessoasFisicas.remove(i);
                return;
            }
        }
        System.out.println("Pessoa Física com ID " + id + " não encontrada.");
    }

    public PessoaFisica obter(int id) {
        for (PessoaFisica pessoaFisica : pessoasFisicas) {
            if (pessoaFisica.getId() == id) {
                return pessoaFisica;
            }
        }
        System.out.println("Pessoa Física com ID " + id + " não encontrada.");
        return null;
    }

     public List<PessoaFisica> obterTodos() {
        return new ArrayList<>(pessoasFisicas); // Retorna uma nova lista para evitar modificações externas
    }

    
}