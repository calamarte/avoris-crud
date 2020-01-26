package es.aboris.crud.controllers;

import es.aboris.crud.model.Room;
import es.aboris.crud.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController extends AbstractCrudController<Room, Integer>{

    @Autowired
    protected RoomController(RoomService service) {
        super(service);
    }
}
