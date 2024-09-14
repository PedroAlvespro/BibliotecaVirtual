package cadastrousuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class ModeloAdm implements AdmInterface{

                protected String nome;
                protected String email;
                protected String senha;
                
                public ModeloAdm(String  nome, String email, String senha){
                    this.nome = nome;
                    this.email = email;
                    this.senha = senha;
                }
                @Override
                public void criarArquivoAdm (String nome, String email, String senha) {
                    // Define o caminho da pasta e o nome do arquivo
                    String pastaPath = "C:\\projetojavabiblioteca\\src\\arquivosadm";
                    File pasta = new File(pastaPath);
                    String nomeArquivo = nome + "_usuario.txt";
                    File arquivo = new File(pasta, nomeArquivo);
            
                    // Cria a pasta "arquivos" se não existir
                    if (!pasta.exists()) {
                        pasta.mkdirs();
                    }
            
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
                        writer.write("Nome: " + nome);
                        writer.newLine();
                        writer.write("Email: " + email);
                        writer.newLine();
                        writer.write("Senha: " + senha);
                        writer.newLine();
                        System.out.println("Arquivo criado com sucesso: " + arquivo.getAbsolutePath());
                        System.out.printf("Adm %s criado com sucesso", nome);
                    } catch (IOException e) {
                        System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                    }
                   
                
                }

                    // Método para verificar se existe um arquivo com o email e senha
                public boolean login(String emailLog, String senhaLog) {
                String pastaPath = "C:\\projetojavabiblioteca\\src\\arquivosadm";
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
                        if (email.equals(emailArquivo) && senha.equals(senhaArquivo)) {
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

    //método caso o usuário seja true para acessar os livros
    @Override
    public boolean VerificaAdmPermissoes(){
        if (login(email, senha) != true){
            System.out.println("você não é um adm");
            return false;
        } 
         else { 
            int opc;
            while (true){
            Scanner cs = new Scanner(System.in);
            System.out.println("digite 1 para criar livro, 2- para deletar e 3 para alterar livro ou 0-para sair");
            opc = cs.nextInt();
            cs.nextLine();

            switch (opc) {
                case 0:
                cs.close();
                break;
                case 1:
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
                    continue;
                case 2:
                System.out.println("digite o titulo do livro");
                String tituloLivroDeletado = cs.nextLine();
                deletarArquivoLivro(tituloLivroDeletado);
                continue;
                default:
                    break;
                    
            }
            return true;  
        }
            
             
        }
    }

    //método de criar arquivo 
    @Override
    public void criarArquivoLivro(String tituloLivro, String autor, double ISBN, int quantidadeEstoque, String generoLivro) {
        // Define o caminho da pasta e o nome do livro
        String pastaPath = "C:\\projetojavabiblioteca\\src\\livros";
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

    //método de deletar arquivo 
    @Override
    public void deletarArquivoLivro(String tituloLivro) {
        // Define o caminho da pasta e o nome do arquivo do livro a ser deletado
        String pastaPath = "C:\\projetojavabiblioteca\\src\\livros";
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
    




}
