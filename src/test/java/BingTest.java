import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.testng.Assert.assertEquals;


public class BingTest {

    private WebDriver driver;

    @DataProvider(name = "keywords")
    public Object[][] data() throws Exception {
        HashMap<String, String[]> dataSet = new ForTestData(System.getProperty("user.dir") + "\\testdata.txt").getData();

        String bingSearchStrings[] = dataSet.get("bingSearch");

        int size = bingSearchStrings.length;

        Object[][] creds = new Object[size][1];
        for (int i = 0; i < size; i++) {
            creds[i][0] = bingSearchStrings[i];

        }
        return creds;

    }

    @BeforeTest
    public void setUp() throws Exception {
        //String property = System.getProperty(("user.dir") + "\\geckodriver.exe");
        //System.setProperty("webdriver.gecko.driver",property);
        System.setProperty("webdriver.firefox.marionette", ("user.dir") + "\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.bing.com/");
    }

    @Test(dataProvider = "keywords", description = "Bing_Test")
    public void search(String bingSearch) throws IOException, InterruptedException {
        SearchPage searchpage = new SearchPage(driver);
        SearchResultPage resultpage = new SearchResultPage(driver);
        HeadLineResultPage headlineresultpage = new HeadLineResultPage(driver);

        Assert.assertTrue(searchpage.getBingLogo().isDisplayed(), "Bing logo is displayed on the page");
        Assert.assertTrue(searchpage.getSearchField().isEnabled());
        Assert.assertTrue(searchpage.getSearchButton().isEnabled());

        searchpage.searchWord(bingSearch);
        searchpage.fullwordSubmit();

        resultpage.saveLinks();
        final List<String> links = resultpage.readLinks();
        System.out.println(resultpage.getHeadlines().size());
        final int linesCount = resultpage.getHeadlines().size();
        final String searchLink = driver.getCurrentUrl();

        for (int i=0; i < linesCount;i++) {
            List<WebElement> headers =resultpage.getHeadlines();
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(headers.get(i)));
            Assert.assertTrue(headers.get(i).isDisplayed());
            headers.get(i).click();

            for (int j =0; j<40;j++){
                if (links.get(i).contains(driver.getCurrentUrl())){
                    if(j==39)
                        assertEquals(links.get(i),driver.getCurrentUrl());
                    Thread.sleep(250);
                }else{
                    break;
                }
            }


            driver.get(searchLink);

        }


    }

    @AfterTest
    public void tearDown()  {
        driver.quit();
    }
}