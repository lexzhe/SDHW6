package visitor;

import tokens.BraceToken;
import tokens.NumberToken;
import tokens.OperationToken;
import tokens.Token;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ParserVisitor implements TokenVisitor {

    private final Stack<Token> stack = new Stack<>();
    private final List<Token> result = new ArrayList<>();

    public List<Token> getPolishNotationOrder() throws ParseException {
        while (!stack.empty()) {
            if (stack.peek() instanceof BraceToken) {
                throw new IllegalStateException("Unmatched opening parenthesis");
            }
            result.add(stack.pop());
        }
        return result;
    }

    @Override
    public void visit(NumberToken numberToken) {
        result.add(numberToken);
    }

    @Override
    public void visit(BraceToken braceToken) {
        if (braceToken.isLeft()) {
            stack.push(braceToken);
        } else {
            while (!stack.isEmpty()) {
                Token topToken = stack.pop();
                if (topToken instanceof BraceToken && ((BraceToken) topToken).isLeft()) {
                    return;
                }
                result.add(topToken);
            }
        }
    }

    @Override
    public void visit(OperationToken operationToken) {
        int priority = operationToken.getOperationType().getPriority();

        while (!stack.isEmpty()) {
            Token topToken = stack.peek();
            if (!(topToken instanceof OperationToken)) {
                break;
            }
            if (((OperationToken) topToken).getOperationType().getPriority() < priority) {
                result.add(topToken);
                stack.pop();
            } else {
                break;
            }
        }
        stack.add(operationToken);
    }
}
