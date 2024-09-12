import cadastrousuarios.LoginPermissoes;

public class Main {
    public static void main(String[] args) {
       LoginPermissoes usuario = new LoginPermissoes("Nthrti", "nacddningatinho@gmail.com", "miau maiu miau", true);
        
        // Cria o arquivo do usuário
        usuario.criarArquivoUsuario();
    
        boolean isValido = usuario.validando("joao@exemplo.com", "senha123");

        // Verificando permissões
        usuario.LoginPermissoes(isValido);

        // Exibindo permissões
        System.out.println("Permissão: " + usuario.getPermissao());        
    }

   
}
