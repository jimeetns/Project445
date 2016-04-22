import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;

public class OrderManager {

	public String id;
	public String orderDate;
	public String delivery_date;  
	public String amount;
	public String surcharge;
	public String status;
	public String delivery_address;
	public String name;    
	public String email;
	public String mobile;    
	public String note;
	public Customer personal_info;
	public List<LineItems> order_detail;	
	  
		
	public OrderManager()
	{
						
	}
	
	public OrderManager(List<String> menuRowOne)
	{
		this.delivery_date = menuRowOne.get(0);
		this.delivery_address = menuRowOne.get(1);
		this.personal_info = new Customer();
		this.personal_info.setNameTemp(menuRowOne.get(2));
		this.personal_info.setEmailTemp(menuRowOne.get(3));
		this.personal_info.setMobileTemp(menuRowOne.get(4));
		this.note = menuRowOne.get(5);
		String[] menuIdTemp = menuRowOne.get(6).split(",");
		String[] menuCountTemp = menuRowOne.get(7).split(",");
		this.order_detail = new ArrayList<LineItems>();
		for(int i = 0; i < menuIdTemp.length; i++)
		{
			LineItems lTemp = new LineItems();
			lTemp.id = menuIdTemp[i]; 
			lTemp.count = menuCountTemp[i];
			
			this.order_detail.add(lTemp);
		}								
	}
	
	public boolean isMatch_DeliveryDate(String dateStr)
	{
		return (dateStr.equals(this.delivery_date)); 
	}
		
	public boolean isMatch_DeliveryAddress(String addrStr)
	{
		return (addrStr.equals(this.delivery_address)); 
	}
		
	public boolean isMatch_name(String nameStr)
	{
		return (nameStr.equals(this.personal_info.getNameTemp())); 
	}
	
	public boolean isMatch_email(String emailStr)
	{
		return (emailStr.equals(this.personal_info.getEmailTemp())); 
	}
	
	public boolean isMatch_mobile(String mobileStr)
	{
		return (mobileStr.equals(this.personal_info.getMobileTemp())); 
	}
	
	public boolean isMatch_note(String noteStr)
	{
		return (noteStr.equals(this.note)); 
	}
	
	public boolean isMatch_MenuIds(String menuIds)
	{		
		boolean val = false;
		String[] menuIdTemp = menuIds.split(",");
		for(int i =0; i < menuIdTemp.length; i++)
			val = (menuIdTemp[i].equals(this.order_detail.get(i).id));
		
		return val; 
	}
	
	public boolean isMatch_MenuCount(String menuCounts)
	{		
		boolean val = false;
		String[] menuCountTemp = menuCounts.split(",");
		for(int i =0; i < menuCountTemp.length; i++)
			val = (menuCountTemp[i].equals(this.order_detail.get(i).count));
		
		return val; 
	}
	
	
	public int CreateOrder(OrderManager omTemp)
	{
		Order oMain = new Order();
		
		Map<String, String> itemsToAdd = new HashMap<String, String>();
	   itemsToAdd.put("deliveryDate", omTemp.delivery_date);
	   if(omTemp.delivery_address.contains(","))
	   {
		   String[] tempStrings = omTemp.delivery_address.split(",");
		   String tempStr = tempStrings[0];
		   for(int i = 1; i < tempStrings.length; i++)
			   tempStr = tempStr + " " + tempStrings[i];
		   
		   itemsToAdd.put("deliveryAddress", tempStr);
	   }
	   else
		   itemsToAdd.put("deliveryAddress", omTemp.delivery_address);
	   
	   itemsToAdd.put("name", omTemp.personal_info.getNameTemp());
	   itemsToAdd.put("email", omTemp.personal_info.getEmailTemp());
	   itemsToAdd.put("mobile", omTemp.personal_info.getMobileTemp());            
	   itemsToAdd.put("note", omTemp.note);
	   String menuIdStr = omTemp.order_detail.get(0).id;
	   String menuCountStr = omTemp.order_detail.get(0).count;	   
	   for(int i = 1; i < omTemp.order_detail.size(); i++)
	   {
		   menuIdStr = menuIdStr + "," + omTemp.order_detail.get(i).id;
		   menuCountStr = menuCountStr + "," + omTemp.order_detail.get(i).count;
	   }
	   
	   itemsToAdd.put("menuId", menuIdStr);
	   itemsToAdd.put("count", menuCountStr);
	     
	   /*itemsToAdd.put("menuId", "12,15");
	   itemsToAdd.put("count", "50,50");
	   */
	   
	   int oId = oMain.Create_Order(itemsToAdd);			
		
		return oId;		
	}
	
