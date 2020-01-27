package es.aboris.crud.services;

import es.aboris.crud.exceptions.EntityNotFoundException;
import es.aboris.crud.model.AbstractEntity;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class AbstractService<T extends AbstractEntity, ID> {

    private static Logger logger = LoggerFactory.getLogger(AbstractEntity.class);

    private Class<? extends AbstractEntity> clazz;
    protected CrudRepository repository;

    protected AbstractService(CrudRepository<T, ID> repository, Class<? extends AbstractEntity> clazz){
        this.repository = repository;
        this.clazz = clazz;
    }

    public T findById(ID id){
        Optional<T> optional = repository.findById(id);

        if(!optional.isPresent()){
            throw new EntityNotFoundException(clazz, id);
        }

        return optional.get();
    }

    public T save(T entity){
        T result = null;

        try {
            result = (T) this.repository.save(entity);
        }catch (DataIntegrityViolationException e){
            logger.debug("e", e);

            if(e.getCause() instanceof ConstraintViolationException){
                throw new es.aboris.crud.exceptions.ConstraintViolationException(entity);
            }
        }

        return result;
    }

    public Iterable<T> findAll(){
        return this.repository.findAll();
    }

    public void delete(ID id){
        try {
            this.repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            logger.debug("No se ha podido eliminar " + clazz.getName() + " con la id: " + id, e);
            throw new EntityNotFoundException(clazz, id);
        }
    }


}
