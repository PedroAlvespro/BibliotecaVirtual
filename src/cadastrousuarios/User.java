package cadastrousuarios;
import java.util.Scanner;

import notificacoes.NotificacaoExemplo;

public class User {

    public void menuUsuario() {    
        Scanner res = new Scanner(System.in);
        int roll;
        
        do {
            System.out.println("Digite 1 para cadastrar novo usuário, 2 para entrar na conta e 0 para sair");
            roll = res.nextInt();
            res.nextLine();  

            if (roll == 1) {
                System.out.println("Digite seu nome: ");
                String nome = res.nextLine();
                System.out.println("Digite seu e-mail: ");
                String email = res.nextLine();
                System.out.println("Digite sua senha: ");
                String senha = res.nextLine();

                ModeloUsuario usuario = new ModeloUsuario(nome, email, senha);
                usuario.criarArquivoUsuario(nome, email, senha);

            } else if (roll == 2) {
                System.out.println("Digite seu nome:");
                String nomeLog = res.nextLine();
                System.out.println("Digite seu e-mail:");
                String emailLog = res.nextLine();
                System.out.println("Digite sua senha:");
                String senhaLog = res.nextLine();
                
                ModeloUsuario usuario4 = new ModeloUsuario(nomeLog, emailLog, senhaLog);
                if (usuario4.login(emailLog, senhaLog)) {
    
                    NotificacaoExemplo notificacao = new NotificacaoExemplo();
                    notificacao.notifica(emailLog);
                    notificacao.notificaNovidades();
                    
                    Login menu = new Login();
                    menu.MenuLogin();                 

                } else {
                    System.out.println("Credenciais inválidas. Cadastre-se.");
                }

            } else if (roll != 0) 
            {
                System.out.println("Opção inválida. Tente novamente.");
            }

        } while (roll != 0);
    }
}


