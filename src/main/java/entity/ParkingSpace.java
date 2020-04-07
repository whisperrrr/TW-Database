package entity;

import java.util.Date;

public class ParkingSpace {
    private String mark;
    private int id;
    private Boolean isEmpty;
    private String carNumber;
    private Date parkInTime;

    public ParkingSpace(String mark, int id, Boolean isEmpty, String carNumber) {
        this.mark = mark;
        this.id = id;
        this.isEmpty = isEmpty;
        this.carNumber = carNumber;
    }

    public String getMark() {
        return mark;
    }

    public int getId() {
        return id;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

}
