package conditions;

import core.ConciseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElementVisible extends CustomConditions<WebElement> {

    private static By locator;
    public ElementVisible(By locator) {
        this.locator=locator;
    }

    @Override
    public String toString() {
        return "visibility of element located by " + locator;
    }

    @Override
    protected WebElement check(By locator) {
        WebElement element = ConciseAPI.getDriver().findElement(locator);
        return element.isDisplayed() ? element : null;
    }
}