package tokenizer.states;

import tokenizer.Tokenizer;
import tokenizer.TokenizerError;

public abstract class State {

    Tokenizer tokenizer;

    State(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
    }

    public abstract void nextState() throws TokenizerError;
}
