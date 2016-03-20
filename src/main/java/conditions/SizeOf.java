package conditions;

import core.ConciseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SizeOf<V> extends CustomConditions<V> {

    private static int listSize;
    private static List<WebElement> results;
    protected final int expectedSize;

    public SizeOf(int expectedSize) {
        this.expectedSize = expectedSize;
    }

    @Override
    public String toString() {
        return String.format("\nsize of list: %s\n should be: %s\n while actual size is: %s\n", results, expectedSize, listSize);
    }

    @Override
    protected List<WebElement> check(By locator) {
        results = ConciseAPI.getDriver().findElements(locator);
        listSize = results.size();
        return (listSize == expectedSize) ? results : null;
    }
}
