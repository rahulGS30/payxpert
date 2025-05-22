package com.hexaware.entity;
 
import java.util.Date;
 
public class Payroll {
    private int payrollId;
    private int employeeID;
    private Date payPeriodStartDate;
    private Date payPeriodEndDate;
    private int basicSalary;
    private int overTimePay;
    private int deductions;
    private int netSalary;
 
    // Constructor with all fields
    public Payroll(int payrollId, int employeeID, Date payPeriodStartDate, Date payPeriodEndDate,
                   int basicSalary, int overTimePay, int deductions, int netSalary) {
        this.payrollId = payrollId;
        this.employeeID = employeeID;
        this.payPeriodStartDate = payPeriodStartDate;
        this.payPeriodEndDate = payPeriodEndDate;
        this.basicSalary = basicSalary;
        this.overTimePay = overTimePay;
        this.deductions = deductions;
        this.netSalary = netSalary;
    }
 

	// Getters
    public int getPayrollId() { return payrollId; }
    public int getEmployeeID() { return employeeID; }
    public Date getPayPeriodStartDate() { return payPeriodStartDate; }
    public Date getPayPeriodEndDate() { return payPeriodEndDate; }
    public int getBasicSalary() { return basicSalary; }
    public int getOverTimePay() { return overTimePay; }
    public int getDeductions() { return deductions; }
    public int getNetSalary() { return netSalary; }
 
    // Optional: toString
    @Override
    public String toString() {
        return "Payroll [payrollId=" + payrollId + ", employeeID=" + employeeID + ", StartDate=" + payPeriodStartDate +
                ", EndDate=" + payPeriodEndDate + ", BasicSalary=" + basicSalary + ", OverTimePay=" + overTimePay +
                ", Deductions=" + deductions + ", NetSalary=" + netSalary + "]";
    }
}