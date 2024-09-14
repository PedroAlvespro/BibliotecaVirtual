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
            switch (resposta) {
                case 0:
                break;
                 case 1:
                 User.menuUsuario();
                 break;
     
                 case 2:
                UserAdm.menuUsuario();
                 break;
                
    
                 default:
                 System.out.println("impossível");
                 break;
             }
        } while (resposta !=0);
 
    
    
}
}
