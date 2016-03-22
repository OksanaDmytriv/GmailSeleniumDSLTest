package collection;

import conditions.CustomConditions;
import org.openqa.selenium.By;

public class LazyElements extends LazyEntity {

    public LazyElements(By locator) {
        super(locator);
    }

    public LazyElements should(CustomConditions... conditions) {
        assertThat(conditions);
        return this;
    }

    public LazyElements shouldBe(CustomConditions... conditions) {
        return should(conditions);
    }

    public LazyElements shouldHave(CustomConditions... conditions) {
        return should(conditions);
    }
}


