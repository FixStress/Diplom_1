package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientParameterizedTests {
    private Ingredient ingredient;
    private final IngredientType expectedIngredientType;
    private final String expectedIngredientName;
    private final float expectedIngredientPrice;

    public IngredientParameterizedTests(IngredientType expectedIngredientType, String expectedIngredientName, float expectedIngredientPrice) {
        this.expectedIngredientType = expectedIngredientType;
        this.expectedIngredientName = expectedIngredientName;
        this.expectedIngredientPrice = expectedIngredientPrice;
    }

    @Parameterized.Parameters(name = "тип ингредиента: {0}, название ингредиента: {1}, цена ингредиента: {2}")
    public static Object[][] getDataIngredient() {
        return new Object[][]{
                {SAUCE, "космическая жижа", 100.5F},
                {FILLING, "вяленый зелёный человечек", 150.5F},
                {SAUCE, "тёмная материя", 200.5F},
                {FILLING, "белый карлик", 250.5F}
        };
    }

    @Before
    public void createObject() {
        ingredient = new Ingredient(expectedIngredientType, expectedIngredientName, expectedIngredientPrice); // Создали ингредиент
    }

    @Test
    public void getIngredientTypeTest() {
        assertEquals("Некорректный тип ингредиента", expectedIngredientType, ingredient.getType()); // Проверили, что возвращается корректный тип ингредиента
    }

    @Test
    public void getIngredientNameTest() {
        assertEquals("Некорректное название ингредиента", expectedIngredientName, ingredient.getName()); // Проверили, что возвращается корректное название ингредиента
    }

    @Test
    public void getIngredientPriceTest() {
        assertEquals("Некорректная цена ингредиента", expectedIngredientPrice, ingredient.getPrice(), 0.0001F); // Проверили, что возвращается корректная цена ингредиента
    }
}
