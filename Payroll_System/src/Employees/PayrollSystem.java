package Employees;

import Manger_Users.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PayrollSystem {

    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static HashMap<Integer, User> userCredentials = new HashMap<>();
    public static int userId = 1;

    public static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public static Boolean removeEmployee(int id) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID() == id) {
                employees.remove(i);
                return true;
            }

        }
        return false;
    }

    public static double calculateTotalPayroll() {
        double totalPayroll = 0;
        for (Employee e : employees) {
            totalPayroll += e.calculatePay();
        }
        return totalPayroll;
    }

    public static void updateEmployeeDetails(int id, String n, Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getEmployeeID() == id) {
                employees.set(i, employee);
            }

        }
    }

    public static AtomicBoolean registerUser(String username, String password) {
        AtomicBoolean isFounded = new AtomicBoolean(false);
        userCredentials.forEach((s, user) -> {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                isFounded.set(true);
            }
        });
        if (isFounded.get() == false) {
            User user = new User(username, password);
            userCredentials.put(userId, user);
            userId++;
        }
        return isFounded;
    }

    public static AtomicBoolean login(String name, String pass) {

        AtomicBoolean isLogin = new AtomicBoolean(false);
        userCredentials.forEach((s, user) -> {
            if (user.getUsername().equals(name) && user.getPassword().equals(pass)) {
                isLogin.set(true);
            }
        });
        return isLogin;

    }

    public static User showUserDetails(int id) {
        if (userCredentials.containsKey(id)) {
            return userCredentials.get(id);
        }
        return null;

    }

    public static void updateUserDetails(int id, String username, String password) {
        User user = new User(username, password);
        if (userCredentials.containsKey(id)) {
            userCredentials.replace(id, user);
        }
    }

    public static Boolean removeUser(String id) {
        int id_user = Integer.parseInt(id);

        if (userCredentials.containsKey(id_user)) {
            userCredentials.remove(id_user);
            return true;
        }
        return false;
    }

}
