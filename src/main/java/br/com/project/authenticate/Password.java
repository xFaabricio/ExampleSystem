package br.com.project.authenticate;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Named;

@Named
public class Password {
	
	String password;
	
	private String hexDigits = "0123456789abcdef";   

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String encrypts(String password) {
    	
    	String passwordCripted = null;
    	
		String cons = "Encriptador Systems 00xx00";
		
		try {
			
			byte[] b = digest((cons + password).getBytes(), "md5");   
			
			passwordCripted = byteArrayToHexString(b);   

		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return passwordCripted;
	}
	
	public String byteArrayToHexString(byte[] b) {   
        StringBuffer buf = new StringBuffer();   
       
        for (int i = 0; i < b.length; i++) {   
            int j = ((int) b[i]) & 0xFF;    
            buf.append(hexDigits.charAt(j / 16));    
            buf.append(hexDigits.charAt(j % 16));    
        }   
           
        return buf.toString();   
    }  
	
	public byte[] digest(byte[] input, String algoritmo)   
	        throws NoSuchAlgorithmException {   
	        MessageDigest md = MessageDigest.getInstance(algoritmo);   
	        md.reset();   
	        return md.digest(input);   
	    }   
		
}
