package com.hexaware.service;
 
import java.util.List;
import com.hexaware.entity.taxTable;
import com.hexaware.exception.TaxCalculationException;
 
public interface ITaxService {
    List<taxTable> getTaxesForEmployee(int employeeId) throws TaxCalculationException;
    boolean addTax(taxTable tax) throws TaxCalculationException;
    boolean updateTax(taxTable tax) throws TaxCalculationException;
    boolean removeTax(int taxId) throws TaxCalculationException;
    List<taxTable> getAllTaxes() throws TaxCalculationException;  // Add throws clause here
}