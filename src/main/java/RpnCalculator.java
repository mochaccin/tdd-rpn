public class RpnCalculator {

    public int evaluate(String expression) {

        if (containsDecimalNumber(expression)) {
            throw new NumberFormatException("No se permiten decimales.");
        }

        if (!containsOnlyValidCharacters(expression)) {
            throw new IllegalArgumentException("Expresión contiene caracteres inválidos.");
        }

        if (containsNegativeNumber(expression)) {
            throw new IllegalArgumentException("Solo se permiten números positivos.");
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
            
                int b = stack.pop();
                int a = stack.pop();
                switch (token) {
                    case "+" -> stack.push(a + b);
                    case "-" -> stack.push(a - b);
                    case "*" -> stack.push(a * b);
                    case "/" -> {
                        if (b == 0) throw new ArithmeticException("No se puede realizar una división por cero.");
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
            if (token.matches("\\d+")) {
                operandCount++;
            } else if (token.matches("[+\\-*/]")) {
                operatorCount++;
                if (operandCount < 2) return false;
                operandCount--;
            } else {
                return false;
            }
        }

        return operandCount == 1 && operatorCount > 0;
    }
    

    public boolean containsOnlyValidCharacters(String expression) {
        return expression.matches("[\\d+\\-*/\\s.,]+");
    }

    public boolean containsDecimalNumber(String expression) {
        String[] tokens = expression.trim().split("\\s+");
        for (String token : tokens) {
            if (token.contains(".") || token.contains(",")) {
                return true;
            }
        }
        return false;
    }

    public boolean containsNegativeNumber(String expression) {
        String[] tokens = expression.trim().split("\\s+");
        for (String token : tokens) {
            if (token.matches("-\\d+")) {
                return true;
            }
        }
        return false;
    }
}
