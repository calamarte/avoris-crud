package es.aboris.crud.exceptions;

import es.aboris.crud.model.AbstractEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(AbstractEntity entity){
        super("Se ha violado una restricción en la persistencia de " + entity);
    }
}
