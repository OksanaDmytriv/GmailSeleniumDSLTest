package core;


import conditions.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static core.ConciseAPI.*;
import static core.Configuration.pollingIntervalInMillis;

public class LazyElement {
    private static By locator;

    public LazyElement(By locator) {
        this.locator = locator;
    }

    public LazyElement click() {
        Actions actions = new Actions(getDriver());
        $(locator);
        actions.click().perform();
        //$(locator).click();
        return this;
    }

    public LazyElement setValue(String text) {
        //LazyElement element = $(locator);
        //$(locator).clear();
        //$(locator).sendKeys(text);
        return this;
    }

    public LazyElement sendKeys(String text) {
        //LazyElement element = $(locator);
        //$(locator).sendKeys(text);
        $(locator).setValue()
        return this;
    }


    public LazyElement clear() {
        Actions actions = new Actions(getDriver());
        $(locator);
        actions.perform();
        //$(locator).clear();
        return this;
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

    protected LazyElement should(CustomConditions... conditions) {
        CustomConditions[] e = conditions;
        int length = conditions.length;

        for (int i = 0; i < length; ++i) {
            CustomConditions condition = e[i];
            this.waitUntil(locator, condition, Configuration.collectionsTimeout);
        }
        return this;
    }

    public LazyElement shouldHaveSize(int expectedSize) {
        return this.shouldHave(new CustomConditions[]{CustomConditions.sizeOf(expectedSize)});
    }

    public LazyElement shouldBe(CustomConditions... conditions) {
        return this.should(conditions);
    }

    public LazyElement shouldHave(CustomConditions... conditions) {
        return this.should(conditions);
    }
}
