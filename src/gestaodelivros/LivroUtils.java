package gestaodelivros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import gestaodeemprestimos.PrazosPenalidades;

public abstract class LivroUtils implements LivroAlter{


   
    public boolean MenuUtilAdms(String emailLog, String senhaLog){
        if (login(emailLog,senhaLog) != true){
            System.out.println("você não é um adm");
            return false;
        } 
         else { 
            int opc;
            do{
            Scanner cs = new Scanner(System.in);
            System.out.println("digite 1 para criar livro, 2- para deletar e 3 para alterar livro, 4 para ver todos os empréstimos ou 0-para sair");
            opc = cs.nextInt();
            cs.nextLine();

            if(opc == 1){

                System.out.println("digite o titulo do livro");
                String tituloLivro = cs.nextLine();
                System.out.println("digite o autor do livro");
                String autor = cs.nextLine();
                System.out.println("digite o ISBN do livro");
                double ISBN = cs.nextDouble();
                cs.nextLine();
                System.out.println("digite a quantidade de livros no estoque");
                int quantidadeEstoque = cs.nextInt();
                cs.nextLine();
                System.out.println("digite o gênero do livro");
                String generoLivro = cs.nextLine();
                criarArquivoLivro(tituloLivro,autor,ISBN,quantidadeEstoque,generoLivro);
               
            } else if (opc == 2) {
                System.out.println("digite o titulo do livro");
                String tituloLivroDeletado = cs.nextLine();
                deletarArquivoLivro(tituloLivroDeletado);
            } else if (opc == 3) {
                System.out.println("digite o titulo do livro");
                String tituloLivroA = cs.nextLine();
                System.out.println("digite o autor do livro");
                String autorA = cs.nextLine();
                System.out.println("digite o ISBN do livro");
                double ISBNA = cs.nextDouble();
                cs.nextLine();
                System.out.println("digite a quantidade de livros no estoque");
                int quantidadeEstoqueA = cs.nextInt();
                cs.nextLine();
                System.out.println("digite o gênero do livro");
                String generoLivroA = cs.nextLine();
                EditarLivro(tituloLivroA, tituloLivroA, autorA, ISBNA, quantidadeEstoqueA, generoLivroA);
            } else if (opc == 4) {
                PrazosPenalidades AdmListar = new PrazosPenalidades();
                AdmListar.listarTodosEmprestimos();
            }
             else{
                System.out.println("  ");
            }
              
                    
            } while(opc!=0);
            return true;  
        } 
            
        }
    
             
    public boolean login(String emailLog, String senhaLog) {
                String pastaPath = "C:\\bibliotecavirtujava\\src\\arquivosadm";
                File pasta = new File(pastaPath);
            
                 // Listar todos os arquivos na pasta
                 File[] arquivos = pasta.listFiles();
            
                 if (arquivos != null) {
                     for (File arquivo : arquivos) {
                         try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                            String linha;
                             String emailArquivo = null;
                            String senhaArquivo = null;
                        
                        // Ler o arquivo linha por linha
                        while ((linha = reader.readLine()) != null) {
                            if (linha.startsWith("Email: ")) {
                                emailArquivo = linha.substring(7);  // Pega o email após "Email: "
                            } else if (linha.startsWith("Senha: ")) {
                                senhaArquivo = linha.substring(7);  // Pega a senha após "Senha: "
                            }
                        }
                        
                        // Comparar o email e a senha fornecidos com os armazenados
                        if (emailLog.equals(emailArquivo) && senhaLog.equals(senhaArquivo)) {
                            System.out.println("Login bem-sucedido!");
                            return true;
                        }
                        
                    } catch (IOException e) {
                        System.err.println("Erro ao ler o arquivo: " + e.getMessage());
                    }
                }
            }
            
