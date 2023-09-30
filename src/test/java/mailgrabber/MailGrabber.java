package mailgrabber;

import mailgrabber.pages.CustomerList;
import mailgrabber.pages.CustomerPage;
import mailgrabber.pages.HomePage;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class MailGrabber {

    private  final String URL = "http://1182.lv/nozaru-katalogs";
    private int SubCategoryIndex;
    private int Category = 5;
    private int CustomerListIndex;

    private double PageCount;
    private String SubcategoryLink;
    private String SubcategoryName;
    private String CustomerMail;
    private String FileName = "C:/QA/6_Home.txt";

    private final By NEXT_BUTTON = By.linkText("→");
    private BaseFunc baseFunc = new BaseFunc();


    @Test

    public void customerMailCollection () throws Exception {

        baseFunc.openUrl(URL);
        HomePage homePage = new HomePage(baseFunc);
        homePage.selectCategory(Category);

        CustomerList customerList = new CustomerList(baseFunc);
        CustomerPage customerPage = new CustomerPage(baseFunc);

        SubCategoryIndex = customerList.getSubcategoryIndex();
        System.out.println("Subcategory count: " + SubCategoryIndex);

        for (int i = 0; i < SubCategoryIndex; i++) {                      //Start position of subcategory

            SubcategoryLink = customerList.getSubcategoryLink(i);
            SubcategoryName = customerList.getSubcategoryName(i);

            System.out.println("\n" + SubcategoryName);
            baseFunc.saveToFile(SubcategoryName + "\n", FileName);
            baseFunc.openUrl(SubcategoryLink);

            PageCount = customerList.getCustomerCount();
            System.out.println("Page count: " + PageCount);

            for (int k = 0; k<PageCount; k++) {            //Start page of subcategory

                CustomerListIndex = customerList.getCustomerListIndex();

                System.out.println("\nPage number: " + k + " of " + PageCount);
                for (int j = 0; j < CustomerListIndex; j++) {           //Start position on page
                    customerList.selectCustomer(j);
                    try {
                        CustomerMail = customerPage.getMail();
                        System.out.println(CustomerMail);
                        baseFunc.saveToFile(CustomerMail + "\n", FileName);
                    } catch (TimeoutException e) {
                        System.out.println("No e-mail found!");
                    }
                    baseFunc.stepBack();
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
