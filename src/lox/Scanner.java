package lox;

import java.util.ArrayList;
import java.util.List;

import static lox.TokenType.*;

public class Scanner {
    private final String source; //line or file
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0; //first character of lexeme that is being scanned
    private int current = 0; //current character being 'consumed'
    private int line = 1; //line of current

    Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while(!isAtEnd()){
            start = current;
            scanToken();
        }
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private void scanToken(){
        char c = advance(); //gets a character on current position of scanner
        switch (c){
            case '(': addToken(LEFT_PAREN);break;
            case ')': addToken(RIGHT_PAREN);break;
            case '{': addToken(LEFT_BRACE);break;
            case '}': addToken(RIGHT_BRACE);break;
            case ',': addToken(COMMA);break;
            case '.': addToken(DOT);break;
            case '-': addToken(MINUS);break;
            case '+': addToken(PLUS);break;
            case ';': addToken(SEMICOLON);break;
            case '*': addToken(STAR);break;

        }
    }

    private char advance(){
        return source.charAt(current++);
    }

    private void addToken(TokenType type){
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal){
        String text = source.substring(start, current); //takes a lexeme being currently scanned
        tokens.add(new Token(type,text,literal,line));
    }
}
