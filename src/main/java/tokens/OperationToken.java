package tokens;

import visitor.TokenVisitor;

public class OperationToken implements Token{

    private final OperationType operationType;

    public OperationToken(char operationChar){
        operationType = switch (operationChar){
            case '+' -> OperationType.ADD;
            case '-' -> OperationType.SUBTRACT;
            case '*' -> OperationType.MULTIPLY;
            case '/' -> OperationType.DIVIDE;
            default -> throw new IllegalStateException("Unexpected value: " + operationChar);
        };
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public OperationType getOperationType(){
        return operationType;
    }

    public enum OperationType {
        ADD(1),
        SUBTRACT(1),
        MULTIPLY(0),
        DIVIDE(0);

        private final int priority;

        OperationType(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }
}
