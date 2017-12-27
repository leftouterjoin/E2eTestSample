package pkg1.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHeaderDetail {
	@FindBy(css = "[data-test-id='OrderHeader_OrderNo']")
	public WebElement orderHeader_OrderNo;
	@FindBy(css = "[data-test-id='OrderHeader_CustomerId']")
	public WebElement orderHeader_CustomerId;
	@FindBy(css = "[data-test-id='Save']")
	public WebElement save;
	@FindBy(css = "[data-test-id='NewOrderDetailLink']")
	public WebElement newOrderDetailLink;
}
