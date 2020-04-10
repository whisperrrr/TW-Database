package entity;

import database.ParkingLotRepository;
import exception.InvalidTicketException;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class CarParker {
    private String name;
    private int age;

    public CarParker(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 获取空的停车位
    public List<ParkingSpace> getEmptyParkingSpace() {
        return ParkingLotRepository.queryByState(true);
    }

    // 获得车票
    public Ticket getTicket(ParkingSpace parkingSpace, String carNumber) {
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
    public static Ticket fetchCar(Ticket userTicket) {
        List<ParkingSpace> parkingSpace = ParkingLotRepository.queryByTicket(userTicket);
        if (parkingSpace.size() == 0) {
            throw new InvalidTicketException();
        } else {
            try {
                ParkingSpace parkingSpaceByTicket = parkingSpace.get(0);
                ParkingLotRepository.update(parkingSpaceByTicket, null);

                updateTicketInfo(userTicket, parkingSpaceByTicket);
            } catch (SQLException e) {
                e.printStackTrace();
                userTicket = null;
            }
            return userTicket;
        }
    }

    // 停车成功以后更新车票的信息
    private static void updateTicketInfo(Ticket userTicket, ParkingSpace parkingSpaceByTicket) {
        int carTime = getParkingTime(parkingSpaceByTicket);
        double carCharge = charge(parkingSpaceByTicket);
        userTicket.setParkInTime(carTime);
        userTicket.setCharge(carCharge);
    }

    // 收费啦
    private static double charge(ParkingSpace parkingSpace) {
        int parkingTime = getParkingTime(parkingSpace);

        double charge = 0;

        if (parkingTime >= 2 && parkingTime < 5) {
            charge = (Math.ceil(parkingTime) - 2) * 5;
        } else if (parkingTime >= 5) {
            charge = (Math.ceil(parkingTime) - 5) * 10 + 15;
        }
        return charge;
    }

    // 获取停车时间(按分钟来)
    private static int getParkingTime(ParkingSpace parkingSpace) {
        Date parkInTime = parkingSpace.getParkInTime();
        Date fetchTime = new Date(System.currentTimeMillis());
        long timeGap = fetchTime.getTime() - parkInTime.getTime();

        return (int) timeGap / 1000 / 60;
    }

    @Override
    public String toString() {
        return "CarParker{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
