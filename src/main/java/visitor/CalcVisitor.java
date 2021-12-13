package visitor;

import tokens.BraceToken;
import tokens.NumberToken;
import tokens.OperationToken;

import java.util.Stack;

public class CalcVisitor implements TokenVisitor {
    private final Stack<Integer> stack = new Stack<>();

    @Override
    public void visit(NumberToken numberToken) {
        stack.add(numberToken.getNumber());
    }

    @Override
    public void visit(BraceToken braceToken) {
        throw new IllegalStateException("Braces are not expected in Polish notation");
    }

    @Override
    public void visit(OperationToken operationToken) {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Given tokens are not in Polish notation");
        }
        int a = stack.pop();
        int b = stack.pop();
        switch (operationToken.getOperationType()) {
            case ADD: {
                stack.add(a + b);
                break;
            }
            case SUBTRACT: {
                stack.add(b - a);
                break;
            }
            case MULTIPLY: {
                stack.add(a * b);
                break;
            }
            case DIVIDE: {
                stack.add(b / a);
                break;
            }
        }
    }

    public int getResult() {
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Given tokens are not in Polish notation");
        }
        return stack.pop();
    }
}