	public List<OrderManager> DisplayOrder()
	{
		List<OrderManager> om = new ArrayList<OrderManager>();		
		
		List<List<String>> orderDetails = new ArrayList<List<String>>();
		
		Order oMain = new Order();
		orderDetails = oMain.Display_Orders();
		
		for(List<String> menuRowOne : orderDetails)
		{
			OrderManager tempO  = new OrderManager();
			
			tempO.id = menuRowOne.get(0);
			tempO.orderDate = menuRowOne.get(1);
			tempO.delivery_date = menuRowOne.get(2);
			tempO.amount = menuRowOne.get(3);
			tempO.surcharge = menuRowOne.get(4);
			tempO.status = menuRowOne.get(5);
			tempO.email = menuRowOne.get(6);			
			
			om.add(tempO);
		}
		
		return om;
	}
	
	
	public OrderManager DisplayOrder(int oid)
	{
		OrderManager om = new OrderManager();		
		
		List<List<String>> orderDetails = new ArrayList<List<String>>();
		
		Order oMain = new Order();
		personal_info = new Customer();
		orderDetails = oMain.Display_Orders(oid);
		
		if(orderDetails == null)
		{
			om = null;
			return om;
		}
		
		for(List<String> menuRowOne : orderDetails)
		{	
			om.id = menuRowOne.get(0);		
			om.amount = menuRowOne.get(1);
			om.surcharge = menuRowOne.get(2);
			om.status = menuRowOne.get(3);			
			om.orderDate = menuRowOne.get(4);			
			om.delivery_date = menuRowOne.get(5);
			
			/*om.name = menuRowOne.get(6);
			om.email = menuRowOne.get(7);
			om.mobile = menuRowOne.get(8);
			*/
			Customer c1 = new Customer("from OrderManager");
			c1.setNameTemp(menuRowOne.get(6));
			c1.setEmailTemp(menuRowOne.get(7));
			c1.setMobileTemp(menuRowOne.get(8));
			om.personal_info = c1;
			/*om.ordered_by.setNameTemp(menuRowOne.get(6));
			om.ordered_by.setEmailTemp(menuRowOne.get(6));
			om.ordered_by.setMobileTemp(menuRowOne.get(6));
			*/
			om.delivery_address = menuRowOne.get(9);
			om.note = menuRowOne.get(10);
			
			om.order_detail = new ArrayList<LineItems>();
			String[] menuIds = menuRowOne.get(11).split("_");
			String[] menuItems = menuRowOne.get(12).split("_");
			String[] menuCount = menuRowOne.get(13).split("_");
			for(int i = 0; i < menuIds.length; i++)
			{
				List<String> orderDetailsTemp = new ArrayList<String>();
				orderDetailsTemp.add(menuIds[i]);
				orderDetailsTemp.add(menuItems[i]);
				orderDetailsTemp.add(menuCount[i]);
				LineItems l1 = new LineItems(orderDetailsTemp);
				
				om.order_detail.add(l1);
			}
			
			/*om.menuId = menuRowOne.get(11);
			om.menuItem = menuRowOne.get(12);
			om.count = menuRowOne.get(13);*/		
			//System.out.print(om.id);
			
		}
		
		return om;
	}
	
	
	public List<OrderManager> DisplayOrder(String date)
	{
		List<OrderManager> om = new ArrayList<OrderManager>();		
		
		List<List<String>> orderDetails = new ArrayList<List<String>>();
		
		Order oMain = new Order();
		orderDetails = oMain.Display_Orders(date);
		
		if(orderDetails == null)
		{
			om = null;
			return om;
		}
		
		for(List<String> menuRowOne : orderDetails)
		{
			OrderManager tempO  = new OrderManager();
			
			tempO.id = menuRowOne.get(0);
			tempO.orderDate = menuRowOne.get(1);
			tempO.delivery_date = menuRowOne.get(2);
			tempO.amount = menuRowOne.get(3);
			tempO.surcharge = menuRowOne.get(4);
			tempO.status = menuRowOne.get(5);
			tempO.email = menuRowOne.get(6);			
			
			om.add(tempO);
		}
		
		return om;
	}
	
	
	public Integer CancelOrder(int oId)
	{
		Order oMain = new Order();
		int idCancelled = -1;
		idCancelled = oMain.Cancel_Delivered_Order(oId, "Cancel");
		
		return idCancelled;
	}
	
	
	public double GetSurcharge()
	{
		Order o = new Order();
		double surcharge_ = o.Get_Surcharge();
		
		return surcharge_;
	}
	
	
	public void SetSurcharge(OrderManager om1)
	{
		Order o = new Order();
		double surcharge_ = Double.parseDouble(om1.surcharge);
		o.Modify_Surcharge(surcharge_);
	}
}



