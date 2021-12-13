package visitor;

import tokens.BraceToken;
import tokens.NumberToken;
import tokens.OperationToken;

import java.io.OutputStream;
import java.io.PrintWriter;

public class PrintVisitor implements TokenVisitor{
    private final PrintWriter printWriter;

    public PrintVisitor(OutputStream outputStream) {
        this.printWriter = new PrintWriter(outputStream);
    }

    @Override
    public void visit(NumberToken numberToken) {
        printWriter.print(numberToken.getNumber());
        printWriter.print(' ');
    }

    @Override
    public void visit(BraceToken braceToken) {
        boolean brace = braceToken.isLeft();
        if (!brace) {
            printWriter.print('(');
        } else {
            printWriter.print(')');
        }
    }

    @Override
    public void visit(OperationToken operationToken) {
        switch (operationToken.getOperationType()) {
            case ADD: {
                printWriter.print('+');
                break;
            }
            case SUBTRACT: {
                printWriter.print('-');
                break;
            }
            case MULTIPLY: {
                printWriter.print('*');
                break;
            }
            case DIVIDE: {
                printWriter.print('/');
                break;
            }
        }
        printWriter.print(' ');
    }

    public void flush() {
        printWriter.println();
        printWriter.flush();
    }
}
