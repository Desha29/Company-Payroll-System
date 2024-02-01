package Employees;

public class CommissionedEmployee extends Employee {

    private double commissionRate;
    private double totalSales;

    public CommissionedEmployee(int id, String name, String empType, String paymentMethod, String paymentDetails, TaxInformation taxInformation, double commissionRate, double totalSales) {
        super(id, name, empType, paymentMethod, paymentDetails, taxInformation);
        this.commissionRate = commissionRate;
        this.totalSales = totalSales;
    }

    public CommissionedEmployee(String name, String empType, String paymentMethod, String paymentDetails, TaxInformation taxInformation, double commissionRate, double totalSales) {
        super(name, empType, paymentMethod, paymentDetails, taxInformation);
        this.commissionRate = commissionRate;
        this.totalSales = totalSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public double getTotalSales() {
        return totalSales;
    }

    @Override
    public double calculatePay() {


        return (totalSales * commissionRate) - taxInformation.taxAmount;
    }

    @Override
    public String generatePayStub() {
        return String.valueOf(id) + "~" + name + "~" + employeeType.toString() + "~" + paymentMethod + "~" + paymentDetails + "~" + taxInformation.taxDetails + "~" + String.valueOf(taxInformation.taxAmount) + "~" + String.valueOf(commissionRate) + "~" + String.valueOf(totalSales);
    }
}
