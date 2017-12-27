package pkg1.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class OrderHeader_OrderDetail_PopupEditor {
	@FindBys({@FindBy(className = "os-internal-ui-dialog-content"), @FindBy(tagName = "iframe") })
	public WebElement iframe;
	@FindBy(css = "[data-test-id='OrderDetail_ProductId']")
	public WebElement orderDetail_ProductId;
	@FindBy(css = "[data-test-id='OrderDetail_Quantity']")
	public WebElement orderDetail_Quantity;
	@FindBy(css = "[data-test-id='Save']")
	public WebElement save;
}
