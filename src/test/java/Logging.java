import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Logging {

        public void loggingIn(WebDriver driver){

        String email = "sesixag720@kaftee.com";
        String password = "qwerty";
        WebElement emailBracket = driver.findElement(By.xpath("//*[@id=\"field-email\"]"));
        emailBracket.sendKeys(email);
        WebElement passwordBracket = driver.findElement(By.xpath("//*[@id=\"field-password\"]"));
        passwordBracket.sendKeys(password);
        WebElement signIn = driver.findElement(By.cssSelector("#submit-login"));
        signIn.click();
    }
}


