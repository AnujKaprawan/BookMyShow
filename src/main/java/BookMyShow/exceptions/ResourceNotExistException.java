package BookMyShow.exceptions;

public class ResourceNotExistException extends RuntimeException{
    public ResourceNotExistException(String message){
        super(message);
    }
}
