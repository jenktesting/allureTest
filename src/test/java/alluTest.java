import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class alluTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://shop.hiq.de/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void logoPresenece() {
        boolean disstatus = driver.findElement(By.xpath("//*[@class='logo img-responsive']")).isDisplayed();
        Assert.assertEquals(disstatus, true);
    }

    @Test(priority = 2)
    public void loginTest() {
        driver.findElement(By.xpath("//*[@title='Anmelden zu Ihrem Kundenbereich']")).click();
        driver.findElement(By.xpath("//*[@name='email']")).sendKeys("jasin.fazli@hiq.de");
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("pass123");
        driver.findElement(By.id("submit-login")).click();

        Assert.assertEquals(driver.getTitle(), "hiqdemo shop");
    }

    @Test(priority = 3)
    public void registrationTest() {
        throw new SkipException("Skipping this Test");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

