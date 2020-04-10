import database.ParkingLotRepository;
import entity.CarParker;
import entity.ParkingLot;
import entity.ParkingSpace;
import entity.Ticket;
import exception.ParkingLotFullException;
import util.ParseUtil;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        operateParking();
    }

    public static void operateParking() {
        while (true) {
            System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
            Scanner printItem = new Scanner(System.in);
            String choice = printItem.next();
            if (choice.equals("4")) {
                System.out.println("系统已退出");
                break;
            }
            handle(choice);
        }
    }

    private static void handle(String choice) {
        Scanner scanner = new Scanner(System.in);
        switch (choice) {
            case "1":
                System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
                String initInfo = scanner.next();
                init(initInfo);
                break;
            case "2": {
                System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
                String carInfo = scanner.next();
                String ticket = park(carInfo);
                String[] ticketDetails = ticket.split(",");
                System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);
                break;
            }
            case "3": {
                System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
                String ticket = scanner.next();
                Ticket newTicketAfterFetch = fetch(ticket);
                System.out.format("已为您取到车牌号为%s的车辆，停车时间为%d分钟，收费%f元，很高兴为您服务，祝您生活愉快!\n",
                        newTicketAfterFetch.getCarNumber(),
                        newTicketAfterFetch.getParkInTime(),
                        newTicketAfterFetch.getCharge());
                break;
            }
        }
    }

    public static void init(String initInfo) {
        // 先清空数据库里面的数据
        ParkingLotRepository.empty();
        // 根据输入重新初始化停车场数据
        List<ParkingLot> parkingLots = ParseUtil.parseToParkingLot(initInfo);
        parkingLots.forEach(ParkingLotRepository::save);
    }

    public static String park(String carNumber) {
        List<ParkingSpace> emptyParkingSpace = CarParker.getEmptyParkingSpace();
        if (emptyParkingSpace.size() == 0) {
            throw new ParkingLotFullException();
        } else {
            Ticket ticket = CarParker.getTicket(emptyParkingSpace.get(0), carNumber);
            return ticket.toString();
        }
    }

    public static Ticket fetch(String ticket) {
        Ticket userTicket = ParseUtil.parseToTicket(ticket);
        return CarParker.fetchCar(userTicket);
    }
}
