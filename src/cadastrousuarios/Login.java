package cadastrousuarios;

import java.util.Scanner;

public class Login {

   public void MenuLogin(){
    
    int inte;
    Scanner sc = new Scanner(System.in);
    
    while (true) {
        System.out.println("1- Fazer empréstimo, 2 - Ver histórico de empréstimo, 0 - Sair");
        inte = sc.nextInt();
        sc.nextLine();

        switch (inte) {
            case 1:
              System.out.println("digite seu e-mail");
              String emailE = sc.nextLine();
              System.out.println("digite sua senha");
              String senhaE = sc.nextLine();
              System.out.println("digite o titulo do livro");
              String tituloLivroE = sc.nextLine();
              System.out.println("digite o autor");
              String autorE = sc.nextLine();
              System.out.println("digite o ISBN");
              double ISBNE = sc.nextDouble();
             Livro livros2 = new LivroUtils();
             livros2.emprestimo(emailE, senhaE,  tituloLivroE, autorE,  ISBNE);
                break;
            
            case 2:
            System.out.println("digite seu e-mail");
            String emailLog = sc.nextLine();
            System.out.println("digite sua senha");
            String senhaLog = sc.nextLine();
            Livro livros = new LivroUtils();
            livros.historicoEmprestimos(emailLog,senhaLog);
                break;
            
            case 0:
            sc.close();
                break;
            
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
        
    } 
   

   }
        
    }


