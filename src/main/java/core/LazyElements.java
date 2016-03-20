package core;

import conditions.CustomConditions;
import org.openqa.selenium.By;

import static core.Configuration.pollingIntervalInMillis;

public class LazyElements {

    private static By locator;

    public LazyElements(By locator) {
        this.locator = locator;
    }

    /*public static <V> V assertThat(CustomConditions<V> condition, int timeout) {
        return waitUntil(condition, timeout);
    }

    public static <V> V assertThat(CustomConditions<V> condition) {
        return assertThat(condition, Configuration.timeout * 1000);
    }*/

    public static By byCSS(String cssSelector) {
        return By.cssSelector(cssSelector);
    }

    public static LazyElements $$(By locator)
    {
        return (LazyElements) ConciseAPI.getDriver().findElements(locator);
    }

    public static LazyElements $$(String cssSelector) {
        return $$(byCSS(cssSelector));
    }

    /*public static LazyElements $$(By locator, CustomConditions<List<WebElement>> conditionToWaitForListFilteredElements) {
        return assertThat(conditionToWaitForListFilteredElements);
    }

    public static LazyElements $$(By locator) {
        return $$(locator, listVisible(locator));
    }

    public static LazyElements $$(String cssSelector) {
        return $$(byCSS(cssSelector), listVisible(byCSS(cssSelector)));
    }*/

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    public static <V> V waitUntil(CustomConditions<V> condition, int timeoutMs) {
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

    protected LazyElements should(CustomConditions... conditions) {
        CustomConditions[] e = conditions;
        int length = conditions.length;

        for (int i = 0; i < length; ++i) {
            CustomConditions condition = e[i];
            this.waitUntil(condition, Configuration.collectionsTimeout);
        }
        return this;
    }

    public LazyElements shouldHaveSize(int expectedSize) {
        return this.shouldHave(new CustomConditions[]{CustomConditions.sizeOf(expectedSize)});
    }

    public LazyElements shouldBe(CustomConditions... conditions) {
        return this.should(conditions);
    }

    public LazyElements shouldHave(CustomConditions... conditions) {
        return this.should(conditions);
    }

}


