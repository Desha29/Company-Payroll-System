package Employees;

public class SalariedEmployee extends Employee {

    private double salary;

    public SalariedEmployee(int id, String name, String empType, String paymentMethod, String paymentDetails, TaxInformation taxInformation, double salary) {
        super(id, name, empType, paymentMethod, paymentDetails, taxInformation);
        this.salary = salary;

    }

    public SalariedEmployee(String name, String empType, String paymentMethod, String paymentDetails, TaxInformation taxInformation, double salary) {
        super(name, empType, paymentMethod, paymentDetails, taxInformation);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public double calculatePay() {


        return salary - taxInformation.taxAmount;
    }

    @Override
    public String generatePayStub() {
        return String.valueOf(id) + "~" + name + "~" + employeeType.toString() + "~" + paymentMethod + "~" + paymentDetails + "~" + taxInformation.taxDetails + "~" + String.valueOf(taxInformation.taxAmount) + "~" + String.valueOf(salary);
    }
}
