package page;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage  {
    private WebElement usernameInput = Driver.webDriver.findElement(By.xpath("//input[@id='UserName']"));
    private WebElement passwordInput = Driver.webDriver.findElement(By.xpath("//input[@id='password']"));
    private WebElement loginButton = Driver.webDriver.findElement(By.xpath("//button[@id='ys-fastlogin-button']"));


    public void setLoginData(String email, String password){
        usernameInput.sendKeys(email);
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}
