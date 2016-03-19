package core;

import conditions.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.Configuration.pollingIntervalInMilis;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConciseAPI {

    private static Map<Thread, WebDriver> drivers = new HashMap<Thread, WebDriver>();

    public static WebDriver getDriver() {
        return drivers.get(Thread.currentThread());
    }

    public static void setDriver(WebDriver driver) {
        drivers.put(Thread.currentThread(), driver);
    }

    public static <V> V assertThat(By locator, CustomConditions<V> condition, int timeout) {
        return waitUntil(locator, condition, timeout);
    }

    public static <V> V assertThat(By locator, CustomConditions<V> condition) {
        return assertThat(locator, condition, Configuration.timeout);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return (new WebDriverWait(getDriver(), timeout)).until(condition);
    }

    public static WebElement $(By locator) {
        return assertThat(visibilityOfElementLocated(locator));
    }

    public static WebElement $(String cssSelector) {
        return $(byCSS(cssSelector));
    }

    public static List<WebElement> $$(By locator) {
        return assertThat(visibilityOfAllElementsLocatedBy(locator));
    }

    public static By byText(String text) {
        return By.xpath("//*[text()[contains(.,'" + text + "')]]");
    }

    public static By byCSS(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static void open(String URL) {
        getDriver().get(URL);
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static <V> V waitUntil(By locator, CustomConditions<V> condition, int timeoutMs) {
        V results = condition.apply(locator, condition);
        final long startTime = System.currentTimeMillis();
        do {
            if (results == null) {
                sleep(pollingIntervalInMilis);
                continue;
            }
            return results;
        }
        while (System.currentTimeMillis() - startTime < timeoutMs);
        condition.fail();
        return results;
    }

}
