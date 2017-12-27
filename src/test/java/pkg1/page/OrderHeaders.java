package pkg1.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHeaders {
	@FindBy(css = "[data-test-id='Create a new Order Header']")
	public WebElement createANewOrderHeader;

	@FindBy(css = "[data-test-id='SearchInput']")
	public WebElement searchInput;
	@FindBy(css = "[data-test-id='Search']")
	public WebElement search;
	@FindBy(css = "*.Counter_Message")
	public WebElement counter_Message;
	@FindBy(css = "[data-test-id='OrderNo']")
	public WebElement orderNo;
}
