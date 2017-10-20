package com.client.commande.ClientCommandeGestion.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 3161147448578705806L;
	
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public BusinessException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public BusinessException() {
		super();
	}

}
