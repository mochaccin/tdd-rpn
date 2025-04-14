public class RpnCalculator {

    public int evaluate(String expression) {
        if (!containsOnlyValidCharacters(expression)) {
            throw new IllegalArgumentException("Expresión contiene caracteres inválidos.");
        }

        if (!isValidExpression(expression)) {
            throw new IllegalArgumentException("Expresión inválida.");
        }

        StackTDA stack = new StackTDA();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            try {
                int number = Integer.parseInt(token);
                stack.push(number);
            } catch (NumberFormatException e) {
                if (token.contains(".")) {
                    throw new NumberFormatException("No se permiten decimales.");
                }

                // operador
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> {
                        if (b == 0) throw new ArithmeticException("División por cero.");
                        stack.push(a / b);
                    }
                    default -> throw new IllegalArgumentException("Operador inválido: " + token);
                }
            }
        }

        return stack.pop();
    }

    public boolean isValidExpression(String expression) {
        String[] tokens = expression.trim().split("\\s+");
        int operandCount = 0;
        int operatorCount = 0;

        for (String token : tokens) {
            if (token.matches("-?\\d+")) {
                operandCount++;
            } else if (token.matches("[+\\-*/]")) {
                operatorCount++;
                if (operandCount < 2) return false;
                operandCount--; 
            } else {
                return false;
            }
        }

        return operandCount == 1;
    }

    public boolean containsOnlyValidCharacters(String expression) {
        return expression.matches("[\\d+\\-*/\\s]+");
    }
}
