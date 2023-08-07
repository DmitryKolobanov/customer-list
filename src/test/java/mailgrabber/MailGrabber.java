package mailgrabber;

import mailgrabber.model.TestData;
import mailgrabber.pages.CustomerList;
import mailgrabber.pages.CustomerPage;
import mailgrabber.pages.HomePage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class MailGrabber {

    private  final String URL = "http://1182.lv/nozaru-katalogs";
    private int CategoryIndex;
    private int Category = 1;
    private int CustomerListIndex;

    private double PageCount;
    private String SubcategoryLink;
    private String SubcategoryName;
    private String CustomerMail;
    private String FileName = "C:/QA/Auto.txt";
//    private final By SUB_CATEGORY_LINK = By.xpath(".//div[@class= 'keyword-block']/ul/li/a");
    private final By SUB_CATEGORY_LINK = By.xpath (".//div[@class = 'industry-cat']/ul/li/a");
    private final By CUSTOMER_LINK = By.xpath(".//div[contains(@class, 'search-item')]");
    private final By MAIL = By.xpath(".//span[@class = 'prop-email']/a");
    private final By NEXT_BUTTON = By.linkText("→");
    private BaseFunc baseFunc = new BaseFunc();


    @Test

    public void customerMailCollection () throws Exception {

        baseFunc.openUrl(URL);
        HomePage homePage = new HomePage(baseFunc);
        homePage.selectCategory(Category);

        CategoryIndex = baseFunc.findElements(SUB_CATEGORY_LINK).size();
        System.out.println("Subcategory count: " + CategoryIndex);

        CustomerList customerList = new CustomerList(baseFunc);
        CustomerPage customerPage = new CustomerPage(baseFunc);
        for (int i = 61; i < CategoryIndex; i++) {                      //Start position of subcategory


//            SubcategoryLink = baseFunc.findElementFromList(SUB_CATEGORY_LINK).get(i).getAttribute("href");
//            SubcategoryName = baseFunc.findElementFromList(SUB_CATEGORY_LINK).get(i).getText();

            SubcategoryLink = customerList.getSubcategoryLink(i);
            SubcategoryName = customerList.getSubcategoryName(i);

            System.out.println("\n" + SubcategoryName);
            baseFunc.saveToFile(SubcategoryName + "\n", FileName);
            baseFunc.openUrl(SubcategoryLink);

            PageCount = customerList.getCustomerCount();
            System.out.println("Page count: " + PageCount);

            for (int k = 0; k<PageCount; k++) {
                CustomerListIndex = baseFunc.findElements(CUSTOMER_LINK).size();
                System.out.println("\nPage number: " + k);
                for (int j = 0; j < CustomerListIndex; j++) {
                    customerList.selectCustomer(j);
                    try {
//                        CustomerMail = baseFunc.findElement(MAIL).getAttribute("href").substring(7);
                        CustomerMail = customerPage.getMail();
                        System.out.println(CustomerMail);
                        baseFunc.saveToFile(CustomerMail + "\n", FileName);
                    } catch (TimeoutException e) {
                        System.out.println("No e-mail found!");
                    }
                    baseFunc.stepBack();
//                    baseFunc.waitForElementsCountToBe(CUSTOMER_LINK, CustomerListIndex);

                }
                try {
                    baseFunc.click(NEXT_BUTTON);
                } catch (TimeoutException e) {
                    System.out.println("\nEnd of Subcategory " + SubcategoryName + " (# " + i + ")");
                    baseFunc.saveToFile("End of Subcategory " + SubcategoryName + "\n\n", FileName);
                }
            }

            baseFunc.openUrl(URL);
            homePage.selectCategory(Category);
        }

    }
}
