package pageFactory;

import org.junit.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class KarrosAPI_PF {

	public void VerifyStatusCode(Response response, int code) {

		int statusCode = response.getStatusCode();
		System.out.println("code: " + statusCode);
		if (statusCode == code)
			System.out.println("Verify code passed");
		else
			System.out.println("Verify code failed");
	}

	public void VerifyElementOfJson(Response response, Object element, Object validate) {

		JsonPath jsonPathEvaluator = response.jsonPath();

		Object obj = jsonPathEvaluator.getJsonObject((String) element);
		System.out.println("Content of Element: " + element);

		// Validate the response
		Assert.assertEquals(obj, validate);
		System.out.println("Verified " + obj + " passed");
	}
}
