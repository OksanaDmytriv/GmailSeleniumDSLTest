package core;


import conditions.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static core.ConciseAPI.getDriver;
import static core.ConciseAPI.sleep;
import static core.Configuration.pollingIntervalInMillis;

public class LazyElement {
    private static By locator;

    public LazyElement(By locator) {
        this.locator = locator;
    }

    public LazyElement click() {
        WebElement element = getDriver().findElement(locator);
        element.click();
        return this;
    }

    public LazyElement setValue(String text) {
        WebElement element = getDriver().findElement(locator);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    public LazyElement sendKeys(String text) {
        WebElement element = getDriver().findElement(locator);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    public LazyElement clear() {
        WebElement element = getDriver().findElement(locator);
        element.clear();
        return this;
    }

    public LazyElement pressEnter() {
        WebElement element = getDriver().findElement(locator);
        element.sendKeys(Keys.ENTER);
        return this;
    }

    public LazyElement hover() {
        Actions actions = new Actions(getDriver());
        WebElement element = getDriver().findElement(locator);
        actions.moveToElement(element).perform();
        return this;
    }

    public LazyElement doubleClick(By locator) {
        Actions actions = new Actions(getDriver());
        WebElement element = getDriver().findElement(locator);
        actions.doubleClick(element).perform();
        return this;
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
