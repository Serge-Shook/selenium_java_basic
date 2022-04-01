package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.*;

public class Sample4Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void enterNumber() throws Exception {
//         TODO:
//        enter a number under "Number"
//        check that button is not clickable "Clear Result"
//        check that text is not displayed
//        click on "Result" button
//        check that text is displayed
//        check that the correct Text appears ("You entered number: "NUMBER YOU ENTERED"")
//        check that the button "Clear Result" is clickable now
//        click on "Clear Result"
//        check that the text is still (""), but it is not displayed

        WebElement numberInput = driver.findElement(By.name("vfb-9"));
        numberInput.clear();

        String myNumber = "1001";
        numberInput.sendKeys(myNumber);

        WebElement clearResultNumberButton = driver.findElement(By.id("clear_result_button_number"));
        assertFalse("Clear Result is not clickable", clearResultNumberButton.isEnabled());

        WebElement resultMessage = driver.findElement(By.id("result_number"));
        assertFalse("Result is not visible", resultMessage.isDisplayed());

        WebElement resultNumberButton = driver.findElement(By.id("result_button_number"));
        resultNumberButton.click();

        assertTrue("Result is visible", resultMessage.isDisplayed());

        String expectedResultMessage = String.format("You entered number: \"%s\"", myNumber);
        assertEquals("Result message is correct", expectedResultMessage, resultMessage.getText());

        assertTrue("Clear Result is clickable", clearResultNumberButton.isEnabled());

        clearResultNumberButton.click();
        assertEquals("Result message remained?", "", resultMessage.getText());
        assertFalse("Result message is hidden", resultMessage.isDisplayed());

    }

    @Test
    public void clickOnLink() throws Exception {
//         TODO:
//        check current url is base_url
//        click on "This is a link to Homepage"
//        check that current url is not base_url
//        verify that current url is homepage

        assertEquals("Current URL", base_url, driver.getCurrentUrl());
        driver.findElement(By.id("homepage_link")).click();
        assertNotEquals("URL changed", base_url, driver.getCurrentUrl());

        String homePage = "https://kristinek.github.io/site/";
        assertEquals("Redirected to homepage", homePage, driver.getCurrentUrl());
    }
}
