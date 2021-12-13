package tokenizer.states;

import tokenizer.Tokenizer;
import tokens.NumberToken;

import java.io.IOException;

public class NumberState extends State {
    private final StringBuilder stringBuilder = new StringBuilder();

    NumberState(Tokenizer tokenizer, char first) {
        super(tokenizer);
        stringBuilder.append(first);
    }

    @Override
    public void nextState() {
        char character = super.tokenizer.getCurChar();
        if (Character.isDigit(character)) {
            stringBuilder.append(character);
            try {
                super.tokenizer.setCurChar();
            } catch (IOException e) {
                super.tokenizer.setTokenizerState(new ErrorState(super.tokenizer, character));
            }
        } else {
            super.tokenizer.addToken(new NumberToken(Integer.parseInt(stringBuilder.toString())));
            super.tokenizer.setTokenizerState(new StartState(super.tokenizer));
        }
    }
}
