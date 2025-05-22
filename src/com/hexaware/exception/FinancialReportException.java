package com.hexaware.exception;
 
public class FinancialReportException extends Exception {
    private static final long serialVersionUID = 1L;
 
    public FinancialReportException(String message) {
        super(message);
    }
 
    public FinancialReportException(String message, Throwable cause) {
        super(message, cause);
    }
}