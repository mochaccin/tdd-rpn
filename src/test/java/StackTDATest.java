import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StackTDATest {
    private StackTDA stack;
    private RpnCalculator calculator;

    @BeforeEach
    void setUp() {
        stack = new StackTDA();
        calculator = new RpnCalculator();
    }

    // ----------- Decimales -----------

    @Test
    void testDecimalNotAllowedDot() {
        String expression = "2.5 3 +";
        assertThrows(NumberFormatException.class, () -> calculator.evaluate(expression));
    }

    @Test
    void testDecimalNotAllowedComma() {
        String expression = "2,5 3 +";
        assertThrows(NumberFormatException.class, () -> calculator.evaluate(expression));
    }

    // ----------- Caracteres inválidos -----------

    @Test
    void testInvalidCharacterSymbol() {
        String expression = "2 3 $";
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
    }

    @Test
    void testMixedInvalidCharacters() {
        String expression = "3 5 # +";
        assertFalse(calculator.containsOnlyValidCharacters(expression));
    }

    // ----------- Números negativos -----------

    @Test
    void testNegativeNumberDetection() {
        assertTrue(calculator.containsNegativeNumber("-3 5 +"));
    }

    @Test
    void testNegativeNumberThrows() {
        String expression = "-3 4 +";
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
    }

    // ----------- Validaciones de expresión -----------

    @Test
    void testIncompleteExpression() {
        String expression = "5 +";
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
    }

    @Test
    void testTooManyOperands() {
        String expression = "2 3 4 +";
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
    }

    @Test
    void testOnlyOperand() {
        String expression = "5";
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
    }

    // ----------- Operador inválido -----------

    @Test
    void testInvalidOperator() {
        String expression = "2 2 ^";
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expression));
    }

    // ----------- División por cero -----------

    @Test
    void testDivideByZero() {
        String expression = "10 0 /";
        assertThrows(ArithmeticException.class, () -> calculator.evaluate(expression));
    }

    // ----------- Operaciones normales -----------

    @Test
    void testAddition() {
        assertEquals(5, calculator.evaluate("2 3 +"));
    }

    @Test
    void testSubtraction() {
        assertEquals(1, calculator.evaluate("4 3 -"));
    }

    @Test
    void testMultiplication() {
        assertEquals(12, calculator.evaluate("3 4 *"));
    }

    @Test
    void testDivision() {
        assertEquals(2, calculator.evaluate("8 4 /"));
    }

    // ----------- Expresión compleja -----------

    @Test
    void testComplexExpression() {
        String expression = "15 7 1 1 + - / 3 * 2 1 1 + + -";
        assertEquals(5, calculator.evaluate(expression));
    }

    // ----------- Métodos auxiliares -----------

    @Test
    void testValidCharacters() {
        assertTrue(calculator.containsOnlyValidCharacters("3 4 +"));
    }

    @Test
    void testContainsDecimalNumber() {
        assertTrue(calculator.containsDecimalNumber("2.5 4 +"));
    }

    @Test
    void testValidExpressionMethod() {
        assertTrue(calculator.isValidExpression("3 4 +"));
        assertFalse(calculator.isValidExpression("3 +"));
        assertFalse(calculator.isValidExpression("3 4 5"));
    }

    // ----------- Pruebas de StackTDA -----------

    @Test
    void testStackPushPop() {
        stack.push(10);
        assertEquals(10, stack.pop());
    }

    @Test
    void testStackPeek() {
        stack.push(42);
        assertEquals(42, stack.peek());
    }

    @Test
    void testStackUnderflow() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    void testStackOverflow() {
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        assertThrows(StackOverflowError.class, () -> stack.push(10));
    }
}
