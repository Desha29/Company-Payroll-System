/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payroll_system.gui;

import Employees.*;
import FileHandler.FilesHandler;
import Manger_Users.Manger;
import Manger_Users.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Employees.Employee.empId;
import static Employees.PayrollSystem.*;
import static javax.swing.JOptionPane.*;

/**
 * @author mstfo
 */
public class CPS_Home extends javax.swing.JFrame {

    public int clickUser = 0;
    public int clickManger = 0;
    Manger manger = new Manger();
    Employee employee;
    TaxInformation tax;
    double salary_hourlyR_commissionR;
    double totalSales = 0.0;
    double hoursWorked = 0;
    double taxAmmount = 0.0;
    DefaultTableModel tbModel_employees;
    DefaultTableModel tbModel_users;

    /**
     * Creates new form CPS_Home1
     */
    public CPS_Home() {
        initComponents();
        // Users Table 
        jTable_Users.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTable_Users.getTableHeader().setOpaque(false);
        jTable_Users.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable_Users.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable_Users.setRowHeight(25);
        // Employee Table 
        jTable_Employees.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTable_Employees.getTableHeader().setOpaque(false);
        jTable_Employees.getTableHeader().setBackground(new Color(32, 136, 203));
        jTable_Employees.getTableHeader().setForeground(new Color(255, 255, 255));
        jTable_Employees.setRowHeight(25);

    }

