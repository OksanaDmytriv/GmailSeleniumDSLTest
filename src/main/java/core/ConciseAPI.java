package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class ConciseAPI {

    private static Map<Thread, WebDriver> drivers = new HashMap<Thread, WebDriver>();

    public static WebDriver getDriver() {
        return drivers.get(Thread.currentThread());
    }

    public static void setDriver(WebDriver driver) {
        drivers.put(Thread.currentThread(), driver);
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
    }

    public static List<WebElement> $$(By locator, CustomConditions<List<WebElement>> conditionToWaitForListFilteredElements) {
        return assertThat(locator, conditionToWaitForListFilteredElements);
    }

    public static List<WebElement> $$(By locator) {
        return $$(locator, listVisible);
    }

    public static List<WebElement> $$(String cssSelector) {
        return $$(byCSS(cssSelector));
    }*/


    public static By byText(String text) {
        return By.xpath("//*[text()[contains(.,'" + text + "')]]");
    }

    public static By byCSS(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static void open(String URL) {
        getDriver().get(URL);
    }


}
