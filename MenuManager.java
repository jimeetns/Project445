import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;

public class MenuManager {
	
	public String id;
	public String name;
	public String price_per_person;
	public String minimum_order;
	public List<Map<String, String>> categories;
	public String create_date;
	public String last_modified_date;
	
	public MenuManager()
	{
		
		
	}
	
	public MenuManager(List<String> s)
	{
		this.name = s.get(0);
		this.price_per_person = s.get(1);
		this.minimum_order = s.get(2);
		this.categories = new ArrayList<Map<String, String>>();
		
		String[] categoryTemp = s.get(3).split("_");
    	for(int i = 0; i < categoryTemp.length; i++)
    	{
    		Map<String, String> mapTemp = new HashMap<String, String>();
    		mapTemp.put("name", categoryTemp[i]);
    		this.categories.add(mapTemp);
    	}
	}
	
	
	public boolean isMatch_Name(String nameStr)
	{
		return (nameStr.equals(this.name)); 
	}
		
	public boolean isMatch_Price(String PriceStr)
	{
		return (PriceStr.equals(this.price_per_person)); 
	}
		
	public boolean isMatch_MinQty(String MinQtyStr)
	{
		return (MinQtyStr.equals(this.minimum_order)); 
	}
		
	public boolean isMatch_Categories(String[] cat)
	{		
		boolean val = false;
		for(int i =0; i < cat.length; i++)
			val = (cat[i].equals(this.categories.get(i).get("name")));
		return val; 
	}
	
	public int CreateMenu(MenuManager mmTemp)
	{
		List<String> itemsToAdd = new ArrayList<String>();
		itemsToAdd.add(mmTemp.name);
		itemsToAdd.add(mmTemp.price_per_person);
		itemsToAdd.add(mmTemp.minimum_order);
		
		String tempStr = mmTemp.categories.get(0).get("name");
		for(int i = 1; i < mmTemp.categories.size(); i++)
			tempStr = tempStr + "_" + mmTemp.categories.get(i).get("name");			
			
		itemsToAdd.add(tempStr);
		
		Menu m = new Menu();
		int id = m.Create_Menu(itemsToAdd);
		
		return id;		
	}
	
	public List<MenuManager> DisplayMenu()
	{
		List<MenuManager> mmList = new ArrayList<MenuManager>();
		Menu m = new Menu();		
		
		List<List<String>> menuDisplay = m.Display_Menu();
		
		if(menuDisplay == null)
		{
			mmList = null;
			return mmList;
		}
		
		for(List<String> menuRowOne : menuDisplay)
	   {
			MenuManager mmTemp = new MenuManager();
			mmTemp.categories = new ArrayList<Map<String, String>>();
			
	    	mmTemp.id = menuRowOne.get(0);
	    	mmTemp.name = menuRowOne.get(1);
	    	mmTemp.price_per_person = menuRowOne.get(2);
	    	mmTemp.minimum_order = menuRowOne.get(3);    
		    
	    	String[] categoryTemp = menuRowOne.get(4).split("_");
	    	for(int i = 0; i < categoryTemp.length; i++)
	    	{
	    		Map<String, String> mapTemp = new HashMap<String, String>();
	    		mapTemp.put("name", categoryTemp[i]);
	    		mmTemp.categories.add(mapTemp);
	    	}
		    
	    	mmList.add(mmTemp);
	   }		
		
		return mmList;
	}
	
	
	public List<MenuManager> DisplayMenu(int mId)
	{
		List<MenuManager> mmList = new ArrayList<MenuManager>();
		Menu m = new Menu();		
		
		List<List<String>> menuDisplay = m.Display_Menu(mId);
		
		if(menuDisplay == null)
		{
			mmList = null;
			return mmList;
		}
		
		for(List<String> menuRowOne : menuDisplay)
	   {
			MenuManager mmTemp = new MenuManager();
			mmTemp.categories = new ArrayList<Map<String, String>>();
			
	    	mmTemp.id = menuRowOne.get(0);
	    	mmTemp.name = menuRowOne.get(1);
	    	mmTemp.price_per_person = menuRowOne.get(2);
	    	mmTemp.minimum_order = menuRowOne.get(3);    
		    
	    	String[] categoryTemp = menuRowOne.get(4).split("_");
	    	for(int i = 0; i < categoryTemp.length; i++)
	    	{
	    		Map<String, String> mapTemp = new HashMap<String, String>();
	    		mapTemp.put("name", categoryTemp[i]);
	    		mmTemp.categories.add(mapTemp);
	    	}
		    
	    	mmTemp.create_date = menuRowOne.get(5);
	    	mmTemp.last_modified_date = menuRowOne.get(6);
	    	
	    	mmList.add(mmTemp);
	   }		
		
		return mmList;
	}
	
	
	public Integer ModifyMenuPrice(MenuManager mm1, int mid)
	{
		Menu m1 = new Menu();
		
		//int mid = Integer.parseInt(mm1.id);
		double price = Double.parseDouble(mm1.price_per_person);
		
		int modId = m1.Modify_Menu(mid, price);
		
		return modId;
		
	}

}
