package pkg1.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	@FindBy(css = "[data-test-id='UserNameInput']")
	public WebElement userNameInput;
	@FindBy(css = "[data-test-id='PasswordInput']")
	public WebElement passwordInput;
	@FindBy(css = "[data-test-id='LoginButton']")
	public WebElement loginButton;

	public void login(final String username, final String password) {
		userNameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
	}
}
