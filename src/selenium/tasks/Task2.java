package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.junit.Assert.*;

public class Task2 {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
        WebElement nameField = driver.findElement(By.name("name"));
        WebElement ageField = driver.findElement(By.name("age"));

//         TODO:
//         check that all field are empty and no tick are clicked
        assertTrue("Name is empty", nameField.getText().isEmpty());
        assertTrue("Age is empty", ageField.getText().isEmpty());

        for (WebElement checkBox : driver.findElements(By.cssSelector("input[type='checkbox']"))) {
            assertFalse(checkBox.isSelected());
        }

//         "Don't know" is selected in "Genre"
        WebElement genreRadioDontKnow = driver.findElement(By.cssSelector("input[name='gender'][value='']"));
        assertTrue("Don't know selected in genre", genreRadioDontKnow.isSelected());

//         "Choose your option" in "How do you like us?"

        Select howDoYouLikeItSelect = new Select(driver.findElement(By.name("option")));
        assertEquals("Choose your option", howDoYouLikeItSelect.getFirstSelectedOption().getText());

//         check that the button send is blue with white letters
        WebElement sendButton = driver.findElement(By.xpath("//button[.='Send']"));
        assertEquals("Blue", "rgba(33, 150, 243, 1)", sendButton.getCssValue("background-color"));
        assertEquals("White text", "rgba(255, 255, 255, 1)", sendButton.getCssValue("color"));
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
        WebElement sendButton = driver.findElement(By.xpath("//button[.='Send']"));
        sendButton.click();
//         check fields are empty or null
        for (WebElement valueElement : driver.findElements(By.cssSelector(".description>p>span"))) {
            String value = valueElement.getText();
            assertTrue("Empty or null", value.isEmpty() || value.equals("null"));
        }

//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[.='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[.='No']"));

        assertEquals("Red", "rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("White", "rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));

        assertEquals("Green", "rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("White", "rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
        String name = "Simon";
        String age = "41";
        String language = "English";
        String genre = "male";
        String option = "Good";
        String comment = "I like this event, will look for more like this";
//         TODO:
//         fill the whole form, click "Send"
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("age")).sendKeys(age);

        driver.findElement(By.cssSelector(String.format("input[name='language'][value='%s']", language))).click();

        driver.findElement(By.cssSelector(String.format("input[name='gender'][value='%s']", genre))).click();

        Select howDoYouLikeItSelect = new Select(driver.findElement(By.name("option")));
        howDoYouLikeItSelect.selectByVisibleText(option);

        driver.findElement(By.name("comment")).sendKeys(comment);

        driver.findElement(By.xpath("//button[.='Send']")).click();

//         check fields are filled correctly

        assertEquals(name, driver.findElement(By.id("name")).getText());
        assertEquals(age, driver.findElement(By.id("age")).getText());
        assertEquals(language, driver.findElement(By.id("language")).getText());
        assertEquals(genre, driver.findElement(By.id("gender")).getText());
        assertEquals(option, driver.findElement(By.id("option")).getText());
        assertEquals(comment, driver.findElement(By.id("comment")).getText());
//         check button colors
//         (green with white letter and red with white letters)
        WebElement yesButton = driver.findElement(By.xpath("//button[.='Yes']"));
        WebElement noButton = driver.findElement(By.xpath("//button[.='No']"));

        assertEquals("Green", "rgba(76, 175, 80, 1)", yesButton.getCssValue("background-color"));
        assertEquals("White", "rgba(255, 255, 255, 1)", yesButton.getCssValue("color"));

        assertEquals("Red", "rgba(244, 67, 54, 1)", noButton.getCssValue("background-color"));
        assertEquals("White", "rgba(255, 255, 255, 1)", noButton.getCssValue("color"));
    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
        String name = "Simon";
//         TODO:
//         enter only name
        driver.findElement(By.name("name")).sendKeys(name);
//         click "Send"
        driver.findElement(By.xpath("//button[.='Send']")).click();
//         click "Yes"
        driver.findElement(By.xpath("//button[.='Yes']")).click();
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        WebElement messageElement = driver.findElement(By.id("message"));
        WebElement messageContainerElement = driver.findElement(By.xpath("//h2[@id='message']/parent::div"));
        String expectedMessage = String.format("Thank you, %s, for your feedback!", name);
        assertEquals(expectedMessage, messageElement.getText());
        assertEquals(
                "Green",
                "rgba(76, 175, 80, 1)",
                messageContainerElement.getCssValue("background-color"));
        assertEquals(
                "White",
                "rgba(255, 255, 255, 1)",
                messageElement.getCssValue("color"));
    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
        driver.findElement(By.xpath("//button[.='Send']")).click();
//         click "Yes"
        driver.findElement(By.xpath("//button[.='Yes']")).click();
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background
        WebElement messageElement = driver.findElement(By.id("message"));
        WebElement messageContainerElement = driver.findElement(By.xpath("//h2[@id='message']/parent::div"));

        assertEquals("Thank you for your feedback!", messageElement.getText());
        assertEquals(
                "Green",
                "rgba(76, 175, 80, 1)",
                messageContainerElement.getCssValue("background-color"));
        assertEquals(
                "White",
                "rgba(255, 255, 255, 1)",
                messageElement.getCssValue("color"));
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
        String name = "Anna";
        String age = "21";
        String language = "Spanish";
        String genre = "female";
        String option = "Ok, i guess";
        String comment = "You can do better then this. Better luck next time.";
//         TODO:
//         fill the whole form
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("age")).sendKeys(age);

        driver.findElement(By.cssSelector(String.format("input[name='language'][value='%s']", language))).click();

        driver.findElement(By.cssSelector(String.format("input[name='gender'][value='%s']", genre))).click();

        Select howDoYouLikeItSelect = new Select(driver.findElement(By.name("option")));
        howDoYouLikeItSelect.selectByVisibleText(option);

        driver.findElement(By.name("comment")).sendKeys(comment);

//         click "Send"
        driver.findElement(By.xpath("//button[.='Send']")).click();
//         click "No"
        driver.findElement(By.xpath("//button[.='No']")).click();
//         check fields are filled correctly
        assertEquals(name, driver.findElement(By.name("name")).getAttribute("value"));
        assertEquals(age, driver.findElement(By.name("age")).getAttribute("value"));

        assertTrue(
                "Language",
                driver.findElement(By.cssSelector(
                        String.format("input[name='language'][value='%s']", language))).isSelected());

        assertTrue(
                "Genre",
                driver.findElement(By.cssSelector(
                        String.format("input[name='gender'][value='%s']", genre))).isSelected());

        howDoYouLikeItSelect = new Select(driver.findElement(By.name("option")));
        assertEquals(option, howDoYouLikeItSelect.getFirstSelectedOption().getText());

        assertEquals(comment, driver.findElement(By.name("comment")).getAttribute("value"));

    }
}
