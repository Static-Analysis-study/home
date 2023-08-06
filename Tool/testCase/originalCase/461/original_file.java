
public class SomePage {
    @org.openqa.selenium.support.FindBy(id = "id1")
    private WebElement field1;

    @org.openqa.selenium.support.FindBys(value = {@org.openqa.selenium.support.FindByFindBy(id = "id2"),
                                                       @org.openqa.selenium.support.FindBy(xpath = "//div/table")})
    private WebElement table;
}
