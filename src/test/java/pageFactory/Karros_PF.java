package pageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import util.webUtils;

public class Karros_PF {

	WebDriver driver;

	@FindBy(xpath = "//input[@name='email']")
	@CacheLookup
	WebElement txt_username_xpath;

	@FindBy(xpath = "//input[@name='password']")
	@CacheLookup
	WebElement txt_password_xpath;

	@FindBy(xpath = "//a[@class='col-login__btn']")
	@CacheLookup
	WebElement btn_login_xpath;

	@FindBy(xpath = "//a[contains(text(), '[ Parent Portal ]')")
	@CacheLookup
	WebElement ParentPortalText_xpath;

	@FindBy(xpath = "//button[@class='btn btn-filter module_grid__btn_filter btn btn-default']")
	@CacheLookup
	WebElement btn_Filters_xpath;

	@FindBy(xpath = "//button[@class='btn-filter btn btn-default']")
	@CacheLookup
	WebElement btn_ApplyFilters_xpath;

	@FindBy(xpath = "//select[@name='status']")
	@CacheLookup
	WebElement dp_RequestStatus_xpath;

	@FindBy(xpath = "//a[text()='Inactive']")
	@CacheLookup
	WebElement InactivateStatus_xpath;

	public Karros_PF(WebDriver driver) {
		this.driver = driver;

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(factory, this);
	}

	public void enterUsername(String username) {
		txt_username_xpath.sendKeys(username);
	}

	public void enterPassword(String password) {
		txt_password_xpath.sendKeys(password);
	}

	public void ClickLoginButton() {
		btn_login_xpath.click();
	}

	public void VerifyParentPortalpage() {
		ParentPortalText_xpath.isDisplayed();
	}

	public void ClickFilterButton() {
		btn_Filters_xpath.click();
	}

	public void ClickRequestStatusDropdown() throws InterruptedException {
		// dp_RequestStatus.click();
		Select drpStatus = new Select(dp_RequestStatus_xpath);

		Thread.sleep(1000);

		drpStatus.selectByVisibleText("Inactive");

		Thread.sleep(1000);

		btn_ApplyFilters_xpath.click();

		Thread.sleep(1000);

		if (InactivateStatus_xpath.isDisplayed()) {
			System.out.println("Filter successfully");
		} else {
			System.out.println("Failed to filter");
		}

	}

	public void ClickOnTheArrowButtonOfTable(String arrowName, String header) throws InterruptedException {
		String headerName = "";
		String xpathArrow = "";

		switch (header.toLowerCase()) {
		case "first name":
			// Sorting functionality for First Name
			headerName = "First Name";
			break;
//		case "last name":
//			// Sorting functionality for First Name
//			headerName = "Last Name";
//			break;
		}

		// Select column name
		xpathArrow = "//th[@title='" + headerName + "']";

		driver.findElement(By.xpath(xpathArrow)).click();

		if (arrowName.toLowerCase().equals("dsc")) {
			// Sorting Z->A
			xpathArrow = "//th[@title='" + headerName
					+ "']/span[@class='order']/span[@class='caret table-sorting-clean-caret']";
			System.out.println(xpathArrow);
			driver.findElement(By.xpath(xpathArrow)).isDisplayed();
			System.out.println("Sorting is descending");


		} else if (arrowName.toLowerCase().equals("asc")) {
			// Sorting A->Z
			xpathArrow = "//th[@title='" + headerName
					+ "']/span[@class='order dropup']/span[@class='caret table-sorting-clean-caret']";
			System.out.println(xpathArrow);
			driver.findElement(By.xpath(xpathArrow)).isDisplayed();
			System.out.println("Sorting is Ascending");


		} else {
			System.out.println(xpathArrow);
			String actual = "Cannot find arrow name: " + arrowName;
			String expected = "The arrow name: " + arrowName + " is shown";
			webUtils.performTextValidattion(actual, expected);
		}

		Thread.sleep(1000);
	}

	public void VerifySortingOfColumnIsSortedInOrder(String header, String orderType) {
		int timeOut = 10;
		int n = 0;
		int headerName = 0;
		
		List<WebElement> lstWE = null;

		do {
			lstWE = driver.findElements(By.xpath("//thead//th"));
			n += 1;
		} while (n < timeOut && lstWE.size() == 0);

		System.out.println(lstWE);

		if (lstWE.size() > 0) {
			switch (header.toLowerCase()) {
			case "first name":
				// Sorting functionality for First Name
				headerName = 6;
				checkSortedForStringByOrderType(headerName, orderType);
				break;
//			case "last name":
//				// Sorting functionality for Last Name
//				headerName = 7;
//				checkSortedForStringByOrderType(headerName, orderType);
//				break;
//			default:
//				break;
			}
		}
	}

	public void checkSortedForStringByOrderType(int headerName, String orderType) {
		String msg = "Sorting functionality is working correctly for column index: ";

		List<WebElement> lstWE = null;
		List<String> results = new ArrayList<String>();

		lstWE = driver.findElements(By.xpath("//tbody//tr"));

		if (lstWE.size() > 0) {
			int numberCol = driver.findElements(By.xpath("//tbody//td")).size();
			int iNum = 0;

			if (numberCol > 0) {
				for (WebElement e : lstWE) {
					String xpathValue = "//tbody//td[@tabindex='" + String.valueOf(headerName + iNum) + "']";
					String value = e.findElement(By.xpath(xpathValue)).getText().trim();
					results.add(value);
					iNum = iNum + 10;
				}
			}
		}

		if (orderType.toLowerCase().equals("dsc")) {

			if (checkListOfStringIsSorted(results, "dsc")) {
				webUtils.performTextValidattion(msg + headerName, msg + headerName);
			}
		} else {
			if (checkListOfStringIsSorted(results, "asc")) {
				webUtils.performTextValidattion(msg + headerName, msg + headerName);
			}
		}
	}

	private boolean checkListOfStringIsSorted(List<String> lstStr, String orderType) {
		lstStr.replaceAll(String::toUpperCase);
		System.out.println("chl String: " + lstStr);

		if (lstStr.size() > 2) {
			if (orderType.toLowerCase().equals("asc")) {
				for (int i = 0; i < lstStr.size() - 1; i++) {
					if (lstStr.get(i + 1).compareTo(lstStr.get(i)) < 0)
						return false;
					else if (lstStr.get(i).compareTo(lstStr.get(i + 1)) < 0)
						return false;
				}
			} else {
				for (int i = 0; i < lstStr.size() - 1; i++) {
					if (lstStr.get(i + 1).compareTo(lstStr.get(i)) < 0)
						return false;
					else if (lstStr.get(i).compareTo(lstStr.get(i + 1)) < 0)
						return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public void CloseBrowser() {
		driver.close();
		driver.quit();
	}

}
