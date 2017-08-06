package actions;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/*This class will set the selenium web driver to it's intended browser for test run*/
public class SetBrowser {
    public WebDriver setBrowser(String browser) {
        WebDriver driver=null;
        DesiredCapabilities capabilities=new DesiredCapabilities();
        capabilities.setCapability("browserName",browser);
        capabilities.setCapability("acceptSslCerts",true);
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        if(browser.equalsIgnoreCase("chrome")){
            ChromeDriverManager.getInstance().setup();
            driver=new ChromeDriver(capabilities);
        }else if(browser.equalsIgnoreCase("internet explorer") || browser.equalsIgnoreCase("ie")){
            InternetExplorerDriverManager.getInstance().setup();
            driver=new InternetExplorerDriver(capabilities);
        }else if(browser.equalsIgnoreCase("edge")){
            EdgeDriverManager.getInstance().setup();
            driver=new EdgeDriver(capabilities);
        }else if(browser.equalsIgnoreCase("firefox")){
            FirefoxDriverManager.getInstance().setup();
            driver=new FirefoxDriver(capabilities);
        }
        return driver;
    }
}
