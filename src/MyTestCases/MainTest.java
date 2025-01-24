package MyTestCases;

import java.util.ArrayList;
import java.util.List;

//import javax.swing.text.TableView.TableRow;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MainTest extends Parameters {

	@BeforeTest
	public void MySetyp() {
		Configration();

	}

	@BeforeMethod
	public void BeforeAnyTest() throws InterruptedException {
		Thread.sleep(2000);
	}

//////////////////////////////////////////////////////////////////////////////
	// Radio Button Example:
	@Test(priority = 1, description = "Radio Button", invocationCount = 3, enabled = true)
	public void RadioButton() {
		List<WebElement> MyRadioButtons = driver.findElements(By.className("radioButton"));

		int RandomRadio = rand.nextInt(3);
		WebElement SelectedRadio = MyRadioButtons.get(RandomRadio);
		SelectedRadio.click();
		// To Ensure that the Button Selected correctly :
		boolean RadioExpectedResult = SelectedRadio.isSelected();

		Assert.assertEquals(RadioExpectedResult, ActualResult1);

	}

//////////////////////////////////////////////////////////////////////////////
//CheckBox Example:

	@Test(priority = 2, description = "Checkbox", enabled = true)
	public void Checkbox() {
		js.executeScript("window.scrollTo(0,400)");
		List<WebElement> CheckBoxOptions = driver.findElements(By.xpath("\r\n" + "//input[@type=\"checkbox\"]"));
		int CheckBoxRandom = rand.nextInt(3);
		WebElement SelectedCheckBox = CheckBoxOptions.get(CheckBoxRandom);
		SelectedCheckBox.click();
		boolean ExpectedCheckBox = SelectedCheckBox.isSelected();

		// Hard Assertion:
		Assert.assertEquals(ExpectedCheckBox, ActualResult1);

	}

//////////////////////////////////////////////////////////////////////////////
//Dynamic DropDown Example:

	@Test(priority = 3, description = "Dynamic Dropdown", enabled = true)
	public void DynamicDropdown() throws InterruptedException {
		WebElement DynamicListInput = driver.findElement(By.id("autocomplete"));
		String[] countryCodes = { "US", "CA", "OM", "BR", "AR", "FR", "DE", "IT", "ES", "AM" };

		int RandomCode = rand.nextInt(countryCodes.length);
		DynamicListInput.sendKeys(countryCodes[RandomCode]);

		// it will press an arrow down + enter to select the first item from the list:
		Thread.sleep(2000);
		DynamicListInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

		// we need to take the country name that Selenium already selected
		// To convert object to String we use :(String)
		String DataInsideTheInput = (String) js.executeScript("return arguments[0].value", DynamicListInput);
		String UpdateCountryName = DataInsideTheInput.toLowerCase();

		boolean ExpectedDynmicList = UpdateCountryName.contains(countryCodes[RandomCode].toLowerCase());

		Assert.assertEquals(ExpectedDynmicList, ActualResult1);

	}

//////////////////////////////////////////////////////////////////////////////
//Static DropDown Example:

	@Test(priority = 4, description = "Static Dropdown", enabled = true)
	public void StaticDropdown() {
		// "Select" have a special characters :

		WebElement StaticList = driver.findElement(By.id("dropdown-class-example"));
		Select sel = new Select(StaticList);

		// #Without Assertion :
//		//int RandomStaticListOption=rand.nextInt(1, 4);
//		Thread.sleep(2000);
//		 sel.selectByIndex(RandomStaticListOption);
		// Another Ways :
		// sel.selectByValue("option3");
		// sel.selectByVisibleText("Selenium");

		// #If we want to use the assertion :

		List<WebElement> AllOptions = sel.getOptions();
		int RandomStaticListOption = rand.nextInt(1, AllOptions.size());

		String ExpectedSelect = AllOptions.get(RandomStaticListOption).getText();

		sel.selectByIndex(RandomStaticListOption);
		String ActualSelect = sel.getFirstSelectedOption().getText();

		Assert.assertEquals(ExpectedSelect, ActualSelect);

	}

//////////////////////////////////////////////////////////////////////////////
//Switch Window Example:

	@Test(priority = 5, description = "Switch Window ", enabled = true)
	public void SwitchWindow() {
		// We want to click on "technologies" Button in codenbox page
		js.executeScript("window.scrollTo(0,700)");
		WebElement OpenWindowButton = driver.findElement(By.id("openwindow"));
		OpenWindowButton.click();

		// To Get all opening windows:
		List<String> WindowHandels = new ArrayList<String>(driver.getWindowHandles());
		// switch to the other window
		driver.switchTo().window(WindowHandels.get(1));
		// To click on the Button in the other window:
		WebElement technologiesButton = driver.findElement(By.id("menu-item-9675"));
		technologiesButton.click();

		System.out.println("Hello from the Second Window : " + driver.getTitle());
		driver.close();
		// Back to first Window
		driver.switchTo().window(WindowHandels.get(0));
		System.out.println("Hello from the First Window : " + driver.getTitle());

	}

/////////////////////////////////////////////////////////////////////////////
//Switch Tab Example:

	@Test(priority = 6, description = "Switch Tab ", enabled = true)
	public void SwitchTab() {
		js.executeScript("window.scrollTo(0,700)");
		WebElement OpenTabButton = driver.findElement(By.id("opentab"));
		OpenTabButton.click();

		List<String> windowsHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(windowsHandels.get(1));
		driver.close();
		driver.switchTo().window(windowsHandels.get(0));
		System.out.println(driver.getTitle());

	}

