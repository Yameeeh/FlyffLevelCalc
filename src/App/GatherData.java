package App;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GatherData {

	public static List<Monster> parse(String url, int playerLevel) {
		String driverPath = "resources/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverPath);

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			driver.get(url);

			String script = "window.localStorage.setItem('settings', JSON.stringify({level: '" + playerLevel + "'}));";
			((JavascriptExecutor) driver).executeScript(script);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mud-table-row")));

			List<Monster> monsters = new ArrayList<>();

			while (true) {
				// Parse the current page
				monsters.addAll(parsePage(driver, playerLevel));

				// Try to find and click the "Next page" button, else break the loop
				try {
					WebElement nextPageButton = driver.findElement(By.cssSelector("[aria-label='Next page']"));
					if (nextPageButton.isEnabled()) {
						// short sleep to ensure the page isn't skipped too early
						Thread.sleep(50);
						nextPageButton.click();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mud-table-row")));
					} else {
						break;
					}
				} catch (Exception e) {
					System.out.println("No more pages to read or error navigating to the next page.");
					break;
				}
			}

			monsters.removeIf(mon -> mon.getName().startsWith("[Event]"));
			monsters.sort(Comparator.comparingDouble(Monster::getRelativeDamage));

			return monsters;

		} finally {
			driver.quit();
		}
	}

	private static List<Monster> parsePage(WebDriver driver, int playerLevel) {
		List<WebElement> rows = driver.findElements(By.className("mud-table-row"));
		List<Monster> monsters = new ArrayList<>();

		for (WebElement row : rows) {
			try {
				Monster mon = new Monster();

				WebElement nameElement = row.findElement(By.cssSelector("[data-label='Name']"));
				mon.setName(nameElement.getText());

				WebElement levelElement = row.findElement(By.cssSelector("[data-label='Level']"));
				mon.setLvl(Integer.valueOf(levelElement.getText()));

				WebElement elementElement = row.findElement(By.cssSelector("[data-label='Element']"));
				mon.setElement(elementElement.getText());

				WebElement dmgElement = row.findElement(By.cssSelector("[data-label='DMG per %']"));
				mon.setDmgPercent(Integer.valueOf(dmgElement.getText().replaceAll("[^\\d]", "")));

				mon.setRelativeDamage(Calc.calcRelativeDmg(playerLevel, mon.getLvl(), mon.getDmgPercent()));

				monsters.add(mon);
			} catch (Exception e) {
				// Do nothing, because it should only ever be the table header
			}
		}
		return monsters;
	}
}
