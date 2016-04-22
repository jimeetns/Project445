import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.text.*;

public class Menu{
  
  public List<String> menuHeader;
  public List<Integer> id;
  public List<String> food;
  public List<List<String>> category;
  public List<Double> price;
  public List<Integer> minOrder;
  public List<String> createdDate;
  public List<String> modifiedDate;
  //public String menuFile = "/home/jimeet/workspace/Project445/Menu.csv";
  public String menuFile = new File("Menu.csv").getAbsolutePath();
  
  public Menu(){
   SetParameters();
   ReadFile();
  } 
  
  
  public int Create_Menu(List<String> itemsToAdd){
    int idCreated = id.get(id.size() - 1) + 1;
    
    itemsToAdd.add(0, String.valueOf(idCreated));
    Date date1 = new Date();
    Date date2 = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    
    itemsToAdd.add(formatter.format(date1));        
    itemsToAdd.add(formatter.format(date2));
    
    WriteFile(itemsToAdd, 0);       
    
    return idCreated;      
  }
  
 
  public List<List<String>> Display_Menu(){
    
    SetParameters();
    ReadFile();
    
    List<List<String>> menuDisplay = new ArrayList<List<String>>();
    
    for (int i = 0; i < id.size(); i++)
    {
      List<String> tempList = new ArrayList<String>();
      tempList.add(String.valueOf(id.get(i)));
      tempList.add(food.get(i));
      tempList.add(String.valueOf(price.get(i)));
      tempList.add(String.valueOf(minOrder.get(i)));
      String tempCategory = category.get(i).get(0);
      for(int j = 1; j < category.get(i).size(); j++)
        tempCategory = tempCategory + "_" + category.get(i).get(j);
      
      tempList.add(tempCategory);
      tempList.add(createdDate.get(i));
      tempList.add(modifiedDate.get(i));
      
      menuDisplay.add(tempList);
      
    }
          
    return menuDisplay;
  }
  
  
  public List<List<String>> Display_Menu(int menuId){
    
    SetParameters();
    ReadFile();
    
    List<List<String>> menuDisplay = new ArrayList<List<String>>();
    int found = 0;
    for (int i = 0; i < id.size(); i++)
    {
      if(id.get(i) == menuId)
      {
        List<String> tempList = new ArrayList<String>();
        tempList.add(String.valueOf(id.get(i)));
        tempList.add(food.get(i));
        tempList.add(String.valueOf(price.get(i)));
        tempList.add(String.valueOf(minOrder.get(i)));
        String tempCategory = category.get(i).get(0);
        for(int j = 1; j < category.get(i).size(); j++)
          tempCategory = tempCategory + "_" + category.get(i).get(j);
        
        tempList.add(tempCategory);
        
        tempList.add(createdDate.get(i));
        tempList.add(modifiedDate.get(i));
        
        menuDisplay.add(tempList);
        found = 1;
        break;
      }
    }
    
    if(found == 1)
      return menuDisplay;
    
    else
    {      
      menuDisplay = null;
      return menuDisplay;
    }
   }
  
  
  /*public int Modify_Menu(int idToModify, List<String> itemsToModify){    
    
    SetParameters();
    ReadFile();
    
    List<List<String>> itemsReAdd = new ArrayList<List<String>>();
    
    int found = 0;
    for (int i = 0; i < id.size(); i++)
    {
      List<String> tempList = new ArrayList<String>();
      if(id.get(i) == idToModify)
      { 
        tempList.add(String.valueOf(id.get(i)));
        tempList.add(itemsToModify.get(0));
        tempList.add(itemsToModify.get(1));
        tempList.add(itemsToModify.get(2));
        tempList.add(itemsToModify.get(3));
        
        Date date1 = new Date();
        Date date2 = new Date();        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try
        {date1 = formatter.parse(createdDate.get(i));}
        catch(ParseException e)
        {e.printStackTrace();}
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
        tempList.add(String.valueOf(formatter1.format(date1)));
        tempList.add(String.valueOf(formatter1.format(date2)));
                
        itemsReAdd.add(tempList);
        
        found = 1;
      }      
      else
      {        
        tempList.add(String.valueOf(id.get(i)));
        tempList.add(food.get(i));
        tempList.add(String.valueOf(price.get(i)));
        tempList.add(String.valueOf(minOrder.get(i)));
        String tempCategory = category.get(i).get(0);
        for(int j = 1; j < category.get(i).size(); j++)         
          tempCategory = tempCategory + "_" + category.get(i).get(j);
        
        tempList.add(tempCategory);
        Date date1 = new Date();
        Date date2 = new Date();        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try
        {date1 = formatter.parse(createdDate.get(i));
         date2 = formatter.parse(modifiedDate.get(i));}
        catch(ParseException e)
        {e.printStackTrace();}
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
        tempList.add(String.valueOf(formatter1.format(date1)));
        tempList.add(String.valueOf(formatter1.format(date2)));
                
        
        itemsReAdd.add(tempList);        
      }
    }
    
    if(found == 1)
    {
      try{
      FileWriter writer = new FileWriter(menuFile);            
      
      for(int i = 0; i < menuHeader.size(); i++)
      {
        writer.append(menuHeader.get(i));
        writer.append(",");
      }
      writer.append('\n');
      
      for(int j = 0; j < itemsReAdd.size(); j++)
      {
        for(int i = 0; i < itemsReAdd.get(j).size(); i++)
        {
          writer.append(itemsReAdd.get(j).get(i));
          writer.append(",");
        }
        writer.append('\n');        
      }
      
      writer.flush();
      writer.close(); 
    } catch(IOException e){
      e.printStackTrace();
    }   
        
    return idToModify;
    }
    else
    {
      return 0;
    }
    
  }*/
  
  
  public int Modify_Menu(int idToModify, double modifiedPrice){    
	    
	    SetParameters();
	    ReadFile();
	    
	    List<List<String>> itemsReAdd = new ArrayList<List<String>>();
	    
	    int found = 0;
	    for (int i = 0; i < id.size(); i++)
	    {
	      List<String> tempList = new ArrayList<String>();
	      tempList.add(String.valueOf(id.get(i)));
	      tempList.add(food.get(i));
	      //tempList.add(String.valueOf(price.get(i)));
	      tempList.add(String.valueOf(minOrder.get(i)));
	      String tempCategory = category.get(i).get(0);
	        for(int j = 1; j < category.get(i).size(); j++)         
	          tempCategory = tempCategory + "_" + category.get(i).get(j);
	        
	      tempList.add(tempCategory);
	        
	      if(id.get(i) == idToModify)
	      { 
	    	tempList.add(2, String.valueOf(modifiedPrice));
	    	
	        Date date1 = new Date();
	        Date date2 = new Date();        
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	        try
	        {date1 = formatter.parse(createdDate.get(i));}
	        catch(ParseException e)
	        {e.printStackTrace();}
	        
	        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
	        tempList.add(String.valueOf(formatter1.format(date1)));
	        tempList.add(String.valueOf(formatter1.format(date2)));
	        
	        found = 1;
	      }      
	      else
	      { 	    	
	        tempList.add(2, String.valueOf(price.get(i)));
	        
	        Date date1 = new Date();
	        Date date2 = new Date();        
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	        try
	        {date1 = formatter.parse(createdDate.get(i));
	         date2 = formatter.parse(modifiedDate.get(i));}
	        catch(ParseException e)
	        {e.printStackTrace();}
	        
	        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
	        tempList.add(String.valueOf(formatter1.format(date1)));
	        tempList.add(String.valueOf(formatter1.format(date2)));        
	      }
	      itemsReAdd.add(tempList);
	    }
	    
	    if(found == 1)
	    {
	      try{
	      FileWriter writer = new FileWriter(menuFile);            
	      
	      for(int i = 0; i < menuHeader.size(); i++)
	      {
	        writer.append(menuHeader.get(i));
	        writer.append(",");
	      }
	      writer.append('\n');
	      
	      for(int j = 0; j < itemsReAdd.size(); j++)
	      {
	        for(int i = 0; i < itemsReAdd.get(j).size(); i++)
	        {
	          writer.append(itemsReAdd.get(j).get(i));
	          writer.append(",");
	        }
	        writer.append('\n');        
	      }
	      
	      writer.flush();
	      writer.close(); 
	    } catch(IOException e){
	      e.printStackTrace();
	    }   
	        
	    return idToModify;
	    }
	    else
	    {
	      return 0;
	    }
	    
	  }
  
  
  public List<String> Get_ItemNames(String[] mID)
  {
	  List<String> itemNames = new ArrayList<String>();
	  
	  for(int j = 0; j < mID.length; j++)
		  for(int i = 0; i < id.size(); i++)
			  if(Integer.parseInt(mID[j]) == id.get(i))
			  {
				  itemNames.add(food.get(i));
				  break;				  
			  }
	  	  
	  return itemNames;
  }
  
