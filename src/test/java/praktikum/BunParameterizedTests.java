package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParameterizedTests {
    private Bun bun;
    private final String expectedBunName;
    private final float expectedBunPrice;

    public BunParameterizedTests(String expectedBunName, float expectedBunPrice) {
        this.expectedBunName = expectedBunName;
        this.expectedBunPrice = expectedBunPrice;
    }

    @Parameterized.Parameters(name = "название булки: {0}, цена булки: {1}")
    public static Object[][] getDataBun() {
        return new Object[][]{
                {"SpaceX", 125.5F},
                {"Полулуние", 115.5F},
                {"Starlink", 105.5F}
        };
    }

    @Before
    public void createObject() {
        bun = new Bun(expectedBunName, expectedBunPrice); // Создали булку
    }

    @Test
    public void getBunNameTest() {
        assertEquals("Некорректное название булки", expectedBunName, bun.getName()); // Проверили, что возвращается корректное название булки
    }

    @Test
    public void getBunPriceTest() {
        assertEquals("Некорректная цена булки", expectedBunPrice, bun.getPrice(), 0.0001); // Проверили, что возвращается корректная цена булки
    }
}
