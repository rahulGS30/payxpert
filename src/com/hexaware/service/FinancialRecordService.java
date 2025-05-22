package com.hexaware.service;
 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
 
import com.hexaware.entity.finacialRecord;
import com.hexaware.exception.FinancialReportException;
import com.hexaware.util.dbUtil;
 
public class FinancialRecordService implements IFinancialRecordService {
 
	@Override
	public void addFinancialRecord(int employeeId, String description, int amount, String recordType) throws FinancialReportException {
	    try (Connection conn = dbUtil.getConnection()) {
	        String sql = "INSERT INTO FinancialRecord (EmployeeID, Description, Amount, RecordType, RecordDate) VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, employeeId);
	        ps.setString(2, description);
	        ps.setInt(3, amount);
	        ps.setString(4, recordType);
	        ps.setDate(5, new Date(System.currentTimeMillis()));
	        int rows = ps.executeUpdate();
	        if (rows <= 0) {
	            throw new FinancialReportException("Failed to insert financial record.");
	        }
	    } catch (Exception e) {
	        throw new FinancialReportException("Error adding financial record", e);
	    }
	}
 
    @Override
    public finacialRecord getFinancialRecordById(int recordId) {
        finacialRecord record = null;
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "SELECT * FROM FinancialRecord WHERE RecordID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, recordId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                record = new finacialRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("RecordDate"),
                    rs.getString("Description"),
                    rs.getInt("Amount"),
                    rs.getString("RecordType")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }
    @Override
    public List<finacialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<finacialRecord> records = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "SELECT * FROM FinancialRecord WHERE EmployeeID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                finacialRecord record = new finacialRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("RecordDate"),
                    rs.getString("Description"),
                    rs.getInt("Amount"),
                    rs.getString("RecordType")
                );
                records.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
    @Override
    public List<finacialRecord> getFinancialRecordsForDate(Date recordDate) {
        List<finacialRecord> records = new ArrayList<>();
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, recordDate);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                finacialRecord record = new finacialRecord(
                    rs.getInt("RecordID"),
                    rs.getInt("EmployeeID"),
                    rs.getDate("RecordDate"),
                    rs.getString("Description"),
                    rs.getInt("Amount"),
                    rs.getString("RecordType")
                );
                records.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
 
    @Override
    public boolean updateFinancialRecord(finacialRecord record) {
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "UPDATE FinancialRecord SET EmployeeID = ?, Description = ?, Amount = ?, RecordType = ?, RecordDate = ? WHERE RecordID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, record.getEmployeeID());
            ps.setString(2, record.getDescription());
            ps.setInt(3, record.getAmount());
            ps.setString(4, record.getRecordType());
            ps.setDate(5, record.getRecordDate());
            ps.setInt(6, record.getRecordID());
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    @Override
    public boolean removeFinancialRecord(int recordId) {
        try (Connection conn = dbUtil.getConnection()) {
            String sql = "DELETE FROM FinancialRecord WHERE RecordID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, recordId);
            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

	
}