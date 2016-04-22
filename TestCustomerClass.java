import junit.framework.TestCase;
import java.io.*;
import java.text.SimpleDateFormat;

import org.junit.*;
import java.util.*;

public class TestCustomerClass extends TestCase{
	
	  private List<String> s = Arrays.asList("20160501", "10 West 31st ST, Chicago IL 60616", "Virgil B", "virgil@example.com", "312-456-7890", "Room SB-214", "12,15", "25,25");
	  	  
	  @Test
	  public void testDisplayCustomer() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    Customer c= new Customer();
	    List<List<String>> customerList = new ArrayList<List<String>>();
		customerList = c.Get_Customer();
	    
		int match = 0;
		
		for(int i = 0; i < customerList.size(); i++)
	   {
		   if(customerList.get(i).get(2).equals(s.get(3)))
		   {
			   for(int j = 1; j < customerList.get(i).size(); j++)
			   {
				   if(customerList.get(i).get(j).equals(s.get(j+1)))
					   match = 1;
				   else
					   {
					   	match = 0;
					   	break;
					   }
			   }
		   }			
	   }    
	    	       
	    assertTrue(match == 1);
	    
	    List<String> s1 = Arrays.asList("20160501", "10 West 31st ST, Chicago IL 60616", "Virgil", "virgil@example.com", "312-456-7890", "Room SB-214", "12,15", "25,25");
	    
	    omTest = new OrderManager(s1);
	    id1 = omTest.CreateOrder(omTest);
	    
	    Customer c1= new Customer();
	    List<List<String>> customerList1 = new ArrayList<List<String>>();
		customerList1 = c1.Get_Customer();
	    
		match = 0;
		
		for(int i = 0; i < customerList1.size(); i++)
	   {
		   if(customerList1.get(i).get(2).equals(s1.get(3)))
		   {
			   for(int j = 1; j < customerList1.get(i).size(); j++)
			   {
				   if(customerList1.get(i).get(j).equals(s1.get(j+1)))
					   match = 1;
				   else
					   {
					   	match = 0;
					   	break;
					   }
			   }
		   }			
	   }    
	    	       
	    assertFalse(match == 1);
	  }
	  
	  int globalCustId = 0;
	  @Test
	  public void testDisplayCustomerByName() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    Customer c= new Customer();
	    List<List<String>> customerList = new ArrayList<List<String>>();
		customerList = c.Get_Customer(s.get(2));
	    
		int match = 0;
		
		for(int i = 0; i < customerList.size(); i++)
	   {
		   if(customerList.get(i).get(2).equals(s.get(3)))
		   {
			   for(int j = 1; j < customerList.get(i).size(); j++)
			   {
				   if(customerList.get(i).get(j).equals(s.get(j+1)))
				      match = 1;										   
				   else
					   {
					   	match = 0;
					   	break;
					   }
			   }
		   }			
	   }    
	    	       
	    assertTrue(match == 1);
	  }
	  
	  
	  @Test
	  public void testDisplayCustomerByCId() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    Customer c= new Customer();
	    List<String> customerList = new ArrayList<String>();
		
	    List<List<String>> customerList1 = new ArrayList<List<String>>();
		customerList1 = c.Get_Customer(s.get(2));
	    
		int match = 0;
		
		for(int i = 0; i < customerList1.size(); i++)
	   {
		   if(customerList1.get(i).get(2).equals(s.get(3)))
		   {
			   	globalCustId = Integer.parseInt(customerList1.get(i).get(0));
			   	break;
		   }			
	   }
	    
	    customerList = c.Get_Customer(globalCustId);
	    
		match = 0;
		
		for(int i = 1; i < customerList.size(); i++)
	   {
		   if(customerList.get(i).equals(s.get(i+1)))
			   match = 1;
		   else
		   {
			   match = 0;
			   break;
		   }		   			
	   }    
	    	       
	    assertTrue(match == 1);
	  }
	  
}