  private void SetParameters()
  {
    menuHeader = new ArrayList<String>();
    id = new ArrayList<Integer>();
    food = new ArrayList<String>();
    category = new ArrayList<List<String>>();
    price = new ArrayList<Double>();
    minOrder = new ArrayList<Integer>();
    createdDate = new ArrayList<String>();
    modifiedDate = new ArrayList<String>();
  }
  
  
  private void ReadFile()
  { 
    String line = null;
    try{      
      BufferedReader br = new BufferedReader(new FileReader(menuFile));  
      int tempCount = 0;
      while((line = br.readLine()) != null){
        String[] menuItems = line.split(",");
        if(tempCount == 0)
        {
          menuHeader = Arrays.asList(menuItems);                    
        }        
        else
        {        
        id.add(Integer.parseInt(menuItems[0]));
        food.add(menuItems[1]);        
        price.add(Double.parseDouble(menuItems[2]));
        minOrder.add(Integer.parseInt(menuItems[3]));
        
        String[] tempCategory = menuItems[4].split("_");
        List<String> tempCategoryList = new ArrayList<String>();
        for(int i = 0; i < tempCategory.length; i++)
          tempCategoryList.add(tempCategory[i]);
          
        category.add(tempCategoryList);
        
        Date date1 = new Date();
        Date date2 = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try
        {date1 = formatter.parse(menuItems[5]);
          date2 = formatter.parse(menuItems[6]);}
        catch(ParseException e)
        {e.printStackTrace();}
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
        createdDate.add(formatter1.format(date1));        
        modifiedDate.add(formatter1.format(date2));
        
        }
        tempCount++;      
      }
      
      br.close();
    } catch(FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }  
 
  }
    
  
  private void WriteFile(List<String> itemsToAdd, int writeHeader)    
  {
    try{
      FileWriter writer = new FileWriter(menuFile, true);      
      
//      writer.append(String.valueOf(idCreated));
//      writer.append(",");
      for(int i = 0; i < itemsToAdd.size(); i++)
      {
        if (itemsToAdd.get(i).contains(","))
           itemsToAdd.set(i, "\"" + itemsToAdd.get(i) + "\"");
         
        writer.append(itemsToAdd.get(i));
        writer.append(",");
      }
      writer.append('\n');
      //System.out.println(String.join(",", itemsToAdd));
      //writer.append(String.join(",", itemsToAdd));v
      
      //writer.append(itemsToAdd.get(i));
      writer.flush();
      writer.close();           
      
    } catch(IOException e){
      e.printStackTrace();
    }
    
  }
  
  
  
  
  public String test()
  {	  
	  return "Helloooo world";
	  
  }
  
  
}
 
