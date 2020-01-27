package es.aboris.crud.exceptions;

import es.aboris.crud.model.AbstractEntity;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class<? extends AbstractEntity> clazz, Object id){
        super(String.format("No se encuentra %s con la id: %s", clazz.getSimpleName(), id));
    }
}
