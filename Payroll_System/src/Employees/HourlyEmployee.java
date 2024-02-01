package Employees;

public class HourlyEmployee extends Employee {

    private double hourlyPayRate;
    private int hoursWorked;

    public HourlyEmployee(int id, String name, String empType, String paymentMethod, String paymentDetails, TaxInformation taxInformation, double hourlyPayRate, int hoursWorked) {
        super(id, name, empType, paymentMethod, paymentDetails, taxInformation);
        this.hourlyPayRate = hourlyPayRate;
        this.hoursWorked = hoursWorked;
    }

    public HourlyEmployee(String name, String empType, String paymentMethod, String paymentDetails, TaxInformation taxInformation, double hourlyPayRate, int hoursWorked) {
        super(name, empType, paymentMethod, paymentDetails, taxInformation);
        this.hourlyPayRate = hourlyPayRate;
        this.hoursWorked = hoursWorked;
    }

    public double getHourPayRate() {
        return hourlyPayRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }

    @Override
    public double calculatePay() {


        return (hourlyPayRate * (double) hoursWorked) - taxInformation.taxAmount;
    }

    @Override
    public String generatePayStub() {
        return String.valueOf(id) + "~" + name + "~" + employeeType.toString() + "~" + paymentMethod + "~" + paymentDetails + "~" + taxInformation.taxDetails + "~" + String.valueOf(taxInformation.taxAmount) + "~" + String.valueOf(hourlyPayRate) + "~" + String.valueOf(hoursWorked);
    }
}
