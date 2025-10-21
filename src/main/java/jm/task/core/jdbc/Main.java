package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        UserService uService = new UserServiceImpl();

        uService.createUsersTable();

        uService.saveUser("Name1", "LastName1", (byte) 20);
        uService.saveUser("Name2", "LastName2", (byte) 25);
        uService.saveUser("Name3", "LastName3", (byte) 31);
        uService.saveUser("Name4", "LastName4", (byte) 38);

        System.out.println(uService.getAllUsers());
        uService.cleanUsersTable();
        uService.dropUsersTable();
    }
}

