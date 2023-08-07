package mailgrabber.pages;

import mailgrabber.BaseFunc;
import org.openqa.selenium.By;

public class CustomerList {

    private final By CUSTOMER_SELECT = By.xpath(".//div[@class = 'si-img']");
    private final By CUSTOMER_TITLE = By.xpath(".//h2[@class = 'si-title']");
    private final By CUSTOMER_COUNT = By.xpath(".//div[@id ='toolbar']/div[@class = 'title']/span");
    private final By SUB_CATEGORY_LINK = By.xpath (".//div[@class = 'industry-cat']/ul/li/a");
    private BaseFunc baseFunc;

    public CustomerList(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectCustomer(int CustomerIndex)   {
        System.out.println("\n" + CustomerIndex);
        System.out.println(baseFunc.findElements(CUSTOMER_TITLE).get(CustomerIndex).getText());
        baseFunc.clickElementFromList(CUSTOMER_SELECT, CustomerIndex);
    }

    public double getCustomerCount()  {
        String CounterOnPage = baseFunc.findElements(CUSTOMER_COUNT).get(1).getText();
        System.out.println("Customer count: " + CounterOnPage);
        return Math.ceil(Double.parseDouble(CounterOnPage)/10);
    }

    public String getSubcategoryLink(int i) {
        return baseFunc.findElementFromList(SUB_CATEGORY_LINK).get(i).getAttribute("href");
    }

    public String getSubcategoryName(int i) {
        return baseFunc.findElementFromList(SUB_CATEGORY_LINK).get(i).getText();
    }


}
