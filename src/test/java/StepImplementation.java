import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

// Run with tag name => mvn gauge:execute -DspecsDir=specs -Dtags="AllLogin"
// Run with firefox  => mvn gauge:execute -DspecsDir=specs -Denv="firefox"
// Run parallel => mvn gauge:execute -DspecsDir=specs -DinParallel=true -Dnodes=2
public class StepImplementation {
    @Step("Go to Yemek Sepeti City Page")
    public void gotoCityPage() throws InterruptedException {
        WebElement cityButton = Driver.webDriver.findElement(By.xpath("//a[@href='/istanbul']"));
        cityButton.click();

        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Enter Login with <Email> and <Password>")
    public void enterEmailAndPassword(String email, String password) throws InterruptedException {
        WebElement usernameInput = Driver.webDriver.findElement(By.xpath("//input[@id='UserName']"));
        WebElement passwordInput = Driver.webDriver.findElement(By.xpath("//input[@id='password']"));

        usernameInput.sendKeys(email);
        passwordInput.sendKeys(password);

        WebElement loginButton = Driver.webDriver.findElement(By.xpath("//button[@id='ys-fastlogin-button']"));
        loginButton.click();
        // Gauge.captureScreenshot();
    }

    @Step("Check <Username> After Login")
    public void checkUsernameAfterLogin(String username) {
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 10);
        WebElement userNameInfo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ysUserName")));

        assertThat(userNameInfo.getText()).contains(username);
        Gauge.writeMessage("User logins successful...");
    }

    @Step("Check <ErrorMessage> for failed login")
    public void checkErrorMessageForFailedLogin(String errorMessage) {
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 10);
        WebElement errorMessageText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='ui-id-1']/div[1]/span/strong")));

        assertThat(errorMessageText.getText()).contains(errorMessage);
        Gauge.writeMessage("User login failed...");
    }

    @Step("Open the Yemeksepeti homepage")
    public void implementation1() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url + "/");
        assertThat(Driver.webDriver.getTitle()).contains("Yemek Sepeti");
    }
}
