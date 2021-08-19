package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RestaurantDetailPage extends BasePage {
    public WebElement favButton = Driver.webDriver.findElement(By.className("favorite-button"));
    public WebElement restaurantName = Driver.webDriver.findElement(By.className("restaurantName"));

    public void clickFavButton() {
        waitFor(2);
        favButton.click();
    }
}
