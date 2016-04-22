import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;

public class CustomerManager {
	
	public String id;
	public String name;
	public String email;
	public String phone;
	
	public List<CustomerManager> DisplayCustomers()
	{
		List<CustomerManager> cmList = new ArrayList<CustomerManager>();		
		List<List<String>> customerList = new ArrayList<List<String>>();
		
		Customer c = new Customer();
		
		customerList = c.Get_Customer();
		
		if(customerList == null)
		{
			cmList = null;
			return cmList;
		}
		
		for(int i = 0; i < customerList.size(); i++)
		   {
			   CustomerManager cmTemp = new CustomerManager();
			   cmTemp.id = customerList.get(i).get(0);
			   cmTemp.name = customerList.get(i).get(1);
			   cmTemp.email = customerList.get(i).get(2);
			   cmTemp.phone = customerList.get(i).get(3);
			   
			   cmList.add(cmTemp);
		   }
		
		return cmList;
	}
	
	
	public List<CustomerManager> DisplayCustomers(String nameStr)
	{
		List<CustomerManager> cmList = new ArrayList<CustomerManager>();		
		List<List<String>> customerList = new ArrayList<List<String>>();
		
		Customer c = new Customer();
		
		customerList = c.Get_Customer(nameStr);
		
		if(customerList == null)
		{
			cmList = null;
			return cmList;
		}
		
		for(int i = 0; i < customerList.size(); i++)
		   {
			   CustomerManager cmTemp = new CustomerManager();
			   cmTemp.id = customerList.get(i).get(0);
			   cmTemp.name = customerList.get(i).get(1);
			   cmTemp.email = customerList.get(i).get(2);
			   cmTemp.phone = customerList.get(i).get(3);
			   
			   cmList.add(cmTemp);
		   }
		
		return cmList;
	}
	
	
	public CustomerManager DisplayCustomers(int cId)
	{
		CustomerManager cmList = new CustomerManager();		
		List<String> customerList = new ArrayList<String>();
		
		Customer c = new Customer();
		
		customerList = c.Get_Customer(cId);
		
		if(customerList == null)
		{
			cmList = null;
			return cmList;
		}
			   
		cmList.id = customerList.get(0);
		cmList.name = customerList.get(1);
		cmList.email = customerList.get(2);
		cmList.phone = customerList.get(3);	   
		
	   return cmList;
	}
}
