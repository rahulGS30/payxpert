package com.hexaware.service;
 
import com.hexaware.entity.Payroll;
import com.hexaware.exception.PayrollGenerationException;
import java.util.Date;
import java.util.List;
 
public interface IPayrollService {
    Payroll generatePayroll(int employeeId, Date startDate, Date endDate, int basicSalary, int overTimePay, int deductions) throws PayrollGenerationException;
    
    Payroll getPayrollById(int payrollId) throws PayrollGenerationException;
    
    List<Payroll> getPayrollsForEmployee(int employeeId) throws PayrollGenerationException;
    
    List<Payroll> getPayrollsForPeriod(Date startDate, Date endDate) throws PayrollGenerationException;
}