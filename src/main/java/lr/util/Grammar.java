package lr.util;

import java.util.List;

public class Grammar {
    /**
     * left->right
     */
    private Token left;
    private List<Token> right;

    public Grammar(Token left, List<Token> right) {
        this.left = left;
        this.right = right;
    }

    public Token getLeft() {
        return left;
    }

    public List<Token> getRight() {
        return right;
    }
}
