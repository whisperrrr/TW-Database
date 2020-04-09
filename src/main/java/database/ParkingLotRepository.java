package database;

import entity.ParkingLot;
import entity.ParkingSpace;
import entity.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepository {
    // 初始化ParingLot
    public static void save(ParkingLot parkingLot) {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO parking_lot(mark, id, is_empty) " +
                "VALUES (?, ?, 1)";

        for (int i = 0; i < parkingLot.getMaxParkNum(); i++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, parkingLot.getMark());
                preparedStatement.setInt(2, i + 1);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 根据停车位的状态查询，返回相应状态的停车位
    public static List<ParkingSpace> queryByState(Boolean isEmpty) {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT mark, id, is_empty, car_number " +
                "FROM parking_lot " +
                "WHERE is_empty = ?";

        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, isEmpty);
            resultSet = preparedStatement.executeQuery();
            return getParkingSpaceList(resultSet);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    // 根据停车位和车来更新数据库中停车位的状态
    public static void update(ParkingSpace parkingSpace, String carNumber) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "UPDATE parking_lot " +
                "SET is_empty = ?, car_number = ? " +
                "WHERE mark = ? AND id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBoolean(1, !parkingSpace.getEmpty());
            preparedStatement.setString(2, carNumber);
            preparedStatement.setString(3, parkingSpace.getMark());
            preparedStatement.setInt(4, parkingSpace.getId());

            preparedStatement.executeUpdate();
        }
    }

    // 清空数据库
    public static void empty() {
        Connection connection = DbUtil.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE parking_lot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据票据来查询停车位
    public static List<ParkingSpace> queryByTicket(Ticket userTicket) {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT mark, id, is_empty, car_number " +
                "FROM parking_lot " +
                "WHERE mark = ? AND id = ? AND is_empty = 0 AND car_number = ?";

        ResultSet resultSet;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userTicket.getParkingLotMark());
            preparedStatement.setInt(2, userTicket.getParkingLotId());
            preparedStatement.setString(3, userTicket.getCarNumber());

            resultSet = preparedStatement.executeQuery();
            return getParkingSpaceList(resultSet);
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    // 将resultSet转化为停车位列表
    private static List<ParkingSpace> getParkingSpaceList(ResultSet resultSet) throws SQLException {
        ArrayList<ParkingSpace> parkingSpaces = new ArrayList<>();

        while (resultSet.next()) {
            ParkingSpace parkingSpace = new ParkingSpace(resultSet.getString("mark"),
                    resultSet.getInt("id"),
                    resultSet.getBoolean("is_empty"),
                    resultSet.getString("car_number"));
            parkingSpaces.add(parkingSpace);
        }

        return parkingSpaces;
    }
}
