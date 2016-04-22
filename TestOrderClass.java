import junit.framework.TestCase;
import java.io.*;
import java.text.SimpleDateFormat;

import org.junit.*;
import java.util.*;

public class TestOrderClass extends TestCase{
	
	  private List<String> s = Arrays.asList("20160501", "10 West 31st ST, Chicago IL 60616", "Virgil B", "virgil@example.com", "312-456-7890", "Room SB-214", "12,15", "25,25");
	  
	  @Test
	  public void testIsMatchDeliveryDate() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_DeliveryDate(s.get(0)));    
	  }
	  
	  @Test
	  public void testIsMatchDeliveryAddress() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_DeliveryAddress(s.get(1)));    
	  }
	  
	  @Test
	  public void testIsMatchDeliveryName() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_name(s.get(2)));    
	  }
	  
	  @Test
	  public void testIsMatchDeliveryEmail() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_email(s.get(3)));    
	  }

	  @Test
	  public void testIsMatchDeliveryMobile() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_mobile(s.get(4)));    
	  }

	  @Test
	  public void testIsMatchDeliveryNote() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_note(s.get(5)));    
	  }
	  
	  @Test
	  public void testIsMatchDeliveryMenuIds() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_MenuIds(s.get(6)));    
	  }
	  
	  @Test
	  public void testIsMatchDeliveryMenuCounts() {
	    OrderManager omTest = new OrderManager(s);	        
	    assertTrue(omTest.isMatch_MenuCount(s.get(7)));    
	  }
	  
	  
	  @Test
	  public void testCreateOrder() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    OrderManager omTest1 = new OrderManager(s);
	    int id2 = omTest.CreateOrder(omTest1);
	    
	    assertTrue(id2 == id1 + 1);    
	  }
	  
	  
	  @Test
	  public void testDisplayOrder() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    List<OrderManager> omList = new ArrayList<OrderManager>();
	    omList = omTest.DisplayOrder();
	    assertEquals(id1, Integer.parseInt(omList.get(omList.size()-1).id));
	    assertTrue(s.get(0).equals(omList.get(omList.size()-1).delivery_date));
	    
	    Calendar calendar = Calendar.getInstance();
		Date date1 =  calendar.getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    String dateString = formatter.format(date1);	    
	    assertTrue(dateString.equals(omList.get(omList.size()-1).orderDate));
	    
	    //int x = omList.size()-1;	    
	    String status_ = "Open";
	    assertTrue(status_.equals(omList.get(omList.size()-1).status));
	  }
	  
	  
	  @Test
	  public void testDisplayOrderByDate() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    List<OrderManager> omList = new ArrayList<OrderManager>();
	    omList = omTest.DisplayOrder(s.get(0));
	    assertEquals(id1, Integer.parseInt(omList.get(omList.size()-1).id));
	    assertTrue(s.get(0).equals(omList.get(omList.size()-1).delivery_date));
	    
	    Calendar calendar = Calendar.getInstance();
		Date date1 =  calendar.getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    String dateString = formatter.format(date1);	    
	    assertTrue(dateString.equals(omList.get(omList.size()-1).orderDate));
	    	    
	    String status_ = "Open";
	    assertTrue(status_.equals(omList.get(omList.size()-1).status));
	  }
	  
	  
	  @Test
	  public void testDisplayOrderByOid() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    OrderManager omList = new OrderManager();
	    omList = omTest.DisplayOrder(id1);
	    assertEquals(id1, Integer.parseInt(omList.id));
	    assertTrue(s.get(0).equals(omList.delivery_date));
	    
	    Calendar calendar = Calendar.getInstance();
		Date date1 =  calendar.getTime();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    String dateString = formatter.format(date1);	    
	    assertTrue(dateString.equals(omList.orderDate));   
	    	    
	    String status_ = "Open";
	    assertTrue(status_.equals(omList.status));
	    
	    String[] menuIdTemp = s.get(6).split(",");
	    for(int i = 0; i < menuIdTemp.length; i++)
	    {
	    	assertTrue(menuIdTemp[i].equals(omList.order_detail.get(i).id));
	    }
	    
	    String[] menuCountTemp = s.get(7).split(",");
	    for(int i = 0; i < menuCountTemp.length; i++)
	    {
	    	assertTrue(menuCountTemp[i].equals(omList.order_detail.get(i).count));
	    }	    
	  }

	  
	  @Test
	  public void testCancelOrder() {
	    OrderManager omTest = new OrderManager(s);
	    int id1 = omTest.CreateOrder(omTest);
	    
	    OrderManager omList = new OrderManager();
	    omList = omTest.DisplayOrder(id1);
	    String status_ = "Open";
	    assertTrue(status_.equals(omList.status));
	    
	    omTest.CancelOrder(id1);
	    omList = omTest.DisplayOrder(id1);
	    status_ = "Cancel";	    
	    assertTrue(status_.equals(omList.status));
	  }
	  
	  
	  @Test
	  public void testSurcharge() {
	    OrderManager omTest = new OrderManager();
	    double surcharge_ = omTest.GetSurcharge();
	    
	    double newSurcharge = surcharge_ + 100;
	    omTest.surcharge = String.valueOf(newSurcharge);
	    omTest.SetSurcharge(omTest);
	    
	    surcharge_ = omTest.GetSurcharge();
	    
	    assertEquals(newSurcharge, surcharge_);
	  }
}
