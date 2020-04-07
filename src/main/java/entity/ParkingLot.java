package entity;

import database.ParkingLotRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // 获取空的停车位
    public static List<ParkingSpace> getEmptyParkingSpace() {
        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        try {
            parkingSpaces = ParkingLotRepository.queryByState(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return parkingSpaces;
    }

    // 获得车票
    public static Ticket getTicket(ParkingSpace parkingSpace, String carNumber) {
        parkCarToEmptySpace(parkingSpace,carNumber);

        return new Ticket(parkingSpace.getMark(),
                parkingSpace.getId(),
                carNumber);
    }

    // 将车停在空位置上
    public static void parkCarToEmptySpace(ParkingSpace parkingSpace, String carNumber) {
        try {
            ParkingLotRepository.update(parkingSpace, carNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
