package collection;

import conditions.CustomConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static core.ConciseAPI.getDriver;

public class LazyElement extends LazyEntity {

    public LazyElement(By locator) {
        super(locator);
    }

    public LazyElement click() {
        assertThat(CustomConditions.elementVisible);
        getWrappedElement().click();
        return this;
    }

    public LazyElement setValue(String text) {
        assertThat(CustomConditions.elementVisible);
        getWrappedElement().clear();
        getWrappedElement().sendKeys(text);
        return this;
    }

    public LazyElement sendKeys(String text) {
        WebElement element = assertThat(CustomConditions.elementVisible);
        getWrappedElement().sendKeys(text);
        return this;
    }

    public LazyElement clear() {
        WebElement element = assertThat(CustomConditions.elementVisible);
        getWrappedElement().clear();
        return this;
    }

    public LazyElement pressEnter() {
        WebElement element = assertThat(CustomConditions.elementVisible);
        getWrappedElement().sendKeys(Keys.ENTER);
        return this;
    }

    public LazyElement pressEscape() {
        WebElement element = assertThat(CustomConditions.elementVisible);
        getWrappedElement().sendKeys(Keys.ESCAPE);
        return this;
    }

    public LazyElement hover() {
        Actions actions = new Actions(getDriver());
        WebElement element = assertThat(CustomConditions.elementVisible);
        actions.moveToElement(element).perform();
        return this;
    }

    public LazyElement doubleClick(By locator) {
        Actions actions = new Actions(getDriver());
        WebElement element = assertThat(CustomConditions.elementVisible);
        actions.doubleClick(element).perform();
        return this;
    }

    public LazyElement should(CustomConditions... conditions) {
        assertThat(conditions);
        return this;
    }

    public LazyElement shouldBe(CustomConditions... conditions) {
        return should(conditions);
    }

    public LazyElement shouldHave(CustomConditions... conditions) {
        return should(conditions);
    }


}
