package scrapedata;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utility.Readconfig;


public class Baseclass {

	Readconfig readconfig=new Readconfig();

	public String baseURL=readconfig.getApplicationURL();
	public static WebDriver driver;
	public static Logger logger;
	public static  Diabetesscrape ds;
		
	

	@Parameters("browser")

	@BeforeClass
	public void setup(String browser) {

		try {
			logger = Logger.getLogger("webScrapping");
			PropertyConfigurator.configure("Log4j.properties");

			if(browser.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
				driver=new ChromeDriver();
			}
			else if(browser.equals("firefox"))
			{
				System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
				driver = new FirefoxDriver();
			}
			logger.info("********************welcome*********************");

			driver.get(baseURL);
			driver.manage().window().maximize();}

			catch (Exception e) {
				logger.error("Error in Test:",e);
				throw e;



			}

		}
}