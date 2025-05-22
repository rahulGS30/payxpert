package com.hexaware.entity;
import java.sql.Date;
public class finacialRecord {
	@Override
	public String toString() {
		return "finacialRecord [recordID=" + recordID + ", employeeID=" + employeeID + ", recordDate=" + recordDate
				+ ", description=" + description + ", amount=" + amount + ", recordType=" + recordType
				+ ", getRecordID()=" + getRecordID() + ", getEmployeeID()=" + getEmployeeID() + ", getRecordDate()="
				+ getRecordDate() + ", getDescription()=" + getDescription() + ", getAmount()=" + getAmount()
				+ ", getRecordType()=" + getRecordType() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public int getRecordID() {
		return recordID;
	}
	public void setRecordID(int recordID) {
		this.recordID = recordID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	public finacialRecord(int recordID, int employeeID, Date recordDate, String description, int amount,
			String recordType) {
		super();
		this.recordID = recordID;
		this.employeeID = employeeID;
		this.recordDate = recordDate;
		this.description = description;
		this.amount = amount;
		this.recordType = recordType;
	}
	public finacialRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int recordID;
	private int employeeID;
	private Date recordDate;
	private String description;
	private int amount;
	private String recordType;
}
