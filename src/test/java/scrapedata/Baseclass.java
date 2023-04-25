package scrapedata;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
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
	public void setup(@Optional String browser) {

		try {
			logger = Logger.getLogger("webScrapping");
			PropertyConfigurator.configure("Log4j.properties");

			if("firefox".equalsIgnoreCase(browser))
			{
				System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
				driver = new FirefoxDriver();
			}
			else 
			{
				System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
				driver=new ChromeDriver();
			}
			logger.info("********************welcome*********************");
            
			ChromeOptions co = new ChromeOptions();
			co.setAcceptInsecureCerts(true);
			driver.get(baseURL);
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(90));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));}

			catch (Exception e) {
				logger.error("Error in Test:",e);
				throw e;



			}

		}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}