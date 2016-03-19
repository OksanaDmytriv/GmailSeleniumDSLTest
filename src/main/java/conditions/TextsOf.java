package conditions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.TextsMismatch;
import com.codeborne.selenide.impl.WebElementsCollection;
import core.ConciseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextsOf extends Conditions{

    public static By locator;
    private static List<String> currentTexts;
    private static List<WebElement> elements;
    protected final String[] texts;

    public TextsOf(String... texts) {
        if (texts.length == 0) {
            throw new IllegalArgumentException("Array of expected texts is empty.");
        }
        this.texts=texts;
    }

    @Override
    public void fail(WebElementsCollection collection, List<WebElement> elements, Exception lastError, long timeoutMs) {
        if (elements == null || elements.isEmpty()) {
            ElementNotFound elementNotFound = new ElementNotFound(collection, texts, lastError);
            elementNotFound.timeoutMs = timeoutMs;
            throw elementNotFound;
        } else {
            throw new TextsMismatch(collection, ElementsCollection.getTexts(elements), texts, timeoutMs);
        }
    }

    @Override
    public String toString() {
        return String.format("\nActual text of list is: %s\n", Arrays.toString(currentTexts.toArray()));
    }

    @Override
    public List<WebElement> apply(By locator) {
        elements = ConciseAPI.getDriver().findElements(locator);
        currentTexts = new ArrayList<String>();
        for (int i = 0; i < elements.size(); ++i) {
            currentTexts.add(i, elements.get(i).getText());
        }
        if (currentTexts.size() != texts.length) {
            return null;
        } else {
            for (int i = 0; i < texts.length; ++i) {
                if (!currentTexts.get(i).contains(texts[i])) {
                    return null;
                }
            }
            return elements;
        }
    }
}
