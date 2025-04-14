public class StackTDA {
    private int[] elements;
    private int top;

    public StackTDA() {
        elements = new int[10]; 
        top = -1;
    }

    public void push(int value) {
        if (top == elements.length - 1) {
            throw new StackOverflowError("La pila está llena.");
        }
        elements[++top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return elements[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía.");
        }
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}
