package conditions;

import com.codeborne.selenide.impl.WebElementsCollection;
import core.ConciseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListNthElementHasText extends Conditions {
    public static By locator;
    private static String currentText;
    private static List<WebElement> elements;
    protected final String text;
    protected final int index;

    public ListNthElementHasText(int index, String text) {
        this.index = index;
        this.text = text;
    }

    @Override
    public void fail(WebElementsCollection collection, List<WebElement> elements, Exception lastError, long timeoutMs) {
        throw new AssertionError();
    }

    @Override
    public String toString() {
        return String.format("\nActual text of element should is: %s\n", currentText);
    }

    @Override
    public WebElement apply(By locator) {
        elements = ConciseAPI.getDriver().findElements(locator);
        WebElement element = elements.get(index);
        currentText = element.getText();
        return (currentText.contains(text)) ? element : null;
    }
}
