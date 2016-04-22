import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;


public class Order{

  private List<String> orderHeader;
  public List<Integer> id;
  public List<String> orderDate;
  public List<String> deliveryDate;  
  public List<Double> amount;
  public List<Double> surcharge;
  public List<String> status;
  public List<String> name;    
  public List<String> email;    
  public List<List<Integer>> count;    
  public List<List<Integer>> menuId;
  public List<List<String>> menuItem;
  public List<String> deliveryAddress;    
  public List<String> mobile;    
  public List<String> note;    
  private List<String> holidays;
  //private String orderFile = "/home/jimeet/workspace/Project445/Order.csv";
  //private String holidaysFile = "/home/jimeet/workspace/Project445/Holidays.csv";  
  //private String surchargeFile = "/home/jimeet/workspace/Project445/Surcharge.csv";
  
  private String orderFile = "Order.csv";
  private String holidaysFile = "Holidays.csv";  
  private String surchargeFile = "Surcharge.csv";
  
  public Order(){
   SetParameters();
   ReadFile();
  }
  
  
  public int Create_Order(Map<String, String> orderToAdd)
  {
    int idCreated = id.get(id.size() - 1) + 1;
    
    List<String> itemsToAdd = new ArrayList<String>();
    
    itemsToAdd.add(String.valueOf(idCreated));
    
    Date date1 =  new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    String dateString = formatter.format(date1);
    itemsToAdd.add(dateString);
    
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
    Date date2 =  new Date();
    try
    {date2 = formatter1.parse(orderToAdd.get("deliveryDate"));}
    catch(ParseException e)
    {e.printStackTrace();}
    SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
    itemsToAdd.add(formatter2.format(date2));
    
    if(date1.after(date2) || date1.equals(date2))
    	return -1;
    
    //if(date1.compareTo(date2) > -1)
    	//return -1;
    //if(date2 < date1)
    	//return -1;
    
    List<Double> tempAmount = new ArrayList<Double>();
    tempAmount = getAmount(orderToAdd);
    
    if(tempAmount == null)
    {
    	return -1;
    }
    
    itemsToAdd.add(String.valueOf(tempAmount.get(0)));
    itemsToAdd.add(String.valueOf(tempAmount.get(1)));
    itemsToAdd.add("Open"); //Status
    itemsToAdd.add(orderToAdd.get("deliveryAddress"));
    itemsToAdd.add(orderToAdd.get("name"));
    itemsToAdd.add(orderToAdd.get("email"));
    itemsToAdd.add(orderToAdd.get("mobile"));
    itemsToAdd.add(orderToAdd.get("note"));
    String[] menuIdTemp = orderToAdd.get("menuId").split(",");
    String mIdStr = menuIdTemp[0];
    for (int i = 1; i < menuIdTemp.length; i++)
      mIdStr = mIdStr + "_" + menuIdTemp[i];
    itemsToAdd.add(mIdStr);
    
    Menu m = new Menu();
    List<String> itemNames = new ArrayList<String>();
    itemNames = m.Get_ItemNames(menuIdTemp);
    String itemNamesTemp = itemNames.get(0);
    for(int i =1; i < itemNames.size(); i++)
    	itemNamesTemp = itemNamesTemp + "_" + itemNames.get(i);
    itemsToAdd.add(itemNamesTemp);
    
    String[] countTemp = orderToAdd.get("count").split(",");
    String countStr = countTemp[0];
    for (int i = 1; i < countTemp.length; i++)
      countStr = countStr + "_" + countTemp[i];
    itemsToAdd.add(countStr);    
    
    try{
      FileWriter writer = new FileWriter(orderFile, true);      
      
      for(int i = 0; i < itemsToAdd.size(); i++)
      {
        if (itemsToAdd.get(i).contains(","))
           itemsToAdd.set(i, "\"" + itemsToAdd.get(i) + "\"");
                
        writer.append(itemsToAdd.get(i));
        writer.append(",");
      }
      writer.append('\n');
      writer.flush();
      writer.close();           
      
    } catch(IOException e){
      e.printStackTrace();
    } 
    
    Customer c = new Customer();
    c.Create_Customer(itemsToAdd);
    
    return idCreated;      
  }
  
  
  /*public int Modify_Order(int oidToModify, Map<String, String> orderToModify)
  {
	SetParameters();
	ReadFile();
	List<List<String>> itemsReAdd = new ArrayList<List<String>>();
    int found = 0;
    
    for(int i = 0; i < id.size(); i++)    	
    {
    	List<String> tempList = new ArrayList<String>();
    	
    	if(id.get(i) == oidToModify)
    	{
    		tempList.add(String.valueOf(id.get(i)));
    		
    		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
    	    Date date2 =  new Date();
    	    try
    	    {date2 = formatter1.parse(orderDate.get(i));}
    	    catch(ParseException e)
    	    {e.printStackTrace();}
    	    SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
    	    tempList.add(formatter2.format(date2));
    		    		
    	    Date date3 =  new Date();
    	    try
    	    {date3 = formatter1.parse(orderToModify.get("deliveryDate"));}
    	    catch(ParseException e)
    	    {e.printStackTrace();}    	    
    	    tempList.add(formatter2.format(date3));
    		    		
    	    List<Double> tempAmount = new ArrayList<Double>();
    	    tempAmount = getAmount(orderToModify);    	    
    		tempList.add(String.valueOf(tempAmount.get(0)));
    		tempList.add(String.valueOf(tempAmount.get(1)));
    		
    		tempList.add(status.get(i));    		
    		tempList.add(orderToModify.get("deliveryAddress"));
    		tempList.add(orderToModify.get("name"));
    		tempList.add(orderToModify.get("email"));
    		tempList.add(orderToModify.get("mobile"));
    		tempList.add(orderToModify.get("note"));
    	    String[] menuIdTemp = orderToModify.get("menuId").split(",");
    	    String mIdStr = menuIdTemp[0];
    	    for (int j = 1; j < menuIdTemp.length; j++)
    	      mIdStr = mIdStr + "_" + menuIdTemp[j];
    	    tempList.add(mIdStr);
    	    
    	    Menu m = new Menu();
    	    List<String> itemNames = new ArrayList<String>();
    	    itemNames = m.Get_ItemNames(menuIdTemp);
    	    String itemNamesTemp = itemNames.get(0);
    	    for(int j =1; j < itemNames.size(); j++)
    	    	itemNamesTemp = itemNamesTemp + "_" + itemNames.get(j);
    	    tempList.add(itemNamesTemp);
    	    
    	    String[] countTemp = orderToModify.get("count").split(",");
    	    String countStr = countTemp[0];
    	    for (int j = 1; j < countTemp.length; j++)
    	      countStr = countStr + "_" + countTemp[j];
    	    tempList.add(countStr);
    	    
    	    found = 1;
    	}
    	else
    	{
    		tempList.add(String.valueOf(id.get(i)));
    		    		
    		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
    	    Date date2 =  new Date();
    	    try
    	    {date2 = formatter1.parse(orderDate.get(i));}
    	    catch(ParseException e)
    	    {e.printStackTrace();}
    	    SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
    	    tempList.add(formatter2.format(date2));
    		    		
    	    Date date3 =  new Date();
    	    try
    	    {date3 = formatter1.parse(deliveryDate.get(i));}
    	    catch(ParseException e)
    	    {e.printStackTrace();}    	    
    	    tempList.add(formatter2.format(date3));    		
    		
    		tempList.add(String.valueOf(amount.get(i)));
    	    
    		tempList.add(status.get(i));    		
    		tempList.add(deliveryAddress.get(i));
    		tempList.add(name.get(i));
    		tempList.add(email.get(i));
    		tempList.add(mobile.get(i));
    		tempList.add(note.get(i));
    	    
    	    List<Integer> menuIdsTemp = new ArrayList<Integer>();
    	    String mIdStr = String.valueOf(menuId.get(i).get(0));
    	    for(int j = 1; j < menuId.get(i).size(); j++)
    	    	mIdStr = mIdStr + "_" + menuId.get(i).get(j);
    	    tempList.add(mIdStr);
    	    
    	    List<Integer> countTemp = new ArrayList<Integer>();
    	    String countStr = String.valueOf(count.get(i).get(0));
    	    for(int j = 1; j < count.get(i).size(); j++)
    	    	countStr = countStr + "_" + count.get(i).get(j);
    	    tempList.add(countStr);            
    	}
    	itemsReAdd.add(tempList);
    }
    
    if(found == 1)
    {
      try{
      FileWriter writer = new FileWriter(orderFile);            
      
      for(int i = 0; i < orderHeader.size(); i++)
      {
        writer.append(orderHeader.get(i));
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
        
    return oidToModify;
    }
    else
    {
      return 0;
    }
    
  }
  */
  
