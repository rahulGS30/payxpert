package com.hexaware.service;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
 
import com.hexaware.entity.employee;
import com.hexaware.exception.EmployeeNotFoundException;
import com.hexaware.exception.InvalidInputException;
import com.hexaware.util.dbUtil;
 
public class employeeService implements iEmployeeService {
 
    @Override
    public employee getEmployeeById(int id) throws EmployeeNotFoundException, InvalidInputException {
        if (id <= 0) {
            throw new InvalidInputException("Employee ID must be a positive number.");
        }
 
        employee emp = null;
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "SELECT * FROM employee WHERE EmployeeID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
                emp = new employee(
                    rs.getInt("employeeID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getDate("dateOfBirth").toLocalDate(),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getString("phoneNumber"),
                    rs.getString("address"),
                    rs.getString("position"),
                    rs.getDate("joiningDate").toLocalDate(),
                    rs.getDate("terminationDate") != null ? rs.getDate("terminationDate").toLocalDate() : null
                );
            } else {
                throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
            }
        } catch (EmployeeNotFoundException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
 
    @Override
    public List<employee> getAllEmployees() {
        List<employee> list = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "SELECT * FROM employee";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
 
            while (rs.next()) {
                employee emp = new employee(
                    rs.getInt("employeeID"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getDate("dateOfBirth").toLocalDate(),
                    rs.getString("email"),
                    rs.getString("gender"),
                    rs.getString("phoneNumber"),
                    rs.getString("address"),
                    rs.getString("position"),
                    rs.getDate("joiningDate").toLocalDate(),
                    rs.getDate("terminationDate") != null ? rs.getDate("terminationDate").toLocalDate() : null
                );
                list.add(emp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
 
    @Override
    public boolean addEmployee(employee employee) throws InvalidInputException {
    	boolean status=false;
        validateEmployeeInput(employee);
 
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            ps.setString(4, employee.getGender());
            ps.setString(5, employee.getEmail());
            ps.setString(6, employee.getPhoneNumber());
            ps.setString(7, employee.getAddress());
            ps.setString(8, employee.getPosition());
            ps.setDate(9, Date.valueOf(employee.getJoiningDate()));
            if (employee.getTerminationDate() != null) {
                ps.setDate(10, Date.valueOf(employee.getTerminationDate()));
            } else {
                ps.setNull(10, Types.DATE);
            }
            int rows = ps.executeUpdate();
            if( rows > 0) {
            	status=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
 
    @Override
    public boolean updateEmployee(employee employee) throws InvalidInputException {
        validateEmployeeInput(employee);
 
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "UPDATE Employee SET FirstName=?, LastName=?, DateOfBirth=?, Gender=?, Email=?, PhoneNumber=?, Address=?, Position=?, JoiningDate=?, TerminationDate=? WHERE EmployeeID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setDate(3, Date.valueOf(employee.getDateOfBirth()));
            ps.setString(4, employee.getGender());
            ps.setString(5, employee.getEmail());
            ps.setString(6, employee.getPhoneNumber());
            ps.setString(7, employee.getAddress());
            ps.setString(8, employee.getPosition());
            ps.setDate(9, Date.valueOf(employee.getJoiningDate()));
            if (employee.getTerminationDate() != null) {
                ps.setDate(10, Date.valueOf(employee.getTerminationDate()));
            } else {
                ps.setNull(10, Types.DATE);
            }
            ps.setInt(11, employee.getEmployeeID());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public boolean removeEmployee(int id) throws InvalidInputException {
        if (id <= 0) {
            throw new InvalidInputException("Invalid employee ID: must be a positive number.");
        }
 
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "DELETE FROM Employee WHERE EmployeeID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    private void validateEmployeeInput(employee employee) throws InvalidInputException {
        if (employee.getFirstName() == null || employee.getFirstName().isBlank() ||
            employee.getLastName() == null || employee.getLastName().isBlank() ||
            employee.getEmail() == null || employee.getEmail().isBlank() ||
            employee.getPhoneNumber() == null || employee.getPhoneNumber().isBlank() ||
            employee.getGender() == null || employee.getGender().isBlank() ||
            employee.getAddress() == null || employee.getAddress().isBlank() ||
            employee.getPosition() == null || employee.getPosition().isBlank() ||
            employee.getDateOfBirth() == null || employee.getJoiningDate() == null) {
            
            throw new InvalidInputException("All fields must be filled and valid (no null/blank values).");
        }
    }
}