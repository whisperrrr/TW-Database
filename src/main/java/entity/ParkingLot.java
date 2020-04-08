package entity;

public class ParkingLot {
    private String mark;
    private int maxParkNum;

    public ParkingLot(String mark, int maxParkNum) {
        this.mark = mark;
        this.maxParkNum = maxParkNum;
    }

    public String getMark() {
        return mark;
    }

    public int getMaxParkNum() {
        return maxParkNum;
    }

}
