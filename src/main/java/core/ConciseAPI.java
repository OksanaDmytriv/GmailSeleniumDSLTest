package core;


import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.impl.Cleanup;
import com.codeborne.selenide.impl.WebElementsCollection;
import com.google.common.base.Function;
import conditions.Conditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConciseAPI {

    private static WebDriver driver;

    private static WebElementsCollection collection;
    private static List<WebElement> actualElements;
    public static By locator;
    private static Exception lastError;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        ConciseAPI.driver = driver;
    }

    public static <V> V assertThat(By locator, Conditions<V> condition, int timeout) {
        return waitUntil(locator, condition, timeout);
    }

    /*public static <V> V assertThat(By locator, ExpectedCondition<V> condition, int timeout) {
        return waitUntil(locator, condition, timeout);
    }

    public static <V> V assertThat(By locator, ExpectedCondition<V> condition) {
        return assertThat(waitUntil(locator, condition, Configuration.timeout));
    }
*/

    /*public static <V> V assertThat(By locator, Conditions condition) {
        return assertThat(locator, condition, Configuration.timeout);
    }

    public static <V> V assertThat(By locator, Conditions condition, int timeout) {
       return waitUntil(condition, timeout);
        // return (new WebDriverWait(getDriver(), timeout)).until(condition);
    }

    public static <V> V assertThat(By locator, ExpectedCondition<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    public static <V> V assertThat(ExpectedCondition<V> condition, int timeout) {
        return (new WebDriverWait(getDriver(), timeout)).until(condition);
    }*/

    public static WebElement $(By locator) {
        return assertThat(locator, visibilityOfElementLocated(locator));
    }

    public static WebElement $(String cssSelector) {
        return $(byCSS(cssSelector));
    }

    //public static List<WebElement> $$(By locator) {
    //    return assertThat(locator, visibilityOfAllElementsLocatedBy(locator));
    //}

    public static By byText(String text) {
        return By.xpath("//*[text()[contains(.,'" + text + "')]]");
    }

    public static By byCSS(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static void open(String URL) {
        getDriver().get(URL);
    }

    public static <V> V waitUntil(By locator, Conditions<V> condition, long timeoutMs) {
        lastError = null;
        final long startTime = System.currentTimeMillis();
        boolean conditionMatched = false;
        do {
            try {
                actualElements = getDriver().findElements(locator);
                //actualElements = collection.getActualElements();
                if (condition.apply(locator)) {
                    if (conditionMatched) {
                        return V;
                    } else {
                        conditionMatched = true;
                        sleep(Configuration.collectionsPollingInterval);
                        continue;
                    }
                } else {
                    conditionMatched = false;
                }
            } catch (WebDriverException elementNotFound) {
                lastError = elementNotFound;

                if (Cleanup.of.isInvalidSelectorError(elementNotFound)) {
                    throw Cleanup.of.wrap(elementNotFound);
                }
            }
            sleep(Configuration.pollingInterval);
        }
        while (System.currentTimeMillis() - startTime < timeoutMs);

        condition.fail(collection, actualElements, lastError, timeoutMs);
    }

}
