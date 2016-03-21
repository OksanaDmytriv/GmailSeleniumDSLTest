package conditions;

import core.ConciseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.List;

public class ListVisible<V> extends CustomConditions<V> {

    private static By locator;

    @Override
    public String toString() {
        return "visibility of all elements located by " + locator;
    }

    @Override
    protected List<WebElement> check(By locator) {
        List elements = ConciseAPI.getDriver().findElements(locator);
        Iterator iterator = elements.iterator();
        WebElement element;

        do {
            if(!iterator.hasNext()) {
                return elements.size() > 0 ? elements : null;
            }
            element = (WebElement)iterator.next();
        }
        while(element.isDisplayed());
        return null;
    }
}
