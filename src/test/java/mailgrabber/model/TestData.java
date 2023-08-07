package mailgrabber.model;

public class TestData {
    private int Category;
    private int Subcategory;
    private String FileName;

    public TestData (int Category, int Subcategory, String FileName) {
        this.Category = Category;
        this.Subcategory = Subcategory;
        this.FileName = FileName;
    }

    // ------ Getters and Setters ----------

    public int getCategory() {
        return Category;
    }
    public int getSubcategory() {
        return Subcategory;
    }
    public String getFileName() {
        return FileName;
    }
}
