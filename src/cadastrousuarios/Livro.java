package cadastrousuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Livro {
    //método de empréstimo
    public void emprestimo(String email, String senha, String tituloLivro, String autor, double ISBN) {
        // Define o caminho da pasta e o nome do arquivo de empréstimo
        String pastaPath = "C:\\projetojavabiblioteca\\src\\emprestimos";
        File pasta = new File(pastaPath);
        String nomeArquivoEmprestimo = email + "_emprestimo.txt"; // Nomeia o arquivo com base no email
        File arquivo = new File(pasta, nomeArquivoEmprestimo);

        // Cria a pasta "emprestimos" se não existir
        if (!pasta.exists()) {
            pasta.mkdirs();
        }

        // Calcula a data de vencimento (30 dias a partir da data atual)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dataVencimento = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String vencimentoFormatado = dateFormat.format(dataVencimento);

        // Escreve os detalhes do empréstimo no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo))) {
            writer.write("Email do usuário: " + email);
            writer.newLine();
            writer.write("Senha: " + senha);
            writer.newLine();
            writer.write("Título do livro: " + tituloLivro);
            writer.newLine();
            writer.write("Nome do autor: " + autor);
            writer.newLine();
            writer.write("ISBN: " + ISBN);
            writer.newLine();
            writer.write("Data de vencimento: " + vencimentoFormatado);
            writer.newLine();
            writer.write("Status: Livro emprestado");
            writer.newLine();
            System.out.println("Empréstimo registrado com sucesso: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao registrar o empréstimo: " + e.getMessage());
        }
    }
   
     // Método para exibir o histórico de empréstimos de um usuário
     public void historicoEmprestimos(String email, String senha) {
        // Define o caminho do arquivo de empréstimos com base no email
        String pastaPath = "C:\\projetojavabiblioteca\\src\\emprestimos";
        File arquivo = new File(pastaPath, email + "_emprestimo.txt");

        // Verifica se o arquivo existe
        if (!arquivo.exists()) {
            System.err.println("Nenhum histórico de empréstimos encontrado para o usuário: " + email);
            return;
        }

        // Lê o arquivo e exibe o histórico
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean usuarioValido = false;
            while ((linha = reader.readLine()) != null) {
                // Verifica se o email e a senha correspondem
                if (linha.contains("Email do usuário: " + email)) {
                    usuarioValido = true;
                }
                if (usuarioValido && linha.contains("Senha: " + senha)) {
                    // Exibe o conteúdo do arquivo
                    while ((linha = reader.readLine()) != null) {
                        System.out.println(linha);
                    }
                    return;
                }
            }
            System.err.println("Email ou senha incorretos.");
        } catch (IOException e) {
            System.err.println("Erro ao ler o histórico de empréstimos: " + e.getMessage());
        }
    }

}

