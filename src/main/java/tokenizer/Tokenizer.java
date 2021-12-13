package tokenizer;

import tokenizer.states.ErrorState;
import tokenizer.states.StartState;
import tokenizer.states.State;
import tokens.Token;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final Reader reader;
    private final List<Token> tokens = new ArrayList<>();
    private boolean finished = false;

    private State state = new StartState(this);
    private char curChar;

    public Tokenizer(InputStream inputStream) {
        this.reader = new InputStreamReader(inputStream);
    }

    public List<Token> getTokens() throws IOException, TokenizerError {
        setCurChar();
        while (!finished) {
            state.nextState();
        }
        if (state instanceof ErrorState){
            throw new TokenizerError("Error starts with: " + ((ErrorState) state).errorChar);
        }
        return tokens;
    }

    public void setCurChar() throws IOException {
        if (finished) return;
        int next = reader.read();
        if (next == -1) {
            finished = true;
            curChar = '~';
        } else {
            curChar = (char) next;
        }
    }

    public char getCurChar() {
        return curChar;
    }

    public void addToken(Token token) {
        tokens.add(token);
    }

    public void setTokenizerState(State tokenizerState) {
        this.state = tokenizerState;
    }

    public void setFinished() {
        this.finished = true;
    }
}
