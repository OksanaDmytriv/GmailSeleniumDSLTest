package core;

import conditions.CustomConditions;
import org.openqa.selenium.By;

import static core.ConciseAPI.sleep;
import static core.Configuration.pollingIntervalInMillis;

public class LazyElements {

    private static By locator;

    public LazyElements(By locator) {
        this.locator = locator;
    }

    protected LazyElements should(CustomConditions... conditions) {
        CustomConditions[] e = conditions;
        int length = conditions.length;

        for (int i = 0; i < length; ++i) {
            CustomConditions condition = e[i];
            this.waitUntil(locator, condition, Configuration.collectionsTimeout);
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

}


