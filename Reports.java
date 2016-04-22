import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;

public class Reports {
	
	public String _startDate;
	public String _endDate;
	public String _orderCount;
	public String _amountTotal;
	public String _surchargeTotal;
	public List<Map<String, String>> reportNames;
	public List<OrderManager> omList;
	
	public Reports()
	{
		reportNames = new ArrayList<Map<String, String>>();
		Map<String, String>tempMap = new HashMap<String, String>();
		tempMap.put("id", "801");
		tempMap.put("name", "Orders to deliver today");
		reportNames.add(tempMap);
		
		Map<String, String>tempMap1 = new HashMap<String, String>();
		tempMap1.put("id", "802");
		tempMap1.put("name", "Orders to deliver tomorrow");
		reportNames.add(tempMap1);
		
		Map<String, String>tempMap2 = new HashMap<String, String>();
		tempMap2.put("id", "803");
		tempMap2.put("name", "Revenue Report");
		reportNames.add(tempMap2);
		
		Map<String, String>tempMap3 = new HashMap<String, String>();
		tempMap3.put("id", "804");
		tempMap3.put("name", "Orders Delivery Report");
		reportNames.add(tempMap3);
	}
	
	public Reports(int rId)
	{		
	}
		
	
	public List<Reports> Get_Report(int rId)
	{
		List<Reports> r1 = new ArrayList<Reports>();
		List<String> tempList = new ArrayList<String>();
		
		if(rId == 803)
		{
			tempList = Get_Report_803();
			Reports rTemp = new Reports(rId);
			rTemp._startDate = tempList.get(0);
			rTemp._endDate = tempList.get(1);
			rTemp._orderCount = tempList.get(2);
			rTemp._amountTotal = tempList.get(3);
			rTemp._surchargeTotal = tempList.get(4);
			
			r1.add(rTemp);
		}		
		
		if(rId == 801 || rId == 802)
		{	
			Calendar calendar = Calendar.getInstance();
						
			if(rId == 802)			
				calendar.add(calendar.DAY_OF_YEAR, 1);
							
			Date date1 =  calendar.getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		    String dateString = formatter.format(date1);
		    
		    Reports rTemp = new Reports(rId);
		    OrderManager om = new OrderManager();
		    rTemp.omList = om.DisplayOrder(dateString);
		    r1.add(rTemp);
		}
		
		return r1;
	}
	
	
	public List<String> Get_Report_803()
	{	
		List<String> reportDetails = new ArrayList<String>();
		List<List<String>> reportDetailsTemp = new ArrayList<List<String>>();
		Order o = new Order();
		
		reportDetailsTemp = o.Display_Orders();
		int orderCount = 0;
		double amount = 0;
		double surcharge = 0;
		
		for(int i = 0; i < reportDetailsTemp.size(); i++)
		{
			if(!reportDetailsTemp.get(i).get(5).equals("Cancel"))
			{
				orderCount++;
				amount = amount + Double.parseDouble(reportDetailsTemp.get(i).get(3));
				surcharge = surcharge + Double.parseDouble(reportDetailsTemp.get(i).get(4));
			}			
		}
		
		String startDate = "20160301";
		reportDetails.add(startDate);
		
		Integer endDate = 0;
		for(int i = 0; i < reportDetailsTemp.size(); i++)
			if(Integer.parseInt(reportDetailsTemp.get(i).get(1)) > endDate)
				endDate = Integer.parseInt(reportDetailsTemp.get(i).get(1));
		reportDetails.add(String.valueOf(endDate));
		
		reportDetails.add(String.valueOf(orderCount));
		reportDetails.add(String.valueOf(amount));
		reportDetails.add(String.valueOf(surcharge));
		
		return reportDetails;
	}
	
	
	public List<Reports> Get_Report(int rId, String deliveryDate)
	{
		List<Reports> r1 = new ArrayList<Reports>();
		List<String> tempList = new ArrayList<String>();		
	    
	    Reports rTemp = new Reports(rId);
	    OrderManager om = new OrderManager();
	    rTemp.omList = om.DisplayOrder(deliveryDate);
	    r1.add(rTemp);
	
		return r1;
	}	
	
	
}
