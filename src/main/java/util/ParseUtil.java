package util;

import entity.ParkingLot;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParseUtil {
    public static List<ParkingLot> parseToParkingLot(String input) {
        String[] parkingLots = input.split(",");
        return Arrays.stream(parkingLots)
                .map(ele -> new ParkingLot(ele.split(":")[0], Integer.parseInt(ele.split(":")[1])))
                .collect(Collectors.toList());
    }
}