/////////////////////////////////////////////////////////////////////////////
//Switch To Alert Example:

	@Test(priority = 7, description = "Switch To Alert ", enabled = true)
	public void SwitchAlert() throws InterruptedException {

		js.executeScript("window.scrollTo(0,800)");
		WebElement AlertInput = driver.findElement(By.id("name"));
		AlertInput.sendKeys("Shahd Zeyad");
		WebElement AlertButton = driver.findElement(By.id("alertbtn"));
		WebElement ConfirmButton = driver.findElement(By.id("confirmbtn"));

		AlertButton.click();

		// To press" OK " in the alert:
		driver.switchTo().alert().accept();
		ConfirmButton.click();

		driver.switchTo().alert().dismiss();

	}

/////////////////////////////////////////////////////////////////////////////
//Web Table Example:

	@Test(priority = 8, description = "Web Table/play with the data of the column ", enabled = false)
	public void WebTable() {

		WebElement Table = driver.findElement(By.id("product"));

		List<WebElement> TableRows = Table.findElements(By.tagName("tr"));

		// To get the "Price" only in each row :
		for (int i = 1; i < TableRows.size(); i++) {
			int TotalTdInEachRow = TableRows.get(i).findElements(By.tagName("td")).size();

			System.out.println(TableRows.get(i).findElements(By.tagName("td")).get(TotalTdInEachRow - 1).getText());
		}

	}

/////////////////////////////////////////////////////////////////////////////
//Calendar Example:

	@Test(priority = 9, description = "Calendar/open calendar in a new tab ", enabled = true)
	public void Calendar() {
		js.executeScript("window.scrollTo(0,1800)");
		WebElement BookingButton = driver.findElement(By.linkText("Booking Calendar"));
		BookingButton.click();

		// Another Way :
		// WebElement BookingButton=driver.findElement(By.partialLinkText("Booking"));

		// To switch to calender Page:
		List<String> WindowHandels = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(WindowHandels.get(1));

		int totalAvailbleDates = driver.findElements(By.className("date_available")).size();

		driver.findElements(By.className("date_available")).get(0).click();
		driver.findElements(By.className("date_available")).get(totalAvailbleDates - 1).click();

	
		driver.close();
		driver.switchTo().window(WindowHandels.get(0));
	}

/////////////////////////////////////////////////////////////////////////////
//iFrame Example:

	@Test(priority = 13, description = "Switch to iFrame inside the main page", enabled = true)
	public void iFrame() {
		js.executeScript("window.scrollTo(0,2500)");
		WebElement IFRAME = driver.findElement(By.id("courses-iframe"));
		// Many Ways to switch to the Frame :
		// 1: By WebElement
		driver.switchTo().frame(IFRAME);
		// 2:By index -----> driver.switchTo().frame(0);
		// 3:By id -----> driver.switchTo().frame("courses-iframe");

//		String theTextinsideTheFrame = driver.findElement(By.xpath("//*[@id=\"ct_text_editor- 		be8c5ad\"]/div/div/p")).getText();
//
//		System.out.println(theTextinsideTheFrame );

	}

/////////////////////////////////////////////////////////////////////////////
//Element Displayed Example:

	@Test(priority = 10, description = " Hide and Show buttons", enabled = true)
	public void ElementDisplaye()  {
		// I want to use Soft Assertion here:
		SoftAssert myAssert = new SoftAssert();

		js.executeScript("window.scrollTo(0,1800)");
		WebElement HideButton = driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton = driver.findElement(By.id("show-textbox"));

		WebElement Input = driver.findElement(By.id("displayed-text"));

		Input.sendKeys(TestData);
		HideButton.click();

		// Note :// hard assert---> :
		// Assert.assertEquals(theTEXXXXXTINPUT.isDisplayed(), true);

		myAssert.assertEquals(Input.isDisplayed(), ActualResult2);

		
		ShowButton.click();
		myAssert.assertEquals(Input.isDisplayed(), ActualResult1);

		myAssert.assertAll();

	}
/////////////////////////////////////////////////////////////////////////////
	// Enabled/Disabled Example:

	@Test(priority = 11, description = "Enabled/Disabled Example", enabled = true)
	public void EnabledDisabled()   {
		js.executeScript("window.scrollTo(0,1900)");
		WebElement DisabledButton = driver.findElement(By.id("disabled-button"));
		WebElement EnabledButton = driver.findElement(By.id("enabled-button"));

		WebElement Input = driver.findElement(By.id("enabled-example-input"));

		Input.sendKeys(TestData + "& Disabled");
		DisabledButton.click();

		Assert.assertEquals(Input.isEnabled(), ActualResult2);

		EnabledButton.click();
		Input.clear();
		Input.sendKeys("Now Enabled");
		Assert.assertEquals(Input.isEnabled(), ActualResult1);
	}

/////////////////////////////////////////////////////////////////////////////
//Mouse Hover Example:

	@Test(priority = 12, description = "The hover to certain elemente", enabled = true)
	public void MouseHover() throws InterruptedException {
		js.executeScript("window.scrollTo(0,1900)");
		WebElement mouseHoverBox = driver.findElement(By.id("mousehover"));

		action.moveToElement(mouseHoverBox).perform();
		

	
		driver.findElement(By.partialLinkText("Relo")).click();
	}

	@AfterTest
	public void AfterTheTestEnd() throws InterruptedException {
		Thread.sleep(4000);
		driver.close();

	}

}
