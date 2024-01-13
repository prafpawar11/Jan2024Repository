package Steps;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginPageStep {
	WebDriver driver;

	@Given("user is on Login Page")
	public void user_is_on_login_page() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().deleteAllCookies();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}

	@When("user enter valid credentails")
	public void user_enter_valid_credentails() {
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");

	}

	@When("user click on login button")
	public void user_click_on_login_button() {
		driver.findElement(By.xpath("//button[text()=' Login ']")).click();
	}

	@AfterStep
	public void tearDown(Scenario scenario) throws InterruptedException {
		Thread.sleep(4000);
		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] f = ts.getScreenshotAs(OutputType.BYTES);
		// scenario.attach(f, "image/png", "TC1");

		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String screenshotName = scenario.getName() + "-" + timestamp + ".png";

		// scenario.attach(f, "image/png", screenshotName);
		scenario.attach(f, "image/png", scenario.getName());

	}

}
