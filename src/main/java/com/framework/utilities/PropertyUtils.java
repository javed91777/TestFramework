package com.framework.utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *Read & Write from/to Property File
 */
public class PropertyUtils {
	
	
	public String path;
	
	PropertyUtils(String path){
		
		this.path = path;
	}
	
	
	public  String getValue(String key){
		Properties props = new Properties();
		
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			props.load(fis);
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}

		return props.getProperty(key);
		
	}
	
	
	public  void setValue(String key, String value){
		
	Properties props = new Properties();
		
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file);
			props.load(fis);
		} catch (FileNotFoundException e) {
 	//		e.printStackTrace();
		} catch (IOException e) {
 	//		e.printStackTrace();
		}
		
	 	
		
		try {
			if(value!=null){
				props.setProperty(key, value);
			props.store(new FileOutputStream(path), "");
			System.out.println("Property is set for "+key +"="+value);
 			}
			else
				System.out.println("Property NOT set for "+key);
		} catch (FileNotFoundException e) {
 			e.printStackTrace();
		} catch (IOException e) {
 			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}