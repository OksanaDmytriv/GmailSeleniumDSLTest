package conditions;


import org.openqa.selenium.*;

import java.util.List;

public abstract class CustomConditions<V> {

    public void fail() {
        throw new AssertionError(toString());
    }

    public abstract String toString();

    protected abstract <V> V check(By locator);

    public <V> V apply(By locator) {
        try {
            return check(locator);
        } catch (StaleElementReferenceException e) {
            return null;
        } catch (ElementNotVisibleException e) {
            return null;
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (NoSuchElementException e) {
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

    public static CustomConditions<WebElement> elementVisible = new ElementVisible();

    public static CustomConditions<List<WebElement>> listVisible = new ListVisible();

}
