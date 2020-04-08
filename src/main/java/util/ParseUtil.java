package util;

import entity.ParkingLot;
import entity.Ticket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParseUtil {
    public static List<ParkingLot> parseToParkingLot(String input) {
        String parkingLotRegExPattern = "^([A-Z]:\\d+)(,[A-Z]:\\d+)*";
        FormatUtil.inputFormat(input,parkingLotRegExPattern);

        String[] parkingLots = input.split(",");
        return Arrays.stream(parkingLots)
                .sorted()
                .map(ele -> new ParkingLot(ele.split(":")[0], Integer.parseInt(ele.split(":")[1])))
                .collect(Collectors.toList());
    }

    public static Ticket parseToTicket(String input) {
        String ticketRegExPattern = "^[A-Z],\\-?\\d+,[A-Z][0-9]{5}";
        FormatUtil.inputFormat(input,ticketRegExPattern);

        String[] ticketInfo = input.split(",");
        return new Ticket(ticketInfo[0],
                Integer.parseInt(ticketInfo[1]),
                ticketInfo[2]);
    }

}
