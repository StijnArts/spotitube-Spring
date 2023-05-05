package han.stijn.spotitube.resource.mapper;

import han.stijn.spotitube.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.mvc.method.annotation.*;

import java.sql.*;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {DatabaseConnectionException.class, IncompleteQueryResultException.class,PreparedStatementException.class})
    protected ResponseEntity<Object> handleInternalServerError(SQLException ex, WebRequest request){
        return handleExceptionInternal(ex,null,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR,request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {TokenNotSetException.class, UserNotFoundException.class})
    protected ResponseEntity<Object> handleRuntime(RuntimeException ex, WebRequest request){
        return handleExceptionInternal(ex,null,new HttpHeaders(),HttpStatus.METHOD_NOT_ALLOWED,request);
    }


}
