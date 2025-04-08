import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

// ----------- Decimales: evaluar que no se puedan usar decimales -----------
 @Test
 void testDecimalNoPermitido() {
     String expresion = "2.5 3 +";
     assertThrows(NumberFormatException.class, () -> calculator.evaluate(expresion),
         "No se permiten decimales en la expresión.");
 }
    


    

            // Operaciones básicas

    // Suma correcta
    @Test
    void testSumaRPN() {
        String expression = "1 3 +";
        int result = calculator.calculate(expression);
        assertEquals(4, result, "Suma realizada exitosamente");
    }

    // Resta correcta
    @Test
    void testRestaRPN() {
        String expression = "2 1 -";
        int result = calculator.calculate(expression);
        assertEquals(1, result, "Resta realizada exitosamente.");
    }

    // Multiplicación correcta
    @Test
    void testMultiplicacionRPN() {
        String expression = "2 2 *";
        int result = calculator.calculate(expression);
        assertEquals(4, result, "Multiplicación realizada exitosamente.");
    }

    // División correcta
    @Test
    void testDivisionRPN() {
        String expression = "2 2 /";
        int result = calculator.calculate(expression);
        assertEquals(1, result, "División realizada exitosamente.");
    }


     // ----------- Muy grande -----------
    @Test
    void testNumeroMuyGrandeRPN() {
        String expresion = "2147483647 1 -"; 
        int result = calculator.evaluate(expresion);
        assertEquals(2147483646, result, "Número muy grande evaluado correctamente.");
    }



            // Muy pequeño    
    @Test
    void testNumeroMuyPequenoRPN() {
        String expresion = "-2147483648 1 +"; // Integer.MIN_VALUE
        int result = calculator.evaluate(expresion);
        assertEquals(-2147483647, result, "Número muy pequeño evaluado correctamente.");
    }

    

            // División por 0


            // Caracteres invalidos

    @Test
    void testExpresionCorrectaRPN() {
        String expresion = "3 5 3 * +";
        int result = calculator.evaluate(expresion);
        assertEquals(18, result, "Se ingresó la expresión correctamente.");
    }

    @Test
    void testExpresionInvalidaRPN() {
        String expresion = "3 + *";
        assertFalse(calculator.isValidExpression(expresion), "Expresión inválida detectada.");
    }
    
    // Expresión debe tener operadores
    @Test
    void testExpresionSoloOperandosRPN() {
        String expresion = "3 4 5";
        assertFalse(calculator.isValidExpression(expresion), "Ingresaste una expresión sin operandos.");
    }

    // Expresión debe tener números
    @Test
    void testExpresionSinOperandosRPN() {
        String expresion = "+ - * /";
        assertFalse(calculator.isValidExpression(expresion), "Ingresaste una expresión sin numeros.");
    }

    // Expresión usa carácteres válidos
    @Test
    void testCaracteresValidosRPN() {
        String expresion = "3 5 2 * +";
        assertTrue(calculator.containsOnlyValidCharacters(expresion), "Caracteres ingresados validos.");
    }

    // Expresión usa carácteres inválidos
    @Test
    void testCaracteresInvalidosRPN() {
        String expresion = "3 5 xa +";
        assertFalse(calculator.containsOnlyValidCharacters(expresion), "Ingresaste un carácter inválido.");
    }
    
            // Control de la pila

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

            // Expresión compleja
    // Una expresión que tenga al menos los 4 operandos en un stack


    

}

