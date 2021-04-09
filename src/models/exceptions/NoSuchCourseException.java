package models.exceptions;

public class NoSuchCourseException extends Exception{
    public NoSuchCourseException(String message){
        super(message);
    }
}
