package cadastrousuarios;

public interface AdmInterface {
    //métodos que serão usados 
    public void criarArquivoAdm (String nome, String email, String senha);
    public boolean login(String emailLog, String senhaLog);
    public boolean VerificaAdmPermissoes();
    public void criarArquivoLivro(String tituloLivro, String autor, double ISBN, int quantidadeEstoque, String generoLivro);
    public void deletarArquivoLivro(String tituloLivro);
}
