package tokenizer.states;

import tokenizer.Tokenizer;

public class EndState extends State{
    EndState(Tokenizer tokenizer) {
        super(tokenizer);
    }

    @Override
    public void nextState() {
        super.tokenizer.setFinished();
    }
}
