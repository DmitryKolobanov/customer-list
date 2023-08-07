package mailgrabber.pages;

import mailgrabber.BaseFunc;
import org.openqa.selenium.By;

public class CustomerPage {
    private final By MAIL = By.xpath(".//span[@class = 'prop-email']/a");

    private BaseFunc baseFunc;

    public CustomerPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }
    public  String  getMail()   {
        return baseFunc.findElement(MAIL).getAttribute("href").substring(7);
    }


}
