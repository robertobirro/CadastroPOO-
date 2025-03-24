package cadastropoo;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;


/**
 *
 * @author Roberto Birro Alves Filho
 */
public class CadastroPOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo pessoaFisicaRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pessoaJuridicaRepo = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Incluir");
            System.out.println("2 - Alterar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Obter por ID");
            System.out.println("5 - Obter todos");
            System.out.println("6 - Salvar dados");
            System.out.println("7 - Recuperar dados");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            
            

            switch (opcao) {
                case 1:
                    incluir(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                    break;
                case 2:
                    alterar(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                    break;
                case 3:
                    excluir(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                    break;
                case 4:
                    obterPorId(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                    break;
                case 5:
                    obterTodos(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                    break;
                case 6:
                    salvarDados(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                    break;
                case 7:
                    recuperarDados(scanner, pessoaFisicaRepo, pessoaJuridicaRepo);
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
  private static void recuperarDados(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
            System.out.print("Prefixo do arquivo: ");
            String prefixo = scanner.nextLine();
            try {
                pessoaFisicaRepo.recuperar(prefixo + ".fisica.bin");
                pessoaJuridicaRepo.recuperar(prefixo + ".juridica.bin");
                System.out.println("Dados recuperados com sucesso.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Erro ao recuperar dados: " + e.getMessage());
            }

            System.out.println("\nPessoas Físicas recuperadas:");
            List<PessoaFisica> pessoasFisicas = pessoaFisicaRepo.obterTodos();
            for (PessoaFisica pessoa : pessoasFisicas) {
                pessoa.exibir();
                System.out.println("---");
            }

            System.out.println("\nPessoas Jurídicas recuperadas:");
            List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaRepo.obterTodos();
            for (PessoaJuridica pessoa : pessoasJuridicas) {
                pessoa.exibir();
                System.out.println("---");
            }
        }
    private static void incluir(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
        System.out.print("Tipo (F/J): ");
        String tipo = scanner.nextLine();
        if (tipo.equalsIgnoreCase("F")) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            scanner.nextLine();
            pessoaFisicaRepo.inserir(new PessoaFisica(id, nome, cpf, idade));
        } else if (tipo.equalsIgnoreCase("J")) {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            pessoaJuridicaRepo.inserir(new PessoaJuridica(id, nome, cnpj));
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void alterar(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
        System.out.print("Tipo (F/J): ");
        String tipo = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (tipo.equalsIgnoreCase("F")) {
            PessoaFisica pessoa = pessoaFisicaRepo.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nova Idade: ");
                int idade = scanner.nextInt();
                scanner.nextLine();
                pessoaFisicaRepo.alterar(new PessoaFisica(id, nome, cpf, idade));
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo.equalsIgnoreCase("J")) {
            PessoaJuridica pessoa = pessoaJuridicaRepo.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
                System.out.print("Novo Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CNPJ: ");
                String cnpj = scanner.nextLine();
                pessoaJuridicaRepo.alterar(new PessoaJuridica(id, nome, cnpj));
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void excluir(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
        System.out.print("Tipo (F/J): ");
        String tipo = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (tipo.equalsIgnoreCase("F")) {
            pessoaFisicaRepo.excluir(id);
        } else if (tipo.equalsIgnoreCase("J")) {
            pessoaJuridicaRepo.excluir(id);
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void obterPorId(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
        System.out.print("Tipo (F/J): ");
        String tipo = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (tipo.equalsIgnoreCase("F")) {
            PessoaFisica pessoa = pessoaFisicaRepo.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else if (tipo.equalsIgnoreCase("J")) {
            PessoaJuridica pessoa = pessoaJuridicaRepo.obter(id);
            if (pessoa != null) {
                pessoa.exibir();
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void obterTodos(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
        System.out.print("Tipo (F/J): ");
        String tipo = scanner.nextLine();
        if (tipo.equalsIgnoreCase("F")) {
            List<PessoaFisica> pessoas = pessoaFisicaRepo.obterTodos();
            for (PessoaFisica pessoa : pessoas) {
                pessoa.exibir();
                System.out.println("---");
            }
        } else if (tipo.equalsIgnoreCase("J")) {
            List<PessoaJuridica> pessoas = pessoaJuridicaRepo.obterTodos();
            for (PessoaJuridica pessoa : pessoas) {
                pessoa.exibir();
                System.out.println("---");
            }
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    private static void salvarDados(Scanner scanner, PessoaFisicaRepo pessoaFisicaRepo, PessoaJuridicaRepo pessoaJuridicaRepo) {
        System.out.print("Prefixo do arquivo: ");
        String prefixo = scanner.nextLine();
        try {
            pessoaFisicaRepo.persistir(prefixo + ".fisica.bin");
            pessoaJuridicaRepo.persistir(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}
