import java.util.Scanner;

import cadastrousuarios.User;
import cadastrousuarios.UserAdm;
import relatorioeestatistica.RelatorioGeral;

public class Main {
    public static void main(String[] args) {
        //menu main para cadastro e ver estatísticas
        int resposta;
        do {
          
            Scanner scr = new Scanner(System.in);
            System.out.println("digite 1- para prosseguir como usuario, 2- para adm, 3- para ver os livros mais lidos e 4- para estatísticas 0- sair");
            resposta = scr.nextInt();
                if (resposta ==1){
                    User user = new User();  // Criar instância da classe User
                    user.menuUsuario();  
                } else if(resposta == 2){
                     UserAdm.menuUsuario();
                } else if (resposta == 3) {
                    RelatorioGeral ver = new RelatorioGeral();
                    ver.maisEmprestados();
                } else if (resposta == 4) {
                    RelatorioGeral v = new RelatorioGeral();
                    v.estatisticaGeral();
                }
                 
             
        } while (resposta !=0);
}
}
