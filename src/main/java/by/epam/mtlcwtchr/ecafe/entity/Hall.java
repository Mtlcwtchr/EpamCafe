package by.epam.mtlcwtchr.ecafe.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Hall implements Serializable {

    private static final long serialVersionUID = -2717157075749098350L;

    private int id;
    private int guestsNumber;
    private String hallDescription;

    public Hall(){}
    public Hall(int guestsNumber, String hallDescription) {
        this.guestsNumber = guestsNumber;
        this.hallDescription = hallDescription;
    }

    public Hall(int id, int guestsNumber, String hallDescription) {
        this(guestsNumber, hallDescription);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestsNumber() {
        return guestsNumber;
    }

    public void setGuestsNumber(int guestsNumber) {
        this.guestsNumber = guestsNumber;
    }

    public String getHallDescription() {
        return hallDescription;
    }

    public void setHallDescription(String hallDescription) {
        this.hallDescription = hallDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return id == hall.id &&
                guestsNumber == hall.guestsNumber &&
                Objects.equals(hallDescription, hall.hallDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guestsNumber, hallDescription);
    }

    @Override
    public String toString() {
        return  getClass().getSimpleName() + "{" +
                "id=" + id +
                ", guestsNumber=" + guestsNumber +
                ", hallDescription='" + hallDescription + '\'' +
                '}';
    }
}
