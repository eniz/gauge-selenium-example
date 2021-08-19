import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.BasePage;
import page.LoginPage;
import page.HomePage;
import page.RestaurantDetailPage;

import static org.assertj.core.api.Assertions.assertThat;


public class StepImplementation {
    @Step("Go to Yemek Sepeti City Page")
    public void gotoCityPage() throws InterruptedException {
        WebElement cityButton = Driver.webDriver.findElement(By.xpath("//a[@href='/hatay']"));
        cityButton.click();
        Gauge.writeMessage("Page title is %s", Driver.webDriver.getTitle());
    }

    @Step("Enter Login with <Email> and <Password>")
    public void enterEmailAndPassword(String email, String password) throws InterruptedException {
        LoginPage loginPage =  new LoginPage();
        loginPage.setLoginData(email, password);
        loginPage.clickLoginButton();
        Gauge.captureScreenshot();
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
        Gauge.captureScreenshot();
        Gauge.writeMessage("User login failed...");
    }

    @Step("Check <ErrorMessage> for <mail> null charachter login")
    public void checkErrorMessageForNullCharachterLogin(String errorMessage, String type) {
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 10);
        if(type=="mail"){
            WebElement errorMessageText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-form\"]/div[1]/div/small\n")));
            assertThat(errorMessageText.getText()).contains(errorMessage);
        }
        else if(type=="pass"){
            WebElement errorMessageText = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"login-form\"]/div[2]/div/small")));
            assertThat(errorMessageText.getText()).contains(errorMessage);
        }

        Gauge.captureScreenshot();
        Gauge.writeMessage("User login failed...");
    }

    @Step("Open the Yemeksepeti homepage")
    public void implementation1() {
        String app_url = System.getenv("APP_URL");
        Driver.webDriver.get(app_url + "/");
        assertThat(Driver.webDriver.getTitle()).contains("Yemek Sepeti");
    }

    @Step("Select city and search restaurant")
    public void selectCitySearchRestaurant() throws InterruptedException {
        HomePage homePage = new HomePage();
        homePage.searchRestaurantOnStates();
        Gauge.captureScreenshot();
    }

    @Step("Add favorite restaurant")
    public void addFavoriteRestaurant() throws InterruptedException {
        RestaurantDetailPage restaurantDetailPage = new RestaurantDetailPage();
        restaurantDetailPage.clickFavButton();

        Gauge.writeMessage(restaurantDetailPage.restaurantName + " to added favorite restaurants");
    }

    @Step("Check selected restaurant in favorite list")
    public void checkSelectedRestaurant() throws InterruptedException {
        WebElement favoriteTab = Driver.webDriver.findElement(By.xpath("//*[@data-content-id='favorites']"));
        favoriteTab.click();
    }

    @Step("Remove favorite restaurant")
    public void removeSelectedRestaurant() throws InterruptedException {

    }



}
