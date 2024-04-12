package francescoandrisani.progettou5w2.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestExceptions extends RuntimeException{
    private List<ObjectError> errorList;
    public BadRequestExceptions(String message){
        super(message);
    }

    public BadRequestExceptions(List<ObjectError> errorList){
        this.errorList=errorList;
    }
}
