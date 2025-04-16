import static org.junit.jupiter.api.Assertions.*;
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

    // ----------- Enteros: ya cubiertos en operaciones básicas ----------

    // ----------- Decimales -----------
    @Test
    void testDecimalNotAllowed() {
        String expresion = "2.5 3 +";
        assertThrows(IllegalArgumentException.class, () -> calculator.evaluate(expresion),
            "No se permiten decimales en la expresión.");
    }

    // ------------ Operaciones básicas ------------

    // Suma correcta
    @Test
    void testAddition() {
        String expression = "134 3 +";
        int result = calculator.evaluate(expression);
        assertEquals(137, result, "Suma realizada exitosamente");
    }

    // Resta correcta
    @Test
    void testSubtraction() {
        String expression = "2 1 -";
        int result = calculator.evaluate(expression);
        assertEquals(1, result, "Resta realizada exitosamente.");
    }

    // Multiplicación correcta
    @Test
    void testMultiplication() {
        String expression = "2 2 *";
        int result = calculator.evaluate(expression);
        assertEquals(4, result, "Multiplicación realizada exitosamente.");
    }

    // División correcta
    @Test
    void testDivision() {
        String expression = "2 2 /";
        int result = calculator.evaluate(expression);
        assertEquals(1, result, "División realizada exitosamente.");
    }

    // ----------- Muy grande -----------
    @Test
    void testVeryLargeNumber() {
        String expresion = "2147483647 1 -"; 
        int result = calculator.evaluate(expresion);
        assertEquals(2147483646, result, "Número muy grande evaluado correctamente.");
    }

    // ----------- Muy pequeño -----------
    @Test
    void testNegativeNumberNotAllowed() {
        String expresion = "-2147483648 1 +"; // Integer.MIN_VALUE
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.evaluate(expresion);
        }, "Debe lanzar excepción al usar números negativos.");
    }


    // ----------- División por 0 -----------

    @Test
    void testDivisionByZero() {
        String expresion = "5 0 /";
        assertThrows(ArithmeticException.class, () -> calculator.evaluate(expresion),
            "No se puede dividir por cero.");
    }

    // ----------- Caracteres inválidos -----------

    @Test
    void testValidExpression() {
        String expresion = "3 5 3 * +";
        int result = calculator.evaluate(expresion);
        assertEquals(18, result, "Se ingresó la expresión correctamente.");
    }

    @Test
    void testInvalidExpression() {
        String expresion = "3 + *";
        assertFalse(calculator.isValidExpression(expresion), "Expresión inválida detectada.");
    }

    // Expresión debe tener operadores
    @Test
    void testExpressionWithoutOperators() {
        String expresion = "3 4 5";
        assertFalse(calculator.isValidExpression(expresion), "Ingresaste una expresión sin operandos.");
    }

    // Expresión debe tener números
    @Test
    void testExpressionWithoutNumbers() {
        String expresion = "+ - * /";
        assertFalse(calculator.isValidExpression(expresion), "Ingresaste una expresión sin numeros.");
    }

    // Expresión usa carácteres válidos
    @Test
    void testValidCharacters() {
        String expresion = "3 5 2 * +";
        assertTrue(calculator.containsOnlyValidCharacters(expresion), "Caracteres ingresados validos.");
    }

    // Expresión usa carácteres inválidos
    @Test
    void testInvalidCharacters() {
        String expresion = "3 5 xa +";
        assertFalse(calculator.containsOnlyValidCharacters(expresion), "Ingresaste un carácter inválido.");
    }

    // ----------- Control de la pila -----------

    @Test
    void testStackIsEmpty() {
        assertTrue(stack.isEmpty(), "La pila está vacía.");
    }

    @Test
    void testPushElementToStack() {
        stack.push(1);
        assertFalse(stack.isEmpty(), "Se agrego un elemento a la pila exitosamente.");
    }

    @Test
    void testPopElementFromStack() {
        stack.push(1);
        int elemento = stack.pop();
        assertEquals(1, elemento, "Se extrajo último elemento de la pila exitosamente.");
    }

    @Test
    void testPeekElementFromStack() {
        stack.push(69);
        int elemento = stack.peek();
        assertEquals(69, elemento, "Se obtuvo el último elemento de la pila exitosamente.");
    }

    // ----------- Expresión compleja -----------
    @Test
    void testComplexExpression() {
        String expresion = "15 7 1 1 + - / 3 * 2 1 1 + + -"; // equivalente a (15 / (7 - (1 + 1))) * 3 - (2 + (1 + 1)) = 5
        int result = calculator.evaluate(expresion);
        assertEquals(5, result, "Expresión compleja evaluada correctamente.");
    }
}
