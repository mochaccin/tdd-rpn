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

    @Test
    void testPilaVacia() {
        assertTrue(stack.isEmpty(), "La pila está vacía.");
    }

    @Test
    void testAgregarElemento() {
        stack.push(1);
        assertFalse(stack.isEmpty(), "Se agrego un elemento a la pila exitosamente.");
    }

    @Test
    void testExtraerElemento() {
        stack.push(1);
        int elemento = stack.pop();
        assertEquals(1, elemento, "Se extrajo último elemento de la pila exitosamente.");
    }

    @Test
    void testObtenerElemento() {
        stack.push(69);
        int elemento = stack.peek();
        assertEquals(69, elemento, "Se obtuvo el último elemento de la pila exitosamente.");
    }

    @Test
    void testSumaRPN() {
        String expression = "1 3 +";
        int result = calculator.evaluate(expression);
        assertEquals(4, result, "Suma realizada exitosamente");
    }

    @Test
    void testRestaRPN() {
        String expression = "2 1 -";
        int result = calculator.evaluate(expression);
        assertEquals(1, result, "Resta realizada exitosamente.");
    }

    @Test
    void testMultiplicacionRPN() {
        String expression = "2 2 *";
        int result = calculator.evaluate(expression);
        assertEquals(4, result, "Multiplicación realizada exitosamente.");
    }

    @Test
    void testDivisionRPN() {
        String expression = "2 2 /";
        int result = calculator.evaluate(expression);
        assertEquals(1, result, "División realizada exitosamente.");
    }

    @Test
    void testExpresionCorrectaRPN() {
        String expresion = "3 5 3 * +";
        int result = calculator.evaluate(expresion);
        assertEquals(18, result, "Se ingresó la expresión correctamente.");
    }

    @Test
    void testExpresionSoloOperandosRPN() {
        String expresion = "3 4 5";
        assertFalse(calculator.isValidExpression(expresion), "Ingresaste una expresión sin operandos.");
    }


    @Test
    void testExpresionSinOperandosRPN() {
        String expresion = "+ - * /";
        assertFalse(calculator.isValidExpression(expresion), "Ingresaste una expresión sin numeros.");
    }

    @Test
    void testCaracteresValidosRPN() {
        String expresion = "3 5 2 * +";
        assertTrue(calculator.containsOnlyValidCharacters(expresion), "Caracteres ingresados validos.");
    }

    @Test
    void testExpresionInvalidaRPN() {
        String expresion = "3 + *";
        assertFalse(calculator.isValidExpression(expresion), "Expresión inválida detectada.");
    }

    @Test
    void testExpresionConCaracteresInvalidos() {
        String expresion = "3 5 # *";
        assertFalse(calculator.containsOnlyValidCharacters(expresion), "Ingresaste un carácter no válido.");
    }
}
