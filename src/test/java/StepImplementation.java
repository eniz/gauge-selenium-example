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

import static org.assertj.core.api.Assertions.assertThat;


public class StepImplementation {
    private String favRestaurantFile = "favRestaurant.txt";

    @Step("Go to Yemek Sepeti City Page")
    public void gotoCityPage() throws InterruptedException {
        WebElement cityButton = Driver.webDriver.findElement(By.xpath("//a[@href='/istanbul']"));
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
        HomePage homePage = new HomePage();
        homePage.checkUsername(username);
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
    public void checkErrorMessageForNullCharacterLogin(String errorMessage, String type) {
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
        String appUrl = System.getenv("APP_URL");
        Driver.webDriver.get(appUrl + "/");
        String appTitle = System.getenv("APP_TITLE");
        assertThat(Driver.webDriver.getTitle()).contains(appTitle);
    }

    @Step("Select city and search restaurant")
    public void selectCitySearchRestaurant() throws InterruptedException {
        HomePage homePage = new HomePage();
        homePage.searchRestaurantOnStates();
        Gauge.captureScreenshot();
    }

    @Step("Add favorite restaurant")
    public void addFavoriteRestaurant() throws InterruptedException {
        WebElement addFavButton = Driver.webDriver.findElement(By.cssSelector(".favorite-button.add"));
        addFavButton.click();

        BasePage base = new BasePage();
        String favRestaurant = base.readData(favRestaurantFile);

        Gauge.captureScreenshot();
        Gauge.writeMessage(favRestaurant + " to added favorite restaurants");
    }

    @Step("Check selected restaurant in favorite list")
    public void checkSelectedRestaurant() throws InterruptedException {
        WebElement userName = Driver.webDriver.findElement(By.xpath("//b[@id='ysUserName']"));
        userName.click();

        WebElement favoriteMenu = Driver.webDriver.findElement(By.xpath("//a[@href='/hesabim/favorilerim']"));
        favoriteMenu.click();

        BasePage base = new BasePage();
        String favRestaurant = base.readData(favRestaurantFile);

        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 10);
        WebElement favoritesElm = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("favorites")));
        assertThat(favoritesElm.getText()).contains(favRestaurant);
        Gauge.captureScreenshot();
        Gauge.writeMessage("Checked favorites list" + favRestaurant);
    }

    @Step("Remove favorite restaurant")
    public void removeSelectedRestaurant() throws InterruptedException {
        BasePage base = new BasePage();
        String favRestaurant = base.readData(favRestaurantFile);

        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 10);
        WebElement favoritesElm = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("favorites")));

        WebElement favoritedRestaurant = favoritesElm.findElement(By.linkText(favRestaurant));
        favoritedRestaurant.click();

        WebElement removeFavButton = Driver.webDriver.findElement(By.cssSelector(".favorite-button.delete"));
        removeFavButton.click();

        Gauge.captureScreenshot();
        Gauge.writeMessage("Removed from favorites list" + favRestaurant);
    }
}