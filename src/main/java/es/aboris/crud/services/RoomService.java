package es.aboris.crud.services;

import es.aboris.crud.model.Room;
import es.aboris.crud.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends AbstractService<Room, Integer> {

    @Autowired
    protected RoomService(RoomRepository repository) {
        super(repository);
    }
}
