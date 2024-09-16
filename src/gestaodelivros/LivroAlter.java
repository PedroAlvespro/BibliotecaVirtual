package gestaodelivros;

public interface LivroAlter {
    public void  EditarLivro(String tituloLivroA, String TituloA, String AutorA,
     double ISBNA, int QuantidadeEstoqueA, String GeneroLivroA);
    public void criarArquivoLivro(String tituloLivro, String autor, double ISBN, int quantidadeEstoque, String generoLivro);
    public void deletarArquivoLivro(String tituloLivro);
    public boolean MenuUtilAdms(String emailLog, String senhaLog);
    public boolean login(String emailLog, String senhaLog);
    public void SistemaDeBusca();


}
