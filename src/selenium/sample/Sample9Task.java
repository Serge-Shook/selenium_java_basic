package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.GreenAndBluePage;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class Sample9Task {
    WebDriver driver;
    GreenAndBluePage greenAndBluePage;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement startGreenButton = driver.findElement(By.id("start_green"));
        startGreenButton.click();
//         * 2) check that button does not appear,
        assertFalse(startGreenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreenElement = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreenElement.getText());
//         * 3) check that both button
        Thread.sleep(6000);
        assertFalse(startGreenButton.isDisplayed());
//         * and loading text is not seen,
        assertFalse(loadingGreenElement.isDisplayed());
//         * success is seen instead "Green Loaded"
        String finishGreenText = driver.findElement(By.id("finish_green")).getText();
        assertEquals("Green Loaded", finishGreenText);
    }

    @Test
    public void pomTest() throws InterruptedException {
        greenAndBluePage = PageFactory.initElements(driver, GreenAndBluePage.class);

        greenAndBluePage.clickStartGreen();

        assertFalse(greenAndBluePage.isStartGreenDisplayed());

        assertEquals("Loading green...", greenAndBluePage.getLoadingGreenText());

        Thread.sleep(6000);

        assertFalse(greenAndBluePage.isStartGreenDisplayed());

        assertFalse(greenAndBluePage.isLoadingGreenDisplayed());

        assertEquals("Green Loaded", greenAndBluePage.getLoadGreenSuccessText());
    }

    @Test
    public void loadGreenImplicit() throws Exception {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//         TODO:
//         * 1) click on start loading green button
        WebElement startGreenButton = driver.findElement(By.id("start_green"));
        startGreenButton.click();
//         * 2) check that button does not appear,
        assertFalse(startGreenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreenElement = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreenElement.getText());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement finishGreenElement = driver.findElement(By.id("finish_green")); // imp wait here
        assertEquals("Green Loaded", finishGreenElement.getText());
        assertFalse(startGreenButton.isDisplayed());
        assertFalse(loadingGreenElement.isDisplayed());
    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
        WebElement startGreenButton = driver.findElement(By.id("start_green"));
        startGreenButton.click();
//         * 2) check that button does not appear,
        assertFalse(startGreenButton.isDisplayed());
//         * but loading text is seen instead   "Loading green..."
        WebElement loadingGreenElement = driver.findElement(By.id("loading_green"));
        assertEquals("Loading green...", loadingGreenElement.getText());
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"
        WebElement finishGreenElement = new WebDriverWait(driver, 10) // exp wait here
                .until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green")));
        assertEquals("Green Loaded", finishGreenElement.getText());
        assertFalse(startGreenButton.isDisplayed());
        assertFalse(loadingGreenElement.isDisplayed());
    }

    @Test
    public void loadGreenAndBlueBonus() throws InterruptedException {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */
        WebDriverWait wait = new WebDriverWait(driver, 15);

        // 0) wait until button to load green and blue appears
        WebElement startLoadGreenAndBlueButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        // 1) click on start loading green and blue button
        startLoadGreenAndBlueButton.click();

        // 2) check that button does not appear, but loading text is seen instead for green
        assertFalse(startLoadGreenAndBlueButton.isDisplayed());

        WebElement loadingGreenElement = driver.findElement(By.id("loading_green_without_blue"));
        assertTrue("Loading green... displayed", loadingGreenElement.isDisplayed());
        assertEquals("Loading green...", loadingGreenElement.getText());

        // 3) check that button does not appear, but loading text is seen instead for green and blue
        WebElement loadingBlueElement = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("loading_green_with_blue")));
        assertFalse("Start loading G & B not visible", startLoadGreenAndBlueButton.isDisplayed());
        assertTrue("Loading green... displayed", loadingGreenElement.isDisplayed());
        assertEquals("Loading blue...", loadingBlueElement.getText());
        assertTrue("Loading blue... displayed", loadingBlueElement.isDisplayed());

        // 4) check that button and loading green does not appear,
        //    but loading text is seen instead for blue and success for green is seen

        WebElement greenLoadedSuccess = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green_and_blue")));
        assertFalse("Start loading G & B not visible", startLoadGreenAndBlueButton.isDisplayed());
        assertFalse("Loading green... displayed", loadingGreenElement.isDisplayed());

        System.out.println("load B no G " + driver.findElement(By.id("loading_blue_without_green")).getText());
        System.out.println("load G no B " + driver.findElement(By.id("loading_green_without_blue")).getText());
        System.out.println("load G + B " + driver.findElement(By.id("loading_green_with_blue")).getText());
        System.out.println("finish " + driver.findElement(By.id("finish_green_and_blue")).getText());

        Thread.sleep(3000);
    }

}