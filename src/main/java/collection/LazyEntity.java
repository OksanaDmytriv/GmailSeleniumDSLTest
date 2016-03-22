package collection;

import conditions.CustomConditions;
import core.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static core.ConciseAPI.getDriver;
import static core.Configuration.pollingIntervalInMillis;

public class LazyEntity {

    public By locator;

    public LazyEntity(By locator) {
        this.locator = locator;
    }

    protected <V> V assertThat(CustomConditions<V> condition, int timeout) {
        return waitUntil(condition, timeout);
    }

    protected <V> V assertThat(CustomConditions<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    protected <V> V assertThat(CustomConditions<V>... conditions) {
        V result = null;
        for (CustomConditions<V> condition : conditions) {
            result = assertThat(condition);
        }
        return result;
    }

    public WebElement getWrappedElement(){
      WebElement element = getDriver().findElement(locator);
        return element;
    }

    public List<WebElement> getWrappedElements(){
        List<WebElement> elements = getDriver().findElements(locator);
        return elements;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private <V> V waitUntil(CustomConditions<V> condition, int timeoutMs) {
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
}
