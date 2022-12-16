package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    private final Connection connection = Util.getConnection();
    public void createUsersTable() {
        String sql =
                "CREATE TABLE user9 (" +
                        "userId INTEGER NOT NULL AUTO_INCREMENT, " +
                        "userName CHAR(45), userSurname CHAR(45), userAge TINYINT, PRIMARY KEY (userId))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user9";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user9 (userName, userSurname, userAge) VALUES ('" + name + "', '" + lastName + "', " + age + ")";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM user9 WHERE `userId` = " + id;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList();
        String sql = "SELECT * FROM user9";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("userId"));
                user.setName(resultSet.getString("userName"));
                user.setLastName(resultSet.getString("userSurname"));
                user.setAge((byte) resultSet.getInt("userAge"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM user9";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
