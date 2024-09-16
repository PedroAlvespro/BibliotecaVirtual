package cadastrousuarios;

public interface AdmInterface {
    //métodos que serão usados 
    public void criarArquivoAdm (String nome, String email, String senha);
    public boolean login(String emailLog, String senhaLog);
    
}
