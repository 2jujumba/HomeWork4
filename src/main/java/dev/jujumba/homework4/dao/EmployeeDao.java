package dev.jujumba.homework4.dao;


import dev.jujumba.homework4.data.Employee;
import lombok.AllArgsConstructor;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class EmployeeDao {
    private final DataSource ds;

    public List<Employee> findAll() {
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement("select * from \"Employee\"");

             ResultSet rs = ps.executeQuery()
        ) {
            List<Employee> result = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("EmployeeId");
                String name = rs.getString("FirstName");
                result.add(new Employee(id, name));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(String name) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into \"Employee\" (\"FirstName\") values (?)")
        ) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(int id) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from \"Employee\" where \"EmployeeId\" = ?")
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee findById(int id) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from \"Employee\" where \"EmployeeId\" = ?")
        ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("EmployeeId"), rs.getString("FirstName"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Employee artist) {
        try (Connection connection = ds.getConnection();
            PreparedStatement ps = connection.prepareStatement("update \"Employee\" set \"FirstName\" = ? where \"EmployeeId\" = ?")
        ) {
            ps.setInt(2, artist.getId());
            ps.setString(1, artist.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
