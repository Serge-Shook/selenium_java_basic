package selenium.pages;

import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GreenAndBluePage extends GenericSamplePage {

    @FindBy(id = "start_green")
    private WebElement startGreenButton;

    @FindBy(id = "loading_green")
    private WebElement loadingGreenElement;

    @FindBy(id = "finish_green")
    private WebElement loadGreenSuccess;

    public void clickStartGreen() {
        startGreenButton.click();
    }

    public boolean isStartGreenDisplayed() {
        return startGreenButton.isDisplayed();
    }

    public String getLoadingGreenText() {
        return loadingGreenElement.getText();
    }

    public boolean isLoadingGreenDisplayed() {
        return loadingGreenElement.isDisplayed();
    }

    public String getLoadGreenSuccessText() {
        return loadGreenSuccess.getText();
    }

}
