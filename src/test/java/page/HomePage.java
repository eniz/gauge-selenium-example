package page;

import java.util.List;

import com.thoughtworks.gauge.Gauge;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class HomePage extends BasePage {
    public WebElement favoriteTab = Driver.webDriver.findElement(By.xpath("//*[@data-content-id='favorites']"));
    public WebElement newRestaurantsTab = Driver.webDriver.findElement(By.xpath("//a[@data-content-id='new-restaurants']"));
    public WebElement resultsContainer = Driver.webDriver.findElement(By.xpath("//span[@id='select2-ys-areaSelector-container']"));


    public void searchRestaurantOnStates() {
        resultsContainer.click();

        List<WebElement> optionList = Driver.webDriver.findElements(By.cssSelector("li.select2-results__option"));
        optionList.get(0).click();

        WebElement searchButton = Driver.webDriver.findElement(By.cssSelector(".search-button"));
        searchButton.click();

        waitFor(2);
        List<WebElement> restaurantList = Driver.webDriver.findElements(By.cssSelector(".ys-item"));
        restaurantList.get(0).findElement(By.cssSelector(".restaurant-display-name")).click();
    }

}