    private void login() {
        AtomicBoolean isLogin = new AtomicBoolean();
        isLogin = PayrollSystem.login(jTextField_username.getText(), jPasswordField_login.getText());
        if (clickManger == 1 && jTextField_username.getText().equals(manger.getUsername()) && jPasswordField_login.getText().equals(manger.getPassword())) {
            System.out.println("Manger");
            jPanel_Home_Manger.setVisible(true);
            jPanel_Home_User.setVisible(false);
            jPanel_admin_user.setVisible(false);
            jPanel_login.setVisible(false);
            jTextField_username.setText("");
            jPasswordField_login.setText("");
        } else if (clickUser == 1 && isLogin.get() == true) {
            userCredentials.forEach((s, user) -> {
                if (user.getUsername().equals(jTextField_username.getText()) && user.getPassword().equals(jPasswordField_login.getText())) {
                    Id_user1.setText(s.toString());
                    jTextField_Username4.setText(jTextField_username.getText());
                    jTextField_Password5.setText(jPasswordField_login.getText());
                }
            });
            System.out.println("User");
            jPanel_Home_User.setVisible(true);
            jPanel_Home_Manger.setVisible(false);
            jPanel_admin_user.setVisible(false);
            jPanel_login.setVisible(false);
            jTextField_username.setText("");
            jPasswordField_login.setText("");
//            id = String.valueOf(userId);
        } else if (jTextField_username.getText().trim().isEmpty() && jPasswordField_login.getText().trim().isEmpty()) {
            jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18)));
            valid_empty_username.setText("Username is Empty !!");
            jPasswordField_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18)));
            valid_empty_password.setText("Password is Empty !!");
        } else if (jTextField_username.getText().trim().isEmpty()) {
            jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18)));
            valid_empty_username.setText("Username is Empty !!");
            valid_empty_password.setText("");
            jPasswordField_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (jPasswordField_login.getText().trim().isEmpty()) {
            valid_empty_username.setText("");
            jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            jPasswordField_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18)));
            valid_empty_password.setText("Password is Empty !!");
        } else {
            showMessageDialog(null, "Invalid Username or Password", "Error", ERROR_MESSAGE);
            valid_empty_username.setText("");
            valid_empty_password.setText("");
            jPasswordField_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        }
    }

    private void addUser(java.awt.event.ActionEvent evt) {
        AtomicBoolean founded = new AtomicBoolean();

        if (jTextField_M_add_Username5.getText().trim().isEmpty() && jPasswordField_add_User.getText().trim().isEmpty()) {
            add_User_Valid.setText("Username and Password Field is Empty !!");
            jTextField_M_add_Username5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            jPasswordField_add_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else if (jTextField_M_add_Username5.getText().trim().isEmpty()) {
            add_User_Valid.setText("Username Field is Empty !!");
            jTextField_M_add_Username5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            jPasswordField_add_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (jPasswordField_add_User.getText().trim().isEmpty()) {
            add_User_Valid.setText("Password Field is Empty !!");

            jTextField_M_add_Username5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            jPasswordField_add_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else if (jPasswordField_add_User.getText().trim().length() < 6) {
            add_User_Valid.setText("The password must be at least 6 characters");
            jPasswordField_add_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else {
            add_User_Valid.setText("");
            jTextField_M_add_Username5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            jPasswordField_add_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            founded = PayrollSystem.registerUser(jTextField_M_add_Username5.getText(), jPasswordField_add_User.getText());
            if (founded.get() == true) {
                showMessageDialog(null, "This user already exists ", "Error", ERROR_MESSAGE);
            } else {
                showMessageDialog(null, "The user has been added successfully  : )", "Add User", INFORMATION_MESSAGE);
                jTextField_M_add_Username5.setText("");
                jPasswordField_add_User.setText("");
            }

        }
        jButton_Manger_Add_UserActionPerformed(evt);
    }

    private void updateUser() {
        try {
            int id = Integer.parseInt(jTextField_M_Id3.getText());
            if (jTextField_M_Id3.getText().isEmpty()) {
                showMessageDialog(null, "You must fill all Fields", "Empty Field", ERROR_MESSAGE);
                jTextField_M_Up_Username6.setText("");
                jTextField_M_Up_Password7.setText("");
                username_user_up.setVisible(false);
                password_user_up.setVisible(false);
                jTextField_M_Up_Username6.setVisible(false);
                jTextField_M_Up_Password7.setVisible(false);
                jButton_CMUpdate_User1.setVisible(false);
            } else if (jTextField_M_Up_Username6.getText().isEmpty() || jTextField_M_Up_Password7.getText().isEmpty()) {
                showMessageDialog(null, "You must fill all Fields", "Empty Field", ERROR_MESSAGE);
            } else if (jTextField_M_Up_Password7.getText().trim().length() < 6) {
                showMessageDialog(null, "The password must be at least 6 characters", "Invalid Password", ERROR_MESSAGE);
            } else {
                PayrollSystem.updateUserDetails(id, jTextField_M_Up_Username6.getText(), jTextField_M_Up_Password7.getText());
                showMessageDialog(null, "The user has been updated successfully  : )", "Update User", INFORMATION_MESSAGE);
                jTextField_M_Id3.setEditable(true);
                jTextField_M_Up_Username6.setText("");
                jTextField_M_Up_Password7.setText("");
                username_user_up.setVisible(false);
                password_user_up.setVisible(false);
                jTextField_M_Up_Username6.setVisible(false);
                jTextField_M_Up_Password7.setVisible(false);
                jButton_CMUpdate_User1.setVisible(false);
            }
        } catch (NumberFormatException e) {
            showMessageDialog(null, "You must fill all Fields", "Empty Field", ERROR_MESSAGE);
        }
    }

    private void removeUser() {

        if (jTextField_M_Re_Id7.getText().trim().isEmpty()) {
            remove_User_Valid3.setText("Id Field is Empty");
            jTextField_M_Re_Id7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else if (PayrollSystem.removeUser(jTextField_M_Re_Id7.getText())) {
            showMessageDialog(null, "The user has been removed successfully  : )", "Remove User", INFORMATION_MESSAGE);
            jTextField_M_Re_Id7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            remove_User_Valid3.setText("");
        } else if (!PayrollSystem.removeUser(jTextField_M_Re_Id7.getText())) {
            showMessageDialog(null, "User not found  : (", "Error", ERROR_MESSAGE);
            jTextField_M_Re_Id7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            remove_User_Valid3.setText("");
        } else {
            showMessageDialog(null, "Invalid remove user  : (", "Error", ERROR_MESSAGE);
            jTextField_M_Re_Id7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            remove_User_Valid3.setText("");
        }
    }

    private void changePassword() {
        if (old_pass.getText().trim().isEmpty() && new_pass.getText().trim().isEmpty() && confirm_new_pass.getText().trim().isEmpty()) {
            valid_empty_password1.setText("Fields  is Empty !!");
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else if (old_pass.getText().trim().isEmpty() && new_pass.getText().trim().isEmpty()) {
            valid_empty_password1.setText("Old and New Password Field is Empty !!");
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (old_pass.getText().trim().isEmpty() && confirm_new_pass.getText().trim().isEmpty()) {
            valid_empty_password1.setText("Old and Confirm New Password Field is Empty !!");
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else if (new_pass.getText().trim().isEmpty() && confirm_new_pass.getText().trim().isEmpty()) {
            valid_empty_password1.setText("New and Confirm New Password Field is Empty !!");
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else if (old_pass.getText().trim().isEmpty()) {
            valid_empty_password1.setText("Old Password Field is Empty !!");
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (new_pass.getText().trim().isEmpty()) {
            valid_empty_password1.setText("New Password Field is Empty !!");
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (confirm_new_pass.getText().trim().isEmpty()) {
            valid_empty_password1.setText("Confirm NewPassword Field is Empty !!");
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else if (new_pass.getText().trim().length() < 6) {
            valid_empty_password1.setText("The password must be at least 6 characters");
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (!confirm_new_pass.getText().equals(new_pass.getText())) {
            valid_empty_password1.setText("The confirm password does not match the new password !!");
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (!old_pass.getText().equals(manger.getPassword())) {
            valid_empty_password1.setText("Invalid Old Password !!");
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else if (old_pass.getText().equals(manger.getPassword()) && confirm_new_pass.getText().equals(new_pass.getText()) && new_pass.getText().trim().length() >= 6) {
            manger.setPassword(new_pass.getText());
            showMessageDialog(null, "The password change was completed successfully  : )", "Change Password", INFORMATION_MESSAGE);
            valid_empty_password1.setText("");
            old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        } else {

            showMessageDialog(null, "Invalid Change Password  : (", "Error", INFORMATION_MESSAGE);
        }
    }

    private void addEmployee(java.awt.event.ActionEvent evt) {
        if (jTextField_Name.getText().isEmpty() || jTextField_TaxAmount.getText().isEmpty() || Salary.getText().isEmpty() || jTextfield_taxDetails.getText().isEmpty() || jTextField_PayDetails.getText().isEmpty() || Hour_work.getText().isEmpty()) {
            showMessageDialog(null, "You must fill all Fields", "Empty Field", ERROR_MESSAGE);
        } else if (!(jTextField_Name.getText().isEmpty() || jTextField_TaxAmount.getText().isEmpty() || Salary.getText().isEmpty() || jTextfield_taxDetails.getText().isEmpty() || jTextField_PayDetails.getText().isEmpty() || Hour_work.getText().isEmpty())) {
            String name = jTextField_Name.getText();
            String selectedItemType = jComboBox_Emp_type.getSelectedItem().toString();
            String selectedItemPay = jComboBox_PayMothod.getSelectedItem().toString();
            taxAmmount = Double.parseDouble(jTextField_TaxAmount.getText());
            salary_hourlyR_commissionR = Double.parseDouble(Salary.getText().trim());
            hoursWorked = Integer.parseInt(Hour_work.getText());
            totalSales = Double.parseDouble(Hour_work.getText());
            String taxDetails = jTextfield_taxDetails.getText();
            String payDetails = jTextField_PayDetails.getText();
            tax = new TaxInformation(taxDetails, taxAmmount);
            Hour_work.setText("0");
            if (selectedItemType.equals("Salaried")) {
                if (taxAmmount >= salary_hourlyR_commissionR) {
                    showMessageDialog(null, "Tax amount is greater than the salary. Must change it", "Warning", WARNING_MESSAGE);

                } else {
                    employee = new SalariedEmployee(name, selectedItemType, selectedItemPay, payDetails, tax, salary_hourlyR_commissionR);
                    PayrollSystem.addEmployee(employee);
                    showMessageDialog(null, "The Employee has been added successfully  : )", "Add Employee", INFORMATION_MESSAGE);
                    jLabel_salary.setText("Salary :");
                    jLabel_hour_work.setText("Hours Worked : ");
                    jLabel_hour_work.setVisible(false);
                    Hour_work.setVisible(false);
                    Hour_work.setText("0");
                    Hour_work.setEnabled(false);
                    jComboBox_Emp_type.setSelectedIndex(0);
                    jTextField_PayDetails.setText("");
                    jTextfield_taxDetails.setText("");
                    jTextField_TaxAmount.setText("");
                    Salary.setText("");
                    jTextField_Name.setText("");
                }
            } else if (selectedItemType.equals("Hourly")) {
                int hours = (int) Math.round(hoursWorked);
                if (taxAmmount >= (hours * salary_hourlyR_commissionR)) {
                    showMessageDialog(null, "Tax amount is greater than the salary. Must change it ", "Warning", WARNING_MESSAGE);
                } else {
                    employee = new HourlyEmployee(name, selectedItemType, selectedItemPay, payDetails, tax, salary_hourlyR_commissionR, hours);
                    PayrollSystem.addEmployee(employee);
                    showMessageDialog(null, "The Employee has been added successfully  : )", "Add Employee", INFORMATION_MESSAGE);
                    jLabel_salary.setText("Salary :");
                    jLabel_hour_work.setText("Hours Worked : ");
                    jLabel_hour_work.setVisible(false);
                    Hour_work.setVisible(false);
                    Hour_work.setText("0");
                    Hour_work.setEnabled(false);
                    jComboBox_Emp_type.setSelectedIndex(0);
                    jTextField_PayDetails.setText("");
                    jTextfield_taxDetails.setText("");
                    jTextField_TaxAmount.setText("");
                    Salary.setText("");
                    jTextField_Name.setText("");
                }
            } else if (selectedItemType.equals("Commissioned")) {
                if (taxAmmount >= (salary_hourlyR_commissionR * totalSales)) {
                    showMessageDialog(null, "Tax amount is greater than the salary. Must change it ", "Warning", WARNING_MESSAGE);
                } else {
                    employee = new CommissionedEmployee(name, selectedItemType, selectedItemPay, payDetails, tax, salary_hourlyR_commissionR, totalSales);
                    PayrollSystem.addEmployee(employee);
                    showMessageDialog(null, "The Employee has been added successfully  : )", "Add Employee", INFORMATION_MESSAGE);
                    jLabel_salary.setText("Salary :");
                    jLabel_hour_work.setText("Hours Worked : ");
                    jLabel_hour_work.setVisible(false);
                    Hour_work.setVisible(false);
                    Hour_work.setText("0");
                    Hour_work.setEnabled(false);
                    jComboBox_Emp_type.setSelectedIndex(0);
                    jTextField_PayDetails.setText("");
                    jTextfield_taxDetails.setText("");
                    jTextField_TaxAmount.setText("");
                    Salary.setText("");
                    jTextField_Name.setText("");
                }
            }
        } else {
            showMessageDialog(null, "You must fill all Fields", "Empty Field", ERROR_MESSAGE);
        }
        jButton_AddActionPerformed(evt);
    }

    private void updateEmployee() {
        if (jTextField_Name2.getText().isEmpty() || jTextField_TaxAmount2.getText().isEmpty() || Salary1.getText().isEmpty() || jTextField_taxDetails1.getText().isEmpty() || jTextField_PayDetails2.getText().isEmpty() || Hour_work1.getText().isEmpty()) {
            showMessageDialog(null, "You must fill all Fields", "Empty Field", ERROR_MESSAGE);
        } else if (!(jTextField_Name2.getText().isEmpty() || jTextField_TaxAmount2.getText().isEmpty() || Salary1.getText().isEmpty() || jTextField_taxDetails1.getText().isEmpty() || jTextField_PayDetails2.getText().isEmpty() || Hour_work1.getText().isEmpty())) {
            String name = jTextField_Name2.getText();
            String emp_id = Emp_Ids1.getSelectedItem().toString();
            String selectedItemType = jComboBox_Emp_type2.getSelectedItem().toString();
            String selectedItemPay = jComboBox_PayMothod2.getSelectedItem().toString();
            taxAmmount = Double.parseDouble(jTextField_TaxAmount2.getText());
            salary_hourlyR_commissionR = Double.parseDouble(Salary1.getText().trim());
            hoursWorked = Double.parseDouble(Hour_work1.getText());
            totalSales = Double.parseDouble(Hour_work1.getText());
            String taxDetails = jTextField_taxDetails1.getText();
            String payDetails = jTextField_PayDetails2.getText();
            tax = new TaxInformation(taxDetails, taxAmmount);

            int id = Integer.parseInt(emp_id);
            if (selectedItemType.equals("Salaried")) {
                if (taxAmmount >= salary_hourlyR_commissionR) {
                    showMessageDialog(null, "Tax amount is greater than the salary. Must change it", "Warning", WARNING_MESSAGE);

                } else {
                    employee = new SalariedEmployee(id, name, selectedItemType, selectedItemPay, payDetails, tax, salary_hourlyR_commissionR);
                    PayrollSystem.updateEmployeeDetails(id, name, employee);
                    showMessageDialog(null, "The Employee has been updated successfully  : )", "Update Employee", INFORMATION_MESSAGE);
                }
            } else if (selectedItemType.equals("Hourly")) {
                int hours = (int) Math.round(hoursWorked);
                if (taxAmmount >= (hours * salary_hourlyR_commissionR)) {
                    showMessageDialog(null, "Tax amount is greater than the salary. Must change it ", "Warning", WARNING_MESSAGE);
                } else {
                    employee = new HourlyEmployee(id, name, selectedItemType, selectedItemPay, payDetails, tax, salary_hourlyR_commissionR, hours);
                    PayrollSystem.updateEmployeeDetails(id, name, employee);
                    showMessageDialog(null, "The Employee has been updated successfully  : )", "Update Employee", INFORMATION_MESSAGE);
                }
            } else if (selectedItemType.equals("Commissioned")) {
                if (taxAmmount >= (salary_hourlyR_commissionR * totalSales)) {
                    showMessageDialog(null, "Tax amount is greater than the salary. Must change it ", "Warning", WARNING_MESSAGE);
                } else {
                    employee = new CommissionedEmployee(id, name, selectedItemType, selectedItemPay, payDetails, tax, salary_hourlyR_commissionR, totalSales);
                    PayrollSystem.updateEmployeeDetails(id, name, employee);
                    showMessageDialog(null, "The Employee has been update successfully  : )", "Update Employee", INFORMATION_MESSAGE);
                }
            }
        } else {
            showMessageDialog(null, "Invalid data :(", "Error", ERROR_MESSAGE);
        }
    }

    private void removeEmployee(java.awt.event.ActionEvent evt) {
        String ids = " ";
        int id = 0;
        try {
            id = Integer.parseInt(Ids_EmpS.getSelectedItem().toString());
        } catch (NullPointerException e) {
            showMessageDialog(null, "There are no employees, go add them ", "Empty", INFORMATION_MESSAGE);
        }
        if (PayrollSystem.removeEmployee(id)) {
            showMessageDialog(null, "The Employee has been removed successfully  : )", "Remove Employee", INFORMATION_MESSAGE);
            Ids_EmpS.removeAllItems();
            Name_rEmp.setText("");
            jButton_RemoveActionPerformed(evt);

        } else {
            showMessageDialog(null, "Invalid remove Employee  : (", "Error", ERROR_MESSAGE);
        }
    }

    private void showEmployees() {

        tbModel_employees = (DefaultTableModel) jTable_Employees.getModel();
        String totalPayslips = String.valueOf(PayrollSystem.calculateTotalPayroll());
        SalariedEmployee emp1;
        HourlyEmployee emp2;
        CommissionedEmployee emp3;
        String salary = null;
        String hourRate = null;
        String hourWorked = null;
        String commissionRate = null;
        String totalSales = null;
        String Total = null;
        if (tbModel_employees.getRowCount() > 0) {
            tbModel_employees.setRowCount(0);
        }
        for (Employee e : employees) {
            String id = String.valueOf(e.getEmployeeID());
            String name = e.getEmployeeName();
            String payMethod = e.getEmpPaymentMethod();
            String EmpType = String.valueOf(e.getEmployeeType());
            String taxAmount = String.valueOf(e.getEmpTaxInformation().taxAmount);
            if (EmpType.equals("Salaried")) {
                emp1 = (SalariedEmployee) e;
                salary = String.valueOf(emp1.getSalary());
                Total = String.valueOf(emp1.calculatePay());
            } else if (EmpType.equals("Hourly")) {
                emp2 = (HourlyEmployee) e;
                hourRate = String.valueOf(emp2.getHourPayRate());
                hourWorked = String.valueOf(emp2.getHoursWorked());
                Total = String.valueOf(emp2.calculatePay());
            } else if (EmpType.equals("Commissioned")) {
                emp3 = (CommissionedEmployee) e;
                commissionRate = String.valueOf(emp3.getCommissionRate());
                totalSales = String.valueOf(emp3.getTotalSales());
                Total = String.valueOf(emp3.calculatePay());
            }
            String[] data = {id, name, EmpType, payMethod, taxAmount, salary, hourRate, hourWorked, commissionRate, totalSales, Total};
            tbModel_employees.addRow(data);
            hourRate = "";
            hourWorked = "";
            salary = "";
            commissionRate = "";
            totalSales = "";
            Total = "";
        }
        totalpay1.setText(totalPayslips);
    }
    private void editAcounttUser() {
        if (jTextField_Username4.getText().isEmpty() || jTextField_Password5.getText().isEmpty()) {
            valid_empty.setText("There is an empty field !!");
        } else if (jTextField_Password5.getText().length() < 6) {
            valid_empty.setText("The password must be at least 6 characters");
        } else {
            valid_empty.setText("");
            int id = Integer.parseInt(Id_user1.getText());
            PayrollSystem.updateUserDetails(id, jTextField_Username4.getText(), jTextField_Password5.getText());
            showMessageDialog(null, "The user has been updated successfully  : )", "Update User", INFORMATION_MESSAGE);
        }
    }
    private void clearAll() {
        jTextfield_taxDetails.setText("");
        jTextField_Name.setText("");
        jTextField_PayDetails.setText("");
        jTextField_TaxAmount.setText("");
        jTextField_Username4.setText("");
        jTextField_Password5.setText("");
        jTextField_username.setText("");
        jPasswordField_login.setText("");
        jPasswordField_add_User.setText("");
        Salary.setText("");
        Hour_work.setText("0");
        Hour_work.setEnabled(false);
        jTextField_M_Id3.setText("");
        jTextField_M_Re_Id7.setText("");
        jTextField_M_Up_Password7.setText("");
        jTextField_M_Up_Username6.setText("");
        jTextField_M_add_Username5.setText("");
        new_pass.setText("");
        confirm_new_pass.setText("");
        old_pass.setText("");
        valid_empty_password.setText("");
        valid_empty_username.setText("");
        remove_User_Valid3.setText("");
        update_User_Valid2.setText("");
        valid_empty.setText("");
        valid_empty_password1.setText("");
        add_User_Valid.setText("");
        jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jPasswordField_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jTextField_M_add_Username5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jPasswordField_add_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jTextField_M_Re_Id7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jTextField_M_Id3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jPanel_Add_Emp.setVisible(false);
        jPanel_Update_Emp.setVisible(false);
        jPanel_Show_Emps.setVisible(false);
        jPanel_Edit_Account.setVisible(false);
        jPanel_Remove_Emp.setVisible(false);
        jLabel_User_Bar.setText("Welcome to payroll system");
        jLabel_Manger_Bar.setText("Welcome to payroll system");
        jPanel_admin_user.setVisible(false);
        jPanel_Home_User.setVisible(false);
        jPanel_Home_Manger.setVisible(false);
        jPanel_Add_Emp.setVisible(false);
        jPanel_Update_Emp.setVisible(false);
        jPanel_Remove_Emp.setVisible(false);
        jPanel_GeneratePayslips.setVisible(false);
        jPanel_Show_Emps.setVisible(false);
        jPanel_Edit_Account.setVisible(false);
        jPanel_login.setVisible(true);
        jTextField_username.setText("");
        jPasswordField_login.setText("");
    }

    private void validInput(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE)) {
            getToolkit().beep();
            evt.consume();
        }
    }

    private void exit() {
        try {
            FilesHandler.FileWriterUser();
            FilesHandler.FileWriterEmployee();
        } catch (IOException ex) {
            Logger.getLogger(CPS_Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel_admin_user = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        jButton_Manager = new javax.swing.JButton();
        jButton_User = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();
        jPanel_login = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton_login = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField_username = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPasswordField_login = new javax.swing.JPasswordField();
        jLabel_Back = new javax.swing.JLabel();
        valid_empty_username = new javax.swing.JLabel();
        valid_empty_password = new javax.swing.JLabel();
        jCheckBox_Hide_Show = new javax.swing.JCheckBox();
        jPanel_Home_User = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel_User_Bar = new javax.swing.JLabel();
        jPanel_Show = new javax.swing.JPanel();
        jPanel_Add_Emp = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField_Name = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_Emp_type = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBox_PayMothod = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jTextField_TaxAmount = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton_Cadd = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();
        jTextField_PayDetails = new javax.swing.JTextField();
        Salary = new javax.swing.JTextField();
        jLabel_salary = new javax.swing.JLabel();
        Hour_work = new javax.swing.JTextField();
        jLabel_hour_work = new javax.swing.JLabel();
        emp_Id = new javax.swing.JLabel();
        jTextfield_taxDetails = new javax.swing.JTextField();
        jPanel_Update_Emp = new javax.swing.JPanel();
        jButton_Cupdate = new javax.swing.JButton();
        Salary1 = new javax.swing.JTextField();
        jComboBox_PayMothod2 = new javax.swing.JComboBox<>();
        jLabel_salary1 = new javax.swing.JLabel();
        Hour_work1 = new javax.swing.JTextField();
        jLabel_hour_work1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField_TaxAmount2 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jTextField_Name2 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jComboBox_Emp_type2 = new javax.swing.JComboBox<>();
        jTextField_PayDetails2 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        Emp_Ids1 = new javax.swing.JComboBox<>();
        jTextField_taxDetails1 = new javax.swing.JTextField();
        jPanel_Remove_Emp = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jButton_Cremove = new javax.swing.JButton();
        Ids_EmpS = new javax.swing.JComboBox<>();
        Name_rEmp = new javax.swing.JLabel();
        jPanel_Show_Emps = new javax.swing.JPanel();
        table_Emps = new javax.swing.JScrollPane();
        jTable_Employees = new javax.swing.JTable();
        jComboBox_Emp_type3 = new javax.swing.JComboBox<>();
        TotalPay1 = new javax.swing.JLabel();
        totalpay1 = new javax.swing.JLabel();
        TotalPay2 = new javax.swing.JLabel();
        jPanel_Edit_Account = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jTextField_Username4 = new javax.swing.JTextField();
        jButton_CUpdate_User = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        jTextField_Password5 = new javax.swing.JTextField();
        Id_user1 = new javax.swing.JLabel();
        valid_empty = new javax.swing.JLabel();
        jPanel_GeneratePayslips = new javax.swing.JPanel();
        jLabel_salary2 = new javax.swing.JLabel();
        jLabel_hour_work2 = new javax.swing.JLabel();
        TaxDetails = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        TaxAmount = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        PayDetails = new javax.swing.JLabel();
        Type = new javax.swing.JLabel();
        PayMethod = new javax.swing.JLabel();
        Id_pay = new javax.swing.JComboBox<>();
        name = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        paymethod = new javax.swing.JLabel();
        paydetails = new javax.swing.JLabel();
        taxamount = new javax.swing.JLabel();
        taxdetails = new javax.swing.JLabel();
        salary = new javax.swing.JLabel();
        hourwork_comission = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        TotalPay = new javax.swing.JLabel();
        totalpay = new javax.swing.JLabel();
        jPanel_Buttons = new javax.swing.JPanel();
        jButton_Add = new javax.swing.JButton();
        jButton_Update = new javax.swing.JButton();
        jButton_Remove = new javax.swing.JButton();
        jButton_Payslips = new javax.swing.JButton();
        jButton_Edit_Account = new javax.swing.JButton();
        jButton_Logout = new javax.swing.JButton();
        jButton_Manager_Login = new javax.swing.JButton();
        jButton_Show_Emps = new javax.swing.JButton();
        jButton_Exit = new javax.swing.JButton();
        jPanel_Home_Manger = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel_Manger_Bar = new javax.swing.JLabel();
        jPanel_Show_Manger = new javax.swing.JPanel();
        jPanel_Add_User = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jTextField_M_add_Username5 = new javax.swing.JTextField();
        jButton_CM_Add_User = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        add_User_Valid = new javax.swing.JLabel();
        jPasswordField_add_User = new javax.swing.JPasswordField();
        jCheckBox_Hide_Show1 = new javax.swing.JCheckBox();
        add_User = new javax.swing.JLabel();
        jPanel_MUpdate_User = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        username_user_up = new javax.swing.JLabel();
        jTextField_M_Up_Username6 = new javax.swing.JTextField();
        jButton_CMUpdate_User1 = new javax.swing.JButton();
        password_user_up = new javax.swing.JLabel();
        jTextField_M_Up_Password7 = new javax.swing.JTextField();
        jTextField_M_Id3 = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        update_User_Valid2 = new javax.swing.JLabel();
        jPanel_Remove_User = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jTextField_M_Re_Id7 = new javax.swing.JTextField();
        remove_User_Valid3 = new javax.swing.JLabel();
        jButton_CM_remove_User = new javax.swing.JButton();
        jPanel_Show_Users = new javax.swing.JPanel();
        table_Users = new javax.swing.JScrollPane();
        jTable_Users = new javax.swing.JTable();
        Id_user = new javax.swing.JLabel();
        jPanel_Change_Password = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jButton_MChange = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        old_pass = new javax.swing.JPasswordField();
        new_pass = new javax.swing.JPasswordField();
        confirm_new_pass = new javax.swing.JPasswordField();
        jCheckBox_Hide_Show2 = new javax.swing.JCheckBox();
        jCheckBox_Hide_Show3 = new javax.swing.JCheckBox();
        jCheckBox_Hide_Show4 = new javax.swing.JCheckBox();
        valid_empty_password1 = new javax.swing.JLabel();
        jPanel_Buttons_Manger = new javax.swing.JPanel();
        jButton_Manger_Change_Pass = new javax.swing.JButton();
        jButton_Manger_Logout = new javax.swing.JButton();
        jButton_User_Login = new javax.swing.JButton();
        jButton_Manger_Add_User = new javax.swing.JButton();
        jButton_Manger_Update_User = new javax.swing.JButton();
        jButton_Manger_Remove_User = new javax.swing.JButton();
        jButton_Show_User = new javax.swing.JButton();
        jButton_Exit1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Payroll Management");
        setPreferredSize(new java.awt.Dimension(1150, 760));
        setSize(new java.awt.Dimension(1150, 760));

        jPanel_admin_user.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_admin_user.setName(""); // NOI18N
        jPanel_admin_user.setPreferredSize(new java.awt.Dimension(1150, 760));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/p.jpg"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(720, 1024));

        jLabel3.setFont(new java.awt.Font("Mongolian Baiti", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 52, 149));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/p5.png"))); // NOI18N
        jLabel3.setToolTipText("");

        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Mongolian Baiti", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(170, 190, 217));
        label1.setText(" It calculates employee salaries\n and taxes, tracks hours worked, and issues\n payments through direct deposit or check.");

        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label2.setFont(new java.awt.Font("Mongolian Baiti", 1, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(170, 190, 217));
        label2.setText("A payroll system used to manage and automate the\n process of  paying employees. It calculates employee salaries\n and taxes, tracks hours worked, and issues\n payments through direct deposit or check.");

        label3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label3.setFont(new java.awt.Font("Mongolian Baiti", 1, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(170, 190, 217));
        label3.setText(" payments through direct deposit or check.");

        jButton_Manager.setBackground(new java.awt.Color(252, 252, 252));
        jButton_Manager.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jButton_Manager.setForeground(new java.awt.Color(15, 27, 151));
        jButton_Manager.setText("Manger");
        jButton_Manager.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Manager.setFocusable(false);
        jButton_Manager.setPreferredSize(new java.awt.Dimension(216, 49));
        jButton_Manager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ManagerActionPerformed(evt);
            }
        });

        jButton_User.setBackground(new java.awt.Color(15, 27, 151));
        jButton_User.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jButton_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_User.setText("User");
        jButton_User.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), java.awt.Color.white, java.awt.Color.white, java.awt.Color.white));
        jButton_User.setFocusable(false);
        jButton_User.setPreferredSize(new java.awt.Dimension(216, 49));
        jButton_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UserActionPerformed(evt);
            }
        });
        jButton_User.setHideActionText(true);

        jLabel4.setFont(new java.awt.Font("Mongolian Baiti", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 52, 149));
        jLabel4.setText("Payroll");
        jLabel4.setToolTipText("");

        close.setBackground(new java.awt.Color(255, 255, 255));
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/close.png"))); // NOI18N
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_admin_userLayout = new javax.swing.GroupLayout(jPanel_admin_user);
        jPanel_admin_user.setLayout(jPanel_admin_userLayout);
        jPanel_admin_userLayout.setHorizontalGroup(
            jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_admin_userLayout.createSequentialGroup()
                .addGroup(jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_admin_userLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_admin_userLayout.createSequentialGroup()
                                .addComponent(jButton_User, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton_Manager, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_admin_userLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 799, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_admin_userLayout.createSequentialGroup()
                        .addComponent(close)
                        .addGap(185, 185, 185))))
            .addGroup(jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_admin_userLayout.createSequentialGroup()
                    .addGap(92, 92, 92)
                    .addComponent(jLabel4)
                    .addContainerGap(1240, Short.MAX_VALUE)))
        );
        jPanel_admin_userLayout.setVerticalGroup(
            jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_admin_userLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(219, 219, 219)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112)
                .addGroup(jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Manager, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_User, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(284, Short.MAX_VALUE))
            .addGroup(jPanel_admin_userLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(jPanel_admin_userLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_admin_userLayout.createSequentialGroup()
                    .addGap(195, 195, 195)
                    .addComponent(jLabel4)
                    .addContainerGap(509, Short.MAX_VALUE)))
        );

        jPanel_login.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_login.setName(""); // NOI18N
        jPanel_login.setPreferredSize(new java.awt.Dimension(1150, 760));
        jPanel_login.setVisible(false);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/p6.png"))); // NOI18N
        jLabel2.setPreferredSize(new java.awt.Dimension(720, 1024));

        jLabel5.setFont(new java.awt.Font("Mongolian Baiti", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(30, 52, 149));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/p4.png"))); // NOI18N
        jLabel5.setToolTipText("");

        jButton_login.setBackground(new java.awt.Color(15, 27, 151));
        jButton_login.setFont(new java.awt.Font("Mongolian Baiti", 1, 24)); // NOI18N
        jButton_login.setForeground(new java.awt.Color(255, 255, 255));
        jButton_login.setText("Log In");
        jButton_login.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255), new java.awt.Color(255, 255, 255)));
        jButton_login.setFocusable(false);
        jButton_login.setPreferredSize(new java.awt.Dimension(216, 49));
        jButton_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_loginActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(82, 120, 247));
        jLabel6.setLabelFor(jPasswordField_login);
        jLabel6.setText("Password");

        jTextField_username.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(82, 120, 247));
        jLabel7.setLabelFor(jTextField_username);
        jLabel7.setText("Username");

        jPasswordField_login.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        jPasswordField_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));

        jLabel_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back1.png"))); // NOI18N
        jLabel_Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_BackMouseClicked(evt);
            }
        });

        valid_empty_username.setBackground(new java.awt.Color(255, 255, 255));
        valid_empty_username.setForeground(new java.awt.Color(199, 18, 18));
        valid_empty_username.setText("");

        valid_empty_password.setBackground(new java.awt.Color(255, 255, 255));
        valid_empty_password.setForeground(new java.awt.Color(199, 18, 18));
        valid_empty_password.setText("");

        jCheckBox_Hide_Show.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_Hide_Show.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox_Hide_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png"))); // NOI18N
        jCheckBox_Hide_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_Hide_ShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_loginLayout = new javax.swing.GroupLayout(jPanel_login);
        jPanel_login.setLayout(jPanel_loginLayout);
        jPanel_loginLayout.setHorizontalGroup(
            jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_loginLayout.createSequentialGroup()
                .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_loginLayout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jButton_login, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_loginLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_loginLayout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valid_empty_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jTextField_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(jPasswordField_login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                            .addComponent(valid_empty_password, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox_Hide_Show))
                    .addGroup(jPanel_loginLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel_Back)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel_loginLayout.setVerticalGroup(
            jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_loginLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel_Back)
                .addGap(33, 33, 33)
                .addComponent(jLabel5)
                .addGap(141, 141, 141)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(valid_empty_username, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox_Hide_Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField_login, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(valid_empty_password, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jButton_login, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
            .addGroup(jPanel_loginLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(19, 19, 19))
        );

        jPanel_Home_User.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Home_User.setPreferredSize(new java.awt.Dimension(1150, 760));
        jPanel_Home_User.setVisible(false);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/p4.png"))); // NOI18N

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 28, 65));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Home");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 75, 243), 3));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_User_Bar.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_User_Bar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        jLabel_User_Bar.setForeground(new java.awt.Color(15, 28, 65));
        jLabel_User_Bar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_User_Bar.setText("Welcome to payroll system");
        jLabel_User_Bar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(73, 75, 243), 3, true));
        jLabel_User_Bar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel_Show.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Show.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 75, 243), 3));
        jPanel_Show.setMinimumSize(new java.awt.Dimension(1243, 494));

        jPanel_Add_Emp.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Add_Emp.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Add_Emp.setVisible(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Employee Id :");
        jLabel11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Employee Name :");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_Name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_Name.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Employee Type :");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_Emp_type.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_Emp_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salaried", "Hourly", "Commissioned" }));
        jComboBox_Emp_type.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        jComboBox_Emp_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Emp_typeActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("PaymentMethod :");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_PayMothod.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_PayMothod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bank Transfer", "Check" }));
        jComboBox_PayMothod.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(" TaxDetails : ");
        jLabel16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_TaxAmount.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_TaxAmount.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        jTextField_TaxAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_TaxAmountKeyTyped(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("TaxAmount : ");
        jLabel17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("PaymentDetails :");
        jLabel18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton_Cadd.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Cadd.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Cadd.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cadd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton_Cadd.setText("Add ");
        jButton_Cadd.setBorder(null);
        jButton_Cadd.setFocusPainted(false);
        jButton_Cadd.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Cadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CaddActionPerformed(evt);
            }
        });

        jButton_Clear.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Clear.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Clear.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/clear.png"))); // NOI18N
        jButton_Clear.setText("Clear");
        jButton_Clear.setBorder(null);
        jButton_Clear.setFocusPainted(false);
        jButton_Clear.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
            }
        });

        jTextField_PayDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_PayDetails.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));

        Salary.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        Salary.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        Salary.setEnabled(true);
        Salary.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SalaryKeyTyped(evt);
            }
        });

        jLabel_salary.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_salary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_salary.setText("Salary : ");
        jLabel_salary.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Hour_work.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        Hour_work.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        Hour_work.setEnabled(true);
        Hour_work.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Hour_workKeyTyped(evt);
            }
        });
        Hour_work.setVisible(false);
        Hour_work.setEnabled(false);
        Hour_work.setText("0");

        jLabel_hour_work.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_hour_work.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_hour_work.setText(" Hours Worked : ");
        jLabel_hour_work.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_hour_work.setVisible(false);

        emp_Id.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        emp_Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emp_Id.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextfield_taxDetails.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextfield_taxDetails.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));

        javax.swing.GroupLayout jPanel_Add_EmpLayout = new javax.swing.GroupLayout(jPanel_Add_Emp);
        jPanel_Add_Emp.setLayout(jPanel_Add_EmpLayout);
        jPanel_Add_EmpLayout.setHorizontalGroup(
            jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                        .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                        .addGap(44, 44, 44)
                                        .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox_Emp_type, javax.swing.GroupLayout.Alignment.TRAILING, 0, 237, Short.MAX_VALUE)
                                        .addComponent(jTextField_Name, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jComboBox_PayMothod, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField_PayDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emp_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                        .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                .addGap(491, 491, 491)
                                .addComponent(jLabel_hour_work, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                                .addGap(497, 497, 497)
                                .addComponent(jLabel_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Add_EmpLayout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Hour_work, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Salary, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_TaxAmount, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextfield_taxDetails, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))))
                    .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jButton_Cadd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(138, 138, 138)
                        .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Add_EmpLayout.setVerticalGroup(
            jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Add_EmpLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emp_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(92, 92, 92)
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_TaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Emp_type, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextfield_taxDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_PayMothod, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_salary, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salary, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Add_EmpLayout.createSequentialGroup()
                        .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Hour_work, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_hour_work, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Add_EmpLayout.createSequentialGroup()
                        .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_PayDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68)))
                .addGroup(jPanel_Add_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Cadd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel_Update_Emp.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Update_Emp.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Update_Emp.setPreferredSize(new java.awt.Dimension(1185, 571));
        jPanel_Update_Emp.setVisible(false);

        jButton_Cupdate.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Cupdate.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Cupdate.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cupdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update2.png"))); // NOI18N
        jButton_Cupdate.setText("Update");
        jButton_Cupdate.setBorder(null);
        jButton_Cupdate.setFocusPainted(false);
        jButton_Cupdate.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Cupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CupdateActionPerformed(evt);
            }
        });

        Salary1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        Salary1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        Salary1.setEnabled(true);
        Salary1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Salary1KeyTyped(evt);
            }
        });

        jComboBox_PayMothod2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_PayMothod2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bank Transfer", "Check" }));
        jComboBox_PayMothod2.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));

        jLabel_salary1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_salary1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_salary1.setText("Salary : ");
        jLabel_salary1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Hour_work1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        Hour_work1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        Hour_work1.setEnabled(true);
        Hour_work1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Hour_work1KeyTyped(evt);
            }
        });
        Hour_work1.setVisible(false);
        Hour_work1.setEnabled(false);
        Hour_work1.setText("0");

        jLabel_hour_work1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_hour_work1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_hour_work1.setText(" Hours Worked : ");
        jLabel_hour_work1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_hour_work1.setVisible(false);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText(" TaxDetails : ");
        jLabel29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_TaxAmount2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_TaxAmount2.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        jTextField_TaxAmount2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_TaxAmount2KeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Employee Id :");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("TaxAmount : ");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Employee Name :");
        jLabel31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("PaymentDetails :");
        jLabel32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_Name2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_Name2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_Name2.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        jTextField_Name2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField_Name2.setEnabled(false);

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Employee Type :");
        jLabel33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox_Emp_type2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_Emp_type2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Salaried", "Hourly", "Commissioned" }));
        jComboBox_Emp_type2.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        jComboBox_Emp_type2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Emp_type2ActionPerformed(evt);
            }
        });

        jTextField_PayDetails2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_PayDetails2.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("PaymentMethod :");
        jLabel34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Emp_Ids1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Emp_Ids1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        Emp_Ids1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Emp_Ids1ItemStateChanged(evt);
            }
        });

        jTextField_taxDetails1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_taxDetails1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField_taxDetails1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));
        jTextField_taxDetails1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField_Name2.setEnabled(false);

        javax.swing.GroupLayout jPanel_Update_EmpLayout = new javax.swing.GroupLayout(jPanel_Update_Emp);
        jPanel_Update_Emp.setLayout(jPanel_Update_EmpLayout);
        jPanel_Update_EmpLayout.setHorizontalGroup(
            jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Update_EmpLayout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Emp_Ids1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(412, 412, 412))
            .addGroup(jPanel_Update_EmpLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Update_EmpLayout.createSequentialGroup()
                        .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox_PayMothod2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_Name2)
                            .addComponent(jComboBox_Emp_type2, 0, 237, Short.MAX_VALUE))
                        .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Update_EmpLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Update_EmpLayout.createSequentialGroup()
                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29))
                                    .addGroup(jPanel_Update_EmpLayout.createSequentialGroup()
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(14, 14, 14))))
                            .addGroup(jPanel_Update_EmpLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel_hour_work1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel_salary1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                    .addComponent(jTextField_PayDetails2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Update_EmpLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Hour_work1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Salary1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField_TaxAmount2, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField_taxDetails1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Update_EmpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Cupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(528, 528, 528))
        );
        jPanel_Update_EmpLayout.setVerticalGroup(
            jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Update_EmpLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Emp_Ids1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_Name2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_TaxAmount2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_Emp_type2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_taxDetails1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_PayMothod2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_salary1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Salary1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel_Update_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_PayDetails2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_hour_work1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Hour_work1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_Cupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel_Remove_Emp.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Remove_Emp.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Remove_Emp.setPreferredSize(new java.awt.Dimension(1185, 571));
        jPanel_Remove_Emp.setVisible(false);

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Employee Id :");
        jLabel37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("Employee Name :");
        jLabel38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton_Cremove.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Cremove.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Cremove.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cremove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fired.png"))); // NOI18N
        jButton_Cremove.setText("Remove");
        jButton_Cremove.setBorder(null);
        jButton_Cremove.setFocusPainted(false);
        jButton_Cremove.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Cremove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CremoveActionPerformed(evt);
            }
        });

        Ids_EmpS.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Ids_EmpS.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        Ids_EmpS.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Ids_EmpSItemStateChanged(evt);
            }
        });

        Name_rEmp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Name_rEmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Name_rEmp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel_Remove_EmpLayout = new javax.swing.GroupLayout(jPanel_Remove_Emp);
        jPanel_Remove_Emp.setLayout(jPanel_Remove_EmpLayout);
        jPanel_Remove_EmpLayout.setHorizontalGroup(
            jPanel_Remove_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Remove_EmpLayout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addGroup(jPanel_Remove_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_Remove_EmpLayout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(Ids_EmpS, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Remove_EmpLayout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_Remove_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_Remove_EmpLayout.createSequentialGroup()
                                .addComponent(jButton_Cremove, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(136, 136, 136))
                            .addComponent(Name_rEmp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Remove_EmpLayout.setVerticalGroup(
            jPanel_Remove_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Remove_EmpLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel_Remove_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ids_EmpS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(jPanel_Remove_EmpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Name_rEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(133, 133, 133)
                .addComponent(jButton_Cremove, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_Show_Emps.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Show_Emps.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Show_Emps.setPreferredSize(new java.awt.Dimension(1185, 571));
        jPanel_Show_Emps.setVisible(false);

        jTable_Employees.setFont(new java.awt.Font("Segoe UI Variable", 1, 14)); // NOI18N
        jTable_Employees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Type", "PayMethod", "TaxAmount", "Salary", "Hour Rate", "Hours Worked", "Commission Rate", "Total Sales", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Employees.setDragEnabled(true);
        jTable_Employees.setEnabled(false);
        jTable_Employees.setGridColor(new java.awt.Color(0, 0, 0));
        jTable_Employees.setRowHeight(25);
        jTable_Employees.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable_Employees.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable_Employees.setShowGrid(true);
        table_Emps.setViewportView(jTable_Employees);

        jComboBox_Emp_type3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox_Emp_type3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Salaried", "Hourly", "Commissioned" }));
        jComboBox_Emp_type3.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        jComboBox_Emp_type3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_Emp_type3ItemStateChanged(evt);
            }
        });

        TotalPay1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TotalPay1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalPay1.setText("Total Payslips : ");
        TotalPay1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        totalpay1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalpay1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalpay1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        totalpay1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TotalPay2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        TotalPay2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalPay2.setText("Employee Type : ");
        TotalPay2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel_Show_EmpsLayout = new javax.swing.GroupLayout(jPanel_Show_Emps);
        jPanel_Show_Emps.setLayout(jPanel_Show_EmpsLayout);
        jPanel_Show_EmpsLayout.setHorizontalGroup(
            jPanel_Show_EmpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table_Emps)
            .addGroup(jPanel_Show_EmpsLayout.createSequentialGroup()
                .addGroup(jPanel_Show_EmpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Show_EmpsLayout.createSequentialGroup()
                        .addGap(382, 382, 382)
                        .addComponent(TotalPay2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox_Emp_type3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Show_EmpsLayout.createSequentialGroup()
                        .addGap(402, 402, 402)
                        .addComponent(TotalPay1)
                        .addGap(30, 30, 30)
                        .addComponent(totalpay1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(389, Short.MAX_VALUE))
        );
        jPanel_Show_EmpsLayout.setVerticalGroup(
            jPanel_Show_EmpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Show_EmpsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_Show_EmpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Show_EmpsLayout.createSequentialGroup()
                        .addComponent(jComboBox_Emp_type3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(TotalPay2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(table_Emps, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Show_EmpsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalPay1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalpay1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        jPanel_Edit_Account.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Edit_Account.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Edit_Account.setVisible(false);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText(" Id :");
        jLabel39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Username :");
        jLabel40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_Username4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_Username4.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));

        jButton_CUpdate_User.setBackground(new java.awt.Color(73, 75, 243));
        jButton_CUpdate_User.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_CUpdate_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_CUpdate_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update2.png"))); // NOI18N
        jButton_CUpdate_User.setText("Update");
        jButton_CUpdate_User.setBorder(null);
        jButton_CUpdate_User.setFocusPainted(false);
        jButton_CUpdate_User.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_CUpdate_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CUpdate_UserActionPerformed(evt);
            }
        });

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel41.setText("Password :");
        jLabel41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_Password5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_Password5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));

        Id_user1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Id_user1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Id_user1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        valid_empty.setBackground(new java.awt.Color(255, 255, 255));
        valid_empty.setForeground(new java.awt.Color(199, 18, 18));
        valid_empty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valid_empty_username.setText("");

        javax.swing.GroupLayout jPanel_Edit_AccountLayout = new javax.swing.GroupLayout(jPanel_Edit_Account);
        jPanel_Edit_Account.setLayout(jPanel_Edit_AccountLayout);
        jPanel_Edit_AccountLayout.setHorizontalGroup(
            jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Edit_AccountLayout.createSequentialGroup()
                .addGap(367, 367, 367)
                .addGroup(jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_Edit_AccountLayout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Id_user1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Edit_AccountLayout.createSequentialGroup()
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField_Username4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Edit_AccountLayout.createSequentialGroup()
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(valid_empty, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField_Password5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))))
                .addContainerGap(469, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Edit_AccountLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_CUpdate_User, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(532, 532, 532))
        );
        jPanel_Edit_AccountLayout.setVerticalGroup(
            jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Edit_AccountLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Id_user1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Username4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel_Edit_AccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Password5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(valid_empty, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton_CUpdate_User, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_GeneratePayslips.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_GeneratePayslips.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_GeneratePayslips.setVisible(false);

        jLabel_salary2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_salary2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_salary2.setText("Salary : ");
        jLabel_salary2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_hour_work2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel_hour_work2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_hour_work2.setText(" Hours Worked : ");
        jLabel_hour_work2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel_hour_work2.setVisible(false);

        TaxDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TaxDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaxDetails.setText(" TaxDetails : ");
        TaxDetails.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Employee Id :");
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TaxAmount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TaxAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TaxAmount.setText("TaxAmount : ");
        TaxAmount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Name.setText(" Name :");
        Name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        PayDetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PayDetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PayDetails.setText("Payment Details :");
        PayDetails.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Type.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Type.setText("Type :");
        Type.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        PayMethod.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        PayMethod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PayMethod.setText("Payment Method :");
        PayMethod.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        Id_pay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Id_pay.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 1, true));
        Id_pay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Id_payItemStateChanged(evt);
            }
        });

        name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        type.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        type.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        type.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        type.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        paymethod.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        paymethod.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paymethod.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        paymethod.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        paydetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        paydetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        paydetails.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        paydetails.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        taxamount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        taxamount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taxamount.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        taxamount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        taxdetails.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        taxdetails.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taxdetails.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        taxdetails.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        salary.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        salary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salary.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        salary.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        hourwork_comission.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        hourwork_comission.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hourwork_comission.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        hourwork_comission.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        hourwork_comission.setVisible(false);
        hourwork_comission.setEnabled(false);

        Total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Total.setText("Total : ");
        Total.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        total.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        total.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        TotalPay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        TotalPay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalPay.setText("Total Payslips : ");
        TotalPay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        totalpay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalpay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalpay.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue));
        totalpay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel_GeneratePayslipsLayout = new javax.swing.GroupLayout(jPanel_GeneratePayslips);
        jPanel_GeneratePayslips.setLayout(jPanel_GeneratePayslipsLayout);
        jPanel_GeneratePayslipsLayout.setHorizontalGroup(
            jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                .addGap(373, 373, 373)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Id_pay, 0, 241, Short.MAX_VALUE)
                .addGap(412, 412, 412))
            .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(PayMethod, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(paydetails, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(paymethod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(totalpay, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(PayDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(TotalPay)))
                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(TaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(TaxDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(taxamount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(taxdetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(130, 130, 130))
                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_hour_work2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))
                            .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                        .addGap(55, 55, 55)
                                        .addComponent(jLabel_salary2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)))
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hourwork_comission, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel_GeneratePayslipsLayout.setVerticalGroup(
            jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Id_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TaxAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taxamount, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Type, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_GeneratePayslipsLayout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(taxdetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TaxDetails, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
                .addGap(74, 74, 74)
                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_salary2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymethod, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PayMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salary, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paydetails, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PayDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_hour_work2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hourwork_comission, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_GeneratePayslipsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TotalPay, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalpay, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_ShowLayout = new javax.swing.GroupLayout(jPanel_Show);
        jPanel_Show.setLayout(jPanel_ShowLayout);
        jPanel_ShowLayout.setHorizontalGroup(
            jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ShowLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel_Add_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jPanel_Update_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 1153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(39, Short.MAX_VALUE)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap(10, Short.MAX_VALUE)
                    .addComponent(jPanel_Remove_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 1173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(54, 54, 54)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(jPanel_Show_Emps, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(35, Short.MAX_VALUE)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap(8, Short.MAX_VALUE)
                    .addComponent(jPanel_Edit_Account, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(30, 30, 30)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_GeneratePayslips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );
        jPanel_ShowLayout.setVerticalGroup(
            jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ShowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_Add_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Update_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Remove_Emp, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Show_Emps, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(8, Short.MAX_VALUE)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Edit_Account, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
            .addGroup(jPanel_ShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_ShowLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_GeneratePayslips, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1, Short.MAX_VALUE)))
        );

        jPanel_Buttons.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Buttons.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 75, 243), 3));

        jButton_Add.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Add.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Add.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton_Add.setText("Add Employee");
        jButton_Add.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Add.setFocusPainted(false);
        jButton_Add.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });

        jButton_Update.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Update.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Update.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user-update.png"))); // NOI18N
        jButton_Update.setText("Update Employee");
        jButton_Update.setActionCommand("Add Employee");
        jButton_Update.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Update.setFocusPainted(false);
        jButton_Update.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_UpdateActionPerformed(evt);
            }
        });

        jButton_Remove.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Remove.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Remove.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fired.png"))); // NOI18N
        jButton_Remove.setText("Remove Employee");
        jButton_Remove.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Remove.setFocusPainted(false);
        jButton_Remove.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveActionPerformed(evt);
            }
        });

        jButton_Payslips.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Payslips.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Payslips.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Payslips.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/finance.png"))); // NOI18N
        jButton_Payslips.setText("Generate Payslips");
        jButton_Payslips.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Payslips.setFocusPainted(false);
        jButton_Payslips.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Payslips.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PayslipsActionPerformed(evt);
            }
        });

        jButton_Edit_Account.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Edit_Account.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Edit_Account.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Edit_Account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lock_reset_.png"))); // NOI18N
        jButton_Edit_Account.setText("Edit Account");
        jButton_Edit_Account.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Edit_Account.setFocusPainted(false);
        jButton_Edit_Account.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Edit_Account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit_AccountActionPerformed(evt);
            }
        });

        jButton_Logout.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Logout.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Logout.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        jButton_Logout.setText("Log Out");
        jButton_Logout.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Logout.setFocusPainted(false);
        jButton_Logout.setHideActionText(true);
        jButton_Logout.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LogoutActionPerformed(evt);
            }
        });

        jButton_Manager_Login.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Manager_Login.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Manager_Login.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Manager_Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login_.png"))); // NOI18N
        jButton_Manager_Login.setText("Manger Login");
        jButton_Manager_Login.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Manager_Login.setFocusPainted(false);
        jButton_Manager_Login.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Manager_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Manager_LoginActionPerformed(evt);
            }
        });

        jButton_Show_Emps.setBackground(new java.awt.Color(238, 238, 238));
        jButton_Show_Emps.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Show_Emps.setForeground(new java.awt.Color(15, 27, 150));
        jButton_Show_Emps.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/employees.png"))); // NOI18N
        jButton_Show_Emps.setText("Show Employees");
        jButton_Show_Emps.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150), new java.awt.Color(15, 27, 150)));
        jButton_Show_Emps.setFocusPainted(false);
        jButton_Show_Emps.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Show_Emps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Show_EmpsActionPerformed(evt);
            }
        });

        jButton_Exit.setBackground(new java.awt.Color(255, 0, 51));
        jButton_Exit.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Exit.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        jButton_Exit.setText("Exit");
        jButton_Exit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Exit.setFocusPainted(false);
        jButton_Exit.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ButtonsLayout = new javax.swing.GroupLayout(jPanel_Buttons);
        jPanel_Buttons.setLayout(jPanel_ButtonsLayout);
        jPanel_ButtonsLayout.setHorizontalGroup(
            jPanel_ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ButtonsLayout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(jPanel_ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ButtonsLayout.createSequentialGroup()
                        .addGroup(jPanel_ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_Show_Emps, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Add, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Update, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Remove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Payslips, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Edit_Account, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Manager_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ButtonsLayout.createSequentialGroup()
                        .addComponent(jButton_Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        jPanel_ButtonsLayout.setVerticalGroup(
            jPanel_ButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Show_Emps, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jButton_Payslips, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Edit_Account, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton_Manager_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jButton_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Exit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_Home_UserLayout = new javax.swing.GroupLayout(jPanel_Home_User);
        jPanel_Home_User.setLayout(jPanel_Home_UserLayout);
        jPanel_Home_UserLayout.setHorizontalGroup(
            jPanel_Home_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Home_UserLayout.createSequentialGroup()
                .addGroup(jPanel_Home_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Buttons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Home_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_User_Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Show, javax.swing.GroupLayout.PREFERRED_SIZE, 1223, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Home_UserLayout.createSequentialGroup()
                .addContainerGap(627, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(568, 568, 568))
        );
        jPanel_Home_UserLayout.setVerticalGroup(
            jPanel_Home_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Home_UserLayout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel_Home_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_User_Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Home_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_Buttons, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Show, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        jPanel_Home_Manger.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Home_Manger.setPreferredSize(new java.awt.Dimension(1150, 760));
        jPanel_Home_Manger.setVisible(false);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/p4.png"))); // NOI18N

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 28, 65));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Home");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 75, 243), 3));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel_Manger_Bar.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_Manger_Bar.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        jLabel_Manger_Bar.setForeground(new java.awt.Color(15, 28, 65));
        jLabel_Manger_Bar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Manger_Bar.setText("Welcome to payroll system");
        jLabel_Manger_Bar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(73, 75, 243), 3, true));
        jLabel_Manger_Bar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel_Show_Manger.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Show_Manger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 75, 243), 3));

        jPanel_Add_User.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Add_User.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Add_User.setVisible(false);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText(" Id :");
        jLabel42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Username :");
        jLabel43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_M_add_Username5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jTextField_M_add_Username5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));

        jButton_CM_Add_User.setBackground(new java.awt.Color(73, 75, 243));
        jButton_CM_Add_User.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_CM_Add_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_CM_Add_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton_CM_Add_User.setText("Add");
        jButton_CM_Add_User.setBorder(null);
        jButton_CM_Add_User.setFocusPainted(false);
        jButton_CM_Add_User.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_CM_Add_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CM_Add_UserActionPerformed(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("Password :");
        jLabel44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        add_User_Valid.setForeground(new java.awt.Color(199, 18, 18));
        add_User_Valid.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_User_Valid.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add_User_Valid.setText("");

        jPasswordField_add_User.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jPasswordField_add_User.setBorder(new javax.swing.border.LineBorder(java.awt.Color.blue, 2, true));

        jCheckBox_Hide_Show1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_Hide_Show1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox_Hide_Show1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png"))); // NOI18N
        jCheckBox_Hide_Show1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_Hide_Show1ActionPerformed(evt);
            }
        });

        add_User.setBackground(new java.awt.Color(255, 255, 255));
        add_User.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        add_User.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add_User.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add_User_Valid.setText("");

        javax.swing.GroupLayout jPanel_Add_UserLayout = new javax.swing.GroupLayout(jPanel_Add_User);
        jPanel_Add_User.setLayout(jPanel_Add_UserLayout);
        jPanel_Add_UserLayout.setHorizontalGroup(
            jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Add_UserLayout.createSequentialGroup()
                .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_User, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                        .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                                .addGap(361, 361, 361)
                                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                                .addGap(358, 358, 358)
                                .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPasswordField_add_User)
                            .addComponent(jTextField_M_add_Username5, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox_Hide_Show1)
                .addGap(470, 470, 470))
            .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                        .addGap(525, 525, 525)
                        .addComponent(jButton_CM_Add_User, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(add_User_Valid, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Add_UserLayout.setVerticalGroup(
            jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_User, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_M_add_Username5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Add_UserLayout.createSequentialGroup()
                        .addGroup(jPanel_Add_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordField_add_User, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(add_User_Valid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton_CM_Add_User, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox_Hide_Show1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(148, 148, 148))
        );

        jPanel_MUpdate_User.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_MUpdate_User.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_MUpdate_User.setVisible(false);

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText(" Id :");
        jLabel45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        username_user_up.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        username_user_up.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        username_user_up.setText("Username :");
        username_user_up.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        username_user_up.setVisible(false);

        jTextField_M_Up_Username6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_M_Up_Username6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));
        jTextField_M_Up_Username6.setVisible(false);

        jButton_CMUpdate_User1.setBackground(new java.awt.Color(73, 75, 243));
        jButton_CMUpdate_User1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_CMUpdate_User1.setForeground(new java.awt.Color(255, 255, 255));
        jButton_CMUpdate_User1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update2.png"))); // NOI18N
        jButton_CMUpdate_User1.setText("Update");
        jButton_CMUpdate_User1.setBorder(null);
        jButton_CMUpdate_User1.setFocusPainted(false);
        jButton_CMUpdate_User1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_CMUpdate_User1.setVisible(false);
        jButton_CMUpdate_User1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CMUpdate_User1ActionPerformed(evt);
            }
        });

        password_user_up.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        password_user_up.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        password_user_up.setText("Password :");
        password_user_up.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        password_user_up.setVisible(false);

        jTextField_M_Up_Password7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_M_Up_Password7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));
        jTextField_M_Up_Password7.setVisible(false);

        jTextField_M_Id3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_M_Id3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jTextField_M_Id3.setEnabled(false);
        jTextField_M_Id3.setEnabled(true);
        jTextField_M_Id3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_M_Id3KeyTyped(evt);
            }
        });

        search.setBackground(new java.awt.Color(73, 75, 243));
        search.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Search");
        search.setBorder(null);
        search.setFocusPainted(false);
        search.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_CMUpdate_User1.setVisible(false);
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        update_User_Valid2.setForeground(new java.awt.Color(199, 18, 18));
        update_User_Valid2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        update_User_Valid2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add_User_Valid.setText("");

        javax.swing.GroupLayout jPanel_MUpdate_UserLayout = new javax.swing.GroupLayout(jPanel_MUpdate_User);
        jPanel_MUpdate_User.setLayout(jPanel_MUpdate_UserLayout);
        jPanel_MUpdate_UserLayout.setHorizontalGroup(
            jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MUpdate_UserLayout.createSequentialGroup()
                .addContainerGap(355, Short.MAX_VALUE)
                .addGroup(jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MUpdate_UserLayout.createSequentialGroup()
                        .addGroup(jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MUpdate_UserLayout.createSequentialGroup()
                                .addGroup(jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(username_user_up, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_M_Up_Username6, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(update_User_Valid2, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_M_Id3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42))
                            .addGroup(jPanel_MUpdate_UserLayout.createSequentialGroup()
                                .addComponent(password_user_up, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_M_Up_Password7, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(382, 382, 382))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_MUpdate_UserLayout.createSequentialGroup()
                        .addComponent(jButton_CMUpdate_User1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(546, 546, 546))))
        );
        jPanel_MUpdate_UserLayout.setVerticalGroup(
            jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_MUpdate_UserLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jTextField_M_Id3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(update_User_Valid2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_M_Up_Username6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username_user_up, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addGroup(jPanel_MUpdate_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_M_Up_Password7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password_user_up, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(90, 90, 90)
                .addComponent(jButton_CMUpdate_User1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );

        jPanel_Remove_User.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Remove_User.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Remove_User.setVisible(false);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Id :");
        jLabel48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField_M_Re_Id7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField_M_Re_Id7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));
        jTextField_M_Re_Id7.setEnabled(false);
        jTextField_M_Re_Id7.setEnabled(true);
        jTextField_M_Re_Id7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_M_Re_Id7KeyTyped(evt);
            }
        });

        remove_User_Valid3.setForeground(new java.awt.Color(199, 18, 18));
        remove_User_Valid3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        remove_User_Valid3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add_User_Valid.setText("");

        jButton_CM_remove_User.setBackground(new java.awt.Color(73, 75, 243));
        jButton_CM_remove_User.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_CM_remove_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_CM_remove_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fired.png"))); // NOI18N
        jButton_CM_remove_User.setText("Remove");
        jButton_CM_remove_User.setBorder(null);
        jButton_CM_remove_User.setFocusPainted(false);
        jButton_CM_remove_User.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_CM_remove_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CM_remove_UserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Remove_UserLayout = new javax.swing.GroupLayout(jPanel_Remove_User);
        jPanel_Remove_User.setLayout(jPanel_Remove_UserLayout);
        jPanel_Remove_UserLayout.setHorizontalGroup(
            jPanel_Remove_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Remove_UserLayout.createSequentialGroup()
                .addGroup(jPanel_Remove_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Remove_UserLayout.createSequentialGroup()
                        .addGap(441, 441, 441)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_M_Re_Id7, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Remove_UserLayout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(remove_User_Valid3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(438, 438, 438))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Remove_UserLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton_CM_remove_User, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(511, 511, 511))
        );
        jPanel_Remove_UserLayout.setVerticalGroup(
            jPanel_Remove_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Remove_UserLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanel_Remove_UserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_M_Re_Id7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(remove_User_Valid3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jButton_CM_remove_User, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(253, 253, 253))
        );

        jPanel_Show_Users.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Show_Users.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Show_Users.setVisible(false);

        jTable_Users.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTable_Users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Username", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Users.setColumnSelectionAllowed(true);
        jTable_Users.setEnabled(false);
        jTable_Users.setFocusable(false);
        jTable_Users.setGridColor(new java.awt.Color(128, 128, 128));
        jTable_Users.setRowHeight(25);
        jTable_Users.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jTable_Users.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable_Users.setShowGrid(true);
        jTable_Users.getTableHeader().setReorderingAllowed(false);
        table_Users.setViewportView(jTable_Users);

        Id_user.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Id_user.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Id_user.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel_Show_UsersLayout = new javax.swing.GroupLayout(jPanel_Show_Users);
        jPanel_Show_Users.setLayout(jPanel_Show_UsersLayout);
        jPanel_Show_UsersLayout.setHorizontalGroup(
            jPanel_Show_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table_Users, javax.swing.GroupLayout.DEFAULT_SIZE, 1185, Short.MAX_VALUE)
            .addGroup(jPanel_Show_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_UsersLayout.createSequentialGroup()
                    .addGap(474, 474, 474)
                    .addComponent(Id_user, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(474, Short.MAX_VALUE)))
        );
        jPanel_Show_UsersLayout.setVerticalGroup(
            jPanel_Show_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(table_Users)
            .addGroup(jPanel_Show_UsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_UsersLayout.createSequentialGroup()
                    .addGap(273, 273, 273)
                    .addComponent(Id_user, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(274, Short.MAX_VALUE)))
        );

        jPanel_Change_Password.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Change_Password.setMinimumSize(new java.awt.Dimension(1185, 571));
        jPanel_Change_Password.setVisible(false);

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("Old Password :");
        jLabel62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("New Password :");
        jLabel63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton_MChange.setBackground(new java.awt.Color(73, 75, 243));
        jButton_MChange.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_MChange.setForeground(new java.awt.Color(255, 255, 255));
        jButton_MChange.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lock_reset_.png"))); // NOI18N
        jButton_MChange.setText("Change");
        jButton_MChange.setBorder(null);
        jButton_MChange.setFocusPainted(false);
        jButton_MChange.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_MChange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MChangeActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel64.setText("Confirm New Password :");
        jLabel64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        old_pass.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        old_pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));

        new_pass.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));

        confirm_new_pass.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        confirm_new_pass.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.blue, 2));

        jCheckBox_Hide_Show2.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_Hide_Show2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox_Hide_Show2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png"))); // NOI18N
        jCheckBox_Hide_Show2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_Hide_Show2ActionPerformed(evt);
            }
        });

        jCheckBox_Hide_Show3.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_Hide_Show3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox_Hide_Show3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png"))); // NOI18N
        jCheckBox_Hide_Show3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_Hide_Show3ActionPerformed(evt);
            }
        });

        jCheckBox_Hide_Show4.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox_Hide_Show4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jCheckBox_Hide_Show4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png"))); // NOI18N
        jCheckBox_Hide_Show4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox_Hide_Show4ActionPerformed(evt);
            }
        });

        valid_empty_password1.setBackground(new java.awt.Color(255, 255, 255));
        valid_empty_password1.setForeground(new java.awt.Color(199, 18, 18));
        valid_empty_password1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        valid_empty_password1.setLabelFor(jPanel_Change_Password);
        valid_empty_password.setText("");

        javax.swing.GroupLayout jPanel_Change_PasswordLayout = new javax.swing.GroupLayout(jPanel_Change_Password);
        jPanel_Change_Password.setLayout(jPanel_Change_PasswordLayout);
        jPanel_Change_PasswordLayout.setHorizontalGroup(
            jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                        .addGap(234, 234, 234)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(confirm_new_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox_Hide_Show4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                        .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                                .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                                        .addGap(314, 314, 314)
                                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                                        .addGap(295, 295, 295)
                                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(old_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(new_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox_Hide_Show2)
                                    .addComponent(jCheckBox_Hide_Show3)))
                            .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                                .addGap(530, 530, 530)
                                .addComponent(jButton_MChange, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(472, 472, 472))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Change_PasswordLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(valid_empty_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(336, 336, 336))
        );
        jPanel_Change_PasswordLayout.setVerticalGroup(
            jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(60, 60, 60))
                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                        .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_Hide_Show2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(old_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                        .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_Hide_Show3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(new_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)))
                .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel64, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                    .addGroup(jPanel_Change_PasswordLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel_Change_PasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox_Hide_Show4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirm_new_pass, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(valid_empty_password1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton_MChange, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );

        javax.swing.GroupLayout jPanel_Show_MangerLayout = new javax.swing.GroupLayout(jPanel_Show_Manger);
        jPanel_Show_Manger.setLayout(jPanel_Show_MangerLayout);
        jPanel_Show_MangerLayout.setHorizontalGroup(
            jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1265, Short.MAX_VALUE)
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(jPanel_Add_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addComponent(jPanel_MUpdate_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(jPanel_Remove_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addGap(54, 54, 54)
                    .addComponent(jPanel_Show_Users, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(15, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Show_MangerLayout.createSequentialGroup()
                    .addContainerGap(56, Short.MAX_VALUE)
                    .addComponent(jPanel_Change_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel_Show_MangerLayout.setVerticalGroup(
            jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jPanel_Add_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(27, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addGap(7, 7, 7)
                    .addComponent(jPanel_MUpdate_User, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addComponent(jPanel_Remove_User, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel_Show_MangerLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Show_Users, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(48, Short.MAX_VALUE)))
            .addGroup(jPanel_Show_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Show_MangerLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Change_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)))
        );

        jPanel_Buttons_Manger.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Buttons_Manger.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(73, 75, 243), 3));

        jButton_Manger_Change_Pass.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Manger_Change_Pass.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Manger_Change_Pass.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Manger_Change_Pass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/lock_reset_.png"))); // NOI18N
        jButton_Manger_Change_Pass.setText("Change Password");
        jButton_Manger_Change_Pass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Manger_Change_Pass.setFocusPainted(false);
        jButton_Manger_Change_Pass.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Manger_Change_Pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Manger_Change_PassActionPerformed(evt);
            }
        });

        jButton_Manger_Logout.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Manger_Logout.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Manger_Logout.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Manger_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        jButton_Manger_Logout.setText("Log Out");
        jButton_Manger_Logout.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Manger_Logout.setFocusPainted(false);
        jButton_Manger_Logout.setHideActionText(true);
        jButton_Manger_Logout.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Manger_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Manger_LogoutActionPerformed(evt);
            }
        });

        jButton_User_Login.setBackground(new java.awt.Color(73, 75, 243));
        jButton_User_Login.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_User_Login.setForeground(new java.awt.Color(255, 255, 255));
        jButton_User_Login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/login_.png"))); // NOI18N
        jButton_User_Login.setText("User Login");
        jButton_User_Login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_User_Login.setFocusPainted(false);
        jButton_User_Login.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_User_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_User_LoginActionPerformed(evt);
            }
        });

        jButton_Manger_Add_User.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Manger_Add_User.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Manger_Add_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Manger_Add_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/add.png"))); // NOI18N
        jButton_Manger_Add_User.setText("Add User");
        jButton_Manger_Add_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Manger_Add_User.setFocusPainted(false);
        jButton_Manger_Add_User.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Manger_Add_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Manger_Add_UserActionPerformed(evt);
            }
        });

        jButton_Manger_Update_User.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Manger_Update_User.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Manger_Update_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Manger_Update_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/user-update.png"))); // NOI18N
        jButton_Manger_Update_User.setText("Update User");
        jButton_Manger_Update_User.setActionCommand("Add Employee");
        jButton_Manger_Update_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Manger_Update_User.setFocusPainted(false);
        jButton_Manger_Update_User.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Manger_Update_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Manger_Update_UserActionPerformed(evt);
            }
        });

        jButton_Manger_Remove_User.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Manger_Remove_User.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Manger_Remove_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Manger_Remove_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fired.png"))); // NOI18N
        jButton_Manger_Remove_User.setText("Remove User");
        jButton_Manger_Remove_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Manger_Remove_User.setFocusPainted(false);
        jButton_Manger_Remove_User.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Manger_Remove_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Manger_Remove_UserActionPerformed(evt);
            }
        });

        jButton_Show_User.setBackground(new java.awt.Color(73, 75, 243));
        jButton_Show_User.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Show_User.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Show_User.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/employees.png"))); // NOI18N
        jButton_Show_User.setText("Show Users");
        jButton_Show_User.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Show_User.setFocusPainted(false);
        jButton_Show_User.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Show_User.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Show_UserActionPerformed(evt);
            }
        });

        jButton_Exit1.setBackground(new java.awt.Color(255, 0, 51));
        jButton_Exit1.setFont(new java.awt.Font("Microsoft New Tai Lue", 1, 14)); // NOI18N
        jButton_Exit1.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Exit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/exit.png"))); // NOI18N
        jButton_Exit1.setText("Exit");
        jButton_Exit1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton_Exit1.setFocusPainted(false);
        jButton_Exit1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton_Exit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Exit1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_Buttons_MangerLayout = new javax.swing.GroupLayout(jPanel_Buttons_Manger);
        jPanel_Buttons_Manger.setLayout(jPanel_Buttons_MangerLayout);
        jPanel_Buttons_MangerLayout.setHorizontalGroup(
            jPanel_Buttons_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Buttons_MangerLayout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(jPanel_Buttons_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Buttons_MangerLayout.createSequentialGroup()
                        .addGroup(jPanel_Buttons_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_Show_User, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Manger_Remove_User, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Manger_Update_User, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_Manger_Change_Pass, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Manger_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_User_Login, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                            .addComponent(jButton_Manger_Add_User, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_Buttons_MangerLayout.createSequentialGroup()
                        .addComponent(jButton_Exit1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))))
        );
        jPanel_Buttons_MangerLayout.setVerticalGroup(
            jPanel_Buttons_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Buttons_MangerLayout.createSequentialGroup()
                .addComponent(jButton_Manger_Add_User, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton_Manger_Update_User, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton_Manger_Remove_User, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton_Show_User, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jButton_Manger_Change_Pass, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton_User_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton_Manger_Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton_Exit1)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel_Home_MangerLayout = new javax.swing.GroupLayout(jPanel_Home_Manger);
        jPanel_Home_Manger.setLayout(jPanel_Home_MangerLayout);
        jPanel_Home_MangerLayout.setHorizontalGroup(
            jPanel_Home_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Home_MangerLayout.createSequentialGroup()
                .addGroup(jPanel_Home_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_Home_MangerLayout.createSequentialGroup()
                        .addGroup(jPanel_Home_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel_Buttons_Manger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_Home_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel_Show_Manger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel_Manger_Bar, javax.swing.GroupLayout.DEFAULT_SIZE, 1271, Short.MAX_VALUE)))
                    .addGroup(jPanel_Home_MangerLayout.createSequentialGroup()
                        .addGap(629, 629, 629)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_Home_MangerLayout.setVerticalGroup(
            jPanel_Home_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_Home_MangerLayout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Home_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jLabel_Manger_Bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_Home_MangerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_Buttons_Manger, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_Show_Manger, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_admin_user, javax.swing.GroupLayout.DEFAULT_SIZE, 1497, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_login, javax.swing.GroupLayout.DEFAULT_SIZE, 1497, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_Home_User, javax.swing.GroupLayout.DEFAULT_SIZE, 1497, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel_Home_Manger, javax.swing.GroupLayout.DEFAULT_SIZE, 1485, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_admin_user, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_login, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_Home_User, javax.swing.GroupLayout.DEFAULT_SIZE, 778, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(jPanel_Home_Manger, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
                    .addGap(4, 4, 4)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Display manger's panels
    private void jButton_ManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ManagerActionPerformed

        jPanel_admin_user.setVisible(false);
        jPanel_Home_User.setVisible(false);
        jPanel_Home_Manger.setVisible(false);
        jPanel_login.setVisible(true);
        clickManger = 1;
    }//GEN-LAST:event_jButton_ManagerActionPerformed

    // Display user's panels
    private void jButton_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UserActionPerformed
        clickUser = 1;
        jPanel_admin_user.setVisible(false);
        jPanel_Home_User.setVisible(false);
        jPanel_login.setVisible(true);
        jTextField_username.setText("");
        jPasswordField_login.setText("");
    }//GEN-LAST:event_jButton_UserActionPerformed

    // <-- Back
    private void jLabel_BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_BackMouseClicked
        clickManger = 0;
        clickUser = 0;
        jPanel_admin_user.setVisible(true);
        jPanel_Home_User.setVisible(false);
        jPanel_Home_Manger.setVisible(false);
        jPanel_login.setVisible(false);
        jTextField_username.setText("");
        jPasswordField_login.setText("");
        jPasswordField_login.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        jTextField_username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
        valid_empty_username.setText("");
        valid_empty_password.setText("");
        jCheckBox_Hide_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png")));
        jPasswordField_login.setEchoChar(('*'));

    }//GEN-LAST:event_jLabel_BackMouseClicked

    // Login Button
    private void jButton_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loginActionPerformed
        login();
    }//GEN-LAST:event_jButton_loginActionPerformed

    //_______________________________________________  Users Buttons ______________________________________________//
    // Display panel add employees
    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        String id = String.valueOf(empId);
        emp_Id.setText(id);
        jPanel_Update_Emp.setVisible(false);
        jPanel_Show_Emps.setVisible(false);
        jPanel_Remove_Emp.setVisible(false);
        jPanel_Edit_Account.setVisible(false);
        jPanel_GeneratePayslips.setVisible(false);
        jPanel_Add_Emp.setVisible(true);
        jLabel_User_Bar.setText("Add Employee");
    }//GEN-LAST:event_jButton_AddActionPerformed

    // Display panel update employees
    private void jButton_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_UpdateActionPerformed
        String id = " ";
        Emp_Ids1.removeAllItems();
        for (int i = 0; i < employees.size(); i++) {
            id = String.valueOf(employees.get(i).getEmployeeID());
            Emp_Ids1.addItem(id);
        }
        jPanel_Add_Emp.setVisible(false);
        jPanel_Show_Emps.setVisible(false);
        jPanel_Remove_Emp.setVisible(false);
        jPanel_Edit_Account.setVisible(false);
        jPanel_GeneratePayslips.setVisible(false);
        jPanel_Update_Emp.setVisible(true);
        jLabel_User_Bar.setText("Update Employee");

    }//GEN-LAST:event_jButton_UpdateActionPerformed

    // Display panel remove employees
    private void jButton_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveActionPerformed

        String id = " ";
        Ids_EmpS.removeAllItems();
        for (int i = 0; i < employees.size(); i++) {
            id = String.valueOf(employees.get(i).getEmployeeID());
            Ids_EmpS.addItem(id);
        }
        jPanel_Add_Emp.setVisible(false);
        jPanel_Update_Emp.setVisible(false);
        jPanel_Show_Emps.setVisible(false);
        jPanel_Edit_Account.setVisible(false);
        jPanel_GeneratePayslips.setVisible(false);
        jPanel_Remove_Emp.setVisible(true);
        jLabel_User_Bar.setText("Remove Employee");


    }//GEN-LAST:event_jButton_RemoveActionPerformed

    // Display panel show employees
    private void jButton_Show_EmpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Show_EmpsActionPerformed
        showEmployees();
        jPanel_Add_Emp.setVisible(false);
        jPanel_Update_Emp.setVisible(false);
        jPanel_Remove_Emp.setVisible(false);
        jPanel_Edit_Account.setVisible(false);
        jPanel_GeneratePayslips.setVisible(false);
        jLabel_User_Bar.setText("Employees");
        jPanel_Show_Emps.setVisible(true);

    }//GEN-LAST:event_jButton_Show_EmpsActionPerformed

    // Display panel generate payslips for each employee
    private void jButton_PayslipsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PayslipsActionPerformed
        String id = " ";
        Id_pay.removeAllItems();
        for (int i = 0; i < employees.size(); i++) {
            id = String.valueOf(employees.get(i).getEmployeeID());
            Id_pay.addItem(id);
        }
        jPanel_Add_Emp.setVisible(false);
        jPanel_Update_Emp.setVisible(false);
        jPanel_Remove_Emp.setVisible(false);
        jPanel_Edit_Account.setVisible(false);
        jLabel_User_Bar.setText("Payslips");
        jPanel_Show_Emps.setVisible(false);
        jPanel_GeneratePayslips.setVisible(true);
    }//GEN-LAST:event_jButton_PayslipsActionPerformed

    // Display panel edit account users
    private void jButton_Edit_AccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit_AccountActionPerformed
        jPanel_Add_Emp.setVisible(false);
        jPanel_Update_Emp.setVisible(false);
        jPanel_Remove_Emp.setVisible(false);
        jPanel_GeneratePayslips.setVisible(false);
        jLabel_User_Bar.setText("Edit Account");
        jPanel_Show_Emps.setVisible(false);
        jPanel_Edit_Account.setVisible(true);

    }//GEN-LAST:event_jButton_Edit_AccountActionPerformed

    // Manger login from user panel
    private void jButton_Manager_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Manager_LoginActionPerformed
        clearAll();
        clickManger = 1;
        clickUser = 0;
    }//GEN-LAST:event_jButton_Manager_LoginActionPerformed

    // User Logout
    private void jButton_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LogoutActionPerformed
        clearAll();
        jPanel_login.setVisible(false);
        clickManger = 0;
        clickUser = 0;
        jPanel_admin_user.setVisible(true);
    }//GEN-LAST:event_jButton_LogoutActionPerformed

    // User Exit
    private void jButton_ExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExitActionPerformed
        exit();
    }//GEN-LAST:event_jButton_ExitActionPerformed

    //_______________________________________________  Manger Buttons ______________________________________________//
    // Display panel add users
    private void jButton_Manger_Add_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Manger_Add_UserActionPerformed
        String id = String.valueOf(userId);
        add_User.setText(id);
        jLabel_Manger_Bar.setText("Add User");
        jPanel_Add_User.setVisible(true);
        jPanel_MUpdate_User.setVisible(false);
        jPanel_Remove_User.setVisible(false);
        jPanel_Show_Users.setVisible(false);
        jPanel_Change_Password.setVisible(false);
    }//GEN-LAST:event_jButton_Manger_Add_UserActionPerformed

    // Display panel update users
    private void jButton_Manger_Update_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Manger_Update_UserActionPerformed
        jLabel_Manger_Bar.setText("Update User");
        jPanel_Add_User.setVisible(false);
        jPanel_MUpdate_User.setVisible(true);
        jPanel_Remove_User.setVisible(false);
        jPanel_Show_Users.setVisible(false);
        jPanel_Change_Password.setVisible(false);
    }//GEN-LAST:event_jButton_Manger_Update_UserActionPerformed

    // Display panel remove users
    private void jButton_Manger_Remove_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Manger_Remove_UserActionPerformed
        jLabel_Manger_Bar.setText("Remove User");
        jPanel_Add_User.setVisible(false);
        jPanel_MUpdate_User.setVisible(false);
        jPanel_Remove_User.setVisible(true);
        jPanel_Show_Users.setVisible(false);
        jPanel_Change_Password.setVisible(false);
    }//GEN-LAST:event_jButton_Manger_Remove_UserActionPerformed

    // Display panel show users
    private void jButton_Show_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Show_UserActionPerformed
        tbModel_users = (DefaultTableModel) jTable_Users.getModel();
        if (tbModel_users.getRowCount() > 0) {
            tbModel_users.setRowCount(0);
        }
        PayrollSystem.userCredentials.forEach((s, user) -> {
            String data[] = {s.toString(), user.getUsername(), user.getPassword()};
            tbModel_users.addRow(data);
        });
        jLabel_Manger_Bar.setText("Users");
        jPanel_Add_User.setVisible(false);
        jPanel_MUpdate_User.setVisible(false);
        jPanel_Remove_User.setVisible(false);
        jPanel_Change_Password.setVisible(false);

        jPanel_Show_Users.setVisible(true);

    }//GEN-LAST:event_jButton_Show_UserActionPerformed

    // Display panel Manger's change password
    private void jButton_Manger_Change_PassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Manger_Change_PassActionPerformed
        jLabel_Manger_Bar.setText("Change Password");
        jPanel_Add_User.setVisible(false);
        jPanel_MUpdate_User.setVisible(false);
        jPanel_Remove_User.setVisible(false);
        jPanel_Show_Users.setVisible(false);
        jPanel_Change_Password.setVisible(true);
    }//GEN-LAST:event_jButton_Manger_Change_PassActionPerformed

    // User login from manger panel
    private void jButton_User_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_User_LoginActionPerformed
        clearAll();
        clickManger = 0;
        clickUser = 1;
    }//GEN-LAST:event_jButton_User_LoginActionPerformed

    // Manger logout
    private void jButton_Manger_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Manger_LogoutActionPerformed
        clearAll();
        jPanel_login.setVisible(false);
        clickManger = 0;
        clickUser = 0;

        jPanel_admin_user.setVisible(true);
    }//GEN-LAST:event_jButton_Manger_LogoutActionPerformed

    // Manger Exit
    private void jButton_Exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Exit1ActionPerformed
        exit();
    }//GEN-LAST:event_jButton_Exit1ActionPerformed

    private void jCheckBox_Hide_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Hide_ShowActionPerformed
        if (jCheckBox_Hide_Show.isSelected()) {
            jPasswordField_login.setEchoChar((char) 0);
            jCheckBox_Hide_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility.png")));
        } else {
            jCheckBox_Hide_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png")));
            jPasswordField_login.setEchoChar(('*'));
        }
    }//GEN-LAST:event_jCheckBox_Hide_ShowActionPerformed

    private void jCheckBox_Hide_Show1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Hide_Show1ActionPerformed
        if (jCheckBox_Hide_Show1.isSelected()) {
            jPasswordField_add_User.setEchoChar((char) 0);
            jCheckBox_Hide_Show1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility.png")));
        } else {
            jCheckBox_Hide_Show1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png")));
            jPasswordField_add_User.setEchoChar(('*'));
        }
    }//GEN-LAST:event_jCheckBox_Hide_Show1ActionPerformed

    private void jCheckBox_Hide_Show2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Hide_Show2ActionPerformed
        if (jCheckBox_Hide_Show2.isSelected()) {
            old_pass.setEchoChar((char) 0);
            jCheckBox_Hide_Show2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility.png")));
        } else {
            jCheckBox_Hide_Show2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png")));
            old_pass.setEchoChar(('*'));
        }
    }//GEN-LAST:event_jCheckBox_Hide_Show2ActionPerformed

    private void jCheckBox_Hide_Show3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Hide_Show3ActionPerformed
        if (jCheckBox_Hide_Show3.isSelected()) {
            new_pass.setEchoChar((char) 0);
            jCheckBox_Hide_Show3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility.png")));
        } else {
            jCheckBox_Hide_Show3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png")));
            new_pass.setEchoChar(('*'));
        }
    }//GEN-LAST:event_jCheckBox_Hide_Show3ActionPerformed

    private void jCheckBox_Hide_Show4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox_Hide_Show4ActionPerformed
        if (jCheckBox_Hide_Show4.isSelected()) {
            confirm_new_pass.setEchoChar((char) 0);
            jCheckBox_Hide_Show4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility.png")));
        } else {
            jCheckBox_Hide_Show4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/visibility_off.png")));
            confirm_new_pass.setEchoChar(('*'));
        }
    }//GEN-LAST:event_jCheckBox_Hide_Show4ActionPerformed

    // Add User
    private void jButton_CM_Add_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CM_Add_UserActionPerformed
        addUser(evt);
    }//GEN-LAST:event_jButton_CM_Add_UserActionPerformed

    // Searh button for find users
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

        if (jTextField_M_Id3.getText().trim().isEmpty()) {
            update_User_Valid2.setText("Id Field is Empty");
            jTextField_M_Id3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(199, 18, 18), 2));
        } else {
            int id = Integer.parseInt(jTextField_M_Id3.getText());
            update_User_Valid2.setText("");
            jTextField_M_Id3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(17, 6, 122), 2));
            try {
                User user = PayrollSystem.showUserDetails(id);
                if (user.getUsername() == null || user.getPassword() == null) {

                    username_user_up.setVisible(false);
                    password_user_up.setVisible(false);
                    jTextField_M_Up_Username6.setVisible(false);
                    jTextField_M_Up_Password7.setVisible(false);
                    jButton_CMUpdate_User1.setVisible(false);
                } else {
                    username_user_up.setVisible(true);
                    password_user_up.setVisible(true);
                    jTextField_M_Up_Username6.setVisible(true);
                    jTextField_M_Up_Password7.setVisible(true);
                    jButton_CMUpdate_User1.setVisible(true);
                    jTextField_M_Up_Username6.setText(user.getUsername());
                    jTextField_M_Up_Password7.setText(user.getPassword());

                }
            } catch (NullPointerException e) {
                showMessageDialog(null, "This user not found : (", "Not Found User", ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_searchActionPerformed

    // Update User
    private void jButton_CMUpdate_User1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CMUpdate_User1ActionPerformed
        updateUser();
    }//GEN-LAST:event_jButton_CMUpdate_User1ActionPerformed

    // Remove User
    private void jButton_CM_remove_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CM_remove_UserActionPerformed
        removeUser();
    }//GEN-LAST:event_jButton_CM_remove_UserActionPerformed

    //Change password's manger
    private void jButton_MChangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MChangeActionPerformed
        changePassword();
    }//GEN-LAST:event_jButton_MChangeActionPerformed

    // Add Employee
    private void jButton_CaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CaddActionPerformed
        addEmployee(evt);
    }//GEN-LAST:event_jButton_CaddActionPerformed

    // Clear Employee data
    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed

        jTextField_PayDetails.setText("");
        jTextfield_taxDetails.setText("");
        jTextField_TaxAmount.setText("");
        Salary.setText("");
        jTextField_Name.setText("");
        Hour_work.setText("");
    }//GEN-LAST:event_jButton_ClearActionPerformed

    // Update Employee
    private void jButton_CupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CupdateActionPerformed
        updateEmployee();

    }//GEN-LAST:event_jButton_CupdateActionPerformed

    // Remove Employee
    private void jButton_CremoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CremoveActionPerformed
        removeEmployee(evt);
    }//GEN-LAST:event_jButton_CremoveActionPerformed

    // Show Employee
    private void jComboBox_Emp_type3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_Emp_type3ItemStateChanged

        String query = jComboBox_Emp_type3.getSelectedItem().toString();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tbModel_employees);
        jTable_Employees.setRowSorter(sorter);
        if (!query.equals("None")) {
            sorter.setRowFilter(RowFilter.regexFilter(query));
        } else {
            jTable_Employees.setRowSorter(sorter);
        }
    }//GEN-LAST:event_jComboBox_Emp_type3ItemStateChanged

    // Edit Account user
    private void jButton_CUpdate_UserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CUpdate_UserActionPerformed
        editAcounttUser();
    }//GEN-LAST:event_jButton_CUpdate_UserActionPerformed

    private void jTextField_M_Id3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_M_Id3KeyTyped
        validInput(evt);
    }//GEN-LAST:event_jTextField_M_Id3KeyTyped

    private void jTextField_M_Re_Id7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_M_Re_Id7KeyTyped
        validInput(evt);
    }//GEN-LAST:event_jTextField_M_Re_Id7KeyTyped

    private void SalaryKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SalaryKeyTyped
        validInput(evt);
    }//GEN-LAST:event_SalaryKeyTyped

    private void Hour_workKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Hour_workKeyTyped
        validInput(evt);
    }//GEN-LAST:event_Hour_workKeyTyped

    private void jTextField_TaxAmountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_TaxAmountKeyTyped
        validInput(evt);
    }//GEN-LAST:event_jTextField_TaxAmountKeyTyped

    private void Salary1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Salary1KeyTyped
        validInput(evt);
    }//GEN-LAST:event_Salary1KeyTyped

    private void Hour_work1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Hour_work1KeyTyped
        validInput(evt);
    }//GEN-LAST:event_Hour_work1KeyTyped

    private void jTextField_TaxAmount2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_TaxAmount2KeyTyped
        validInput(evt);
    }//GEN-LAST:event_jTextField_TaxAmount2KeyTyped

    private void jComboBox_Emp_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Emp_typeActionPerformed
        String selectedItem = jComboBox_Emp_type.getSelectedItem().toString();
        if (selectedItem.equals("Hourly")) {
            jLabel_salary.setText(" Hourly PayRate :");
            jLabel_hour_work.setText("Hours Worked : ");
            jLabel_hour_work.setVisible(true);
            Hour_work.setVisible(true);
            Hour_work.setEnabled(true);
        }
        if (selectedItem.equals("Commissioned")) {
            jLabel_salary.setText("Commission Rate :");
            jLabel_hour_work.setText(" Total Sales :");
            jLabel_hour_work.setVisible(true);
            Hour_work.setVisible(true);
            Hour_work.setEnabled(true);
        }
        if (selectedItem.equals("Salaried")) {
            jLabel_salary.setText("Salary :");
            jLabel_hour_work.setText("Hours Worked : ");
            jLabel_hour_work.setVisible(false);
            Hour_work.setVisible(false);
            Hour_work.setEnabled(false);
        }


    }//GEN-LAST:event_jComboBox_Emp_typeActionPerformed

    private void jComboBox_Emp_type2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Emp_type2ActionPerformed
        String selectedItem = jComboBox_Emp_type2.getSelectedItem().toString();

        if (selectedItem.equals("Hourly")) {
            jLabel_salary1.setText(" Hourly PayRate :");
            jLabel_hour_work1.setText("Hours Worked : ");
            jLabel_hour_work1.setVisible(true);
            Hour_work1.setVisible(true);
            Hour_work1.setEnabled(true);

        }
        if (selectedItem.equals("Commissioned")) {
            jLabel_salary1.setText("Commission Rate :");
            jLabel_hour_work1.setText(" Total Sales :");
            jLabel_hour_work1.setVisible(true);
            Hour_work1.setVisible(true);
            Hour_work1.setEnabled(true);

        }
        if (selectedItem.equals("Salaried")) {
            jLabel_salary1.setText("Salary :");
            jLabel_hour_work1.setText("Hours Worked : ");
            jLabel_hour_work1.setVisible(false);
            Hour_work1.setVisible(false);
            Hour_work1.setEnabled(false);

        }
    }//GEN-LAST:event_jComboBox_Emp_type2ActionPerformed

    private void Ids_EmpSItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Ids_EmpSItemStateChanged

        String name = "";
        for (int i = 0; i < employees.size(); i++) {
            String id = String.valueOf(employees.get(i).getEmployeeID());
            if (id.equals(Ids_EmpS.getSelectedItem())) {
                name = employees.get(i).getEmployeeName();

            } else {
                Name_rEmp.setText(name);
            }
        }
        Name_rEmp.setText(name);
    }//GEN-LAST:event_Ids_EmpSItemStateChanged

    private void Emp_Ids1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Emp_Ids1ItemStateChanged
        for (int i = 0; i < employees.size(); i++) {
            String id = String.valueOf(employees.get(i).getEmployeeID());
            if (Emp_Ids1.getSelectedItem() == null && id.isEmpty()) {
                showMessageDialog(null, "There are no employees, go add them ", "Empty", INFORMATION_MESSAGE);
                jTextField_PayDetails2.setText("");
                jTextField_taxDetails1.setText("");
                jTextField_TaxAmount2.setText("");
                Salary1.setText("");
                jTextField_Name2.setText("");
                Hour_work1.setText("");
            } else if (id.equals(Emp_Ids1.getSelectedItem())) {
                String taxAmount = String.valueOf(employees.get(i).getEmpTaxInformation().taxAmount);
                jTextField_Name2.setText(employees.get(i).getEmployeeName());
                jTextField_TaxAmount2.setText(taxAmount);
                jTextField_taxDetails1.setText(employees.get(i).getEmpTaxInformation().taxDetails);
                jTextField_PayDetails2.setText(employees.get(i).getEmpPaymentDetails());
                jComboBox_PayMothod2.setSelectedItem(employees.get(i).getEmpPaymentMethod());
                String empType = String.valueOf(employees.get(i).getEmployeeType());
                if (empType.equals("Salaried")) {
                    SalariedEmployee emp = (SalariedEmployee) employees.get(i);
                    String salary = String.valueOf(emp.getSalary());
                    jLabel_salary1.setText("Salary :");
                    jLabel_hour_work1.setText("Hours Worked : ");
                    jLabel_hour_work1.setVisible(false);
                    Hour_work1.setVisible(false);
                    Hour_work1.setEnabled(false);
                    Salary1.setText(salary);
                    jComboBox_Emp_type2.setSelectedIndex(0);
                } else if (empType.equals("Hourly")) {
                    HourlyEmployee emp = (HourlyEmployee) employees.get(i);
                    String hourRate = String.valueOf(emp.getHourPayRate());
                    String hourWorked = String.valueOf(emp.getHoursWorked());
                    jLabel_salary1.setText(" Hourly PayRate :");
                    jLabel_hour_work1.setText("Hours Worked : ");
                    jLabel_hour_work1.setVisible(true);
                    Hour_work1.setVisible(true);
                    Hour_work1.setEnabled(true);
                    Salary1.setText(hourRate);
                    Hour_work1.setText(hourWorked);
                    jComboBox_Emp_type2.setSelectedIndex(1);
                } else if (empType.equals("Commissioned")) {
                    CommissionedEmployee emp = (CommissionedEmployee) employees.get(i);
                    String commissionRate = String.valueOf(emp.getCommissionRate());
                    String totalSales = String.valueOf(emp.getTotalSales());
                    jLabel_salary1.setText("Commission Rate :");
                    jLabel_hour_work1.setText(" Total Sales :");
                    jLabel_hour_work1.setVisible(true);
                    Hour_work1.setVisible(true);
                    Hour_work1.setEnabled(true);
                    Salary1.setText(commissionRate);
                    Hour_work1.setText(totalSales);
                    jComboBox_Emp_type2.setSelectedIndex(2);
                }

            }

        }

    }//GEN-LAST:event_Emp_Ids1ItemStateChanged

    private void Id_payItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Id_payItemStateChanged
        String totalPayslips = String.valueOf(PayrollSystem.calculateTotalPayroll());

        for (int i = 0; i < employees.size(); i++) {
            String id = String.valueOf(employees.get(i).getEmployeeID());
            if (Id_pay.getSelectedItem() == null && id.isEmpty()) {
                showMessageDialog(null, "There are no employees, go add them ", "Empty", INFORMATION_MESSAGE);
                paydetails.setText("");
                totalpay.setText("");
                paymethod.setText("");
                taxamount.setText("");
                taxdetails.setText("");
                salary.setText("");
                name.setText("");
                type.setText("");
                hourwork_comission.setText("");
                total.setText("");
            } else if (id.equals(Id_pay.getSelectedItem())) {
                String taxAmount = String.valueOf(employees.get(i).getEmpTaxInformation().taxAmount);
                name.setText(employees.get(i).getEmployeeName());
                taxamount.setText(taxAmount);
                taxdetails.setText(employees.get(i).getEmpTaxInformation().taxDetails);
                paydetails.setText(employees.get(i).getEmpPaymentDetails());
                paymethod.setText(employees.get(i).getEmpPaymentMethod());
                String empType = String.valueOf(employees.get(i).getEmployeeType());

                if (empType.equals("Salaried")) {
                    SalariedEmployee emp = (SalariedEmployee) employees.get(i);
                    String salaries = String.valueOf(emp.getSalary());
                    String Total = String.valueOf(emp.calculatePay());
                    jLabel_salary2.setText("Salary :");
                    jLabel_hour_work2.setText("Hours Worked : ");
                    jLabel_hour_work2.setVisible(false);
                    hourwork_comission.setVisible(false);
                    hourwork_comission.setEnabled(false);
                    salary.setText(salaries);
                    type.setText(empType);
                    total.setText(Total);
                } else if (empType.equals("Hourly")) {
                    HourlyEmployee emp = (HourlyEmployee) employees.get(i);
                    String hourRate = String.valueOf(emp.getHourPayRate());
                    String hourWorked = String.valueOf(emp.getHoursWorked());
                    String Total = String.valueOf(emp.calculatePay());
                    jLabel_salary2.setText(" Hourly PayRate :");
                    jLabel_hour_work2.setText("Hours Worked : ");
                    jLabel_hour_work2.setVisible(true);
                    hourwork_comission.setVisible(true);
                    hourwork_comission.setEnabled(true);
                    salary.setText(hourRate);
                    hourwork_comission.setText(hourWorked);
                    type.setText(empType);
                    total.setText(Total);
                } else if (empType.equals("Commissioned")) {
                    CommissionedEmployee emp = (CommissionedEmployee) employees.get(i);
                    String commissionRate = String.valueOf(emp.getCommissionRate());
                    String totalSales = String.valueOf(emp.getTotalSales());
                    String Total = String.valueOf(emp.calculatePay());
                    jLabel_salary2.setText("Commission Rate :");
                    jLabel_hour_work2.setText(" Total Sales :");
                    jLabel_hour_work2.setVisible(true);
                    hourwork_comission.setVisible(true);
                    hourwork_comission.setEnabled(true);
                    salary.setText(commissionRate);
                    hourwork_comission.setText(totalSales);
                    type.setText(empType);
                    total.setText(Total);
                }

            }
        }
        totalpay.setText(totalPayslips);
    }//GEN-LAST:event_Id_payItemStateChanged

    // Close Form
    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        exit();
    }//GEN-LAST:event_closeMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Emp_Ids1;
    private javax.swing.JTextField Hour_work;
    private javax.swing.JTextField Hour_work1;
    private javax.swing.JComboBox<String> Id_pay;
    private javax.swing.JLabel Id_user;
    private javax.swing.JLabel Id_user1;
    private javax.swing.JComboBox<String> Ids_EmpS;
    private javax.swing.JLabel Name;
    private javax.swing.JLabel Name_rEmp;
    private javax.swing.JLabel PayDetails;
    private javax.swing.JLabel PayMethod;
    private javax.swing.JTextField Salary;
    private javax.swing.JTextField Salary1;
    private javax.swing.JLabel TaxAmount;
    private javax.swing.JLabel TaxDetails;
    private javax.swing.JLabel Total;
    private javax.swing.JLabel TotalPay;
    private javax.swing.JLabel TotalPay1;
    private javax.swing.JLabel TotalPay2;
    private javax.swing.JLabel Type;
    private javax.swing.JLabel add_User;
    private javax.swing.JLabel add_User_Valid;
    private javax.swing.JLabel close;
    private javax.swing.JPasswordField confirm_new_pass;
    private javax.swing.JLabel emp_Id;
    private javax.swing.JLabel hourwork_comission;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_CMUpdate_User1;
    private javax.swing.JButton jButton_CM_Add_User;
    private javax.swing.JButton jButton_CM_remove_User;
    private javax.swing.JButton jButton_CUpdate_User;
    private javax.swing.JButton jButton_Cadd;
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_Cremove;
    private javax.swing.JButton jButton_Cupdate;
    private javax.swing.JButton jButton_Edit_Account;
    private javax.swing.JButton jButton_Exit;
    private javax.swing.JButton jButton_Exit1;
    private javax.swing.JButton jButton_Logout;
    private javax.swing.JButton jButton_MChange;
    private javax.swing.JButton jButton_Manager;
    private javax.swing.JButton jButton_Manager_Login;
    private javax.swing.JButton jButton_Manger_Add_User;
    private javax.swing.JButton jButton_Manger_Change_Pass;
    private javax.swing.JButton jButton_Manger_Logout;
    private javax.swing.JButton jButton_Manger_Remove_User;
    private javax.swing.JButton jButton_Manger_Update_User;
    private javax.swing.JButton jButton_Payslips;
    private javax.swing.JButton jButton_Remove;
    private javax.swing.JButton jButton_Show_Emps;
    private javax.swing.JButton jButton_Show_User;
    private javax.swing.JButton jButton_Update;
    private javax.swing.JButton jButton_User;
    private javax.swing.JButton jButton_User_Login;
    private javax.swing.JButton jButton_login;
    private javax.swing.JCheckBox jCheckBox_Hide_Show;
    private javax.swing.JCheckBox jCheckBox_Hide_Show1;
    private javax.swing.JCheckBox jCheckBox_Hide_Show2;
    private javax.swing.JCheckBox jCheckBox_Hide_Show3;
    private javax.swing.JCheckBox jCheckBox_Hide_Show4;
    private javax.swing.JComboBox<String> jComboBox_Emp_type;
    private javax.swing.JComboBox<String> jComboBox_Emp_type2;
    private javax.swing.JComboBox<String> jComboBox_Emp_type3;
    private javax.swing.JComboBox<String> jComboBox_PayMothod;
    private javax.swing.JComboBox<String> jComboBox_PayMothod2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Back;
    private javax.swing.JLabel jLabel_Manger_Bar;
    private javax.swing.JLabel jLabel_User_Bar;
    private javax.swing.JLabel jLabel_hour_work;
    private javax.swing.JLabel jLabel_hour_work1;
    private javax.swing.JLabel jLabel_hour_work2;
    private javax.swing.JLabel jLabel_salary;
    private javax.swing.JLabel jLabel_salary1;
    private javax.swing.JLabel jLabel_salary2;
    private javax.swing.JPanel jPanel_Add_Emp;
    private javax.swing.JPanel jPanel_Add_User;
    private javax.swing.JPanel jPanel_Buttons;
    private javax.swing.JPanel jPanel_Buttons_Manger;
    private javax.swing.JPanel jPanel_Change_Password;
    private javax.swing.JPanel jPanel_Edit_Account;
    private javax.swing.JPanel jPanel_GeneratePayslips;
    private javax.swing.JPanel jPanel_Home_Manger;
    private javax.swing.JPanel jPanel_Home_User;
    private javax.swing.JPanel jPanel_MUpdate_User;
    private javax.swing.JPanel jPanel_Remove_Emp;
    private javax.swing.JPanel jPanel_Remove_User;
    private javax.swing.JPanel jPanel_Show;
    private javax.swing.JPanel jPanel_Show_Emps;
    private javax.swing.JPanel jPanel_Show_Manger;
    private javax.swing.JPanel jPanel_Show_Users;
    private javax.swing.JPanel jPanel_Update_Emp;
    private javax.swing.JPanel jPanel_admin_user;
    private javax.swing.JPanel jPanel_login;
    private javax.swing.JPasswordField jPasswordField_add_User;
    private javax.swing.JPasswordField jPasswordField_login;
    private javax.swing.JTable jTable_Employees;
    private javax.swing.JTable jTable_Users;
    private javax.swing.JTextField jTextField_M_Id3;
    private javax.swing.JTextField jTextField_M_Re_Id7;
    private javax.swing.JTextField jTextField_M_Up_Password7;
    private javax.swing.JTextField jTextField_M_Up_Username6;
    private javax.swing.JTextField jTextField_M_add_Username5;
    private javax.swing.JTextField jTextField_Name;
    private javax.swing.JTextField jTextField_Name2;
    private javax.swing.JTextField jTextField_Password5;
    private javax.swing.JTextField jTextField_PayDetails;
    private javax.swing.JTextField jTextField_PayDetails2;
    private javax.swing.JTextField jTextField_TaxAmount;
    private javax.swing.JTextField jTextField_TaxAmount2;
    private javax.swing.JTextField jTextField_Username4;
    private javax.swing.JTextField jTextField_taxDetails1;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JTextField jTextfield_taxDetails;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private javax.swing.JLabel name;
    private javax.swing.JPasswordField new_pass;
    private javax.swing.JPasswordField old_pass;
    private javax.swing.JLabel password_user_up;
    private javax.swing.JLabel paydetails;
    private javax.swing.JLabel paymethod;
    private javax.swing.JLabel remove_User_Valid3;
    private javax.swing.JLabel salary;
    private javax.swing.JButton search;
    private javax.swing.JScrollPane table_Emps;
    private javax.swing.JScrollPane table_Users;
    private javax.swing.JLabel taxamount;
    private javax.swing.JLabel taxdetails;
    private javax.swing.JLabel total;
    private javax.swing.JLabel totalpay;
    private javax.swing.JLabel totalpay1;
    private javax.swing.JLabel type;
    private javax.swing.JLabel update_User_Valid2;
    private javax.swing.JLabel username_user_up;
    private javax.swing.JLabel valid_empty;
    private javax.swing.JLabel valid_empty_password;
    private javax.swing.JLabel valid_empty_password1;
    private javax.swing.JLabel valid_empty_username;
    // End of variables declaration//GEN-END:variables
}
