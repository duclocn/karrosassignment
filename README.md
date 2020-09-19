# karrosassignment

I. Manual Test Challenge:
1. Explore the site and define test cases to cover all main functionalities of the site
=> Please refer to the "Karros_TCs.xlsx" for more details.

2. Execute the test cases and report your test results. Please report the bugs found in the separate document/excel sheet
=> Please refer to the "Karros_TCs.xlsx" and "Karros_Issue.docx" for more details.

II. Automation Test Challenge:
1. Provide the ONE XPath to return the requests which are submitted and approved in 2019 (Request Status = Approved && Date Submitted = 2019)
=> //td[contains(@class,'td-request-approved')]/following-sibling::td[1]/div[contains(text(),'2019')]

2. Write the automation script to verify for 
  a.Verify filter Student Access Request with INACTIVE
  b. Verify sorting of First Name column 
  c. The API endpoint - GET https://my-json-server.typicode.com/typicode/demo/posts/1
=> The result is in the source code.

# Guideline to run my project
1. Download and install JDK 1.8 or higher
2. Install Homebrew on your Macbook by command: /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"
3. Install Maven: brew install maven
4. Download and install Eclipse for Mac: https://www.eclipse.org/downloads/
5. Clone my project and import one to Eclipse. Make sure that you imports the project as Maven project
6. Install "dependencies" library as in the pom.xml. If the jars library are existing in the project, please ignore this step.
7. Install "Cucumber Eclipse plugin" to read the .feature file in the Eclipse IDE by: Help > Eclipse Marketplace...
8. Download and Import Rest Assured (rest-assured-4.3.1-dist.zip) library for running the API testcase: https://github.com/rest-assured/rest-assured/wiki/Downloads
9. Open the TestRunner in the /src/test/jave/stepDefinitions foler > Right click > Run As > JUnit Test for running the testcases.

P/S: I also attached file "htmlReport.html" in target/HtmlReports to demonstrate that all testcases are passed on my system.

Thanks!
