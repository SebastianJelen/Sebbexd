import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class MyStepdefs {

    private WebDriver driver;
    public Logging logger = new Logging();


    @Given("browser on logging in page")
    public void userHasAlreadyAnAccountCreatedBeforehand() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");
    }

    @When("User logs in")
    public void userLogsIn() {

        logger.loggingIn(driver);

    }

    @Then("User is on address site")
    public void userIsOnHttpsMystoreTestlabCoderslabPlIndexPhpControllerAddresses() {
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=address");
//        WebElement addressesButton = driver.findElement(By.xpath("//*[@id=\"addresses-link\"]/span/i"));
//        addressesButton.click();
//        WebElement createNewAddressButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a/span"));
//        createNewAddressButton.click();
    }
    @Then("He fills in {}, {} and {}")
    public void heFillsInAllTheBrackets(String Address, String City, String ZipPostalCode) {
        WebElement addressBracket = driver.findElement(By.xpath("//*[@id=\"field-address1\"]"));
        addressBracket.sendKeys(Address);
        WebElement cityBracket = driver.findElement(By.xpath("//*[@id=\"field-city\"]"));
        cityBracket.sendKeys(City);
        WebElement zipCodeBracket = driver.findElement(By.xpath("//*[@id=\"field-postcode\"]"));
        zipCodeBracket.sendKeys(ZipPostalCode);
        WebElement countryBracket = driver.findElement(By.xpath("//*[@id=\"field-id_country\"]"));
        countryBracket.sendKeys("u");
        WebElement saveButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/footer/button"));
        saveButton.click();
        WebElement updateButton = driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/article/div[2]/a[1]/span"));
        updateButton.click();
        String currentCityAddress = driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div/div/form/section/div[8]/div[1]/input")).getAttribute("value");
        Assertions.assertEquals(City, currentCityAddress);
        WebElement saveButton1 = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/form/footer/button"));
        saveButton1.click();
//        String currentAddress = driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div/div/form/section/div[6]/div[1]/input")).getText();
 //       Assertions.assertEquals(Address, currentAddress);
        WebElement deleteButton = driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/article/div[2]/a[2]/span"));
        deleteButton.click();
//         Assertions.assertEquals(ZipPostalCode, ZipPostalCode);
        String messageDeletedAccount = driver.findElement(By.xpath("//*[@id=\"notifications\"]/div/article/ul/li")).getText();
        Assertions.assertEquals(messageDeletedAccount, "Address successfully deleted!");
 //      String postalCheck = driver.findElement(By.xpath("//*[@id=\"field-postcode\"]")).getText();
      //   Assertions.assertEquals(ZipPostalCode, 32150);
   //     saveButton.click();
        int x = 1;





    }

    @And("User is on the addresses page and close browser")
    public void checkWhetherDataInBracketsIsCorrect() {
        WebElement subscriptionBracket = driver.findElement(By.xpath("//*[@id=\"blockEmailSubscription_displayFooterBefore\"]/div/div/form/div/div[1]/div[1]/input"));
        subscriptionBracket.sendKeys("ggg");

    }
}
//*[@id="address-30404"]/div[2]/a[2]/span
//*[@id="address-30405"]/div[2]/a[2]/span
//*[@id="blockEmailSubscription_displayFooterBefore"]/div/div/form/div/div[1]/div[1]/input
// notification
//*[@id="notifications"]/div/article/ul/li