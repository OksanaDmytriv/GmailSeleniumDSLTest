package core;

import com.codeborne.selenide.ex.UIAssertionError;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ElementTextMismatch extends UIAssertionError {
    public ElementTextMismatch(int index, List<WebElement> elements, String text, Exception lastError, long timeoutMs) {
        super(": expected: " + text + ", actual: " + elements.get(index).getText() + ", collection: " + lastError);
        super.timeoutMs = timeoutMs;
    }

    public String toString() {
        return this.getClass().getSimpleName() + " " + this.getMessage() + this.uiDetails();
    }
}
