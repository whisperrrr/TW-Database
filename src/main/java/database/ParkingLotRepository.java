package database;

import entity.ParkingLot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ParkingLotRepository {
    public static void save(ParkingLot parkingLot) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO parking_lot(mark, id, state) " +
                "VALUES (?, ?, 'N')";
        for (int i = 0; i < parkingLot.getMaxParkNum(); i++) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, parkingLot.getMark());
                preparedStatement.setInt(2, i + 1);

                preparedStatement.executeUpdate();
            }
        }
    }

    public static void empty(){
        Connection connection = DbUtil.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE parking_lot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
