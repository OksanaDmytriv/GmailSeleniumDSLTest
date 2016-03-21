package core;

import conditions.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static core.ConciseAPI.byCSS;
import static core.Configuration.pollingIntervalInMillis;

public class Lazy {

    public By locator;

    public Lazy(By locator) {
        this.locator = locator;
    }

    protected <V> V assertThat(By locator, CustomConditions<V> condition, int timeout) {
        return waitUntil(locator, condition, timeout);
    }

    protected <V> V assertThat(By locator, CustomConditions<V> condition) {
        return assertThat(locator, condition, Configuration.timeout);
    }

    protected WebElement findElement(By locator, CustomConditions<WebElement> conditionToWaitElement) {
        return assertThat(locator, conditionToWaitElement);
    }

    protected LazyElement findElement(By locator) {
        return new LazyElement(locator);
    }

    protected LazyElement findElement(String cssSelector) {
        return findElement(byCSS(cssSelector));
    }

    protected LazyElements findElements(By locator) {
        return new LazyElements(locator);
    }

    protected LazyElements findElements(String cssSelector) {
        return findElements(byCSS(cssSelector));
    }

    protected  <V> V assertThat(CustomConditions<V> ... conditions){

    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    private <V> V waitUntil(By locator, CustomConditions<V> condition, int timeoutMs) {
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
