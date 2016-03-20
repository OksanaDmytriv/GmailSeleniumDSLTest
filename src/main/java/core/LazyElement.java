package core;


import conditions.CustomConditions;
import org.openqa.selenium.By;

public interface LazyElement {
    
    LazyElement setValue(String var1);

    LazyElement pressEnter();

    LazyElement pressEscape();

    String getText();

    boolean isDisplayed();

    LazyElement should(CustomConditions... var1);

    LazyElement shouldHave(CustomConditions... var1);

    LazyElement shouldBe(CustomConditions... var1);

    LazyElement waitUntil(CustomConditions var1, long var2);

    String toString();

    LazyElement find(String var1);

    LazyElement find(String var1, int var2);

    LazyElement find(By var1);

    LazyElement find(By var1, int var2);

    LazyElement $(String var1);

    LazyElement $(String var1, int var2);

    LazyElement $(By var1);

    LazyElement $(By var1, int var2);

    LazyElements findAll(String var1);

    LazyElements findAll(By var1);

    LazyElements $$(String var1);

    LazyElements $$(By var1);

    void click();

    LazyElement doubleClick();

    LazyElement hover();
}
