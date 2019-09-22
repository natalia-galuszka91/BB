import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CarBooking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Natalia\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.phptravels.net/demo");
		driver.manage().window().maximize();
		WebDriverWait w = new WebDriverWait(driver, 20);
		w.until(ExpectedConditions.elementToBeClickable(By.id("cookyGotItBtn")));
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
		Thread.sleep(5000);
		
		int num1 = driver.findElements(By.cssSelector("div[class='row']")).size();
		System.out.println(num1);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[4]/a")).click();
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#s2id_carlocations > a")));
		
		driver.findElement(By.cssSelector("#s2id_carlocations > a")).click();
		driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys(Keys.DOWN);
		driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys(Keys.ENTER);
		driver.findElement(By.id("departcar")).click();
		
		int count = driver.findElements(By.xpath("/html/body/div[11]/div[1]/table/tbody/tr/td")).size();
		for (int i=0; i<count; i++) {
			List<WebElement> date = driver.findElements(By.xpath("/html/body/div[11]/div[1]/table/tbody/tr/td"));
			if (date.get(i).getAttribute("class").contains("active")) {
				date.get(i+1).click();
				break;
			}
		}
		
		int count2 = driver.findElements(By.xpath("/html/body/div[12]/div[1]/table/tbody/tr/td")).size();
		for (int j=0; j<count2; j++) {
			List<WebElement> date2 = driver.findElements(By.xpath("/html/body/div[12]/div[1]/table/tbody/tr/td"));
			if (date2.get(j).getAttribute("class").contains("active")) {
				String a = date2.get(j).getAttribute("cellIndex");
				int x = Integer.parseInt(a);
				int y = 7-x;
				date2.get(j+y).click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//*[@id='cars']/form/div[7]/button")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.cssSelector("button[class='btn btn-action loader loader btn-block br25']")).click();
		
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='btn btn-block btn-action btn-lg']")));
		driver.findElement(By.cssSelector("button[class='btn btn-block btn-action btn-lg']")).click();
		
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='btn btn-success btn-lg btn-block completebook']")));
		driver.findElement(By.cssSelector("button[class='btn btn-success btn-lg btn-block completebook']")).click();
		
		w.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[class='btn btn-default arrivalpay']")));
		driver.findElement(By.cssSelector("button[class='btn btn-default arrivalpay']")).click();
		driver.switchTo().alert().accept();
		
		driver.findElement(By.cssSelector("a[class='navbar-brand go-right']")).click();
		
		WebElement user = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a"));
		js.executeScript("arguments[0].click();", user);
		
		WebElement account = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[1]/a"));
		js.executeScript("arguments[0].click();", account);
		
		int num2 = driver.findElements(By.cssSelector("div[class='row']")).size();
		System.out.println(num2);
		
		if ((num1+1) == num2) {
			System.out.println("New booking is visible");
			Assert.assertTrue(true);
		}
		else {
			System.out.println("Operation not successful");
			Assert.assertTrue(false);
		}
		
		driver.close();
	}
}

