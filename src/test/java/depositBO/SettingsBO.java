package depositBO;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SettingsBO {
    public ChromeDriver driver;
    public WebDriverWait wait;


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/main/resources/drivers/chromedriver84.exe");
        driver = new ChromeDriver();
 //       driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().maximize();
        String primary_url="https://finmaxbo.com";
        System.out.println("start test");

    }

    @After
    public void quit(){
        System.out.println("End test");
        driver.quit();
    }
}
