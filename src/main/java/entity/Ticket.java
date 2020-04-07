package entity;

public class Ticket {
    private String parkingLotMark;
    private int parkingLotId;
    private String carNumber;

    public Ticket(String parkingLotMark, int parkingLotId, String carNumber) {
        this.parkingLotMark = parkingLotMark;
        this.parkingLotId = parkingLotId;
        this.carNumber = carNumber;
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%s", parkingLotMark, parkingLotId, carNumber);
    }
}
