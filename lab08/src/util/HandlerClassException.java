package util;

public class HandlerClassException extends Exception {
    private String classe;
    private String metodo;
    private String mensagem;

    public HandlerClassException() {
        super();
    }

    public HandlerClassException(String classe, String metodo, String mensagem, String erroOriginal) {
        super(erroOriginal);
        this.classe = classe;
        this.metodo = metodo;
        this.mensagem = mensagem;
    }

    public String getClasse() {
        return classe;
    }

    public String getMetodo() {
        return metodo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
