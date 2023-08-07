package mailgrabber.pages;

import mailgrabber.BaseFunc;
import mailgrabber.model.TestData;
import org.openqa.selenium.By;

public class HomePage {

//    private final By CATEGORY_LINK = By.xpath(".//ol[@class = 'group-navigation']/li");
    private final By CATEGORY_LINK = By.xpath(".//div[@class = 'industry-cat']/h2/a");
    private final By SUB_CATEGORY_LINK = By.xpath(".//div[@class= 'keyword-block']/ul/li/a");

    private BaseFunc baseFunc;

    TestData testData = new TestData(0,2, "C:/QA/Subcategory.txt");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void selectCategory(int Category)    {
        baseFunc.clickElementFromList(CATEGORY_LINK, Category);
    }

    public void selectSubCategory() throws Exception {
        String SubcategoryLink = baseFunc.findElementFromList(SUB_CATEGORY_LINK).get(testData.getSubcategory()).
                getAttribute("href");
        String SubcategoryName = baseFunc.findElementFromList(SUB_CATEGORY_LINK).get(testData.getSubcategory()).
                getText();
        baseFunc.saveToFile(SubcategoryName + "\n", testData.getFileName());
        baseFunc.saveToFile(SubcategoryLink, testData.getFileName());
        baseFunc.openUrl(SubcategoryLink);

    }
}
