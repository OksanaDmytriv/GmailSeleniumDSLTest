package core;

import conditions.CustomConditions;
import org.openqa.selenium.By;

public class LazyElements extends Lazy{

    public LazyElements(By locator) {
        super(locator);
    }

    public LazyElements should(CustomConditions... conditions) {
        CustomConditions[] e = conditions;
        int length = conditions.length;

        for (int i = 0; i < length; ++i) {
            CustomConditions condition = e[i];
            this.waitUntil(locator, condition, Configuration.collectionsTimeout);
        }
        return this;
    }

    public LazyElements shouldHaveSize(int expectedSize) {
        return this.should(new CustomConditions[]{CustomConditions.sizeOf(expectedSize)});
    }

    public LazyElements shouldBe(CustomConditions... conditions) {
        return this.should(conditions);
    }

    public LazyElements shouldHave(CustomConditions... conditions) {
        return this.should(conditions);
    }


}


