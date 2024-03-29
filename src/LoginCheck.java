import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LoginCheck {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Natalia\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.phptravels.net/demo");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("cookyGotItBtn")).click();
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		WebElement element1 = driver.findElement(By.xpath("//*[@id='li_myaccount']/a"));
		if (element1.getAttribute("innerText").contains(" MY ACCOUNT ")) {
			js.executeScript("arguments[0].click();", element1);
		}
		
		
		WebElement element2 = driver.findElement(By.xpath("//*[@id='li_myaccount']/ul/li[1]/a"));
		if (element2.getAttribute("innerText").contains("Login")) {
			js.executeScript("arguments[0].click();", element2);
		}
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("user@phptravels.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demouser");
		driver.findElement(By.xpath("//*[@id='loginfrm']/button")).click();
		
		Thread.sleep(2000);
		if(driver.getCurrentUrl().equals("https://www.phptravels.net/demo/account/")) {
			System.out.println("Login successful");
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		
		driver.close();
	}
	

}
