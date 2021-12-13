package tokens;

import visitor.TokenVisitor;

public class BraceToken implements Token{

    // false = left, true = right
    private final boolean brace;

    public BraceToken(boolean brace){
        this.brace = brace;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public boolean isLeft() {
        return !brace;
    }
}
