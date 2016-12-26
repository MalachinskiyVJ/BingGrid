import org.openqa.selenium.WebDriver;

/**
 * Created by Вова on 25.12.2016.
 */
public class HeadLineResultPage {


    private WebDriver driver;

    public HeadLineResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public SearchResultPage goBack() {
        driver.navigate().back();
        return new SearchResultPage(driver);
    }

}
