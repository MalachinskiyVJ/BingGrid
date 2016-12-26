import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Вова on 25.12.2016.
 */
public class SearchPage {

    private static final String BING_LOGO = "//div[@class='hp_sw_logo hpcLogoWhite']";
    private static final String SEARCH_FIELD = "//input[@id='sb_form_q']";
    private static final String SEARCH_BUTTON = "//input[@id='sb_form_go']";
    private static final String FULL_WORD = "//li[@class='sa_sg' and @query='automation']";


    private WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement getBingLogo(){
        return driver.findElement(By.xpath(BING_LOGO));
    }

    public WebElement getSearchField(){
        return driver.findElement(By.xpath(SEARCH_FIELD));
    }
    public WebElement getSearchButton(){
        return driver.findElement(By.xpath(SEARCH_BUTTON));
    }
    public WebElement getFullWord(){
        return driver.findElement(By.xpath(FULL_WORD));
    }

public  void searchWord(String bingSearch){
    getSearchField().sendKeys(bingSearch);
}
    public SearchResultPage fullwordSubmit(){
        getFullWord().click();
        return new SearchResultPage(driver);
    }

}

