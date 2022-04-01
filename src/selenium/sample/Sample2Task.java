package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.List;

public class Sample2Task {
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
//        Thread.sleep(800);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.quit();
    }

    @Test
    public void findElementByID() throws Exception {
//         TODO:
//         get text "Heading 2 text" using id
        String heading2text = driver.findElement(By.id("heading_2")).getText();
        System.out.println("Element with id=\"heading_2\" has text: " + heading2text);
    }

    @Test
    public void findElementByName() throws Exception {
//         TODO:
//         get attribute "id" and "value" of button "This is also a button" using name
        WebElement randomButton2 = driver.findElement(By.name("randomButton2"));
        System.out.println("Id is: " + randomButton2.getAttribute("id"));
        System.out.println("Value is: " + randomButton2.getAttribute("value"));
    }

    @Test
    public void findElementByClassFirst() throws Exception {
//         TODO:
//         get first text of class "test" (should be "Test Text 1")
        WebElement classTest = driver.findElement(By.className("test"));
        System.out.println("First element of class \"test\" has text: " + classTest.getText());
    }

    @Test
    public void findElementByClassAll() throws Exception {
//         TODO:
//         get size text of class "test" (should be 5)
//         get text of class "test"
//         get third text of class "test" (should be "Test Text 4")
        List<WebElement> allTextClass = driver.findElements(By.className("test"));
        System.out.println("Class \"test\" has " + allTextClass.size() + " elements");
        System.out.println();

        System.out.println("Elements of class \"test\" have texts:");
        for (WebElement testClass : allTextClass) {
            System.out.println(testClass.getText());
        }

        System.out.println();
        System.out.println("Third element has text: " + allTextClass.get(2).getText());
    }
}
