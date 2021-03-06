package autoRegLand;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class RegisterLang {
    private WebDriver driver;
    private WebDriverWait wait;

    private By phone_element = By.cssSelector("input[name=\"phone\"]");
    private By email_element = By.cssSelector("input[name=\"email\"]");
    private By currency_element = By.cssSelector("[name=\"currency\"]");

    private By firstname_element = By.xpath("//*[contains(@name,'firstname') or contains(@name,'firstName')]");
    private By lastname_element = By.xpath("//*[contains(@name,'lastname') or contains(@name,'lastName')]");



    public RegisterLang(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,20);
    }
    public String register(String url) throws InterruptedException {

        driver.get(url);
        int formnubmer = 0;
        try
        {
            System.out.println("form - " + formnubmer);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("form")));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElements(By.cssSelector("form")).get(formnubmer));

        }
        catch (TimeoutException e) {
            formnubmer ++ ;
            System.out.println("form - " + formnubmer);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",driver.findElements(By.cssSelector("form")).get(formnubmer));

        }

        WebElement forms = driver.findElements(By.cssSelector("form")).get(formnubmer);
        forms.findElement(firstname_element).sendKeys("Testauto");
        forms.findElement(lastname_element).sendKeys("Testauto");

        Random random = new Random();
        String number = "545455" + (random.nextInt(999) + 100 );
        String rand_email = "Selenium" + (random.nextInt(999999) + 1000) + "@autotestselenium.com";
        //System.out.println(formnubmer);

        try
        {
            forms.findElement(email_element).sendKeys(rand_email);
            forms.findElement(phone_element).sendKeys(number);
        }
        catch (ElementNotInteractableException e) {
            driver.findElements(By.cssSelector("form")).get(formnubmer - 1).findElement(email_element).sendKeys(rand_email);
            driver.findElements(By.cssSelector("form")).get(formnubmer - 1).findElement(phone_element).sendKeys(number);
        }

        String currency;
        try {
            forms.findElement(By.xpath("//*[contains(@name,'currency') and contains(@type,'hidden')]"));
        }catch (NoSuchElementException e){
            Thread.sleep(100);
          wait.until(ExpectedConditions.elementToBeClickable(currency_element));
            try {
                forms.findElement(currency_element).click();
                forms.findElement(By.xpath("//*[@value=\"usd\" or @value=\"USD\"]")).click();
            } catch (ElementClickInterceptedException d){
              //  forms.findElement(By.xpath("//*[@id=\"app\"]/aside/form/div[5]/label[1]/i")).click();
                forms.findElement(By.xpath("//label[1]/i")).click();
            }
           // wait.until(ExpectedConditions.attributeToBe(currency_element,"value","usd"));
        }

        /*try {
             currency = forms.findElement(currency_element).getAttribute("value");
            System.out.println(currency);

        }catch (NoSuchElementException e){

            currency = forms.findElement(By.xpath("//*[contains(@name,'currency') and contains(@type,'hidden')]")).getAttribute("value");
        }
        if (currency.equals("")){
           Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(currency_element));
            forms.findElement(currency_element).click();
            forms.findElement(By.cssSelector("[value=\"usd\"]")).click();
            wait.until(ExpectedConditions.attributeToBe(currency_element,"value","usd"));
        }
*/
        /*try{
            forms.findElement(By.cssSelector("[type=\"submit\"]")).click();
        } catch (NoSuchElementException e){
            forms.findElement(By.cssSelector("button")).click();
        }*/

        ExpectedCondition e = (ExpectedCondition<Boolean>) d -> (!d.getCurrentUrl().equals(url));
        wait.until(e);

        return rand_email;
    }

}
