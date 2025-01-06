package App;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GatherData {

    public static List<Monster> parse(String url, int playerLevel) {
    	String driverPath = "resources/chromedriver.exe";
    	System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(url);

        String script = "window.localStorage.setItem('settings', JSON.stringify({level: '" + playerLevel + "'}));";
        ((JavascriptExecutor) driver).executeScript(script);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mud-table-row")));

        List<Monster> monsters = new ArrayList<>();

        // Process the first page
        monsters.addAll(parsePage(driver, playerLevel));

        // Process the second page, if it exists
        try {
            WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[aria-label='Next page']")));

            if (nextPageButton.isEnabled()) {
                nextPageButton.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mud-table-row")));
                monsters.addAll(parsePage(driver, playerLevel));
            }
        } catch (Exception e) {
            System.out.println("No second page or error navigating to it.");
        }

        driver.quit();
        
        monsters.sort(Comparator.comparingDouble(Monster::getRelativeDamage));
        
        return monsters;
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
                // Do Nothing
            }
        }
        return monsters;
    }
}
