package pkg1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class TestSupport {
	public static final int WAIT_TIME_SEC = 20;
	public static final String SERVER_NAME;

	static {
		String serverName = System.getProperty("serverName");   //
		System.out.println("serverName: " + serverName);
		if (serverName == null || serverName.equals("")) {
			SERVER_NAME = "leftouterjoin.outsystemscloud.com";
		} else {
			SERVER_NAME = serverName;
		}
	}

	public static final Iterable<Class<? extends WebDriver>> getWebDriverList() {
		String targetBrowser = System.getProperty("targetBrowser");   // chrome,firefox,ie
		System.out.println("targetBrowser: " + targetBrowser);
		List<Class<? extends WebDriver>> l = new ArrayList<>();
		if (targetBrowser == null || targetBrowser.equals("")) {
			l.add(ChromeDriver.class);
		} else {
			for (String s : targetBrowser.split(",")) {
				switch (s) {
				case "chrome":
					l.add(ChromeDriver.class);
					break;
				case "firefox":
					l.add(FirefoxDriver.class);
					break;
				case "ie":
					l.add(InternetExplorerDriver.class);
					break;
				}
			}
		}
		if (l.size() < 1)
			throw new RuntimeException("targetBrowserが不正です。");
		return l;
	}

	public static final void setupWebDriver() {
		String poroxy = System.getProperty("proxy");
		System.out.println("proxy: " + poroxy);
		if (poroxy == null || poroxy.equals("")) {
			FirefoxDriverManager.getInstance().setup();
			ChromeDriverManager.getInstance().setup();
			InternetExplorerDriverManager.getInstance().arch32().setup();
		} else {
			FirefoxDriverManager.getInstance().proxy(poroxy).setup();
			ChromeDriverManager.getInstance().proxy(poroxy).setup();
			InternetExplorerDriverManager.getInstance().arch32().proxy(poroxy).setup();
		}
	}

	public static final WebDriver newWebDriver(Class<? extends WebDriver> clazz) {
		WebDriver driver = null;
		try {
			driver = clazz.newInstance();
			driver.manage().timeouts().implicitlyWait(WAIT_TIME_SEC, TimeUnit.SECONDS);
			driver.manage().window().maximize();
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
		return driver;
	}
}
