import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCustomerClass.class, TestMenuClass.class, TestOrderClass.class })
public class AllTests {

	public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("AllTests");
	}
	
}
