package es.aboris.crud.exceptions;

import es.aboris.crud.model.AbstractEntity;

public class ConstraintViolationException extends RuntimeException {

    public ConstraintViolationException(AbstractEntity entity){
        super("Se ha violado una restricción en la persistencia de " + entity);
    }
}
