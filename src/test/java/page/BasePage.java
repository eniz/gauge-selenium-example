package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.*;

public class BasePage {

    private  WebDriver webDriver;

    public BasePage() {
        this.webDriver = Driver.webDriver;
    }

    public void clickElement(By selector){
        webDriver.findElement(selector).click();
    }

    public void writeData(String text, String fileName) {
        File FC = new File(fileName);
        try {
            FC.createNewFile();

            FileWriter FW = new FileWriter(fileName);
            BufferedWriter BW = new BufferedWriter(FW);
            BW.write(text);
            BW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readData(String fileName) {
        String line = "";
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            line = br.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }
}