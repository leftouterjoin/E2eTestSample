package pkg1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestDescription extends TestWatcher {
	private Description description;
	WebDriver driver;

	public Description getDescription() {
		return this.description;
	}

	@Override
	protected void starting(Description description) {
		this.description = description;
		System.out.println("TestDescription starting");
	}

	@Override
	protected void finished(Description description) {
		File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
			FileUtils.copyFile(file, new File("./e2eTest_snapshot_" + sdf.format(Calendar.getInstance().getTime()) + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver.quit();
		System.out.println("TestDescription finished");
	}
}