package lr.util;

public class Token {
    private String symbol; //词法单元符号

    public String getSymbol() {
        return symbol;
    }

    public Token(String symbol) {
        this.symbol = symbol;
    }
}
