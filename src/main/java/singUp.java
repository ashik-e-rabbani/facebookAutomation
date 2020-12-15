import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;

public class singUp {

    WebDriver driver =  new ChromeDriver();
    WebDriver driver2 =  new ChromeDriver();

    public void doAtumation(HashMap<String, String> credentials)
    {
        driver.get("https://www.facebook.com/");

//        Check for Create Account Button
        WebElement createAccountStatus = driver.findElement(By.xpath("//*[@id=\"u_0_2\"]"));
        if (createAccountStatus.isDisplayed())
        {
            driver.findElement(By.id("u_0_2")).click();
        }else {
            System.out.println("Create New Account Button Not Found");
        }

        WebDriverWait waitForSignupModal = new WebDriverWait(driver,10);
        waitForSignupModal.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"u_1_d\"]")));
        driver.findElement(By.name("firstname")).sendKeys(credentials.get("fname"));
        driver.findElement(By.name("lastname")).sendKeys(credentials.get("lname"));




        driver2.get("https://temp-mail.org/en/");
        WebDriverWait waitForTempMail = new WebDriverWait(driver2,10);
        waitForTempMail.until(ExpectedConditions.elementToBeClickable(By.id("click-to-copy")));

        String getMail = driver2.findElement(By.id("mail")).getAttribute("value");


        driver.findElement(By.name("reg_email__")).sendKeys(getMail);
        WebDriverWait confirmMail = new WebDriverWait(driver,3);
        confirmMail.until(ExpectedConditions.visibilityOfElementLocated(By.name("reg_email_confirmation__")));
        driver.findElement(By.name("reg_email_confirmation__")).sendKeys(getMail);

        driver.findElement(By.name("reg_passwd__")).sendKeys("pa88w0rdg1v3n");


        Select selDate = new Select(driver.findElement(By.id("day")));
        Select selMonth = new Select(driver.findElement(By.id("month")));
        Select selYear = new Select(driver.findElement(By.id("year")));

        selDate.selectByVisibleText(credentials.get("day"));
        selMonth.selectByVisibleText(credentials.get("month"));
        selYear.selectByVisibleText(credentials.get("year"));

        driver.findElement(By.xpath("//input[@type='radio' and @value='2']")).click();
//        driver.findElement(By.name("websubmit")).click();

        driver.quit();
        driver2.quit();


    }




}
