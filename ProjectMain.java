import java.util.*;


public class ProjectMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Menu m1 = new Menu();
		Admin a = new Admin();
		List<String> s = new ArrayList<String>();
		s.add("Lasagna");
		s.add("2.49");
		s.add("6");
		s.add("Organic_Vegetarian");
		
		List<String> ss = Arrays.asList("Soup2", "99", "3", "Veg_InOrganic");
		MenuManager mm = new MenuManager(ss);
		String[] cat = {"Veg", "InOrganic"};
	    mm.isMatch_Categories(cat);
	    System.out.println();
		
		OrderManager om = new OrderManager();
		List<OrderManager> omList = new ArrayList<OrderManager>();
		
		//om.DisplayOrder(1); 
		
		//Reports r = new Reports(804);
		//System.out.println(r._endDate);
		//r.Get_Report();
		//System.out.println(r.rName1);
		//a.Order_Delivered(1);
		//a.Modify_MenuPrice(13, 2199);
		/*double surcharge = a.Get_Surcharge();
		System.out.println(surcharge);
		a.Modify_Surcharge(0.1);
		System.out.println(a.Get_Surcharge());*/
		
		//int idCreated = a.Create_Menu(s);
		//System.out.println(idCreated);
		
		/*int idCreated = m1.Create_Menu(s);
		
		System.out.println(idCreated); */
		
		/*List<List<String>> menuDisplay = m1.Display_Menu();
		
		for(List<String> menuRowOne : menuDisplay)
	   {
			
		    for(int j = 0; j < menuRowOne.size(); j++)
		    	System.out.print(menuRowOne.get(j) + ", ");     
		     
		    System.out.println();
	   }*/
		
		/*int idModified = m1.Modify_Menu(18, s);
		
		List<List<String>> menuDisplay1 = m1.Display_Menu(15);
		
		for(List<String> menuRowOne : menuDisplay1)
	   {
			
		    for(int j = 0; j < menuRowOne.size(); j++)
		    	System.out.print(menuRowOne.get(j) + ", ");     
		     
		    System.out.println();
	   }
*/
		Order o = new Order();
		//o.Modify_Surcharge(200);
		//System.out.println(o.Cancel_Delivered_Order(4, "Cancel"));
	   Map<String, String> itemsToAdd = new HashMap<String, String>();
	   itemsToAdd.put("deliveryDate", "20160411");
	   itemsToAdd.put("deliveryAddress", "151 Madison");   
	   itemsToAdd.put("name", "qwerty");
	   itemsToAdd.put("email", "qwerty@qwerty.com");
	   itemsToAdd.put("mobile", "99099");            
	   itemsToAdd.put("note", "drop it at the back gate");
	   itemsToAdd.put("menuId", "12,15");
	   itemsToAdd.put("count", "50,50");
	   //test holidays
	   //int i = o.Create_Order(itemsToAdd);
	   //int i = o.Modify_Order(4, itemsToAdd);
	   //System.out.println(i);
	   /*
	   Customer c = new Customer();
	   List<List<String>> customerList = new ArrayList<List<String>>();
	   customerList = c.Get_Customer();
	   
	   for(i = 0; i < customerList.size(); i++)
	   {
		   for(int j = 0; j < customerList.get(i).size(); j++)
			   System.out.print(customerList.get(i).get(j) + " ");
		   System.out.println();
	   }
*/	
	/*Reports r = new Reports();
	
	List<String> reportDetails = new ArrayList<String>();
	reportDetails = r.Get_Report("20160301", "20160331");
	
	for(int j = 0; j < reportDetails.size(); j++)
		   System.out.print(reportDetails .get(j) + " ");
	*/
	}
	
}
