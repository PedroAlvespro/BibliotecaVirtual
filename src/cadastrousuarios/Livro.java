package cadastrousuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class Livro {
    
    public void emprestimo(String email, String senha, String tituloLivro, String autor, double ISBN) {
    // Define o caminho da pasta de livros
    String pastaLivrosPath = "C:\\bibliotecavirtujava\\src\\livros";
    File pastaLivros = new File(pastaLivrosPath);
    File[] arquivosLivros = pastaLivros.listFiles();

    if (arquivosLivros == null || arquivosLivros.length == 0) {
        System.out.println("Nenhum livro encontrado no sistema.");
        return;
    }

    // Variável para armazenar a quantidade de estoque
    int quantidadeEstoque = -1;
    File arquivoLivroAtualizado = null;
    
    // Verificar se o livro existe e ler a quantidade no estoque
    for (File arquivo : arquivosLivros) {
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            String tituloArquivo = null;
            String autorArquivo = null;
            int estoqueAtual = -1;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Titulo do Livro: ")) {
                    tituloArquivo = linha.substring(16).trim();
                } else if (linha.startsWith("Nome autor: ")) {
                    autorArquivo = linha.substring(12).trim();
                } else if (linha.startsWith("quantidade no estoque: ")) {
                    estoqueAtual = Integer.parseInt(linha.substring(23).trim());
                }
            }

            // Verificar se o título e o autor coincidem
            if (tituloLivro.equalsIgnoreCase(tituloArquivo) && autor.equalsIgnoreCase(autorArquivo)) {
                quantidadeEstoque = estoqueAtual;
                arquivoLivroAtualizado = arquivo;
                break;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo do livro: " + e.getMessage());
        }
    }

    // Verificar se o livro foi encontrado e se há estoque disponível
    if (arquivoLivroAtualizado != null && quantidadeEstoque > 0) {
        // Atualizar o estoque no arquivo do livro
        try {
            List<String> linhasArquivo = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivoLivroAtualizado))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    // Atualizar a quantidade no estoque
                    if (linha.startsWith("quantidade no estoque: ")) {
                        linha = "quantidade no estoque: " + (quantidadeEstoque - 1);
                    }
                    linhasArquivo.add(linha);
                }
            }

            // Reescrever o arquivo com a nova quantidade de estoque
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoLivroAtualizado))) {
                for (String linha : linhasArquivo) {
                    writer.write(linha);
                    writer.newLine();
                }
            }
            System.out.println("Estoque atualizado com sucesso. Nova quantidade: " + (quantidadeEstoque - 1));
        } catch (IOException e) {
            System.err.println("Erro ao atualizar o arquivo do livro: " + e.getMessage());
        }

        // Registra o empréstimo após atualizar o estoque
        String pastaEmprestimosPath = "C:\\bibliotecavirtujava\\src\\emprestimos";
        File pastaEmprestimos = new File(pastaEmprestimosPath);
        String nomeArquivoEmprestimo = email + "_emprestimo.txt"; // Nome do arquivo de empréstimo
        File arquivoEmprestimo = new File(pastaEmprestimos, nomeArquivoEmprestimo);

        // Cria a pasta "emprestimos" se não existir
        if (!pastaEmprestimos.exists()) {
            pastaEmprestimos.mkdirs();
        }

        // Calcula a data de vencimento (30 dias a partir da data atual)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date dataVencimento = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String vencimentoFormatado = dateFormat.format(dataVencimento);

        // Escreve os detalhes do empréstimo no arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoEmprestimo))) {
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
            System.out.println("Empréstimo registrado com sucesso: " + arquivoEmprestimo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao registrar o empréstimo: " + e.getMessage());
        }
    } else if (quantidadeEstoque == 0) {
        System.out.println("Não há mais exemplares disponíveis deste livro.");
    } else {
        System.out.println("Livro não encontrado.");
    }
}


     // Método para exibir o histórico de empréstimos de um usuário
     public void historicoEmprestimos(String email, String senha) {
        String pastaPath = "C:\\bibliotecavirtujava\\src\\emprestimos";
        File arquivo = new File(pastaPath, email + "_emprestimo.txt");

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

