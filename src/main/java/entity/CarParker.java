package entity;

import database.ParkingLotRepository;
import exception.InvalidTicketException;

import java.sql.SQLException;
import java.util.List;

public class CarParker {
    // 获取空的停车位
    public static List<ParkingSpace> getEmptyParkingSpace() {
        return ParkingLotRepository.queryByState(true);
    }

    // 获得车票
    public static Ticket getTicket(ParkingSpace parkingSpace, String carNumber) {
        Ticket ticket = null;

        try {
            parkCarToEmptySpace(parkingSpace, carNumber);
            ticket = new Ticket(parkingSpace.getMark(),
                    parkingSpace.getId(),
                    carNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket;
    }

    // 将车停在空位置上
    public static void parkCarToEmptySpace(ParkingSpace parkingSpace, String carNumber) throws SQLException {
        ParkingLotRepository.update(parkingSpace, carNumber);
    }

    // 取车
    public static String fetchCar(Ticket userTicket) {
        String fetchCarNumber = null;
        List<ParkingSpace> parkingSpace = ParkingLotRepository.queryByTicket(userTicket);
        if (parkingSpace.size() == 0) {
            throw new InvalidTicketException();
        } else {
            try {
                ParkingLotRepository.update(parkingSpace.get(0), null);
                fetchCarNumber = userTicket.getCarNumber();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return fetchCarNumber;
        }
    }
}
