package com.elvinar.casestudy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ExtractServices {
	static File file= null;
	static int count = 0;
	 static ArrayList<String> namearray = new ArrayList<String>();

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ParseException {
		String OS=System.getProperty("os.name");
		
	//	System.out.println(OS);
		{
		if(OS.toUpperCase().contains("WINDOWS"))
		{
			 file =  new File(".//resource//test.log"); 
				
				  } 
		
		else if(OS.toUpperCase().contains("MAC"))
		{
			 file =  new File(".//resource//test.log"); 
				 
				  } 
		}
		{  
		Scanner sc = new Scanner(file); 
			  while (sc.hasNextLine()) 
			  {	
		   String str = sc.nextLine();
		  // System.out.println(str); 

			  count =  count+ 1; 
			  fetchnamefromservice(str);
			  }
		}
		//System.out.println(count); 

	}

	private static void fetchnamefromservice(String str) throws IOException, ParseException {
		int totalrequest;
		String servicenames = str;
		servicenames = servicenames.substring(servicenames.indexOf("(")+ 1, servicenames.indexOf(":",servicenames.indexOf("(")+ 1));
		if(namearray.contains(servicenames)) 
		{
		//	System.out.println("Name is already existing" + servicenames);
		}
		else
		{
		namearray.add(servicenames);
		// System.out.println(namearray);
	//	System.out.println(servicename);
		totalrequest = findtotalcountofservicename(servicenames);
		long maxtimeinseconds = findmaximumtime(servicenames);
		System.out.println("Service name is : " + servicenames + "  ; Amount of Request to Service : " + totalrequest + " ; Max time Execution in Seconds :" + maxtimeinseconds);
		
	}
	}
	private static long findmaximumtime(String servicenames) throws FileNotFoundException, ParseException {
		  ArrayList<String> ar1 = new ArrayList<String>();

		 file =  new File(".//resource//test.log"); 
		 Scanner sc = new Scanner(file); 
		  while (sc.hasNextLine()) 
		  {	
	   String str = sc.nextLine();
	   if((str.contains(servicenames)))
			   {
			 ar1.add(str);
	   }
		  }
		  //System.out.println(ar1);
		 long time = findmaxtime(ar1);
		return time;
	}

	private static long findmaxtime(ArrayList<String> ar1) throws ParseException {
		 long maxseconds = 0;
		//System.out.println(ar1.size());
		String entry;
		String  entrytime = null,exittime = null;
	for(int i=0;i<ar1.size();i++) {
		entry = ar1.get(i).toString().substring(ar1.get(i).toString().indexOf("(")+1, ar1.get(i).toString().indexOf(")"));
		//System.out.println("Name with id " +entry);
		
		for(int j=0; j< ar1.size();j++) {
	if((ar1.get(j).toString().contains(entry)) && (ar1.get(j).toString().contains("entry"))) {
		entrytime = ar1.get(j).toString().substring(0, ar1.get(j).toString().indexOf("TRACE"));
		//System.out.println(entrytime);
		}
	{
		if((ar1.get(j).toString().contains(entry)) && (ar1.get(j).toString().contains("exit"))) {
			exittime = ar1.get(j).toString().substring(0, ar1.get(j).toString().indexOf("TRACE"));
		//	System.out.println(exittime);
		}
	}
		} }
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date parseDate = sdf.parse(entrytime);
	    Date parseDate1 = sdf.parse(exittime);
	    String output = sdf1.format(parseDate);
	    String output1 = sdf1.format(parseDate1);
	   // System.out.println(output);
	    //System.out.println(output1);

	LocalDateTime dateTime1= LocalDateTime.parse(output, formatter);
	LocalDateTime dateTime2= LocalDateTime.parse(output1, formatter);

	long diffInseconds = java.time.Duration.between(dateTime1, dateTime2).getSeconds();
	diffInseconds  = Math.abs(diffInseconds);
//	System.out.println(diffInseconds);
	if(maxseconds < diffInseconds ) {
		maxseconds = diffInseconds;
	}
	return maxseconds;
	}
	
	public static int findtotalcountofservicename(String servicename) throws IOException {
		  int totalcount = 0;
		 file =  new File(".//resource//test.log"); 
		 ArrayList<String> ar = new ArrayList<String>();
		 Scanner sc = new Scanner(file); 
		  while (sc.hasNextLine()) 
		  {	
	   String str = sc.nextLine();
	   if((str.contains(servicename))&& (str.contains("entry with"))) {
			 ar.add(str);
			 totalcount = totalcount + 1;
			// System.out.println(str);
	   }
		  }
		  return totalcount;
		//	 System.out.println("*******************" + ar);
	   }
	   }
	
