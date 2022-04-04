package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.junit.Assert.*;

public class Sample7Task {
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
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
        WebElement checkbox1 = driver.findElement(By.id("vfb-6-0"));
        WebElement checkbox2 = driver.findElement(By.id("vfb-6-1"));
        WebElement checkbox3 = driver.findElement(By.id("vfb-6-2"));

        assertFalse(checkbox1.isSelected() || checkbox2.isSelected() || checkbox3.isSelected());
//        tick  "Option 2"
        checkbox2.click();
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
        assertFalse(checkbox1.isSelected() || checkbox3.isSelected());
        assertTrue(checkbox2.isSelected());
//        tick  "Option 3"
        checkbox3.click();
//        click result
        driver.findElement(By.id("result_button_checkbox")).click();
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed
        String actualText = driver.findElement(By.id("result_checkbox")).getText();
        assertEquals("You selected value(s): Option 2, Option 3", actualText);
    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
        WebElement radioButton1 = driver.findElement(By.id("vfb-7-1"));
        WebElement radioButton2 = driver.findElement(By.id("vfb-7-2"));
        WebElement radioButton3 = driver.findElement(By.id("vfb-7-3"));
//        check that none of the radio are selected
        assertFalse(radioButton1.isSelected() || radioButton2.isSelected() || radioButton3.isSelected());
//        select  "Option 3"
        radioButton3.click();
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
        assertFalse(radioButton1.isSelected() || radioButton2.isSelected());
        assertTrue(radioButton3.isSelected());
//        select  "Option 1"
        radioButton1.click();
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
        assertFalse(radioButton2.isSelected() || radioButton3.isSelected());
        assertTrue(radioButton1.isSelected());
//        click result
        driver.findElement(By.id("result_button_ratio")).click();
//        check that 'You selected option: Option 1' text is being displayed
        String actualMessage = driver.findElement(By.id("result_radio")).getText();
        assertEquals("You selected option: Option 1", actualMessage);
    }

    @Test
    public void selectOption() throws Exception {
        Select selectOption = new Select(driver.findElement(By.id("vfb-12")));
//        select "Option 3" in Select
        selectOption.selectByVisibleText("Option 3");
//        check that selected option is "Option 3"
        assertEquals(selectOption.getFirstSelectedOption().getText(), "Option 3");
//        select "Option 2" in Select
        selectOption.selectByVisibleText("Option 2");
//        check that selected option is "Option 2"
        assertEquals(selectOption.getFirstSelectedOption().getText(), "Option 2");
//        click result
        driver.findElement(By.id("result_button_select")).click();
//        check that 'You selected option: Option 2' text is being displayed
        String actualMessage = driver.findElement(By.id("result_select")).getText();
        assertEquals("You selected option: Option 2", actualMessage);
    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
        WebElement dateField = driver.findElement(By.id("vfb-8"));
        dateField.click();

        while (!driver.findElement(By.className("ui-datepicker-title")).getText().equals("July 2007")) {
            driver.findElement(By.cssSelector("a[data-handler='prev']")).click();
        }

        driver.findElement(By.className("ui-datepicker-calendar")).findElement(By.linkText("4")).click();
//        check that correct date is added
        assertEquals("07/04/2007", dateField.getAttribute("value"));

        driver.findElement(By.id("result_button_date")).click();

        String actualMessage = driver.findElement(By.id("result_date")).getText();
        assertEquals("You entered date: 07/04/2007", actualMessage);
    }

    @Test
    public void chooseDateViaTextBoxBonus() {
//         TODO:
//        enter date '2 of May 1959' using text
        WebElement dateField = driver.findElement(By.id("vfb-8"));
        dateField.sendKeys("05/02/1959" + Keys.ENTER); // <- option to hide calendar

//        driver.findElement(By.xpath("//h2[.='Date']")).click(); // option to hide calendar
        driver.findElement(By.id("result_button_date")).click();

//        check that correct date is added
        assertEquals("05/02/1959", dateField.getAttribute("value"));

        String actualMessage = driver.findElement(By.id("result_date")).getText();
        assertEquals("You entered date: 05/02/1959", actualMessage);
    }
}
