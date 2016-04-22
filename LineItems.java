import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;
import java.text.*;


public class LineItems {
	
	public String id;
	public String name;
	public String count;
	
	public LineItems()
	{
		
		
	}
	
	public LineItems(List<String> orderDetailsTemp) {
		// TODO Auto-generated constructor stub
		this.id = orderDetailsTemp.get(0);
		this.name = orderDetailsTemp.get(1);
		this.count = orderDetailsTemp.get(2);
	}

}
