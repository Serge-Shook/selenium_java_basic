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

public class Sample3Task {
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
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void assertEqualsTask() throws Exception {
//         TODO:
//         check how many element with class "test" there are on page (5)
        int numberOfTestElements = driver.findElements(By.className("test")).size();
        assertEquals("Number of test elements", 5, numberOfTestElements);

        //         check that value of second button is "This is also a button"
//        WebElement secondButton = driver.findElements(By.tagName("button")).get(1);
        WebElement secondButton = driver.findElement(By.id("buttonId"));
        assertEquals(
                "Text of second button",
                "This is also a button",
                secondButton.getAttribute("value"));
    }

    @Test
    public void assertTrueTask() throws Exception {
//         TODO:
//         check that it is True that value of second button is
//         "this is Also a Button" if you ignore Caps Locks
//         fail with custom error message:
        WebElement secondButton = driver.findElement(By.id("buttonId"));
        assertTrue(
                "Second button text",
                secondButton.getAttribute("value").equalsIgnoreCase("this is Also a Button"));

    }

    @Test
    public void assertFalseTask() throws Exception {
//         TODO:
//        check that it is False that value of second button is "This is a button"
        WebElement secondButton = driver.findElement(By.id("buttonId"));
        assertFalse(
                "Second button text is not 'This is a button'",
                secondButton.getAttribute("value").equals("This is a button"));
    }

    @Test
    public void failTask() throws Exception {
//        TODO:
//        check that none of items with class "test"
//        contain number 190
        for (WebElement testElement : driver.findElements(By.className("test"))) {
            assertFalse("Contains 190", testElement.getText().contains("190"));
        }

    }
}
