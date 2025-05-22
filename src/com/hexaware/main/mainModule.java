package com.hexaware.main;
 
import com.hexaware.entity.*;
import com.hexaware.exception.*;
import com.hexaware.service.*;
 
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
 
public class mainModule {
    static iEmployeeService empService = new employeeService();
    static ITaxService taxService = new TaxService();
    static IPayrollService payrollService = new PayrollService();
    static IFinancialRecordService financialService = new FinancialRecordService();
 
    static Scanner sc = new Scanner(System.in);
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 
    public static void main(String[] args) throws EmployeeNotFoundException, TaxCalculationException, FinancialReportException, InvalidInputException {
        while (true) {
            System.out.println("\n========== Main Menu ==========");
            System.out.println("1. Employee Management");
            System.out.println("2. Tax Management");
            System.out.println("3. Payroll Management");
            System.out.println("4. Financial Record Management");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
 
            switch (mainChoice) {
                case 1 -> employeeMenu();
                case 2 -> taxMenu();
                case 3 -> payrollMenu();
                case 4 -> financialMenu();
                case 5 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }
 
    // -------------------- Employee Section --------------------
    static void employeeMenu() throws EmployeeNotFoundException, InvalidInputException {
        while (true) {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Get Employee by ID");
            System.out.println("2. Get All Employees");
            System.out.println("3. Add Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Remove Employee");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    employee emp = empService.getEmployeeById(id);
                    System.out.println(emp != null ? emp : "Employee not found.");
                }
                case 2 -> {
                    List<employee> list = empService.getAllEmployees();
                    if (list.isEmpty()) System.out.println("No employees found.");
                    else list.forEach(System.out::println);
                }
                case 3 -> addEmployee();
                case 4 -> updateEmployee();
                case 5 -> {
                    System.out.print("Enter Employee ID to remove: ");
                    int id = sc.nextInt();
                    boolean removed = empService.removeEmployee(id);
                    System.out.println(removed ? "Removed successfully." : "Not found or failed.");
                }
                case 6 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
 
    static void addEmployee() {
        try {
            sc.nextLine();
            System.out.print("First Name: ");
            String fname = sc.nextLine();
            System.out.print("Last Name: ");
            String lname = sc.nextLine();
            System.out.print("Date of Birth (yyyy-MM-dd): ");
            LocalDate dob = LocalDate.parse(sc.nextLine());
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Gender: ");
            String gender = sc.nextLine();
            System.out.print("Phone Number: ");
            String phone = sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();
            System.out.print("Position: ");
            String position = sc.nextLine();
            System.out.print("Joining Date (yyyy-MM-dd): ");
            LocalDate joinDate = LocalDate.parse(sc.nextLine());
            System.out.print("Termination Date (yyyy-MM-dd or blank): ");
            String termInput = sc.nextLine();
            LocalDate termDate = termInput.isEmpty() ? null : LocalDate.parse(termInput);
 
            employee emp = new employee(0, fname, lname, dob, email, gender, phone, address, position, joinDate, termDate);
            System.out.println(empService.addEmployee(emp) ? "Added successfully." : "Add failed.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    static void updateEmployee() {
        try {
            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("First Name: ");
            String fname = sc.nextLine();
            System.out.print("Last Name: ");
            String lname = sc.nextLine();
            System.out.print("Date of Birth (yyyy-MM-dd): ");
            LocalDate dob = LocalDate.parse(sc.nextLine());
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Gender: ");
            String gender = sc.nextLine();
            System.out.print("Phone Number: ");
            String phone = sc.nextLine();
            System.out.print("Address: ");
            String address = sc.nextLine();
            System.out.print("Position: ");
            String position = sc.nextLine();
            System.out.print("Joining Date (yyyy-MM-dd): ");
            LocalDate joinDate = LocalDate.parse(sc.nextLine());
            System.out.print("Termination Date (yyyy-MM-dd or blank): ");
            String termInput = sc.nextLine();
            LocalDate termDate = termInput.isEmpty() ? null : LocalDate.parse(termInput);
 
            employee emp = new employee(id, fname, lname, dob, email, gender, phone, address, position, joinDate, termDate);
            System.out.println(empService.updateEmployee(emp) ? "Updated successfully." : "Update failed.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
 
    // -------------------- Tax Section --------------------
    static void taxMenu() throws TaxCalculationException {
        System.out.println("\n--- Tax Management ---");
        System.out.print("""
            1. Add Tax
            2. View All Tax Records
            3. View Tax Records by Employee ID
            4. Update Tax Record
            5. Remove Tax Record
            Choose: """);
     
        int c = sc.nextInt();
        switch (c) {
            case 1 -> {
                System.out.print("Employee ID: ");
                int id = sc.nextInt();
                System.out.print("Year: ");
                int year = sc.nextInt();
                System.out.print("Taxable Income: ");
                int income = sc.nextInt();
                System.out.print("Tax Amount: ");
                int tax = sc.nextInt();
                taxTable t = new taxTable(0, id, year, income, tax);
                System.out.println(taxService.addTax(t) ? "Tax added." : "Failed.");
            }
            case 2 -> {
                List<taxTable> list = taxService.getAllTaxes();
                list.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Enter Employee ID: ");
                int empId = sc.nextInt();
                List<taxTable> list = taxService.getTaxesForEmployee(empId);
                list.forEach(System.out::println);
            }
            case 4 -> {
                System.out.print("Tax ID to Update: ");
                int taxId = sc.nextInt();
                System.out.print("New Employee ID: ");
                int id = sc.nextInt();
                System.out.print("Year: ");
                int year = sc.nextInt();
                System.out.print("Taxable Income: ");
                int income = sc.nextInt();
                System.out.print("Tax Amount: ");
                int tax = sc.nextInt();
                taxTable t = new taxTable(taxId, id, year, income, tax);
                System.out.println(taxService.updateTax(t) ? "Tax updated." : "Failed.");
            }
            case 5 -> {
                System.out.print("Enter Tax ID to Remove: ");
                int taxId = sc.nextInt();
                System.out.println(taxService.removeTax(taxId) ? "Tax removed." : "Failed.");
            }
        }
    }
     
 
    // -------------------- Payroll Section --------------------
    static void payrollMenu() {
        System.out.println("\n--- Payroll Management ---");
        System.out.println("1. Generate Payroll");
        System.out.println("2. Get Payroll By ID");
        System.out.println("3. View Payrolls for Employee");
        System.out.println("4. View Payrolls in Period");
        System.out.print("Choose: ");
        int c = sc.nextInt();
        sc.nextLine(); // consume newline
        switch (c) {
            case 1 -> {
                System.out.print("Employee ID: ");
                int empId = sc.nextInt();
                System.out.print("Start Date (yyyy-mm-dd): ");
                java.sql.Date start = java.sql.Date.valueOf(sc.next());
                System.out.print("End Date (yyyy-mm-dd): ");
                java.sql.Date end = java.sql.Date.valueOf(sc.next());
                System.out.print("Basic Salary: ");
                int basic = sc.nextInt();
                System.out.print("Overtime Pay: ");
                int ot = sc.nextInt();
                System.out.print("Deductions: ");
                int ded = sc.nextInt();
     
                PayrollService service = new PayrollService();
                Payroll payroll = service.generatePayroll(empId, start, end, basic, ot, ded);
                System.out.println((payroll != null) ? "Payroll Generated:\n" + payroll : "Failed to generate.");
            }
            case 2 -> {
                System.out.print("Enter Payroll ID: ");
                int id = sc.nextInt();
                PayrollService service = new PayrollService();
                Payroll payroll = service.getPayrollById(id);
                System.out.println((payroll != null) ? payroll : "Payroll not found.");
            }
            case 3 -> {
                System.out.print("Enter Employee ID: ");
                int empId = sc.nextInt();
                PayrollService service = new PayrollService();
                List<Payroll> list = service.getPayrollsForEmployee(empId);
                list.forEach(System.out::println);
            }
            case 4 -> {
                System.out.print("Start Date (yyyy-mm-dd): ");
                java.sql.Date start = java.sql.Date.valueOf(sc.next());
                System.out.print("End Date (yyyy-mm-dd): ");
                java.sql.Date end = java.sql.Date.valueOf(sc.next());
                PayrollService service = new PayrollService();
                List<Payroll> list = service.getPayrollsForPeriod(start, end);
                list.forEach(System.out::println);
            }
        }
    }
     
 
    // -------------------- Financial Section --------------------
    public static void financialMenu() throws FinancialReportException {
        System.out.println("\n--- Financial Records ---");
        System.out.println("1. Add Record");
        System.out.println("2. View Employee Financial Records");
        System.out.println("3. View Financial Records By Date");
        System.out.println("4. Delete a Record");
        System.out.println("5. Update a Record");
        System.out.print("Choose: ");
        
        int c = sc.nextInt();
        sc.nextLine();
        
        switch (c) {
            case 1 -> {
                System.out.print("Employee ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Description: ");
                String desc = sc.nextLine();
                System.out.print("Amount: ");
                int amount = sc.nextInt();
                sc.nextLine();
                System.out.print("Record Type (Credit/Debit): ");
                String type = sc.nextLine();
                financialService.addFinancialRecord(id, desc, amount, type);
                System.out.println("Added.");
            }
            case 2 -> {
                System.out.print("Enter Employee ID: ");
                int id = sc.nextInt();
                List<finacialRecord> list = financialService.getFinancialRecordsForEmployee(id);
                list.forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("Enter Date (yyyy-mm-dd): ");
                java.sql.Date date = java.sql.Date.valueOf(sc.nextLine());
                List<finacialRecord> list = financialService.getFinancialRecordsForDate(date);
                list.forEach(System.out::println);
            }
            case 4 -> {
                System.out.print("Enter Record ID to delete: ");
                int recordId = sc.nextInt();
                boolean success = financialService.removeFinancialRecord(recordId);
                System.out.println(success ? "Deleted successfully." : "Delete failed.");
            }
            case 5 -> {
                System.out.print("Record ID to update: ");
                int recordId = sc.nextInt();
                sc.nextLine();
                finacialRecord old = financialService.getFinancialRecordById(recordId);
                if (old == null) {
                    System.out.println("Record not found.");
                    return;
                }
                System.out.print("New Employee ID (" + old.getEmployeeID() + "): ");
                int empId = sc.nextInt();
                sc.nextLine();
                System.out.print("New Description (" + old.getDescription() + "): ");
                String desc = sc.nextLine();
                System.out.print("New Amount (" + old.getAmount() + "): ");
                int amount = sc.nextInt();
                sc.nextLine();
                System.out.print("New Record Type (" + old.getRecordType() + "): ");
                String type = sc.nextLine();
                System.out.print("New Record Date (yyyy-mm-dd): ");
                java.sql.Date date = java.sql.Date.valueOf(sc.nextLine());
                finacialRecord updated = new finacialRecord(recordId, empId, date, desc, amount, type);
                boolean updatedSuccess = financialService.updateFinancialRecord(updated);
                System.out.println(updatedSuccess ? "Updated successfully." : "Update failed.");
            }
            default -> System.out.println("Invalid choice.");
        }
     
    
    }
}
 