package Employees;

enum EmployeeType {
    Salaried,
    Hourly,
    Commissioned,
}

public abstract class Employee {
    protected int id;
    protected String name;
    protected EmployeeType employeeType;
    protected String paymentMethod;
    protected String paymentDetails;
    protected TaxInformation taxInformation;
    public static int empId = 1;

    public Employee(int id, String name, String employeeType, String paymentMethod, String paymentDetails, TaxInformation taxInformation) {
        this.id = id;
        this.name = name;
        this.employeeType = EmployeeType.valueOf(employeeType);
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.taxInformation = taxInformation;

    }

    public Employee(String name, String employeeType, String paymentMethod, String paymentDetails, TaxInformation taxInformation) {
        this.id = empId;
        this.name = name;
        this.employeeType = EmployeeType.valueOf(employeeType);
        this.paymentMethod = paymentMethod;
        this.paymentDetails = paymentDetails;
        this.taxInformation = taxInformation;
        empId++;
    }

    public abstract double calculatePay();

    public abstract String generatePayStub();

    public int getEmployeeID() {
        return id;
    }

    public String getEmployeeName() {
        return name;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public String getEmpPaymentMethod() {
        return paymentMethod;
    }

    public String getEmpPaymentDetails() {
        return paymentDetails;
    }

    public TaxInformation getEmpTaxInformation() {
        return taxInformation;
    }
}
