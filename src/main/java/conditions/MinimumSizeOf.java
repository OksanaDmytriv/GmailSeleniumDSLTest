package conditions;

import com.codeborne.selenide.ex.ListSizeMismatch;
import com.codeborne.selenide.impl.WebElementsCollection;
import core.ConciseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MinimumSizeOf<V> extends Conditions<V>{

    public static By locator;
    private static int listSize;
    private static List<WebElement> results;
    protected final int minimumSize;

    public MinimumSizeOf(int minimumSize) {
        if (minimumSize == 0) {
            throw new IllegalArgumentException("Minimum size of element's list is 0.");
        }
        this.minimumSize = minimumSize;
    }

    @Override
    public void fail(WebElementsCollection collection, List<WebElement> elements, Exception lastError, long timeoutMs) {
        throw new ListSizeMismatch(minimumSize, collection, elements, lastError, timeoutMs);
    }

    @Override
    public String toString() {
        return String.format("\nActual size of list: %s\n is: %s\n", results, listSize);
    }

    @Override
    public List<WebElement> apply(By locator) {
        results = ConciseAPI.getDriver().findElements(locator);
        listSize = results.size();
        return (listSize >= minimumSize) ? results : null;
    }
}
