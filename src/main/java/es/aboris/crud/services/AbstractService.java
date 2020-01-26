package es.aboris.crud.services;

import es.aboris.crud.model.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public abstract class AbstractService<T extends AbstractEntity, ID> {

    protected CrudRepository repository;

    protected AbstractService(CrudRepository<T, ID> repository){
        this.repository = repository;
    }

    public Optional<T> findById(ID id){
        return repository.findById(id);
    }

    public T save(T entity){
        return (T) this.repository.save(entity);
    }

    public Iterable<T> findAll(){
        return this.repository.findAll();
    }

    public void delete(ID id){
        this.repository.deleteById(id);
    }

}
