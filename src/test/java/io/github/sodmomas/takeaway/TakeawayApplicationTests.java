package io.github.sodmomas.takeaway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

@SpringBootTest
class TakeawayApplicationTests {

    @Test
    void testSqlConnect() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        final String jdbc = "jdbc:postgresql://localhost:5432/takeaway";
        final Connection connection = DriverManager.getConnection(jdbc, "takeaway", "takeaway");
        final PreparedStatement ps = connection.prepareStatement("SELECT 1");
        final ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            final int result = rs.getInt(1);
            System.out.println(result);
        }
        rs.close();
        ps.close();
        connection.close();
    }

}