  public List<List<String>> Display_Orders()
  {
    SetParameters();
    ReadFile();
    
    List<List<String>> orderDisplay = new ArrayList<List<String>>();
    
    for (int i = 0; i < id.size(); i++)
    {
      List<String> tempList = new ArrayList<String>();
      tempList.add(String.valueOf(id.get(i)));
      tempList.add(orderDate.get(i));
      tempList.add(deliveryDate.get(i));
      tempList.add(String.valueOf(amount.get(i)));
      tempList.add(String.valueOf(surcharge.get(i)));
      tempList.add(status.get(i));
      tempList.add(email.get(i));         
      
      orderDisplay.add(tempList);      
    }
    
    return orderDisplay;
  }
  
  
  public List<List<String>> Display_Orders(String byDeliveryDate)
  {
    SetParameters();
    ReadFile();
    
    List<List<String>> orderDisplay = new ArrayList<List<String>>();
    int found = 0;
    
    for (int i = 0; i < id.size(); i++)
    {
      if(deliveryDate.get(i).equals(byDeliveryDate))
      {
        List<String> tempList = new ArrayList<String>();
        tempList.add(String.valueOf(id.get(i)));
        tempList.add(orderDate.get(i));
        tempList.add(deliveryDate.get(i));
        tempList.add(String.valueOf(amount.get(i)));
        tempList.add(String.valueOf(surcharge.get(i)));
        tempList.add(status.get(i));
        tempList.add(email.get(i));         
        
        orderDisplay.add(tempList);      
        found = 1;
      }
    }
    
    if(found == 1)
      return orderDisplay;
    else
    {
      orderDisplay = null;
      return orderDisplay;      
    }
    
  }
    

