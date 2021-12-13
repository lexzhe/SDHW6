import tokenizer.Tokenizer;
import tokenizer.TokenizerError;
import tokens.Token;
import visitor.CalcVisitor;
import visitor.ParserVisitor;
import visitor.PrintVisitor;

import java.io.*;
import java.text.ParseException;
import java.util.List;

public class Calculator {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputLine = reader.readLine() + " ";
        InputStream targetStream = new ByteArrayInputStream(inputLine.getBytes());
        Tokenizer tokenizer = new Tokenizer(targetStream);
        List<Token> infixNotationTokens;
        try {
            infixNotationTokens = tokenizer.getTokens();
        } catch (TokenizerError e){
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("Parsing");
        ParserVisitor parserVisitor = new ParserVisitor();
        for (Token token : infixNotationTokens) {
            token.accept(parserVisitor);
        }

        List<Token> polishNotationTokens;
        try {
            polishNotationTokens = parserVisitor.getPolishNotationOrder();
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("Printing");
        PrintVisitor printVisitor = new PrintVisitor(System.out);
        for (Token token : polishNotationTokens) {
            token.accept(printVisitor);
        }
        printVisitor.flush();

        System.out.println("Calculating");
        CalcVisitor calcVisitor = new CalcVisitor();
        for (Token token : polishNotationTokens) {
            token.accept(calcVisitor);
        }
        System.out.println(calcVisitor.getResult());
    }
}
