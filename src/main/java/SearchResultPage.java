import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchResultPage {

    private static final String LINKS = ".//*[@id='b_results']//cite";
    private static final String HEADLINES = ".//*[@id='b_results']//h2";


    private WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    //List<WebElement> links = driver.findElements(By.xpath(LINKS));
    public List<WebElement> getLinks() {
        return driver.findElements(By.xpath(LINKS));
    }

    public List<WebElement> getHeadlines() {
        return driver.findElements(By.xpath(HEADLINES));
    }


    public void saveLinks() throws IOException {
        FileWriter fstream = new FileWriter("d:\\sites.txt");
        BufferedWriter out = new BufferedWriter(fstream);

        for (String link : readLinks()) {
            out.write(link + "\r\n");
            out.newLine();
        }
        out.close();
    }

    public List <String> readLinks(){
        List <String> links = new ArrayList<String>();
        for (WebElement element : getLinks()) {
            links.add(element.getText());
        }
        return links;
    }

    public void pageHeadlinesClick() {
        for (WebElement element : getHeadlines()) {


            for (int i = 0; i < getHeadlines().size(); i++) {
                element.click();
                HeadLineResultPage re = new HeadLineResultPage(driver);
                re.goBack();

            }
            //(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(element));


        }

    }


}


