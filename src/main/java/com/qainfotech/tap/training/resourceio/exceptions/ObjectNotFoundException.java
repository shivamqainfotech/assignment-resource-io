package com.qainfotech.tap.training.resourceio.exceptions;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class ObjectNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(String type, String key, String value){
        super(type + " Object with "+key+"="+value+" not found");
    }
    
}
