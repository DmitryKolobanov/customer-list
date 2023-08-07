package mailgrabber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.net.FileNameMap;
import java.time.Duration;
import java.util.List;

public class BaseFunc {
    private WebDriver browser;
    private WebDriverWait wait;

    public BaseFunc() {            //constructor
        System.setProperty("webdriver.chrome.driver", "C://QA2/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        browser = new ChromeDriver(options);
        browser.manage().window().maximize();
        browser.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        wait = new WebDriverWait(browser, Duration.ofSeconds(10));
    }

    public void openUrl(String url) {

        browser.get(url);
    }

    public void pageRefresh()   {
        browser.navigate().refresh();
    }

    public void newTab()    {
        browser.switchTo().newWindow(WindowType.TAB);
    }

    public void stepBack()  {
        browser.navigate().back();
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void select(By locator, String value) {
        WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Select select = new Select(we);
        select.selectByValue(value);
    }

    public void selectByText(By locator, String text) {
        WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Select select = new Select(we);
        select.selectByVisibleText(text);
    }

    public void type(By locator, String text) {
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        input.clear();
        input.sendKeys(text);
    }

    public void type(By locator, int text) {
        type(locator, String.valueOf(text));
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void hoverElement(By locator) {
        WebElement l = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        Actions a = new Actions(browser);
        a.moveToElement(l).build().perform();
    }
    public void hoverElementFromList(By locator, int position)    {
        WebElement l = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)).get(position);
        Actions a = new Actions(browser);
        a.moveToElement(l).build().perform();
    }
    public void clickElementFromList (By locator, int position)   {
       wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)).get(position).click();
    }

    public List<WebElement> findElements(By locator) {
        return browser.findElements(locator);
    }

    public List<WebElement> findElementFromList (By locator)  {
       return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

//    public List<WebElement> findSeparateElementFromList (By locator, int position)  {
//        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator)).get(position);
//    }
    public void waitForElementsCountToBe(By locator, int count) {
        wait.until(ExpectedConditions.numberOfElementsToBe(locator, count));
    }

    public void saveToFile(String TextToSave, String FileName) throws Exception {
        FileWriter fw = new FileWriter(FileName, true);
        fw.write(TextToSave +"\n");
        fw.close();
    }

    public void closeBrowser() {
        browser.close();
    }


}

