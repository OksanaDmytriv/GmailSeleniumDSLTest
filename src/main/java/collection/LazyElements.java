package collection;

import conditions.CustomConditions;
import core.Configuration;
import org.openqa.selenium.By;

import static conditions.CustomConditions.sizeOf;

public class LazyElements extends Lazy {

    public LazyElements(By locator) {
        super(locator);
    }

    public LazyElements should(CustomConditions... conditions) {
        for (CustomConditions condition : conditions) {
            assertThat(condition, Configuration.collectionsTimeout);
        }
        return this;
    }

    public LazyElements shouldHaveSize(int expectedSize) {
        return should(new CustomConditions[]{sizeOf(expectedSize)});
    }

    public LazyElements shouldBe(CustomConditions... conditions) {
        return should(conditions);
    }

    public LazyElements shouldHave(CustomConditions... conditions) {
        return should(conditions);
    }
}

