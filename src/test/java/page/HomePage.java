package page;

import java.util.List;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {
    public void searchRestaurantOnStates() {
        clickElement(By.xpath("//span[@id='select2-ys-areaSelector-container']"));

        List<WebElement> optionList = Driver.webDriver.findElements(By.cssSelector("li.select2-results__option"));
        optionList.get(0).click();

        clickElement(By.cssSelector(".search-button"));

        List<WebElement> restaurantList = Driver.webDriver.findElements(By.cssSelector(".ys-item"));

        WebElement restaurantName = restaurantList.get(0).findElement(By.cssSelector(".restaurant-display-name"));
        writeData(restaurantName.getText(), "favRestaurant.txt");
        restaurantName.click();
    }

    public void checkUsername(String username){
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 10);
        WebElement userNameInfo = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ysUserName")));
        assertThat(userNameInfo.getText()).contains(username);
    }
}