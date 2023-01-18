import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BuyingTest {

    private WebDriver driver;
    public Logging logger = new Logging();

    @Given("browser on loggin in page")
    public void browserOnLoggingInPage() {
        // wejscie na strone logowania
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("user logs in and goes to the clothes section")
    public void userLogsIn() {

        //logowanie i wejscie w cloth sekcje
        logger.loggingIn(driver);
        WebElement clothesButton = driver.findElement(By.xpath("//*[@id=\"category-3\"]/a"));
        clothesButton.click();
    }

    @Then("Selects item he wants to buy and fixes all parameters")
    public void selectsItemHeWantsToBuyAndFixesAllParameters() throws IOException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement selectSweater = driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div[2]/article/div/div[1]/a/img"));
        selectSweater.click();
        String discount = driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li")).getText();
        ;
        Assertions.assertEquals("-20%", discount);
        WebElement sizeChoice = driver.findElement(By.xpath("//*[@id=\"group_1\"]"));
        sizeChoice.sendKeys("M");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")));
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a/i")).click();
        WebElement quantity = driver.findElement(By.xpath("/html/body/main/section/div/div/section/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/input"));
        WebElement upArrow = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[3]/div/div[2]/div/div[1]/div/span[3]/button[1]/i"));
        quantity.sendKeys("5");
        quantity.sendKeys(Keys.DELETE);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")).click();


    }

    @When("User selects all the necessary delivery and paytment options")
    public void userSelectsAllTheNecessaryDeliveryAndPaytmentOptions() {
        driver.findElement(By.xpath("//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/main/section/div/div/section/div/div[1]/section[3]/div/div[2]/form/button")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-option-1\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"conditions_to_approve[terms-and-conditions]\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button")).click();
    }

    @And("User sees order confirmation")
    public void userSeesOrderConfirmation() throws IOException {

        //generowanie daty dla screenshota
        Date date = new Date();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String s = formatter.format(date);
        String screenshot_name = s + "_" + "screenshot.png";
        // screenshot
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("C:\\Users\\Sebek\\WarsztatyZadanie\\Zadanie1\\untitled1\\" + screenshot_name));
        String orderCode = driver.findElement(By.xpath("//*[@id=\"order-reference-value\"]")).getText();
        String orderPrice = driver.findElement(By.xpath("//*[@id=\"content-hook_payment_return\"]/div/div/div/ul/li[1]/span/strong")).getText();
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a[2]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"history-link\"]/span/i")).click();
        String orderCodeHistory = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[1]/th")).getText();
        String orderPriceHistory = driver.findElement(By.xpath("//*[@id=\"content\"]/table/tbody/tr[1]/td[2]")).getText();
        // sprawdzenie zgodnosci zamowienia
        Assertions.assertEquals(orderPrice, orderPriceHistory);
        String awaitMessage = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/div[1]/div[3]/span")).getAttribute("innerText");
        Assertions.assertTrue(awaitMessage.contains("Awaiting check payment"));


    }
}



