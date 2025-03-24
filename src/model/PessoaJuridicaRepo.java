package model;


/**
 *
 * @author Roberto Birro Alves Filho
 */
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        this.pessoasJuridicas = new ArrayList<>();
    }
    
     public void persistir(String nomeArquivo) throws IOException{
          try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(pessoasJuridicas);
        }
    }
     
      public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            this.pessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
        pessoasJuridicas.add(pessoaJuridica);
    }

    public void alterar(PessoaJuridica pessoaJuridicaAtualizada) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            if (pessoasJuridicas.get(i).getId() == pessoaJuridicaAtualizada.getId()) {
                pessoasJuridicas.set(i, pessoaJuridicaAtualizada);
                return;
            }
        }
        System.out.println("Pessoa Jurídica com ID " + pessoaJuridicaAtualizada.getId() + " não encontrada.");
    }


   public void excluir(int id) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            if (pessoasJuridicas.get(i).getId() == id) {
                pessoasJuridicas.remove(i);
                return;
            }
        }
        System.out.println("Pessoa Jurídica com ID " + id + " não encontrada.");
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pessoaJuridica : pessoasJuridicas) {
            if (pessoaJuridica.getId() == id) {
                return pessoaJuridica;
            }
        }
        return null; // Retorna null se a pessoa jurídica não for encontrada
    }

   public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoasJuridicas); // Retorna uma nova lista para evitar modificações externas
    }

    
}