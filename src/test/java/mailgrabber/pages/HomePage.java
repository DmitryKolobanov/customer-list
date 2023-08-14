package mailgrabber.pages;

import mailgrabber.BaseFunc;
import org.openqa.selenium.By;

public class HomePage {
    private final By CATEGORY_LINK = By.xpath(".//div[@class = 'industry-cat']/h2/a");

    private BaseFunc baseFunc;


    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectCategory(int Category)    {
        baseFunc.clickElementFromList(CATEGORY_LINK, Category);
    }

}
