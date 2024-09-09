package cadastrousuarios;

public class ExceptionAllUtil extends Exception{
    public ExceptionAllUtil (String Message){
        super(Message);
    }

    public ExceptionAllUtil (){
        System.out.println("Email Inválido");
    }

}
