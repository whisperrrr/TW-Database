package entity;


public class Ticket {
    private String parkingLotMark;
    private int parkingLotId;
    private String carNumber;
    private int parkInTime;
    private double charge;

    public Ticket(String parkingLotMark, int parkingLotId, String carNumber) {
        this.parkingLotMark = parkingLotMark;
        this.parkingLotId = parkingLotId;
        this.carNumber = carNumber;
    }

    public String getParkingLotMark() {
        return parkingLotMark;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public int getParkInTime() {
        return parkInTime;
    }

    public double getCharge() {
        return charge;
    }

    public void setParkInTime(int parkInTime) {
        this.parkInTime = parkInTime;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%s", parkingLotMark, parkingLotId, carNumber);
    }
}
