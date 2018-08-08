package pkg1.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonElement {
	@FindBy(className = "Login_Info_Logout")
	public WebElement login_Info_Logout;
	@FindBy(css = "[data-test-id='Customers']")
	public WebElement menuCustomers;
	@FindBy(css = "[data-test-id='Products']")
	public WebElement menuProducts;
	@FindBy(css = "[data-test-id='Order Headers']")
	public WebElement menuOrderHeaders;
	@FindBy(className = "Feedback_Message_Text")
	public WebElement feedback_Message_Text;
	@FindBy(className = "Feedback_Message_Wrapper_Close")
	public WebElement feedback_Message_Wrapper_Close;
	@FindBy(className = "Feedback_AjaxWait")
	public WebElement feedback_AjaxWait;
}
