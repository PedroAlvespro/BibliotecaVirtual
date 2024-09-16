import java.util.Scanner;

import cadastrousuarios.User;
import cadastrousuarios.UserAdm;

public class Main {
    public static void main(String[] args) {

        //menu main para cadastro
        int resposta;
        do {
            Scanner scr = new Scanner(System.in);
            System.out.println("digite 1- para prosseguir como usuario, 2- para adm e 0- sair");
            resposta = scr.nextInt();
                if (resposta ==1){
                    User user = new User();  // Criar instância da classe User
                    user.menuUsuario();  
                } else if(resposta == 2){
                     UserAdm.menuUsuario();
                }
                 
             
        } while (resposta !=0);
 
    
    
}
}
