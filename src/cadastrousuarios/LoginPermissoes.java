package cadastrousuarios;
//essa classe é um objeto da classe abstrata que uso.
public class LoginPermissoes extends Usuario {

    public LoginPermissoes(String nome, String email, String senha, boolean UsuarioOuAdm) {
        super(nome, email, senha,UsuarioOuAdm);
    }

    //pega a permissao 
    public String getPermissao() {
        return UsuarioOuAdm ? "Administrador" : "Usuário";
    }
}
