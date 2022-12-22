package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = Util.getSessionFactory();
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        System.out.println("User с именем – Name1 добавлен в базу данных");
        userService.saveUser("Name2", "LastName2", (byte) 25);
        System.out.println("User с именем – Name2 добавлен в базу данных");
        userService.saveUser("Name3", "LastName3", (byte) 31);
        System.out.println("User с именем – Name3 добавлен в базу данных");
        userService.saveUser("Name4", "LastName4", (byte) 38);
        System.out.println("User с именем – Name4 добавлен в базу данных");

        userService.removeUserById(2);

        userService.getAllUsers().stream().forEach(System.out :: println);

        userService.cleanUsersTable();
        userService.dropUsersTable();
        sessionFactory.close();
    }
}
