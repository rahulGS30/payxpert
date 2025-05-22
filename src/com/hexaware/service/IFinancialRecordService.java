package com.hexaware.service;
 
import java.sql.Date;
import java.util.List;
import com.hexaware.entity.finacialRecord;
import com.hexaware.exception.FinancialReportException;
 
public interface IFinancialRecordService {
 
    finacialRecord getFinancialRecordById(int id);
    
    List<finacialRecord> getFinancialRecordsForEmployee(int employeeId);
 
    List<finacialRecord> getFinancialRecordsForDate(Date recordDate);
 
    void addFinancialRecord(int employeeId, String description, int amount, String recordType) throws FinancialReportException;
 
    boolean updateFinancialRecord(finacialRecord record);
 
    boolean removeFinancialRecord(int id);
}