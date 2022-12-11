package dev.jujumba.homework4.dao;

import dev.jujumba.homework4.data.Customer;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class CustomerDao {
    private final DataSource ds;
    public List<Customer> findByEmployee(int artistId) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from \"Customer\" where \"SupportRepId\" = ?")
        ) {
            ps.setInt(1, artistId);
            ResultSet rs = ps.executeQuery();
            List<Customer> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("CustomerId");
                String name = rs.getString("FirstName");
                result.add(new Customer(id, name, artistId));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
