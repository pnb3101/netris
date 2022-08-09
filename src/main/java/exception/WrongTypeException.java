package exception;

public class WrongTypeException extends Exception{

    public WrongTypeException (){
        super();
    }

    public WrongTypeException (String e){
        super(e);
    }
}
