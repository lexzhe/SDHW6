package visitor;

import tokens.BraceToken;
import tokens.NumberToken;
import tokens.OperationToken;

public interface TokenVisitor {
    void visit(NumberToken numberToken);

    void visit(BraceToken braceToken);

    void visit(OperationToken operationToken);
}
