package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Sample5Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.manage().window().maximize();
        driver.get("https://kristinek.github.io/site/examples/alerts_popups");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void goToAlertedPageViaButton() throws InterruptedException {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();
        Thread.sleep(500);
//        switch to alert
//        click ok
        Alert firstAlert = driver.switchTo().alert();
        firstAlert.accept();
//        Thread.sleep(500);
//        switch to second alert
        Alert secondAlert = driver.switchTo().alert();
        Thread.sleep(500);
//        verify alert text
        assertEquals("Booooooooo!", secondAlert.getText());
        Thread.sleep(500);
//        click ok on second alert
        secondAlert.accept();
        Thread.sleep(500);
//        verify that the correct page is opened
        assertEquals("https://kristinek.github.io/site/examples/alerted_page", driver.getCurrentUrl());
        Thread.sleep(500);
    }

    @Test
    public void doNotGoToAlertedPageViaButton() throws Exception {
//         TODO:
//        click on "To go to alerted page press Ok. Or stay here" button
        driver.findElement(By.className("w3-blue")).click();
//        switch to alert
//        click cancel
        driver.switchTo().alert().dismiss();
//        verify the text on page
        assertEquals("So you desided to say? Good!", driver.findElement(By.id("textForAlerts")).getText());
    }
}
