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

public class Sample6Task {
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
        driver.close();
    }

    @Test
    public void findElementByXPath() {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        WebElement heading1 = driver.findElement(By.xpath("//h2[2]"));
        assertEquals("Heading 2 text", heading1.getText());
        WebElement heading2 = driver.findElement(By.xpath("//*[@id='heading_2']"));
        assertEquals("Heading 2 text", heading2.getText());
//        1-2 ways to find text: "Test Text 1"
        WebElement testText1_1 = driver.findElement(By.xpath("//p[@class='test'][1]"));
        assertEquals("Test Text 1", testText1_1.getText());
        WebElement testText1_2 = driver.findElement(By.xpath("//div[@id='test1']/p[@class='test']"));
        assertEquals("Test Text 1", testText1_2.getText());
//        1-2 ways to find text: "Test Text 2"
        WebElement testText2_1 = driver.findElement(By.xpath("//p[@class='twoTest'][1]"));
        assertEquals("Test Text 2", testText2_1.getText());
        WebElement testText2_2 = driver.findElement(By.xpath("//p[.='Test Text 2']"));
        assertEquals("Test Text 2", testText2_2.getText());
//        1-2 ways to find text: "Test Text 3"
        WebElement testText3_1 = driver.findElement(By.xpath("(//p[@class='test'])[2]"));
        assertEquals("Test Text 3", testText3_1.getText());
        WebElement testText3_2 = driver.findElement(By.xpath("//p[text()='Test Text 3']"));
        assertEquals("Test Text 3", testText3_2.getText());
//        1-2 ways to find text: "Test Text 4"
        WebElement testText4_1 = driver.findElement(By.cssSelector("#test3>p.test:last-child"));
        assertEquals("Test Text 4", testText4_1.getText());
        WebElement testText4_2 = driver.findElement(By.xpath("//p[.='Test Text 4']"));
        assertEquals("Test Text 4", testText4_2.getText());
//        1-2 ways to find text: "Test Text 5"
        WebElement testText5_1 = driver.findElement(By.cssSelector("#test2 .Test:first-child"));
        assertEquals("Test Text 5", testText5_1.getText());
        WebElement testText5_2 = driver.findElement(By.xpath("//div[@id='test2']/p[1]"));
        assertEquals("Test Text 5", testText5_2.getText());
//        1-2 ways to find text: "This is also a button"*/
        WebElement alsoButton = driver.findElement(By.xpath("//input[@value='This is also a button']"));
        assertEquals("This is also a button", alsoButton.getAttribute("value"));
        WebElement alsoButton2 = driver.findElement(By.xpath("//input[@name='randomButton2']"));
        assertEquals("This is also a button", alsoButton2.getAttribute("value"));
    }

    @Test
    public void findElementByCssName() {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        WebElement heading1 = driver.findElement(By.cssSelector("#heading_2"));
        assertEquals("Heading 2 text", heading1.getText());
        WebElement heading2 = driver.findElement(By.cssSelector("h2#heading_2"));
        assertEquals("Heading 2 text", heading2.getText());
//        1-2 ways to find text: "Test Text 1"
        WebElement testText1_1 = driver.findElement(By.cssSelector("#test1>.test"));
        assertEquals("Test Text 1", testText1_1.getText());
        WebElement testText1_2 = driver.findElement(By.cssSelector("#test1>p:first-child"));
        assertEquals("Test Text 1", testText1_2.getText());
//        1-2 ways to find text: "Test Text 2"
        WebElement testText2_1 = driver.findElement(By.cssSelector("#test1>.twoTest"));
        assertEquals("Test Text 2", testText2_1.getText());
        WebElement testText2_2 = driver.findElement(By.cssSelector("#test1>p:last-child"));
        assertEquals("Test Text 2", testText2_2.getText());
//        1-2 ways to find text: "Test Text 3"
        WebElement testText3_1 = driver.findElement(By.cssSelector("#test3>.test"));
        assertEquals("Test Text 3", testText3_1.getText());
        WebElement testText3_2 = driver.findElement(By.cssSelector("#test3 p.test:first-child"));
        assertEquals("Test Text 3", testText3_2.getText());
//        1-2 ways to find text: "This is also a button"
        WebElement alsoButton = driver.findElement(By.cssSelector("input[value='This is also a button']"));
        assertEquals("This is also a button", alsoButton.getAttribute("value"));
        WebElement alsoButton2 = driver.findElement(By.cssSelector("input[name='randomButton2']"));
        assertEquals("This is also a button", alsoButton2.getAttribute("value"));
    }
}
