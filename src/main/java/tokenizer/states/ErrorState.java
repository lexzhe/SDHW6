package tokenizer.states;

import tokenizer.Tokenizer;

public class ErrorState extends State{
    public final char errorChar;
    ErrorState(Tokenizer tokenizer, char c) {
        super(tokenizer);
        errorChar = c;
    }

    @Override
    public void nextState() {
        super.tokenizer.setFinished();
    }
}
