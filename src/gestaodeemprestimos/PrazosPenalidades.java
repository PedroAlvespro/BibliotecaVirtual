package gestaodeemprestimos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrazosPenalidades {
    
    public void penalidade(String email) {
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
                    String vencimentoStr = linha.substring("Data de vencimento: ".length()).trim(); // Ajuste para extrair a data corretamente
                    try {
                        dataVencimento = dateFormat.parse(vencimentoStr);
                    } catch (ParseException e) {
                        System.err.println("Erro ao analisar a data de vencimento: " + e.getMessage());
                        return; // Sai do método se a data estiver mal formatada
                    }
                }
            }

            if (dataVencimento != null) {
                // Calcula o atraso
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
}
