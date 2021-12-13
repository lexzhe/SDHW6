package tokens;

import visitor.TokenVisitor;

public interface Token {

    void accept(TokenVisitor tokenVisitor);
}
