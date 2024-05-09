package praktikum;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    private Burger burger;
    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockIngredient;
    @Mock
    private Ingredient secondMockIngredient;

    @Before
    public void createObject() {
        burger = new Burger(); // Создали бургер
    }


    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun); // Добавили мок булки
        assertEquals("Не установлено значение булки",mockBun, burger.bun); // Проверили, что в бургере установилось значение мока булки
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIngredient); // Добавили мок ингредиента
        assertTrue("Ингредиент не добавлен в список", burger.ingredients.contains(mockIngredient)); // Проверили, что список ингредиентов содержит мок ингредиента
        assertEquals("Список содержит некорректное количество ингредиентов", 1, burger.ingredients.size()); // Убедились, что список содержит только добавленный мок ингредиента
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockIngredient); // Добавили мок ингредиента
        burger.removeIngredient(0); // Вызвали метод удаления ингредиента по индексу
        assertTrue("Ингредиент не удалён из списка", burger.ingredients.isEmpty()); // Проверили, что список ингредиентов пустой
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockIngredient); // Добавили мок ингредиента
        burger.addIngredient(secondMockIngredient); // Добавили второй мок ингредиента
        burger.moveIngredient(0, 1); // Удалили мок по индексу и добавили его на новую позицию
        assertEquals("Некорректная позиция ингредиента", mockIngredient, burger.ingredients.get(1)); // Проверили, что индексы моков изменились на корректные
        assertEquals("Некорректная позиция ингредиента", secondMockIngredient, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(mockBun); // Добавили мок булки
        burger.addIngredient(mockIngredient); // Добавили мок ингредиента
        Mockito.when(mockBun.getPrice()).thenReturn(125.5F); // Стаб для геттера цены булки
        Mockito.when(mockIngredient.getPrice()).thenReturn(125.5F); // Стаб для геттера цены ингредиента
        float expectedBurgerPrice = 376.5F; // Ожидаемая цена бургера
        assertEquals("Некорректная цена бургера", expectedBurgerPrice, burger.getPrice(), 0.0001F); // Проверили, что ожидаемая и фактическая цена равны

    }

    @Test
    public void getReceiptTest() {
        Mockito.when(mockBun.getName()).thenReturn("SpaceX"); //Стаб для геттера названия булки
        Mockito.when(mockBun.getPrice()).thenReturn(125.5F); // Стаб для геттера цены булки
        Mockito.when(mockIngredient.getType()).thenReturn(SAUCE); // Стаб для геттера типа ингредиента
        Mockito.when(mockIngredient.getName()).thenReturn("космический тар-тар"); // Стаб для названия ингредиента
        Mockito.when(mockIngredient.getPrice()).thenReturn(125.5F); // Стаб для геттера цены ингредиента
        burger.setBuns(mockBun); // Добавили мок булки
        burger.addIngredient(mockIngredient); // Добавили мок ингредиента
        // Ожидаемый формат и текст чека
        String expectedReceipt = "(==== SpaceX ====)\r\n" +
                "= sauce космический тар-тар =\r\n" +
                "(==== SpaceX ====)\r\n" +
                "\r\n" +
                "Price: 376,50\r\n";
        assertEquals("Некорректный формат или текст чека", expectedReceipt, burger.getReceipt()); // Проверили, что ожидаемый и фактический чеки равны
    }
}
