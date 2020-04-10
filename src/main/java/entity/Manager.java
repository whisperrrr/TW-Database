package entity;

import database.ParkingLotRepository;
import exception.ParkingLotFullException;
import util.ParseUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Manager {
    private List<CarParker> carParkerInManage = new ArrayList<>();
    private int carParkerFlag = 0;
    private int maxManageNum;

    public void initCarParkers() {
        Collections.addAll(carParkerInManage,
                new CarParker("hebe", 18),
                new CarParker("selina", 20));
        this.maxManageNum = carParkerInManage.size();
    }

    // 按顺序派人
    public CarParker chooseCarParkerByTurn() {
        CarParker carParkerSended = carParkerInManage.get(carParkerFlag % maxManageNum);
        carParkerFlag++;
        return carParkerSended;
    }

    // 随机派人
    public CarParker chooseCarParkerByRandom() {
        Random random = new Random();
        int chooseNumber = random.nextInt(maxManageNum);

        return carParkerInManage.get(chooseNumber);
    }

    public void init(String initInfo) {
        // 先清空数据库里面的数据
        ParkingLotRepository.empty();
        // 根据输入重新初始化停车场数据
        List<ParkingLot> parkingLots = ParseUtil.parseToParkingLot(initInfo);
        parkingLots.forEach(ParkingLotRepository::save);
    }

    public String park(String carNumber) {
        CarParker carParker = chooseCarParkerByTurn();
        // 随机派人
        // CarParker carParker = chooseCarParkerByRandom();
        List<ParkingSpace> emptyParkingSpace = carParker.getEmptyParkingSpace();
        if (emptyParkingSpace.size() == 0) {
            throw new ParkingLotFullException();
        } else {
            Ticket ticket = carParker.getTicket(emptyParkingSpace.get(0), carNumber);
            return ticket.toString();
        }
    }

    public Ticket fetch(String ticket) {
        Ticket userTicket = ParseUtil.parseToTicket(ticket);
        return CarParker.fetchCar(userTicket);
    }
}
