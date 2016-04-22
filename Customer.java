import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;

public class Customer {
	
	private List<Integer> id;
	private List<String> nameL;    
	private List<String> emailL;
	private List<String> phoneL;
	private String name;	
	private String email;
	private String phone;
	//private String customerFile = "/home/jimeet/workspace/Project445/Customer.csv";
	private String customerFile;
	
	public Customer(){
		//customerFile = "/home/jimeet/workspace/Project445/Customer.csv";
		customerFile = "Customer.csv";
	   SetParameters();
	   ReadFile();
	  }
	
	public Customer(String frmOrderManager){
		   
		  }
	
	
	public String getNameTemp() {
		return name;
	}
	public void setNameTemp(String nameTemp) {
		this.name = nameTemp;
	}
	public String getEmailTemp() {
		return email;
	}
	public void setEmailTemp(String emailTemp) {
		this.email = emailTemp;
	}
	public String getMobileTemp() {
		return phone;
	}
	public void setMobileTemp(String mobileTemp) {
		this.phone = mobileTemp;
	}

		

	public void Create_Customer(List<String> itemsToAdd)
	{
		int idCreated = id.get(id.size() - 1) + 1;
		
		List<String> customerToAdd = new ArrayList<String>();
		
		String email_ = itemsToAdd.get(8);
		int found = 0;
		
		for(int i = 0; i < id.size(); i++)
			if(emailL.get(i).equals(email_))
				found=1;
		
		if(found == 0)
		{
			customerToAdd.add(String.valueOf(idCreated));
			customerToAdd.add(itemsToAdd.get(7));
			customerToAdd.add(itemsToAdd.get(8));
			customerToAdd.add(itemsToAdd.get(9));
			
			try{
			      FileWriter writer = new FileWriter(customerFile, true);      
			      
			      for(int i = 0; i < customerToAdd.size(); i++)
			      {
			        if (customerToAdd.get(i).contains(","))
			        	customerToAdd.set(i, "\"" + itemsToAdd.get(i) + "\"");
			                
			        writer.append(customerToAdd.get(i));
			        writer.append(",");
			      }
			      writer.append('\n');
			      writer.flush();
			      writer.close();           
			      
			    } catch(IOException e){
			      e.printStackTrace();
			    }
		}
	}
	
	
	public List<List<String>> Get_Customer()
	{
		List<List<String>> customerList = new ArrayList<List<String>>();
		
		int found = 0; 
		
		for(int i = 0; i < id.size(); i++)
		{
			List<String> tempList = new ArrayList<String>();
			
			tempList.add(String.valueOf(id.get(i)));
			tempList.add(nameL.get(i));
			tempList.add(emailL.get(i));
			tempList.add(phoneL.get(i));
			
			customerList.add(tempList);
			found = 1;
		}
		
		if(found == 0)
			customerList = null;
		
		return customerList;		
	}
	
	
	public List<List<String>> Get_Customer(String nameStr)
	{
		List<List<String>> customerList = new ArrayList<List<String>>();
		
		int found = 0;
		
		for(int i = 0; i < id.size(); i++)
		{
			if(nameL.get(i).contains(nameStr))
			{
				List<String> tempList = new ArrayList<String>();
				
				tempList.add(String.valueOf(id.get(i)));
				tempList.add(nameL.get(i));
				tempList.add(emailL.get(i));
				tempList.add(phoneL.get(i));
				
				customerList.add(tempList);
				found = 1;
			}			
		}
		if(found == 0)
			customerList = null;
		
		return customerList;		
	}
	
	public List<String> Get_Customer(Integer cid)
	{
		List<String> customerList = new ArrayList<String>();
		int found = 0;
		
		for(int i = 0; i < id.size(); i++)
		{
			if(id.get(i) == cid)
			{
				customerList.add(String.valueOf(id.get(i)));
				customerList.add(nameL.get(i));
				customerList.add(emailL.get(i));
				customerList.add(phoneL.get(i));
				
				found = 1;
				break;
			}						
		}
		
		if(found == 0)
			customerList = null;
		
		return customerList;		
	}

	
	private void SetParameters()
	  {	    
	    id = new ArrayList<Integer>();	    
	    nameL = new ArrayList<String>();
	    emailL = new ArrayList<String>();
	    phoneL = new ArrayList<String>();
	  }
	  
	private void ReadFile()
	{ 
	  String line = null;
	  try{      
	    BufferedReader br = new BufferedReader(new FileReader(customerFile));  
	    
	    br.readLine();
	    while((line = br.readLine()) != null){
	      
	      String[] customerItems = line.split(",");
	      
	      id.add(Integer.parseInt(customerItems[0]));           
	      nameL.add(customerItems[1]);       
	      emailL.add(customerItems[2]);        
	      phoneL.add(customerItems[3]);
	    }
	    
	    br.close();
	  } catch(FileNotFoundException e) {
	    e.printStackTrace();
	  } catch (IOException e) {
	    e.printStackTrace();
	  } 
	} 
}
