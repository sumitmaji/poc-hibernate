package ${package_name}.server.exception;

public class BusinessException extends Exception{

public BusinessException(String message){
super(message);
}

public BusinessException(String message, Throwable cause){
super(message,cause);
}
}
