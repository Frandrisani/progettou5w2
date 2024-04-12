package francescoandrisani.progettou5w2.exceptions;
import francescoandrisani.progettou5w2.payloads.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    //Errore generato quando non si ritrova un determinato elemento in una ricerca
    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO hendleNotFound(NotFound ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }


    // Errore generato lato server - Bad Request
    @ExceptionHandler(BadRequestExceptions.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO hendleBadRequest(BadRequestExceptions ex){
        if(ex.getErrorList() != null){
            String message = ex.getErrorList().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));
            return new ErrorDTO(message, LocalDateTime.now());

        }else{
            return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
        }

    }


    // Errore generico
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO genericError(Exception ex){
        ex.printStackTrace();
        return new ErrorDTO("Errore! Il server non risponde - contattare assistenza", LocalDateTime.now());

    }
}
