package com.hexaware.entity;

import java.time.LocalDate;

public class employee {
	private int employeeID;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	@Override
	public String toString() {
		return "employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", dateOfBirth=" + dateOfBirth + ", email=" + email + ", gender=" + gender + ", phoneNumber="
				+ phoneNumber + ", address=" + address + ", position=" + position + ", joiningDate=" + joiningDate
				+ ", terminationDate=" + terminationDate + ", getEmployeeID()=" + getEmployeeID() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + ", getDateOfBirth()=" + getDateOfBirth()
				+ ", getEmail()=" + getEmail() + ", getGender()=" + getGender() + ", getPhoneNumber()="
				+ getPhoneNumber() + ", getAddress()=" + getAddress() + ", getPosition()=" + getPosition()
				+ ", getJoiningDate()=" + getJoiningDate() + ", getTerminationDate()=" + getTerminationDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public LocalDate getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(LocalDate terminationDate) {
		this.terminationDate = terminationDate;
	}
	private String email;
	private String gender;
	private String phoneNumber;
	private String address;
	private String position;
	private LocalDate joiningDate;
	private LocalDate terminationDate;
	public employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public employee(int employeeID, String firstName, String lastName, LocalDate dateOfBirth, String email,
			String gender, String phoneNumber, String address, String position, LocalDate joiningDate,
			LocalDate terminationDate) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.position = position;
		this.joiningDate = joiningDate;
		this.terminationDate = terminationDate;
	}
}