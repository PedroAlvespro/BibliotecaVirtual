package cadastrousuarios;
import java.util.Scanner;

public class User {



    public static void menuUsuario () {    

        Scanner res = new Scanner(System.in);
        System.out.println("digite 1 para cadastrar novo usuario, 2 para entrar na conta e 0- para sair");
        int roll = res.nextInt();
        res.nextLine();

        
            switch (roll) {
                case 0:
                res.close();
                break;
                
                case 1:
    
                System.out.println("digite seu nome: ");
                String nome = res.nextLine();
                System.out.println("digite seu email: ");
                String email = res.nextLine();
                System.out.println("digite sua senha: ");
                String senha = res.nextLine();

                ModeloUsuario usuario = new ModeloUsuario(nome,email,senha);
                usuario.criarArquivoUsuario(nome,email,senha);

                case 2:
                System.out.println("digite seu nome");
                String nomeLog = res.nextLine();
               System.out.println("digite seu e-mail");
               String emailLog = res.nextLine();
               System.out.println("digite sua senha");
               String senhaLog = res.nextLine();
               
               ModeloUsuario usuario4 = new ModeloUsuario(nomeLog, emailLog, senhaLog);
               usuario4.login(emailLog, senhaLog);
               if(usuario4.login(emailLog, senhaLog) == true){
                Login menu = new Login();
                menu.MenuLogin();
               } else{
                System.out.println("cadastre-se");
               }
                   
                default:
                    break;
            }
            }


}