package com.hexaware.exception;
 
public class TaxCalculationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaxCalculationException(String message) {
        super(message);
    }
 
    public TaxCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}