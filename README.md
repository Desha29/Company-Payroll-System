# Company-Payroll-System
# OOP Project
## Description 
This is a Company Payroll System (CPS) meticulously crafted using object-oriented programming principles 
using the Java language and Swing for powerful GUI. CPS is equipped 
to handle diverse employee types, to meet the needs of salaried, 
hourly, and commissioned employees. The system adapts to the 
intricacies of each employment category. It facilitates the accurate 
calculation of employee salaries, generation of detailed pay stubs, and 
users of this system can also calculate the total payroll and generate 
payslips. 
There is also a manager with a suite of controls for user management. 
Managers can add, remove, and update user accounts, this 
managerial oversight enhances the system's security and 
accountability. 

 
## Classes and Functionality Overview: 
### - Enum Constants: 
 - `Salaried`: Represents employees who receive a fixed salary
 - `Hourly`: Represents employees who are paid based on hourly rates and hours worked
 - `Commissioned`: Represents sales-based employees who earn commissions on total sales 
 
### - Employee Class: 
The `Employee` class serves as the abstract foundation for representing different employee types within the system. 
Methods: 
 - `calculatePay()`: Computes the pay for an employee based on their specific type (Salaried, Hourly, or Commissioned). 
 - `generatePayStub()`: Produces an intricate pay stub for the employee considering their employee type and associated details. 
 - `getEmployeeID()`: Retrieves the employee's ID. 
 - `getEmployeeName()`: Retrieves the employee's name. 
 - `getEmployeeType():`: Retrieves the employee's type. 
 - `getEmpPaymentMethod()`: Retrieves the employee's payment method. 
 - `getEmpPaymentDetails()`: Retrieves the employee's payment details. 
 - `getEmpTaxInformation()`: Retrieves the employee's tax information. 
### - SalariedEmployee Class:
The `SalariedEmployee` class is a concrete implementation of the `Employee` class, representing employees with a fixed salary. 

Methods: 
 - `getSalary()`: Retrieves the employee's fixed salary. 
 
### - HourlyEmployee Class: 
The `HourlyEmployee` class is a concrete implementation of the `Employee` class, representing employees who are paid on an hourly basis.  

Methods: 
 - `getHourlyPayRate()`: Retrieves the hourly pay rate for the employee. 
 - `getHoursWorked()`: Retrieves the hours worked by the employee.
   
### - CommissionedEmployee Class: 
The `CommissionedEmployee` class is a concrete implementation of the `Employee` class, representing employees who receive compensation based on commission from total sales.

Methods: 
 - `getCommissionRate()`: Retrieves the commission rate for the employee. 
 - `getTotalSales()`: Retrieves the total sales made by the employee.
   
### - TaxInformation Class: 
The `TaxInformation` class represents detailed tax-related information for each employee type.

Methods: 
 - `TaxInformation()`: Constructor to initialize `taxDetails` and `taxAmount`.
   
### - User Class: 
The `User` class in the `Manger_Users` package represents individuals with access to the Company Payroll System.

Methods: 
 - `User()`: Constructor to initialize `username` and `password`. 
 - `setUsername()`: Method to set a new username. 
 - `setPassword()`: Method to set a new password. 
 - `getUsername()`: Retrieves the user's username. 
 - `getPassword()`: Retrieves the user's password.
    
### - Manager Class: 
The `Manager` class in the `Manger_Users` package is responsible for managing users within the system.

Methods: 
 - `Manager()`: Default constructor to set initial values for `username` and `password`. 
 - `getUsername()`: Retrieves the manager's username. 
 - `getPassword()`: Retrieves the manager's password. 
 - `setPassword()`: Sets a new password for the manager.
   
### - PayrollSystem Class: 
The `PayrollSystem` class manages the overall functionality of the payroll system. 

Methods: 
 - `addEmployee()`: Adds an employee to the system. 
 - `removeEmployee()`: Removes an employee from the system based on their ID. 
 - `calculateTotalPayroll()`: Precisely calculates the total payroll for all employees across diverse employee structures. 
 - `updateEmployeeDetails()`: Allows updates to specific employee attributes based on their ID. 
 - `registerUser()`: Registers a user for system access. 
 - `login()`: Authenticates user credentials for system access. 
 - `showUserDetails()`: Retrieves user details based on their ID. 
 - `updateUserDetails()`: Updates user details based on their ID. 
 - `removeUser()`: Removes a user from the system based on their ID. 
 
 








