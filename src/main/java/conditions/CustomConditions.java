package conditions;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class CustomConditions<V> {

    public void fail() {
        toString();
    }

    public abstract String toString();

    public abstract <V> V check(By locator);

    public <V> V apply(By locator, CustomConditions<V> condition) {
        try {
            return condition.check(locator);
        } catch (StaleElementReferenceException e) {
            return null;
        } catch (ElementNotVisibleException e) {
            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public static CustomConditions<List<WebElement>> textsOf(final String... texts) {
        return new TextsOf(texts);
    }

    public static CustomConditions<WebElement> listNthElementHasText(
            final int index, final String text) {
        return new ListNthElementHasText(index, text);
    }

    public static CustomConditions<List<WebElement>> sizeOf(final int expectedSize) {
        return new SizeOf(expectedSize);
    }

    public static CustomConditions<List<WebElement>> minimumSizeOf(final int minimumSize) {
        return new MinimumSizeOf(minimumSize);
    }
}
