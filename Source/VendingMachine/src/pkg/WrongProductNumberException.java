package pkg;

public class WrongProductNumberException extends Exception {

    private static final long serialVersionUID = 1L;

    public WrongProductNumberException(String str) {
        super(str);
    }
}
