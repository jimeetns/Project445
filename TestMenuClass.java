import junit.framework.TestCase;
import java.io.*;
import org.junit.*;
import java.util.*;

public class TestMenuClass extends TestCase{
	
	/*OutputStream outContent = new ByteArrayOutputStream();
	  OutputStream errContent = new ByteArrayOutputStream();  
	*/  private List<String> s = Arrays.asList("Soup2", "99", "3", "Veg_InOrganic");

	  /*@Before
	  public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	  }
	  
	  @After
	  public void cleanUpStreams() {
	    System.setOut(null);
	    System.setErr(null);
	  } */ 
	  
	  @Test
	  public void testIsMatchName() {
	    MenuManager mmTest = new MenuManager(s);	        
	    assertTrue(mmTest.isMatch_Name("Soup2"));    
	  }
	  
	  @Test
	  public void testIsMatchPrice() {
	    MenuManager mmTest = new MenuManager(s);	        
	    assertTrue(mmTest.isMatch_Price("99"));    
	  }
	  
	  @Test
	  public void testIsMatchMinQty() {
	    MenuManager mmTest = new MenuManager(s);	        
	    assertTrue(mmTest.isMatch_MinQty("3"));    
	  }
	  
	  @Test
	  public void testIsMatchCategory() {
	    MenuManager mmTest = new MenuManager(s);
	    String[] cat = {"Veg", "InOrganic"};
	    assertTrue(mmTest.isMatch_Categories(cat));    
	  }
	  
	  
	  @Test
	  public void testCreateMenu() {
	    MenuManager mmTest = new MenuManager(s);
	    int id1 = mmTest.CreateMenu(mmTest);
	    
	    MenuManager mmTest1 = new MenuManager(s);
	    int id2 = mmTest.CreateMenu(mmTest1);
	    
	    assertTrue(id2 == id1 + 1);    
	  }
	  
	  
	  @Test
	  public void testDisplayMenu() {
	    MenuManager mmTest = new MenuManager(s);
	    int id1 = mmTest.CreateMenu(mmTest);
	    
	    List<MenuManager> mmList = new ArrayList<MenuManager>();
	    mmList = mmTest.DisplayMenu();
	    assertEquals(id1, Integer.parseInt(mmList.get(mmList.size()-1).id));
	    assertTrue(s.get(0).equals(mmList.get(mmList.size()-1).name));
	    assertTrue(Double.parseDouble(s.get(1)) == Double.parseDouble((mmList.get(mmList.size()-1).price_per_person)));
	  }
	  
	  
	  @Test
	  public void testDisplayMenuById() {
	    MenuManager mmTest = new MenuManager(s);
	    int id1 = mmTest.CreateMenu(mmTest);
	    
	    List<MenuManager> mmList = new ArrayList<MenuManager>();
	    mmList = mmTest.DisplayMenu(id1);
	    assertEquals(id1, Integer.parseInt(mmList.get(mmList.size()-1).id));
	    assertTrue(s.get(0).equals(mmList.get(mmList.size()-1).name));
	    assertTrue(Double.parseDouble(s.get(1)) == Double.parseDouble((mmList.get(mmList.size()-1).price_per_person)));
	  }
	  
	  @Test
	  public void testModifyMenuPrice() {
	    MenuManager mmTest = new MenuManager(s);
	    int id1 = mmTest.CreateMenu(mmTest);
	    String modPrice = "200";
	    mmTest.price_per_person = modPrice;
	    int modId = mmTest.ModifyMenuPrice(mmTest, id1);
	    
	    List<MenuManager> mmList = new ArrayList<MenuManager>();
	    mmList = mmTest.DisplayMenu(id1);	    
	    assertEquals(Double.parseDouble(modPrice), Double.parseDouble((mmList.get(mmList.size()-1).price_per_person)));	    
	  }
	  

}
