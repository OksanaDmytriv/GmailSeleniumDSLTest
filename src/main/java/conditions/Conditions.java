package conditions;


import com.codeborne.selenide.impl.WebElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class Conditions<V> {

    public static By locator;

    public abstract void fail(WebElementsCollection collection, List<WebElement> elements, Exception lastError, long timeoutMs);

    public abstract String toString();

    public abstract <V> V apply(By locator);

    public static Conditions<List<WebElement>> textsOf(final String... texts) {
        return new TextsOf(texts);
    }

    public static Conditions<WebElement> listNthElementHasText(
            final int index, final String text) {
        return new ListNthElementHasText(index, text);
    }

    public static Conditions<List<WebElement>> sizeOf(final int expectedSize) {
        return new SizeOf(expectedSize);
    }

    public static Conditions<List<WebElement>> minimumSizeOf(final int minimumSize) {
        return new MinimumSizeOf(minimumSize);
    }

   // public static Conditions visibilityOfElementLocated(final By locator){return new VisibilityOfElementLocated(locator);}

    //public static Conditions visibilityOfAllElementsLocatedBy(final By locator){return new VisibilityOfAllElementsLocatedBy(locator);}
}
