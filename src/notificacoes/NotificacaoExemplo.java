package notificacoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificacaoExemplo {

    public void notifica(String email) {
        String pastaPath = "C:\\bibliotecavirtujava\\src\\emprestimos";
        File arquivo = new File(pastaPath, email + "_emprestimo.txt");

        if (!arquivo.exists()) {
            System.err.println("Nenhum histórico de empréstimos encontrado para o usuário: " + email);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            boolean usuarioValido = false;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataVencimento = null;
            
            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("Email do usuário: " + email)) {
                    usuarioValido = true;
                }
                if (usuarioValido && linha.startsWith("Data de vencimento: ")) {
                    String vencimentoStr = linha.substring("Data de vencimento: ".length()).trim();
                    try {
                        dataVencimento = dateFormat.parse(vencimentoStr);
                    } catch (ParseException e) {
                        System.err.println("Erro ao analisar a data de vencimento: " + e.getMessage());
                        return;
                    }
                }
            }

            if (dataVencimento != null) {
                Date hoje = new Date();
                long diffMillis = hoje.getTime() - dataVencimento.getTime();
                long diffDays = diffMillis / (24 * 60 * 60 * 1000);
                
                if (diffDays > 0) {
                    double multa = diffDays * 1.0; // 1 real por dia de atraso
                    System.out.println("O livro está atrasado em " + diffDays + " dias. Multa de R$ " + multa);
                } else {
                    System.out.println("O livro está em dia.");
                }
            } else {
                System.err.println("Nenhuma data de vencimento encontrada para o usuário: " + email);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o histórico de empréstimos: " + e.getMessage());
        }
    }

    public void notificaNovidades() {
        String pastaPath = "C:\\bibliotecavirtujava\\src\\livros"; // Diretório dos livros adicionados recentemente
        File pasta = new File(pastaPath);

        if (!pasta.exists() || !pasta.isDirectory()) {
            System.err.println("Diretório de livros não encontrado: " + pastaPath);
            return;
        }

        File[] arquivos = pasta.listFiles();
        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        File livroMaisRecente = null;
        long ultimaModificacao = Long.MIN_VALUE; // Inicializa com o menor valor possível

        for (File arquivo : arquivos) {
            if (arquivo.isFile() && arquivo.lastModified() > ultimaModificacao) {
                livroMaisRecente = arquivo;
                ultimaModificacao = arquivo.lastModified();
            }
        }

        if (livroMaisRecente != null) {
            String nomeArquivo = livroMaisRecente.getName();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataModificacaoStr = dateFormat.format(new Date(livroMaisRecente.lastModified()));
            System.out.println("Último livro adicionado: " + nomeArquivo + " (Data de adição: " + dataModificacaoStr + ")");
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }

}

