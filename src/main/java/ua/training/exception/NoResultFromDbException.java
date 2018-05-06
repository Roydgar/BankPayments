package ua.training.exception;

public class NoResultFromDbException extends Exception{
    public NoResultFromDbException(String message) {
        super(message);
    }
}
