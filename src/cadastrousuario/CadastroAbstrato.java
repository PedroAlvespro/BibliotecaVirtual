package cadastrousuario;

public abstract class CadastroAbstrato {
    protected String nome;
    protected String email;
    protected String senha;

    /* construtores*/
    public CadastroAbstrato(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void Cadastrar(String nome, String email, String senhas ){
        
    }

  
}
