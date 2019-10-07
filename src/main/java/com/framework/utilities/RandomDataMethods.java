package com.framework.utilities;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

/**
 * 
 *
 */
public class RandomDataMethods {

	public static final String ALPHA_CAPS  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALPHA   = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUM     = "0123456789";
    public static final String SPL_CHARS   = "@$";
 
    public static ArrayList<String> defaultResponses;
	public static Random randomGenerator;

   /**
    * Generation of random value based on provided length of string including no of Capital letters and no of digits and no of special characters.
    * 
    * @param minLen
    * @param maxLen
    * @param noOfCAPSAlpha
    * @param noOfDigits
    * @param noOfSplChars
    * @return pswd Password
    */

    public static char[] generateRandomData(int minLen, int maxLen, int noOfCAPSAlpha, int noOfDigits,int noOfSplChars)
    {
    	
        if(minLen > maxLen)
            throw new IllegalArgumentException("Min. Length > Max. Length!");
        if( (noOfCAPSAlpha + noOfDigits +noOfSplChars) > minLen )
            throw new IllegalArgumentException
            ("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
        Random rnd = new Random();
        int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++)
        {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) 
        {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
        for (int i = 0; i < noOfSplChars; i++) 
        {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
        for(int i = 0; i < len; i++)
        {
            if(pswd[i] == 0) 
            {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }
        return pswd;
    }
 
    /**
     * To get next index value while creating the dynamic test data.
     * 
     * @param rnd
     * @param len
     * @param pswd
     * @return index
     */
    public static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while(pswd[index = rnd.nextInt(len)] != 0);
        return index;
    }

    /**
     * Generate Unique Email
     * @return
     */
    public static String generateEmail()
    {
   	 String email=null;
   	 char[] name = generateRandomData(3, 20, 1, 1, 0);
   	 char[] domain = generateRandomData(3, 20, 1, 1, 0);
   	
   	 email=new String(name)+"@"+new String(domain)+".com";
   	 
   	return email;
    }
    
 
    /**
     * Generate Random MobileNumber
     * @return
     */
      public static String generateMobileNumber()
      {
     	 String mobileNumber=null;
     	 char[] number = generateRandomData(9, 9, 0, 9, 0);
     	
     	mobileNumber= "9"+new String(number);
     	 
     	return mobileNumber;
      }
     
      
      /***
       * Generate Random Date from Start date to End Date
       * @param startDate
       * @param endDate
       * @param Format
       * @return
       * @throws java.text.ParseException
       */
      public static String generateRandomDate(String startDate,String endDate,String Format) throws java.text.ParseException
 	 {
 		 DateFormat formatter = new SimpleDateFormat(Format);
 		 Calendar cal=Calendar.getInstance();
 		 cal.setTime(formatter.parse(startDate));
 		 Long value1 = cal.getTimeInMillis();

 		 cal.setTime(formatter.parse(endDate));
 		 Long value2 = cal.getTimeInMillis();

 		 long value3 = (long)(value1 + Math.random()*(value2 - value1));
 		 cal.setTimeInMillis(value3);
 		 return formatter.format(cal.getTime());
       }
 	
      
      /***
       *  Generate Random Number between maximum and minimum values
       * @param min
       * @param max
       * @return
       */
 	 public static int getRandomNumberBetween(int min, int max) {
 	       
 		    Random foo = new Random();
 	        int randomNumber = foo.nextInt(max - min) + min;
 	        if(randomNumber == min) {
 	            // Since the random number is between the min and max values, simply add 1
 	            return min + 1;
 	        }
 	        else {
 	            return randomNumber;
 	        }

 	    }
 	 
     
 }
   