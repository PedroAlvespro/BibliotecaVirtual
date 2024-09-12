package cadastrousuarios;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;


public abstract class Usuario {
    protected String nome;
    protected String email;
    protected String senha;
    protected boolean UsuarioOuAdm;

    protected static int contadorArquivos = 0;

    
    public Usuario (String nome, String email, String senha, boolean UsuarioOuAdm){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.UsuarioOuAdm = UsuarioOuAdm;
    }


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public boolean UsuarioOuAdm() {
        return UsuarioOuAdm;
    }

    //criar arquivo de usuario e escrever nele o nome, email, senha e se é usuário ou adm
     // Método para criar o arquivo do usuário e escrever as informações
 
     public void criarArquivoUsuario() {
        // Define o caminho da pasta e o nome do arquivo
        String pastaPath = "C:\\projetojavabiblioteca\\src\\arquivos";
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

  //método de contagem de arquivos para ser usado no for, para buscar validação
  // Método estático para incrementar o contador de arquivos
  //eu preciso desse método para rodar dentro de um for que irá percorrer todos os arquivos

    // Método estático para obter o número total de arquivos criados
    public static int getContadorArquivos() {
    return contadorArquivos;
}

    public abstract String getPermissao();

    //método de validação de adm ou usuário
 public boolean validando(String email, String senha) {
    File pasta = new File("C:\\projetojavabiblioteca\\src\\arquivos");
    File[] arquivos = pasta.listFiles((dir, name) -> name.endsWith("_usuario.txt"));

    if (arquivos != null) {
        for (File arquivo : arquivos) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                String emailArquivo = null;
                String senhaArquivo = null;
                
                while ((linha = reader.readLine()) != null) {
                    if (linha.startsWith("Email: ")) {
                        emailArquivo = linha.substring("Email: ".length());
                    } else if (linha.startsWith("Senha: ")) {
                        senhaArquivo = linha.substring("Senha: ".length());
                    }
                }

                if (emailArquivo != null && senhaArquivo != null &&
                    emailArquivo.equals(email) && senhaArquivo.equals(senha)) {
                    return true; // Email e senha encontrados e correspondem
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        }
    }

    return false; // Nenhum arquivo correspondente com email e senha corretos foi encontrado
}

public void LoginPermissoes(boolean isValido) {
    if (!isValido) {
        System.out.println("Usuário não encontrado");
    } else {
        System.out.println("Usuário autenticado com sucesso");
    }
}



}



