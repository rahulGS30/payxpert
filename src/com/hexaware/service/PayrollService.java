package com.hexaware.service;
 
import com.hexaware.entity.Payroll;
import com.hexaware.util.dbUtil;
 
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
public class PayrollService implements IPayrollService {
 
    @Override
    public Payroll generatePayroll(int employeeId, Date startDate, Date endDate, int basicSalary, int overTimePay, int deductions) {
        int netSalary = basicSalary + overTimePay - deductions;
        Payroll payroll = null;
        try (Connection conn = dbUtil.getConnection()) {
            String query = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OverTimePay, Deductions, NetSalary) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, employeeId);
            ps.setDate(2, new java.sql.Date(startDate.getTime()));
            ps.setDate(3, new java.sql.Date(endDate.getTime()));
            ps.setInt(4, basicSalary);
            ps.setInt(5, overTimePay);
            ps.setInt(6, deductions);
            ps.setInt(7, netSalary);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int payrollId = rs.getInt(1);
                    payroll = new Payroll(payrollId, employeeId, startDate, endDate, basicSalary, overTimePay, deductions, netSalary);
                    System.out.println("Payroll generated successfully:\n" + payroll); // Console print
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }
 
    @Override
    public Payroll getPayrollById(int payrollId) {
        Payroll payroll = null;
        try (Connection conn = dbUtil.getConnection()) {
            String query = "SELECT * FROM Payroll WHERE PayrollID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, payrollId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                payroll = new Payroll(
                        rs.getInt("PayrollID"),
                        rs.getInt("EmployeeID"),
                        rs.getDate("PayPeriodStartDate"),
                        rs.getDate("PayPeriodEndDate"),
                        rs.getInt("BasicSalary"),
                        rs.getInt("OverTimePay"),
                        rs.getInt("Deductions"),
                        rs.getInt("NetSalary")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }
 
    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrolls = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String query = "SELECT * FROM Payroll WHERE EmployeeID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payroll payroll = new Payroll(
                        rs.getInt("PayrollID"),
                        rs.getInt("EmployeeID"),
                        rs.getDate("PayPeriodStartDate"),
                        rs.getDate("PayPeriodEndDate"),
                        rs.getInt("BasicSalary"),
                        rs.getInt("OverTimePay"),
                        rs.getInt("Deductions"),
                        rs.getInt("NetSalary")
                );
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }
 
    @Override
    public List<Payroll> getPayrollsForPeriod(Date startDate, Date endDate) {
        List<Payroll> payrolls = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String query = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(startDate.getTime()));
            ps.setDate(2, new java.sql.Date(endDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payroll payroll = new Payroll(
                        rs.getInt("PayrollID"),
                        rs.getInt("EmployeeID"),
                        rs.getDate("PayPeriodStartDate"),
                        rs.getDate("PayPeriodEndDate"),
                        rs.getInt("BasicSalary"),
                        rs.getInt("OverTimePay"),
                        rs.getInt("Deductions"),
                        rs.getInt("NetSalary")
                );
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }
}