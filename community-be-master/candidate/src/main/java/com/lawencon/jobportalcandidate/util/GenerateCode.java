package com.lawencon.jobportalcandidate.util;

public class GenerateCode {
	public static String generateTicket(int limit) {

		final  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz";
		 

		final  StringBuilder sb = new StringBuilder(limit);
		 
		  for (int i = 0; i < limit; i++) {
		 

		final  int index
		    = (int)(AlphaNumericString.length()
		      * Math.random());
		 
		
		   sb.append(AlphaNumericString
		      .charAt(index));
		  }
		 
		  return sb.toString().toUpperCase();
		 }
}