  public List<List<String>> Display_Orders(int orderId)
  {
    SetParameters();
    ReadFile();
    
    List<List<String>> orderDisplay = new ArrayList<List<String>>();
    int found = 0;
    
    for (int i = 0; i < id.size(); i++)
    {
      if(id.get(i) == orderId)
      {
        List<String> tempList = new ArrayList<String>();
        tempList.add(String.valueOf(id.get(i)));
        tempList.add(String.valueOf(amount.get(i)));
        tempList.add(String.valueOf(surcharge.get(i)));
        tempList.add(status.get(i));
        tempList.add(orderDate.get(i));
        tempList.add(deliveryDate.get(i));
        tempList.add(name.get(i)); 
        tempList.add(email.get(i)); 
        tempList.add(mobile.get(i));         
        tempList.add(deliveryAddress.get(i));
        tempList.add(note.get(i));
        
        String menuIdStr = String.valueOf(menuId.get(i).get(0));
        for(int j = 1; j < menuId.get(i).size(); j++)
        	menuIdStr = menuIdStr + "_" + menuId.get(i).get(j);
        tempList.add(menuIdStr);
        
        String menuItemStr = String.valueOf(menuItem.get(i).get(0));
        for(int j = 1; j < menuItem.get(i).size(); j++)
        	menuItemStr = menuItemStr + "_" + menuItem.get(i).get(j);
        tempList.add(menuItemStr);
        
        String countStr = String.valueOf(count.get(i).get(0));
        for(int j = 1; j < count.get(i).size(); j++)
        	countStr = countStr + "_" + count.get(i).get(j);
        tempList.add(countStr);
        
        orderDisplay.add(tempList);  
        found = 1;
        break;
      }
    }
    
    if(found == 1)
      return orderDisplay;
    else
    {
      System.out.println("No order by that ID");
      return null;      
    }   
    
  }

  
  public int Cancel_Delivered_Order(int oidToCancel, String orderModStr)
  {
	SetParameters();
	ReadFile();
	List<List<String>> itemsReAdd = new ArrayList<List<String>>();
    int found = 0;
    
    for(int i = 0; i < id.size(); i++)    	
    {
    	List<String> tempList = new ArrayList<String>();
    	
    	tempList.add(String.valueOf(id.get(i)));
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
	    Date date2 =  new Date();
	    try
	    {date2 = formatter1.parse(orderDate.get(i));}
	    catch(ParseException e)
	    {e.printStackTrace();}
	    SimpleDateFormat formatter2 = new SimpleDateFormat("MM/dd/yyyy");
	    tempList.add(formatter2.format(date2));
		    		
	    Date date3 =  new Date();
	    try
	    {date3 = formatter1.parse(deliveryDate.get(i));}
	    catch(ParseException e)
	    {e.printStackTrace();}    	    
	    tempList.add(formatter2.format(date3));    		
		
		tempList.add(String.valueOf(amount.get(i)));
		tempList.add(String.valueOf(surcharge.get(i)));
		//tempList.add(status.get(i));    		
		tempList.add(deliveryAddress.get(i));
		tempList.add(name.get(i));
		tempList.add(email.get(i));
		tempList.add(mobile.get(i));
		tempList.add(note.get(i));
	    	    
	    String mIdStr = String.valueOf(menuId.get(i).get(0));
	    for(int j = 1; j < menuId.get(i).size(); j++)
	    	mIdStr = mIdStr + "_" + menuId.get(i).get(j);
	    tempList.add(mIdStr);
	    	    
	    String mItemStr = String.valueOf(menuItem.get(i).get(0));
	    for(int j = 1; j < menuItem.get(i).size(); j++)
	    	mItemStr = mItemStr + "_" + menuItem.get(i).get(j);
	    tempList.add(mItemStr);
	    
	    String countStr = String.valueOf(count.get(i).get(0));
	    for(int j = 1; j < count.get(i).size(); j++)
	    	countStr = countStr + "_" + count.get(i).get(j);
	    tempList.add(countStr);
    	
    	if(id.get(i) == oidToCancel)
    	{
    		tempList.add(5, orderModStr);
    	    found = 1;
    	}
    	else
    	{
    		tempList.add(5, status.get(i));	            
    	}
    	itemsReAdd.add(tempList);
    }
    
    if(found == 1)
    {
      try{
      FileWriter writer = new FileWriter(orderFile);            
      
      for(int i = 0; i < orderHeader.size(); i++)
      {
        writer.append(orderHeader.get(i));
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
        
    return oidToCancel;
    }
    else
    {
      return -1;
    }
    
  }
  
  
  private void SetParameters()
  {
    orderHeader = new ArrayList<String>();
    id = new ArrayList<Integer>();
    orderDate = new ArrayList<String>();
    deliveryDate = new ArrayList<String>();
    amount = new ArrayList<Double>();
    surcharge = new ArrayList<Double>();
    status = new ArrayList<String>();
    deliveryAddress = new ArrayList<String>();
    name = new ArrayList<String>();
    email = new ArrayList<String>();
    mobile = new ArrayList<String>();
    note = new ArrayList<String>();
    menuId = new ArrayList<List<Integer>>();
    menuItem = new ArrayList<List<String>>();
    count = new ArrayList<List<Integer>>();
    holidays = new ArrayList<String>();
  
  }
  
  private void ReadFile()
  { 
    String line = null;
    try{      
      BufferedReader br = new BufferedReader(new FileReader(orderFile));  
      int tempCount = 0;
      while((line = br.readLine()) != null){
        String[] orderItems = line.split(",");
        if(tempCount == 0)
        {
          orderHeader = Arrays.asList(orderItems);                    
        }        
        else
        {        
          id.add(Integer.parseInt(orderItems[0]));
          
          Date date1 =  new Date();
          Date date2 =  new Date();
          SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
          try
          {date1 = formatter.parse(orderItems[1]);
            date2 = formatter.parse(orderItems[2]);}
          catch(ParseException e)
          {e.printStackTrace();}
          
          SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
          orderDate.add(formatter1.format(date1));        
          deliveryDate.add(formatter1.format(date2));     
          
          amount.add(Double.parseDouble(orderItems[3]));   
          surcharge.add(Double.parseDouble(orderItems[4]));
          status.add(orderItems[5]);
          deliveryAddress.add(orderItems[6]);
          name.add(orderItems[7]);       
          email.add(orderItems[8]);        
          mobile.add(orderItems[9]);        
          note.add(orderItems[10]);  
          String[] menuIds = orderItems[11].split("_");
          List<Integer> menuIdsTemp = new ArrayList<Integer>();
          for(int i = 0; i < menuIds.length; i++)
            menuIdsTemp.add(Integer.parseInt(menuIds[i]));
          
          menuId.add(menuIdsTemp);          
          
          String[] menuItems = orderItems[12].split("_");
          List<String> menuItemsTemp = new ArrayList<String>();
          for(int i = 0; i < menuItems.length; i++)
        	  menuItemsTemp.add(menuItems[i]);
          
          menuItem.add(menuItemsTemp);       
          
          String[] counts = orderItems[13].split("_");
          List<Integer> countsTemp = new ArrayList<Integer>();
          for(int i = 0; i < counts.length; i++)
            countsTemp.add(Integer.parseInt(counts[i]));
          
          count.add(countsTemp);
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

     
  private List<Double> getAmount(Map<String, String> orderToAdd)
  {
    double amount = 0;
    double surcharge_ = 0;
    List<Double> returnTemp = new ArrayList<Double>();
    
    Menu m1 = new Menu();
    
    String line = null;
    try{      
      BufferedReader br = new BufferedReader(new FileReader(holidaysFile));  
      br.readLine();
      while((line = br.readLine()) != null){        
        Date date1 =  new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try
        {date1 = formatter.parse(line);}
        catch(ParseException e)
        {e.printStackTrace();}
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
        holidays.add(formatter1.format(date1));        
      }
	  
    }catch(FileNotFoundException e) {
	    e.printStackTrace();
	}catch (IOException e) {
	    e.printStackTrace();
	} 
    
    String[] menuId = orderToAdd.get("menuId").split(",");
    String[] count = orderToAdd.get("count").split(",");    
    for(int i = 0; i < menuId.length; i++)
    {
      int menuIdTemp = Integer.parseInt(menuId[i]);
      int countTemp = Integer.parseInt(count[i]);
      int success = 0;
      
      for(int j = 0; j < m1.id.size(); j++)        
      {
        if(m1.id.get(j) == menuIdTemp)
        {
          if(countTemp >= m1.minOrder.get(j))
            {
        	  amount = amount + m1.price.get(j)*countTemp;
        	  success = 1;
            }          
        }
      }
      if(success != 1)
      {
    	  returnTemp = null;
    	  return returnTemp;
      }
    } 
    Date date2 = new Date();
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
    try
    {date2 = formatter1.parse(orderToAdd.get("deliveryDate"));}
    catch(ParseException e)
    {e.printStackTrace();}    
    Date date1 =  new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");    
    date1 = date2;
           
    Calendar c = Calendar.getInstance();
    c.set(date1.getYear() + 1900, date1.getMonth(), date1.getDate());    
    if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||  c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)
    {
    	surcharge_ = Get_Surcharge();
    	//amount = amount + surcharge_;    	
    }
    else
    {    	
	    for(int i = 0; i < holidays.size(); i++)
	    	if(orderToAdd.get("deliveryDate").equals(holidays.get(i)))
	    	{
	    		surcharge_ = Get_Surcharge();
	    		//amount = amount + surcharge_;
	    	}	    		
    }  
    
    returnTemp.add(amount);
    returnTemp.add(surcharge_);
    return returnTemp;
  }
  
  
  public double Get_Surcharge()
  {	  
	  double surcharge_ = 0;
	    try{      
	      BufferedReader br = new BufferedReader(new FileReader(surchargeFile));  
	      br.readLine();
	      String temp = br.readLine();
	      
	      surcharge_ = Double.parseDouble(temp);      
	      
	      br.close();
	    } catch(FileNotFoundException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 

	  return surcharge_;
  }
  
  
  public void Modify_Surcharge(double surcharge_)
  {	  	  
	  try{
	      FileWriter writer = new FileWriter(surchargeFile);
	                
	      writer.append("Surcharge");
	      writer.append('\n');
	      writer.append(String.valueOf(surcharge_));
	      writer.append('\n');
	      
	      writer.flush();
	      writer.close();           
	      
	    } catch(IOException e){
	      e.printStackTrace();
	    } 
  }
  
}
