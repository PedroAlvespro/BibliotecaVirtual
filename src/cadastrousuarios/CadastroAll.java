package cadastrousuarios;

public abstract class CadastroAll {
    protected String nome;
    protected String email;
    protected String senhas;

    /* construtores*/
    public CadastroAll (String nome, String email, String senhas) {
        this.nome = nome;
        this.email = email;
        this.senhas = senhas;
    }


    //método para cadastrar
    public void Cadastrar(String nome, String email, String senhas ) {
        System.out.println("digite seu nome");
        this.nome = nome;
         
        System.out.println("digite seu e-mail");
        this.email = email;   

        System.out.println("digite sua senha");
        this.senhas = senhas;
        
    }

  
}
