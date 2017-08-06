package stepDefinitions;

import actions.SetBrowser;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import model.SearchModel;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class SearchStepDefinitions implements Serializable{
    WebDriver driver;
    String homePageUrl="https://www.geniesolutions.com.au";
    @Given("^I am on home page of Genie Solutions website in \"(.*)\" browser$")
    public void iAmOnHomePageOfGenieSolutionsWebsiteInBrowser(String browser){
        SetBrowser setTestBrowser=new SetBrowser();
        driver= setTestBrowser.setBrowser(browser);
        driver.get(homePageUrl);
        if(!browser.equalsIgnoreCase("firefox")){
            /*Currently gecko driver for firefox does not support maximize.
                In firefox 55 version and above this issue will be fixed.
                So to ignore maximize for firefox this if condition
             */
            driver.manage().window().maximize();
        }

        driver.manage().timeouts().pageLoadTimeout(1, TimeUnit.MINUTES);
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
    }

    @When("^I click the search magnifying glass$")
    public void iClickTheSearchMagnifyingGlass()  {
        SearchModel.clickSearchGlass(driver);
    }

    @Then("^the search input box should appear$")
    public void theSearchInputBoxShouldAppear() {
        if(SearchModel.checkSearchInput(driver)){
            System.out.println("Search Input box appeared");
        }else{
            Assert.fail("Search Input box did not appear");
        }
    }

    @When("^I input search string \"(.*)\" in search box$")
    public void iInputSearchStringInSearchBox(String searchString)  {
        SearchModel.inputSearchString(driver,searchString);
        System.out.println("Entered String : "+searchString+ " in the search input box");
    }

    @And("^I press the search magnifying glass in search box$")
    public void iPressTheSearchMagnifyingGlassInSearchBox()  {
        SearchModel.clickSearchInputSubmit(driver);
        System.out.println("Clicked the search input submit magnifying glass to search string");
    }

    @Then("^I should see \"(.*)\" results in result pane$")
    public void iShouldSeeResultsInResultPane(String result)  {
        if(SearchModel.compareResults(driver,result)){
            System.out.println("Number of search results : "+result+" matches ones returned via search on site");
        }else{
            Assert.fail("Number of search results : "+result+" does not matches ones returned via search on site");
        }
    }
    @After
    public void closeSession(){
        driver.close();
    }
}
