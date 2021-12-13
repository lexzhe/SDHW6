package tokens;

import visitor.TokenVisitor;

public class NumberToken implements Token{

    private final int number;

    public NumberToken(int number) {
        this.number = number;
    }

    @Override
    public void accept(TokenVisitor tokenVisitor) {
        tokenVisitor.visit(this);
    }

    public int getNumber(){
        return number;
    }
}
