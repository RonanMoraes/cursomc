package br.com.cnmp.cursomc.services.exeception;

public class DataIntegrityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
		
	}
	
		
	

}
