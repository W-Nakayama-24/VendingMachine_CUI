package pkg;

public class WrongProductIdException extends Exception {

    private static final long serialVersionUID = 1L;

    public WrongProductIdException(String str) {
        super(str);
    }
}
