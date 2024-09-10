import cadastrousuarios.LoginPermissoes;
import cadastrousuarios.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario usuario = new LoginPermissoes("Antonio Pedro Alves Fernandes ", "pedro@example.com", "senha123", true);

        // Cria o arquivo do usuário
        usuario.criarArquivoUsuario();
    }
}
