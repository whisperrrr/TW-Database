package entity;

import database.ParkingLotRepository;
import exception.InvalidTicketException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarParker {
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
        parkCarToEmptySpace(parkingSpace, carNumber);

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

    // 取车
    public static String fetchCar(Ticket userTicket) throws SQLException {
        List<ParkingSpace> parkingSpace = ParkingLotRepository.queryByTicket(userTicket);
        if (parkingSpace.size() == 0) {
            throw new InvalidTicketException();
        } else {
            ParkingLotRepository.update(parkingSpace.get(0), null);
            return userTicket.getCarNumber();
        }
    }
}
