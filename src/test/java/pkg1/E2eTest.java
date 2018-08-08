package pkg1;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pkg1.page.CommonElement;
import pkg1.page.LoginPage;
import pkg1.page.OrderHeaderDetail;
import pkg1.page.OrderHeader_OrderDetail_PopupEditor;
import pkg1.page.OrderHeaders;

@RunWith(Parameterized.class)
@FixMethodOrder (MethodSorters.NAME_ASCENDING)
public class E2eTest extends Application {
	@Rule
	public TestDescription td = new TestDescription();
	@Rule
	public ExternalRule externalRule = new ExternalRule(td);

	private LoginPage loginPage;
	private CommonElement commonElement;
	private OrderHeaders orderHeaders;
	private OrderHeaderDetail orderHeaderDetail;
	private OrderHeader_OrderDetail_PopupEditor orderHeader_OrderDetail_PopupEditor;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Parameters(name = "{0}")
	public static Iterable<Class<? extends WebDriver>> getParmeters() {
		return TestSupport.getWebDriverList();
	}

	public E2eTest(Class<? extends WebDriver> clazz) {
		td.driver = TestSupport.newWebDriver(clazz);
	}

	@Before
	public void setUp() {
		loginPage = PageFactory.initElements(td.driver, LoginPage.class);
		commonElement = PageFactory.initElements(td.driver, CommonElement.class);
		orderHeaders = PageFactory.initElements(td.driver, OrderHeaders.class);
		orderHeaderDetail = PageFactory.initElements(td.driver, OrderHeaderDetail.class);
		orderHeader_OrderDetail_PopupEditor = PageFactory.initElements(td.driver, OrderHeader_OrderDetail_PopupEditor.class);
		System.out.println("InquiryForm setup");
	}

	@Test
	public void testScenario__Normal_01() throws Exception {
		System.out.println("testScenario__Normal_01");
		// ■ログイン画面を表示する
		td.driver.get("http://" + TestSupport.SERVER_NAME + "/SampleAppForE2ETest/");
		// ■ログイン中の場合はログアウトする
		if (!td.driver.getTitle().equals("Login"))
			logout();
		// ■ENG_PICユーザでログインする
		loginPage.login("user1", "outsystems");
		// ■テストデータ用のパラメータを準備する
		String orderNo = "O" + sdf.format(Calendar.getInstance().getTime());
		// ■ヘッダーを新規登録する
		createANewOrderHeader(orderNo);
		// ■明細を新規登録する
		createANewOrderDetail(orderNo);
	}

	private WebDriverWait newWebDriverWait() {
		return (new WebDriverWait(td.driver, TestSupport.WAIT_TIME_SEC));
	}

	private void logout() {
		newWebDriverWait().until(ExpectedConditions.elementToBeClickable(commonElement.login_Info_Logout));
		commonElement.login_Info_Logout.click();
	}

	private void createANewOrderHeader(String orderNo) {
		commonElement.menuOrderHeaders.click();
		orderHeaders.createANewOrderHeader.click();
		// ■登録する
		orderHeaderDetail.orderHeader_OrderNo.sendKeys(orderNo);
		(new Select(orderHeaderDetail.orderHeader_CustomerId)).selectByVisibleText("cust1");
		orderHeaderDetail.save.click();
		// ▲正常終了メッセージが表示されること
		newWebDriverWait().until(ExpectedConditions.visibilityOf(commonElement.feedback_Message_Text));
		assertThat(commonElement.feedback_Message_Text.getText(), endsWith("was successfully created."));
		commonElement.feedback_Message_Wrapper_Close.click();
		// ■対象案件を検索する
		newWebDriverWait().until(ExpectedConditions.elementToBeClickable(orderHeaders.searchInput));
		orderHeaders.searchInput.sendKeys(orderNo);
		orderHeaders.search.click();
		// ▲検索結果に新規登録したデータが表示されていること
		newWebDriverWait().until(
				ExpectedConditions.or(
						ExpectedConditions.textToBePresentInElement(orderHeaders.counter_Message, "1 件"),
						ExpectedConditions.textToBePresentInElement(orderHeaders.counter_Message, "1 record")));
	}

	private void createANewOrderDetail(String orderNo) {
		// ■対象案件が表示されていること
		newWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.partialLinkText(orderNo)));
		td.driver.findElement(By.partialLinkText(orderNo)).click();

		orderHeaderDetail.newOrderDetailLink.click();
		try {
			// ■登録する
			newWebDriverWait().until(ExpectedConditions.elementToBeClickable(orderHeader_OrderDetail_PopupEditor.iframe));
			td.driver.switchTo().frame(orderHeader_OrderDetail_PopupEditor.iframe);
			(new Select(orderHeader_OrderDetail_PopupEditor.orderDetail_ProductId)).selectByVisibleText("りんご");
			newWebDriverWait().until(ExpectedConditions.elementToBeClickable(orderHeader_OrderDetail_PopupEditor.orderDetail_Quantity));
			orderHeader_OrderDetail_PopupEditor.orderDetail_Quantity.clear();
			orderHeader_OrderDetail_PopupEditor.orderDetail_Quantity.sendKeys("3");
			orderHeader_OrderDetail_PopupEditor.save.click();
		} finally {
			td.driver.switchTo().defaultContent();
		}
		// ▲正常終了メッセージが表示されること
		newWebDriverWait().until(ExpectedConditions.visibilityOf(commonElement.feedback_Message_Text));
		assertThat(commonElement.feedback_Message_Text.getText(), equalTo("The order detail was successfully created."));
		commonElement.feedback_Message_Wrapper_Close.click();
	}
}
