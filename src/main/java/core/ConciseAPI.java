package core;

import conditions.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static core.Configuration.pollingIntervalInMillis;

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

    public static WebElement $(By locator, CustomConditions<WebElement> conditionToWaitElement) {
        return assertThat(locator, conditionToWaitElement);
    }

    public static LazyElement $(By locator) {
        return new LazyElement(locator);
    }

    public static LazyElement $(String cssSelector) {
        return $(byCSS(cssSelector));
    }

    public static LazyElement $$$(String text) {
        return new LazyElement(byText(text));
    }

    /*public static WebElement $(By locator) {
        return assertThat(locator, elementVisible);
    }

    public static WebElement $(String cssSelector) {
        return $(byCSS(cssSelector));
    }

    public static WebElement $(By locator, CustomConditions<WebElement> conditionToWaitParentElement, By innerElementLocator) {
        return $(locator, conditionToWaitParentElement).findElement(innerElementLocator);
    }

    public static WebElement $(By locator, CustomConditions<WebElement> conditionToWaitParentElement, String innerElementCssSelector) {
        return $(locator, conditionToWaitParentElement, byCSS(innerElementCssSelector));
    }

    public static WebElement $(By parentElementLocator, By innerElementLocator) {
        return $(parentElementLocator).findElement(innerElementLocator);
    }

    public static WebElement $(By parentElementLocator, String... cssSelectorsOfInnerElements) {
        WebElement element;
        //WebElement element = $(parentElementLocator);
        for (String selector : cssSelectorsOfInnerElements) {
            element = $(parentElementLocator, byCSS(selector));
        }
        return element;
    }*/

    /*public static List<WebElement> $$(By locator, CustomConditions<List<WebElement>> conditionToWaitForListFilteredElements) {
        return assertThat(locator, conditionToWaitForListFilteredElements);
    }

    public static List<WebElement> $$(By locator) {
        return $$(locator, listVisible);
    }

    public static List<WebElement> $$(String cssSelector) {
        return $$(byCSS(cssSelector));
    }*/

    public static LazyElements $$(By locator) {
        return new LazyElements(locator);
    }

    public static LazyElements $$(String cssSelector) {
        return $$(byCSS(cssSelector));
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
        final long startTime = System.currentTimeMillis();
        do {
            V results = condition.apply(locator);
            if (results == null) {
                sleep(pollingIntervalInMillis);
                continue;
            }
            return results;
        }
        while (System.currentTimeMillis() - startTime < timeoutMs);
        condition.fail();
        return null;
    }

    /*public static WebElement hover(By locator) {
        Actions actions = new Actions(getDriver());
        WebElement element = $(locator);
        actions.moveToElement(element).perform();
        return element;
    }

    public static WebElement doubleClick(By locator) {
        Actions actions = new Actions(getDriver());
        WebElement element = $(locator);
        actions.doubleClick(element).perform();
        return element;
    }

    public static WebElement setValue(By locator, String text) {
        WebElement element = $(locator);
        element.clear();
        element.sendKeys(text);
        return element;
    }*/

}
