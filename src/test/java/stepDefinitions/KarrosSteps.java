package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pageFactory.Karros_PF;

public class KarrosSteps {
	WebDriver driver;
	Karros_PF karros;
	int id;
	String title;

	@Given("open the browser")
	public void open_the_browser() {
		String projectPath = System.getProperty("user.dir");
		System.out.print(projectPath);
		
		System.setProperty("webdriver.chrome.driver", projectPath+"/Drivers/chromedriver");
		
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}

	@And("user is on login page")
	public void user_is_on_login_page() {

		driver.navigate().to("http://ktvn-test.s3-website.us-east-1.amazonaws.com/ ");
	}

	@When("^user inputs (.*) and (.*)$")
	public void user_inputs_username_and_password(String username, String password) throws InterruptedException {
		karros = new Karros_PF(driver);

		if (!username.isEmpty())
			karros.enterUsername(username);
		else
			System.out.println("The username is empty");

		if (!password.isEmpty())
			karros.enterPassword(password);
		else
			System.out.println("The password is empty");

		Thread.sleep(1000);
	}

	@And("user clicks Login button")
	public void user_clicks_login_button() throws InterruptedException {

		karros.ClickLoginButton();

		Thread.sleep(1000);
	}

	@And("user verifies filter Student Access Request with Inactive status")
	public void user_navigates_to_parent_portal_page() throws InterruptedException {
		karros.ClickFilterButton();
		karros.ClickRequestStatusDropdown();
		Thread.sleep(1000);
	}

	@Then("user closes the browser")
	public void user_closes_the_browser() throws InterruptedException {
		karros.CloseBrowser();
	}

	// ====== Sentence 2 ===== //

	@Then("user clicks on {string} arrow button of {string} column in the table")
	public void user_clicks_on_arrow_button_of_column_in_the_table(String arrowName, String header) throws Exception {
		karros.ClickOnTheArrowButtonOfTable("dsc", "First Name");
	}

	@And("user verifies sorting of {string} column is sorted in {string} order")
	public void user_verifies_sorting_of_column_is_sorted_in_order(String header, String orderType) throws Exception {
		karros.VerifySortingOfColumnIsSortedInOrder("first name", "dsc");
	}

	@Then("after that user clicks on {string} arrow button of {string} column in the table")
	public void after_that_user_clicks_on_arrow_button_of_column_in_the_table(String arrowName, String header)
			throws Exception {
		karros.ClickOnTheArrowButtonOfTable("asc", "First Name");
	}

	@And("user continue verifies sorting of {string} column is sorted in {string} order")
	public void user_continue_verifies_sorting_of_column_is_sorted_in_order(String header, String orderType) {
		karros.VerifySortingOfColumnIsSortedInOrder("first name", "asc");
	}

	@Then("browser is closed")
	public void browser_is_closed() {
		karros.CloseBrowser();
	}
	
	// ====== Sentence 3 ===== //

	@Given("user connects to API and get data")
	public void user_connects_to_api_and_get_data() {
		// connect to API link
		RestAssured.baseURI = "https://my-json-server.typicode.com/typicode/demo/posts/";

		RequestSpecification httpRequest = RestAssured.given();

		Response results = httpRequest.get("/1");

		// Verify the status code
		int statusCode = results.getStatusCode();

		if (statusCode == 200)
			System.out.println("Correct status code returned: " + statusCode);
		else
			System.out.println("Verify the status code failed. The actual code is: " + statusCode);

		// Get Id, title and validation
		JsonPath jsonPathEvaluator = results.jsonPath();

		int id = jsonPathEvaluator.get("id");

		if (id == 1)
			System.out.println("Correct ID returned: " + id);
		else
			System.out.println("Verify the ID failed. The actual ID is: " + id);

		String title = jsonPathEvaluator.get("title");
		System.out.println("title: " + title);

		if (title.equals("Post 1"))
			System.out.println("Correct Title returned: " + title);
		else
			System.out.println("Verify the Title failed. The actual Title is: " + title);
	}
	
	@Then("close the browser")
	public void close_the_browser() {
		driver.close();
		driver.quit();
	}
}
