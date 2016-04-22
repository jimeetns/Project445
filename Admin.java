import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;

public class Admin {
	
	private String surchargeFile = "Surcharge.csv";
	
	public Admin()
	{
		
	}
	
	
	public int Create_Menu(List<String> itemsToAdd)
	{
		int idCreated = 0;
		Menu m = new Menu();
		idCreated  = m.Create_Menu(itemsToAdd);
		
		return idCreated;
	}
	
	
	public void Modify_MenuPrice(int idToModify, int modifiedPrice)
	{
		Menu m = new Menu();
		int idModified = m.Modify_Menu(idToModify, modifiedPrice);
		
		System.out.println(idModified);
	}
	
	
	public double Get_Surcharge()
	{	  
		double surcharge = 0;
	    try{      
	      BufferedReader br = new BufferedReader(new FileReader(surchargeFile));  
	      br.readLine();
	      String temp = br.readLine();
	      
	      surcharge = Double.parseDouble(temp);      
	      
	      br.close();
	    } catch(FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 

	    return surcharge;
	}
	
	
	public void Modify_Surcharge(double surchargeToMod)
	{
		try{
		      FileWriter writer = new FileWriter(surchargeFile);            
		      
		      writer.append("Surcharge");		      
		      writer.append('\n');
		      
		      writer.append(String.valueOf(surchargeToMod));		      
		      writer.append('\n');
		      
		      writer.flush();
		      writer.close(); 
		    } catch(IOException e){
		      e.printStackTrace();
		    }
	}
	
	
	public void Order_Delivered(int oidToModify)
	{
		Order o = new Order();
		int oidModified = o.Cancel_Delivered_Order(oidToModify, "Delivered");
		
		System.out.println(oidModified);
	}

}
