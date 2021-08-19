package page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    private WebDriverWait webDriverWait;

    private By returnElement(String selector, String selectorType) {
        By by = null;
        if (selectorType.equalsIgnoreCase("id"))
            by = By.id(selector);
        else if (selectorType.equalsIgnoreCase("classname"))
            by = By.className(selector);
        else if (selectorType.equalsIgnoreCase("cssselector"))
            by = By.cssSelector(selector);
        else if (selectorType.equalsIgnoreCase("xpath"))
            by = By.xpath(selector);

        return by;
    }

    public void waitUntilExpectedElement(String selector, String selectorType, int... index){
        By by = returnElement(selector, selectorType);
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (NullPointerException e){
            throw new RuntimeException("ELEMENT (" + by + (index.length > 0 ? index[0] : "")
                    + ") NOT EXIST; AUTOMATION DATAS MAY BE INVALID!");
        }
    }

    public void waitFor(int... timeOut){
        int timeOutI = 2;
        if (timeOut.length != 0){
            timeOutI = timeOut[0];
        }
        try {
            Thread.sleep(timeOutI * 1000L);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
