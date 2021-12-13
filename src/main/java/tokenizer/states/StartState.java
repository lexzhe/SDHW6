package tokenizer.states;

import tokenizer.Tokenizer;
import tokens.BraceToken;
import tokens.OperationToken;
import tokens.Token;

import java.io.IOException;

public class StartState extends State {

    public StartState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void nextState() {
        char c = super.tokenizer.getCurChar();
        try {
            super.tokenizer.setCurChar();
        } catch (IOException e) {
            super.tokenizer.setTokenizerState(new ErrorState(super.tokenizer, c));
            return;
        }
        if (Character.isDigit(c)) {
            super.tokenizer.setTokenizerState(new NumberState(super.tokenizer, c));
            return;
        }
        State nextState = switch (c) {
            case ('~') -> new EndState(super.tokenizer);
            case (' ') -> this;
            case ('(') -> addTokenAndReturnStartState(new BraceToken(false));
            case (')') -> addTokenAndReturnStartState(new BraceToken(true));
            case ('+') -> addTokenAndReturnStartState(new OperationToken('+'));
            case ('-') -> addTokenAndReturnStartState(new OperationToken('-'));
            case ('*') -> addTokenAndReturnStartState(new OperationToken('*'));
            case ('/') -> addTokenAndReturnStartState(new OperationToken('/'));
            default -> new ErrorState(super.tokenizer, c);
        };

        super.tokenizer.setTokenizerState(nextState);
    }


    private State addTokenAndReturnStartState(Token token) {
        super.tokenizer.addToken(token);
        return this;
    }
}
