package com.qainfotech.tap.training.resourceio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class PropertiesOptionsIO{
	Properties property ;
	InputStream in;
	
	/**
	 * Get string as parameter [Key]
	 * @param optionKey
	 * @return String value corresponding to the key
	 * @throws IOException
	 */
    public Object getOptionValue(String optionKey) throws IOException {
		return property.getProperty(optionKey);
	
    }
/**
 * Takes String key,value pairs and save them in to property file at specified location
 * @param optionKey
 * @param optionValue
 * @throws IOException
 */
    public void addOption(String optionKey, String optionValue) throws IOException {
    	property = new Properties();
		in = this.getClass().getResourceAsStream("/options.properties");
		property.load(in);
		property.setProperty(optionKey, optionValue);
		property.store(new FileOutputStream("D:\\Assignment2\\assignment-resource-io-master\\src\\main\\resources\\options.properties"), "Written");
		
    
    }
}
