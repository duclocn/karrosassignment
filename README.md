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
2. Install Homebrew on your Macbook: /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install.sh)"
3. Install Maven: brew install maven
4. Download and install Eclipse for Mac: https://www.eclipse.org/downloads/
5. Clone my project and import one to Eclipse
6. Install "dependencies" library as in the pom.xml
