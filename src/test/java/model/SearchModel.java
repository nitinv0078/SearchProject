package model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.util.concurrent.TimeUnit;

public class SearchModel {
    private static By searchGlass= By.xpath(".//*[@class = 'search-toggle header-search']/*[@id = 'Layer_1']");
    private static By searchInput= By.cssSelector("input[class='search-field']");
    private static By searchInputSubmit= By.cssSelector("input[id='searchsubmit']");
    private static By resultPane=By.cssSelector("div[class='landing-hero-content']");

    public static void clickSearchGlass(WebDriver driver){
        waitForElementVisible(driver,searchGlass);
        ((JavascriptExecutor)driver).executeScript("$(arguments[0]).click();", driver.findElement(searchGlass));
    }

    public static boolean checkSearchInput(WebDriver driver){
        try{
            waitForElementVisible(driver,searchInput);
        }
        catch (Exception e){
            return false;
        }
        return (driver.findElement(searchInput).isDisplayed());
    }

    public static void inputSearchString(WebDriver driver,String searchString){
        driver.findElement(searchInput).sendKeys(searchString);
    }

    public static void clickSearchInputSubmit(WebDriver driver){
        driver.findElement(searchInputSubmit).click();
    }

    public static boolean compareResults(WebDriver driver,String result){
        waitForElementVisible(driver,resultPane);
        String resultText=driver.findElement(resultPane).getText();
        if(result.toLowerCase().contains("atleast one") && resultText.contains("no")){
            return false;
        }else{
            return (resultText.contains(result.toLowerCase()));
        }
    }

    public static void waitForElementVisible(WebDriver driver,By elementBy){
        FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(500,TimeUnit.MILLISECONDS)
                .ignoring(InvalidElementStateException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchWindowException.class)
                .ignoring(ElementNotVisibleException.class);
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        });
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(elementBy)));
    }

}
