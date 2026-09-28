package FWD.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumPradeep.SeleniumFrameworkDesign.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	
	public LandingPage landingPage;

	@BeforeMethod(alwaysRun=true)
	public void InitializeDriver() throws IOException {
		Properties prop = new Properties();
		String path = System.getProperty("user.dir") + "\\src\\main\\java\\FWD\\Resources\\GlobalData.properties";
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		String browserName=	System.getProperty("Browser") !=null ? System.getProperty("Browser") :prop.getProperty("Browser");
//		String browserName=prop.getProperty("Browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();

		    Map<String, Object> prefs = new HashMap<>();
		    
		    options.addArguments("--incognito");
		    options.addArguments("--password-store=basic");

		    prefs.put("credentials_enable_service", false);
		    prefs.put("profile.password_manager_enabled", false);
		    prefs.put("profile.password_manager_leak_detection", false);

		    options.setExperimentalOption("prefs", prefs);
		    if(browserName.contains("headless")) {
		    	options.addArguments("headless");
		    }
		    
			driver = new ChromeDriver(options);
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		return driver;
		
	}
	
	public LandingPage lanuchApplication() throws IOException {
//		driver=InitializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTO();
		return landingPage;
	}
	
	public List<HashMap<String,Object>> getJsonDataToMap(String filePath) throws IOException {
	//Convert json to String
	String jsonContent=	FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
	//String to HashMap
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,Object>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, Object>>>(){
	});
	//Return List of Data
	return data;
		
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//" + testCaseName +".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//" + testCaseName +".png";
	}
	
	@AfterMethod(alwaysRun=true)
	public void setDown()
	{
		if (driver != null)
			{
			driver.quit();	
			}
	}
}

