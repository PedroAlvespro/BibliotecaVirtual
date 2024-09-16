package relatorioeestatistica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioGeral {
    
    public void maisEmprestados() {
        String pastaEmprestimosPath = "C:\\bibliotecavirtujava\\src\\emprestimos";
        File pastaEmprestimos = new File(pastaEmprestimosPath);

        if (!pastaEmprestimos.exists() || !pastaEmprestimos.isDirectory()) {
            System.err.println("Diretório de empréstimos não encontrado: " + pastaEmprestimosPath);
            return;
        }

        File[] arquivosEmprestimos = pastaEmprestimos.listFiles();
        if (arquivosEmprestimos == null || arquivosEmprestimos.length == 0) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }

        Map<String, Integer> emprestimosPorLivro = new HashMap<>();

        // Ler todos os arquivos de empréstimos e contar as ocorrências de cada livro
        for (File arquivo : arquivosEmprestimos) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                String tituloLivro = null;
                
                while ((linha = reader.readLine()) != null) {
                    if (linha.startsWith("Título do livro: ")) {
                        tituloLivro = linha.substring(17).trim();
                    }
                }

                // Se o título do livro foi encontrado, atualiza a contagem
                if (tituloLivro != null) {
                    emprestimosPorLivro.put(tituloLivro, emprestimosPorLivro.getOrDefault(tituloLivro, 0) + 1);
                }
            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo de empréstimo: " + e.getMessage());
            }
        }

        // Ordenar os livros pelo número de empréstimos em ordem decrescente
        List<Map.Entry<String, Integer>> livrosOrdenados = emprestimosPorLivro.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .collect(Collectors.toList());

        // Exibir os livros mais emprestados
        if (livrosOrdenados.isEmpty()) {
            System.out.println("Nenhum livro foi emprestado.");
        } else {
            System.out.println("Livros mais emprestados:");
            for (Map.Entry<String, Integer> entry : livrosOrdenados) {
                System.out.println("Título: " + entry.getKey() + " - Número de empréstimos: " + entry.getValue());
            }
        }
    }
    public void estatisticaGeral() {
        String pastaLivrosPath = "C:\\bibliotecavirtujava\\src\\livros";
        String pastaEmprestimosPath = "C:\\bibliotecavirtujava\\src\\emprestimos";
        
        File pastaLivros = new File(pastaLivrosPath);
        File pastaEmprestimos = new File(pastaEmprestimosPath);

        if (!pastaLivros.exists() || !pastaLivros.isDirectory()) {
            System.err.println("Diretório de livros não encontrado: " + pastaLivrosPath);
            return;
        }
        
        if (!pastaEmprestimos.exists() || !pastaEmprestimos.isDirectory()) {
            System.err.println("Diretório de empréstimos não encontrado: " + pastaEmprestimosPath);
            return;
        }

        // Contar livros disponíveis
        int quantidadeLivrosDisponiveis = 0;
        File[] arquivosLivros = pastaLivros.listFiles();
        if (arquivosLivros != null) {
            quantidadeLivrosDisponiveis = arquivosLivros.length;
        }

        // Contar o total de empréstimos
        int totalEmprestimos = 0;
        File[] arquivosEmprestimos = pastaEmprestimos.listFiles();
        if (arquivosEmprestimos != null) {
            totalEmprestimos = arquivosEmprestimos.length;
        }

        // Exibir a estatística geral
        System.out.println("Estatística Geral:");
        System.out.println("Temos: " + quantidadeLivrosDisponiveis);
        System.out.println("E emprestamos: " + totalEmprestimos);
    }
}
