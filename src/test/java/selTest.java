import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class selTest {
    WebDriver driver;
    utils login = new utils();

    @BeforeClass
    public void initTests(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://seleniumkurs.codingsolo.de");
        driver.manage().window().maximize();
        System.out.println("Starte Test");
    }

    @Test(priority = 1)
    public void login() {
        driver.findElement(By.id("__ac_name")).sendKeys(login.loginname);
        driver.findElement(By.id("__ac_password")).sendKeys(login.pw);
        driver.findElement(By.xpath("//*[@value='Anmelden']")).click();
        //  System.out.println("Login");
        String expect = "Willkommen im Test Bereich für den Selenium Kurs von Coding Solo — Selenium Test Portal";
        Assert.assertEquals(expect, driver.getTitle());
        //  System.out.println("Eingeloggt");
    }

    @Test(priority = 2)
    public void chooseLearning() {
        driver.findElement(By.id("portaltab-burger-menu")).click();
        Assert.assertTrue(driver.getPageSource().contains("Selenium Testapplikationen"));
        driver.findElement(By.xpath("//*[contains(text(), 'Selenium Testapplikationen')]")).click();
        driver.findElement(By.xpath("//*[@title='Lokatoren_Test_Selenium_Form']")).click();
        // System.out.println("Test ausgewählt");
    }

    @Test(priority = 3)
    public void fillForm() {
        driver.findElement(By.xpath("//*[@id='form-widgets-betreff']")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id='form-widgets-name']")).sendKeys(login.customername);
        driver.findElement(By.xpath("//*[@id='form-widgets-auswahl1-1']")).click();
        driver.findElement(By.xpath("//*[@value='Sony']")).click();
        driver.findElement(By.xpath("//*[@name='from2toButton']")).click();
        driver.findElement(By.xpath("//*[@value='Volkswagen']")).click();
        driver.findElement(By.xpath("//*[@name='from2toButton']")).click();
        driver.findElement(By.xpath("//*[@value='Speichern']")).click();
        // System.out.println("Testform ausgefüllt");

        Assert.assertTrue(driver.getPageSource().contains("Max Mustermann"));
        Assert.assertTrue(driver.getPageSource().contains("Java Grundlagen Kurs mit Dieter"));
        Assert.assertTrue(driver.getPageSource().contains("Sony"));
        Assert.assertTrue(driver.getPageSource().contains("Volkswagen"));
        //System.out.println("Assertions alles durch");
    }

    @AfterClass
    public void afterTests() {
        System.out.println("Test abgeschlossen");
        driver.quit();
    }



}
