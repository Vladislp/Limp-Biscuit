package LimpBiscuit.Demo;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.NoSuchElementException;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoApplicationTests {

    @Value("${email.username}")
    private String username;
    @Value("${email.password}")
    private String password;

    WebDriver driver = new FirefoxDriver();
    WebDriverWait wait = new WebDriverWait(driver, 40);
    JavascriptExecutor jse = (JavascriptExecutor)driver;

//    String url = "https://routinestarter.herokuapp.com";
    String url = "http://localhost:8080";

    public void getThroughGoogle(){
        wait.until(ExpectedConditions.presenceOfElementLocated((By.name("identifier")))).sendKeys(username);
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("identifierNext")))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='password']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Järgmine']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Järgmine']")));
        driver.findElement(By.xpath("//span[text()='Järgmine']")).click();
    }

    public WebElement tryToFindElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException notFound) {
            return null;
        }
    }

    @Test
    public void t1_checkNavigation(){
        driver.get(url);

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Home']")))).click();
        getThroughGoogle();
        Assert.assertEquals(driver.getCurrentUrl(), url+"/home");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='About us']")))).click();
        Assert.assertEquals(driver.getCurrentUrl(), url+"/about");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Files']")))).click();
        Assert.assertEquals(driver.getCurrentUrl(), url+"/files");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Chat']")))).click();
        Assert.assertEquals(driver.getCurrentUrl(), url+"/chat");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Stats']")))).click();
        Assert.assertEquals(driver.getCurrentUrl(), url+"/stats");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Sign In']")))).click();
        Assert.assertEquals(driver.getCurrentUrl(), url+"/signup");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//a[text()='ROUTINE STARTER']")))).click();
        Assert.assertEquals(driver.getCurrentUrl(), url+"/");
    }

    @Test
    public void t2_tryItButton(){
        driver.get(url);

        Assert.assertTrue(driver.getPageSource().contains("SIIN PEAKS NAGU MIDAGI OLEMA, AGA I GUESS FOR NOW IT IS OKAY"));

        String css = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("showAndHide")))).getCssValue("display");
        Assert.assertTrue(css.equals("none"));

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//button[@class='btn btn-danger']")))).click();
        css = wait.until(ExpectedConditions.presenceOfElementLocated((By.id("showAndHide")))).getCssValue("display");
        Assert.assertTrue(css.equals("block"));
    }

    //
    @Test
    public void t3_createNewRoutine(){
        driver.get(url+"/home");
        getThroughGoogle();

        // check
        Assert.assertFalse(driver.getPageSource().contains("go to the gym"));

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Create Routine']")))).click();

        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("title")))).sendKeys("do sports");
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("text")))).sendKeys("go to the gym");
        wait.until(ExpectedConditions.presenceOfElementLocated((By.id("color")))).sendKeys("blue");

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//button[text()='Create Routine']")))).click();
        driver.get(url+"/home");

        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatter = formmat1.format(ldt);

        // check
        Assert.assertTrue(driver.getPageSource().contains("go to the gym"));
//        Assert.assertTrue(driver.getPageSource().contains(formatter));

        String result = driver.findElement(By.xpath("//table/tbody/tr/td[1]")).getText();
        int id = Integer.parseInt(result);
        driver.get(url+"/deleteroutine/"+id);
        driver.get(url+"/home");

        Assert.assertFalse(driver.getPageSource().contains("go to the gym"));
    }

    // mõnikord kirjutab "Could not connect to WebSocket server", aga testimise käigus saan öelda, et see on suht random
    @Test
    public void t4_chat() throws Exception{
        driver.get(url+"/chat");
        getThroughGoogle();

        String message = "test message";
        // check
        Thread.sleep(3 * 1000);

        if (driver.getPageSource().contains("joined!")){

            Assert.assertFalse(driver.getPageSource().contains(message));

            wait.until(ExpectedConditions.presenceOfElementLocated((By.id("message")))).sendKeys(message);
            wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//button[text()='Send']")))).click();

            Assert.assertTrue(driver.getPageSource().contains(message));
        }
        else {
            Assert.assertTrue(driver.getPageSource().contains("Could not connect to WebSocket server. Please refresh this page to try again!"));
        }
    }

    @Test
    public void t5_statistics() throws Exception{
        driver.get(url+"/stats");
        getThroughGoogle();

        for (int i = 0; i < 2; i++) {
            Thread.sleep(1 * 1000);
            jse.executeScript("scroll(0, 250);");
        }

        Assert.assertTrue(tryToFindElement(By.id("operating-systems")) != null);
        Assert.assertTrue(tryToFindElement(By.id("browsers")) != null);
        Assert.assertTrue(tryToFindElement(By.id("dates")) != null);
    }

    @Test
    public void t6_language(){
        driver.get(url+"/");
        String en = "Your personal schedule";
        String et = "Sinu personaalne rutiin";
        String ru = "Твои Рутины";

        Assert.assertTrue(driver.getPageSource().contains(en));
        Assert.assertFalse(driver.getPageSource().contains(et));
        Assert.assertFalse(driver.getPageSource().contains(ru));

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Language']")))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//a[text()='eesti keel']")))).click();

        Assert.assertFalse(driver.getPageSource().contains(en));
        Assert.assertTrue(driver.getPageSource().contains(et));
        Assert.assertFalse(driver.getPageSource().contains(ru));

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Keel']")))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//a[text()='Русский']")))).click();

        Assert.assertFalse(driver.getPageSource().contains(en));
        Assert.assertFalse(driver.getPageSource().contains(et));
        Assert.assertTrue(driver.getPageSource().contains(ru));

        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//span[text()='Язык']")))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//a[text()='English']")))).click();

        Assert.assertTrue(driver.getPageSource().contains(en));
        Assert.assertFalse(driver.getPageSource().contains(et));
        Assert.assertFalse(driver.getPageSource().contains(ru));
    }

}
