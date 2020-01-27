package es.aboris.crud.listeners;

import es.aboris.crud.model.Room;
import es.aboris.crud.model.User;
import es.aboris.crud.repositories.RoomRepository;
import es.aboris.crud.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StartUpListener implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger logger = LoggerFactory.getLogger(StartUpListener.class);

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("#{'${users.start.list:admin-admin}'.split(',')}")
    private List<String> users;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<User> users = this.users.stream()
                .filter(Objects::nonNull)
                .map((u) -> {
                    String[] split = u.split("-");
                    return split.length == 2 ? new User(split[0], split[1]) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        users = (List<User>) userRepository.saveAll(users);
        logger.info("Creados los usuarios: " + users);

        List<Room> roomList = Arrays.asList(
                new Room("1", "A", false, Room.RoomsStatus.CLEAN),
                new Room("2", "A", false, Room.RoomsStatus.DIRTY),
                new Room("3", "A", true, Room.RoomsStatus.CLEANING),
                new Room("4", "B", true, Room.RoomsStatus.CLEAN)
        );

        roomList = (List<Room>) roomRepository.saveAll(roomList);
        logger.info("Insertadas las siguientes habitaciones: " + roomList);
    }
}
