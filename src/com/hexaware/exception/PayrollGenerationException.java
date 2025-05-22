package com.hexaware.exception;
 
public class PayrollGenerationException extends Exception {
 
    // Serial version UID for serialization
    private static final long serialVersionUID = 1L;
 
    // Default constructor
    public PayrollGenerationException() {
        super();
    }
 
    // Constructor with a message
    public PayrollGenerationException(String message) {
        super(message);
    }
 
    // Constructor with a message and a cause
    public PayrollGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
 
    // Constructor with a cause
    public PayrollGenerationException(Throwable cause) {
        super(cause);
    }
}