package LimpBiscuit.Demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, 20);

    @Test
    public void contextLoads() {
    }

    @Test
    public void checkRedirectionAfterGoogleLogin(){

        driver.get("https://routinestarter.herokuapp.com/about");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.name("identifier")))).sendKeys("routinestarter@gmail.com");
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("identifierNext")))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("secretemail");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Järgmine']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Järgmine']")));
        driver.findElement(By.xpath("//span[text()='Järgmine']")).click();

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://routinestarter.herokuapp.com/about" );
    }

    @Test
    public void midagi(){

        driver.get("https://routinestarter.herokuapp.com/home");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.name("identifier")))).sendKeys("routinestarter@gmail.com");
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("identifierNext")))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("secretemail");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Järgmine']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Järgmine']")));
        driver.findElement(By.xpath("//span[text()='Järgmine']")).click();
    }
}
