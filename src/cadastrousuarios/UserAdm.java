package cadastrousuarios;

import java.util.Scanner;

public class UserAdm {

    public static void menuUsuario() {
        Scanner res = new Scanner(System.in); // Criação do Scanner fora do loop
        int roll;

        try {
            while (true) {
                System.out.println("Digite 1 para cadastrar novo usuário, 2 para entrar na conta e 0 para sair");
                roll = res.nextInt();
                res.nextLine(); // Consome a nova linha após o inteiro

                switch (roll) {
                    case 0:
                        System.out.println("Saindo do programa.");
                       break;

                    case 1:
                        System.out.println("Digite seu nome: ");
                        String nome = res.nextLine();
                        System.out.println("Digite seu e-mail: ");
                        String email = res.nextLine();
                        System.out.println("Digite sua senha: ");
                        String senha = res.nextLine();
                        
                        ModeloAdm usuario = new ModeloAdm(nome, email, senha);
                        usuario.criarArquivoAdm(nome, email, senha);

                    case 2:
                        System.out.println("Digite seu nome: ");
                        String nomeLog = res.nextLine();
                        System.out.println("Digite seu e-mail: ");
                        String emailLog = res.nextLine();
                        System.out.println("Digite sua senha: ");
                        String senhaLog = res.nextLine();

                        ModeloAdm adm = new ModeloAdm(nomeLog, emailLog, senhaLog);
                        if (adm.login(emailLog, senhaLog)) { // Verifica o login
                            if (!adm.VerificaAdmPermissoes()) { // Verifica permissões
                                System.out.println("Permissões insuficientes.");
                            } else {
                                System.out.println("Acesso concedido.");
                                // Aqui você pode adicionar a lógica para o menu do administrador
                            }
                        } else {
                            System.out.println("Login falhou.");
                        }

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        } finally {
            res.close(); // Garante que o Scanner seja fechado
        }
    }
}
