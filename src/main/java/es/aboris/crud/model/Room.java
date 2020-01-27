package es.aboris.crud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity
public class Room extends AbstractEntity<Integer>{

    public enum RoomsStatus{
        CLEAN,
        DIRTY,
        CLEANING
    }

    @Column(unique=true)
    private String number;
    private String block;
    private boolean occupied;
    private RoomsStatus status;

    public Room(){}

    public Room(String number, String block, boolean occupied, RoomsStatus status) {
        this.number = number;
        this.block = block;
        this.occupied = occupied;
        this.status = status;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public RoomsStatus getStatus() {
        return status;
    }

    public void setStatus(RoomsStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number='" + number + '\'' +
                ", block='" + block + '\'' +
                ", occupied=" + occupied +
                ", status=" + status +
                '}';
    }
}
