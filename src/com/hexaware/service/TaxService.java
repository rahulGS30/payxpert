package com.hexaware.service;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
import com.hexaware.entity.taxTable;
import com.hexaware.util.dbUtil;
import com.hexaware.exception.TaxCalculationException;
 
public class TaxService implements ITaxService {
 
    @Override
    public List<taxTable> getTaxesForEmployee(int employeeId) throws TaxCalculationException {
        List<taxTable> taxList = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "SELECT * FROM taxtable WHERE employeeID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                taxTable tax = new taxTable(
                        rs.getInt("TaxID"),
                        rs.getInt("EmployeeID"),
                        rs.getInt("Year"),
                        rs.getInt("TaxableIncome"),
                        rs.getInt("TaxAmount")
                );
                taxList.add(tax);
            }
        } catch (Exception e) {
            throw new TaxCalculationException("Error retrieving taxes for employee ID " + employeeId, e);
        }
        return taxList;
    }
 
    @Override
    public boolean addTax(taxTable tax) throws TaxCalculationException {
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "INSERT INTO taxtable (employeeID, year, taxableIncome, taxAmount) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tax.getEmployeeID());
            ps.setInt(2, tax.getYear());
            ps.setInt(3, tax.getTaxableIncome());
            ps.setInt(4, tax.getTaxAmount());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            throw new TaxCalculationException("Error adding tax record for EmployeeID " + tax.getEmployeeID(), e);
        }
    }
 
    @Override
    public boolean updateTax(taxTable tax) throws TaxCalculationException {
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "UPDATE TaxTable SET EmployeeID = ?, Year = ?, TaxableIncome = ?, TaxAmount = ? WHERE TaxID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tax.getEmployeeID());
            ps.setInt(2, tax.getYear());
            ps.setInt(3, tax.getTaxableIncome());
            ps.setInt(4, tax.getTaxAmount());
            ps.setInt(5, tax.getTaxID());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            throw new TaxCalculationException("Error updating tax record with TaxID " + tax.getTaxID(), e);
        }
    }
 
    @Override
    public boolean removeTax(int taxId) throws TaxCalculationException {
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "DELETE FROM TaxTable WHERE TaxID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, taxId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            throw new TaxCalculationException("Error removing tax record with TaxID " + taxId, e);
        }
    }
 
    @Override
    public List<taxTable> getAllTaxes() throws TaxCalculationException {
        List<taxTable> taxList = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "SELECT * FROM TaxTable";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                taxTable tax = new taxTable(
                        rs.getInt("TaxID"),
                        rs.getInt("EmployeeID"),
                        rs.getInt("Year"),
                        rs.getInt("TaxableIncome"),
                        rs.getInt("TaxAmount")
                );
                taxList.add(tax);
            }
        } catch (Exception e) {
            throw new TaxCalculationException("Error retrieving all tax records", e);
        }
        return taxList;
    }
}