            // Se não encontrar nenhuma correspondência
            System.out.println("Email ou senha incorretos.");
            return false;
    }

    
    public void deletarArquivoLivro(String tituloLivro) {
        // Define o caminho da pasta e o nome do arquivo do livro a ser deletado
        String pastaPath = "C:\\bibliotecavirtujava\\src\\livros";
        File pasta = new File(pastaPath);
        String nomeArquivoLivro = tituloLivro + "_usuario.txt";
        File arquivo = new File(pasta, nomeArquivoLivro);
    
        // Verifica se o arquivo existe antes de tentar deletar
        if (arquivo.exists()) {
            if (arquivo.delete()) {
                System.out.println("Arquivo de livro deletado com sucesso: " + arquivo.getAbsolutePath());
            } else {
                System.err.println("Erro ao deletar o arquivo: " + arquivo.getAbsolutePath());
            }
        } else {
            System.err.println("Arquivo não encontrado: " + arquivo.getAbsolutePath());
        }
    }

  
    public void criarArquivoLivro(String tituloLivro, String autor, double ISBN, int quantidadeEstoque, String generoLivro) {
        // Define o caminho da pasta e o nome do livro
        String pastaPath = "C:\\bibliotecavirtujava\\src\\livros";
        File pasta = new File(pastaPath);
        String nomeArquivoLivro = tituloLivro + "_usuario.txt";
        File arquivo = new File(pasta, nomeArquivoLivro);

        // Cria a pasta "arquivos" se não existir
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Titulo do Livro: " + tituloLivro);
            writer.newLine();
            writer.write("Nome autor: " + autor);
            writer.newLine();
            writer.write("ISBN: " + ISBN);
            writer.newLine();
            writer.write("quantidade no estoque: " + quantidadeEstoque);
            writer.newLine();
            writer.write("genero do livro: " + generoLivro);
            writer.newLine();
            System.out.println("Arquivo de livro criado com sucesso: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    public void EditarLivro(String tituloLivro, String novoTitulo, String novoAutor, double novoISBN, int novaQuantidadeEstoque, String novoGeneroLivro) {
        // Define o caminho da pasta e o nome do livro
        String pastaPath = "C:\\bibliotecavirtujava\\src\\livros";
        File pasta = new File(pastaPath);
        String nomeArquivoLivro = tituloLivro + "_usuario.txt";
        File arquivo = new File(pasta, nomeArquivoLivro);
    
        // Verifica se o arquivo do livro existe
        if (!arquivo.exists()) {
            System.out.println("Arquivo do livro não encontrado: " + tituloLivro);
            return;
        }
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Titulo do Livro: " + novoTitulo);
            writer.newLine();
            writer.write("Nome autor: " + novoAutor);
            writer.newLine();
            writer.write("ISBN: " + novoISBN);
            writer.newLine();
            writer.write("Quantidade no estoque: " + novaQuantidadeEstoque);
            writer.newLine();
            writer.write("Gênero do livro: " + novoGeneroLivro);
            writer.newLine();
            System.out.println("Informações do livro editadas com sucesso: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao editar o arquivo: " + e.getMessage());
        }
    }
    
    public void SistemaDeBusca() {
        Scanner ea = new Scanner(System.in);
        int opcm;
    
        do {
            System.out.println("1 - Buscar por título, 2 - Buscar por autor, 3 - Buscar por categoria, 0 - Sair");
            opcm = ea.nextInt();
            ea.nextLine(); // Consumir a linha extra
    
            String filtro = "";
            String valorBusca = "";
    
            switch (opcm) {
                case 1:
                    System.out.println("Digite o título do livro:");
                    filtro = "titulo";
                    valorBusca = ea.nextLine();
                    break;
                case 2:
                    System.out.println("Digite o nome do autor:");
                    filtro = "autor";
                    valorBusca = ea.nextLine();
                    break;
                case 3:
                    System.out.println("Digite o gênero (categoria) do livro:");
                    filtro = "genero";
                    valorBusca = ea.nextLine();
                    break;
                case 0:
                    System.out.println("Saindo do sistema de busca.");
                    return; // Sair do método quando a opção for 0
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    continue; // Reiniciar o loop em caso de opção inválida
            }
    
            // Executar a busca com base no filtro e valor informados
            buscarLivro(filtro, valorBusca);
    
        } while (opcm != 0);
    }
    
    public void buscarLivro(String filtro, String valor) {
        // Define o caminho da pasta onde os arquivos de livros estão armazenados
        String pastaPath = "C:\\bibliotecavirtujava\\src\\livros";
        File pasta = new File(pastaPath);
    
        // Verifica se a pasta existe
        if (!pasta.exists()) {
            System.out.println("Pasta de livros não encontrada.");
            return;
        }
    
        // Lista todos os arquivos da pasta
        File[] arquivos = pasta.listFiles();
    
        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo encontrado na pasta de livros.");
            return;
        }
    
        boolean encontrado = false;
    
        for (File arquivo : arquivos) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                String tituloLivro = null;
                String autorLivro = null;
                String generoLivro = null;
    
                // Ler o arquivo linha por linha e coletar informações
                while ((linha = reader.readLine()) != null) {
                    if (linha.startsWith("Titulo do Livro: ")) {
                        tituloLivro = linha.substring(16).trim(); // Extrai o título do livro
                    } else if (linha.startsWith("Nome autor: ")) {
                        autorLivro = linha.substring(12).trim(); // Extrai o nome do autor
                    } else if (linha.startsWith("genero do livro: ")) {
                        generoLivro = linha.substring(17).trim(); // Extrai o gênero (categoria)
                    }
                }
    
                // Verifica o filtro aplicado e se corresponde ao valor informado
                if ((filtro.equalsIgnoreCase("titulo") && tituloLivro != null && tituloLivro.equalsIgnoreCase(valor)) ||
                    (filtro.equalsIgnoreCase("autor") && autorLivro != null && autorLivro.equalsIgnoreCase(valor)) ||
                    (filtro.equalsIgnoreCase("genero") && generoLivro != null && generoLivro.equalsIgnoreCase(valor))) {
                    
                    System.out.println("Livro encontrado:");
                    System.out.println("Título: " + tituloLivro);
                    System.out.println("Autor: " + autorLivro);
                    System.out.println("Gênero: " + generoLivro);
                    System.out.println("--------------------------");
                    encontrado = true;
                }
    
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }
    
        if (!encontrado) {
            System.out.println("Nenhum livro encontrado com o filtro: " + filtro + " = " + valor);
        }
    }
    
    
    
    
}
    
    
    
    

    
   

