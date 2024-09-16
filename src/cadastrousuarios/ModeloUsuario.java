package cadastrousuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ModeloUsuario implements UserInterface{
     
                protected String nome;
                protected String email;
                protected String senha;
                
                public ModeloUsuario(String nome, String email, String senha){
                    this.nome = nome;
                    this.email = email;
                    this.senha = senha;
                }

                @Override
                public void criarArquivoUsuario (String nome, String email, String senha) {
                    // Define o caminho da pasta e o nome do arquivo
                    String pastaPath = "C:\\bibliotecavirtujava\\src\\arquivos";
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
                    } catch (IOException e) {
                        System.err.println("Erro ao criar o arquivo: " + e.getMessage());
                    }
                }
                    // Método para verificar se existe um arquivo com o email e senha
                   @Override
                    public boolean login(String emailLog, String senhaLog) {
                        String pastaPath = "C:\\bibliotecavirtujava\\src\\arquivos";
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
      
        
    }
    

