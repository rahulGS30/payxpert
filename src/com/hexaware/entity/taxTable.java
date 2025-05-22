package com.hexaware.entity;

public class taxTable {
	@Override
	public String toString() {
		return "taxTable [taxID=" + taxID + ", employeeID=" + employeeID + ", year=" + year + ", taxableIncome="
				+ taxableIncome + ", taxAmount=" + taxAmount + ", getTaxID()=" + getTaxID() + ", getEmployeeID()="
				+ getEmployeeID() + ", getYear()=" + getYear() + ", getTaxableIncome()=" + getTaxableIncome()
				+ ", getTaxAmount()=" + getTaxAmount() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public int getTaxID() {
		return taxID;
	}
	public void setTaxID(int taxID) {
		this.taxID = taxID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getTaxableIncome() {
		return taxableIncome;
	}
	public void setTaxableIncome(int taxableIncome) {
		this.taxableIncome = taxableIncome;
	}
	public int getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}
	public taxTable(int taxID, int employeeID, int year, int taxableIncome, int taxAmount) {
		super();
		this.taxID = taxID;
		this.employeeID = employeeID;
		this.year = year;
		this.taxableIncome = taxableIncome;
		this.taxAmount = taxAmount;
	}
	public taxTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int taxID;
	private int employeeID;
	private int year;
	private int taxableIncome;
	private int taxAmount;

}
