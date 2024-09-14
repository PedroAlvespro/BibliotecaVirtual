package cadastrousuarios;

public interface UserInterface {
    //métodos que serão implementados
    public void criarArquivoUsuario (String nome, String email, String senha);
    public boolean login(String emailLog, String senhaLog);
}
