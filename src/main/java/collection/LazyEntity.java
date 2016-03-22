package collection;

import conditions.CustomConditions;
import core.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static core.Configuration.pollingIntervalInMillis;

public class LazyEntity {

    public By locator;

    public LazyEntity(By locator) {
        this.locator = locator;
    }

    protected <V> V assertThat(CustomConditions<V> condition, int timeout) {
        return waitUntil(locator, condition, timeout);
    }

    protected <V> V assertThat(CustomConditions<V> condition) {
        return assertThat(condition, Configuration.timeout);
    }

    protected WebElement findElement(CustomConditions<WebElement> conditionToWaitElement) {
        return assertThat(conditionToWaitElement);
    }

    protected WebElement findElement(By locator) {
        return assertThat(CustomConditions.elementVisible);
    }

    protected <V> V assertThat(CustomConditions<V>... conditions) {
        V result = null;
        for (CustomConditions<V> condition : conditions) {
            result = assertThat(condition);
        }
        return result;
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
