package MyTestCases;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Parameters {
//All Declarations :
	String WebsiteName = "https://codenboxautomationlab.com/practice/";
	WebDriver driver = new ChromeDriver();
	Random rand=new Random();
	boolean ActualResult1=true;
	boolean ActualResult2=false;
	JavascriptExecutor js= (JavascriptExecutor) driver ;
	String TestData="I’m here";
	//Related to the test(priority =12) :
	Actions action=new Actions(driver);

	public void Configration() {
		driver.get(WebsiteName);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
	}

}
