package FileHandler;

import Employees.*;
import static Employees.Employee.empId;
import Manger_Users.User;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import static Employees.PayrollSystem.employees;
import static Employees.PayrollSystem.userCredentials;
import static Employees.PayrollSystem.userId;
import java.net.URISyntaxException;

public class FilesHandler {
// Write to User file
    public static void FileWriterUser() throws IOException {
        FileWriter file = new FileWriter("Users.txt", true);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(userId);
         printWriter.flush();
        userCredentials.forEach((s, user) -> {
            printWriter.println(s + "~" + user.getUsername() + "~" + user.getPassword());
            printWriter.flush();
        });

    }

    public static void lineUser(String str) {
        int id;
        String name, pass;
        Scanner scan = new Scanner(str);
        scan.useDelimiter("~");
        while (scan.hasNext()) {
            id = Integer.parseInt(scan.next());
            name = scan.next();
            pass = scan.next();
            User user = new User(name, pass);
            userCredentials.put(id, user);
        }
        scan.close();
    }

// Write to Employee file
    public static void FileWriterEmployee() throws IOException {
        FileWriter file = new FileWriter("Employees.txt", true);
        PrintWriter printWriter = new PrintWriter(file);
           printWriter.println(empId);
         printWriter.flush();
        for (Employee e : employees) {
            printWriter.println(e.generatePayStub());
            printWriter.flush();
        }

    }

    public static void lineEmployee(String str) {

        int id, hoursWorked;

        String name, empType, paymentMethod, paymentDetails, taxDetails;
        double taxAmount, salary = 5, hourlyRate, totalSales, commissionRate;
        Scanner scan = new Scanner(str);
        scan.useDelimiter("~");
        while (scan.hasNext()) {
            id = Integer.parseInt(scan.next());
            name = scan.next();
            empType = scan.next();
            paymentMethod = scan.next();
            paymentDetails = scan.next();
            taxDetails = scan.next();
            taxAmount = Double.parseDouble(scan.next());
            TaxInformation taxInformation = new TaxInformation(taxDetails, taxAmount);
            if (empType.equals("Salaried")) {
                salary = Double.parseDouble(scan.next());
                Employee employee1 = new SalariedEmployee(id, name, empType, paymentMethod, paymentDetails, taxInformation, salary);
                employees.add(employee1);
            } else if (empType.equals("Hourly")) {
                hourlyRate = Double.parseDouble(scan.next());
                hoursWorked = Integer.parseInt(scan.next());
                Employee employee1 = new HourlyEmployee(id, name, empType, paymentMethod, paymentDetails, taxInformation, hourlyRate, hoursWorked);
                employees.add(employee1);
            } else {
                commissionRate = Double.parseDouble(scan.next());
                totalSales = Double.parseDouble(scan.next());
                Employee employee1 = new CommissionedEmployee(id, name, empType, paymentMethod, paymentDetails, taxInformation, commissionRate, totalSales);
                employees.add(employee1);
            }

        }
        scan.close();
    }
// Read from Employee file
    public static void FileReader() throws FileNotFoundException, IOException,URISyntaxException {

            File file2 = new File("Employees.txt");
            Scanner s2 = new Scanner(file2);

            if (s2.hasNext()) {
                empId = Integer.parseInt(s2.nextLine());
            }
            while (s2.hasNext()) {
                lineEmployee(s2.nextLine());
            }
            s2.close();
            Files.deleteIfExists(Path.of(file2.getCanonicalPath()));

        
                File file = new File("Users.txt");
        Scanner s = new Scanner(file);
             if(s.hasNext()){
               userId=Integer.parseInt(s.nextLine());
             }
        while (s.hasNext()) {
            lineUser(s.nextLine());
        }
        s.close();
        Files.deleteIfExists(Path.of(file.getCanonicalPath()));

    }
}
