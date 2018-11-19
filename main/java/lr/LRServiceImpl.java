package lr;

import lr.exception.LRException;
import lr.util.AnalysisTable;
import lr.util.FileHelper;
import lr.util.Grammar;
import lr.util.Token;

import java.io.File;
import java.util.List;
import java.util.Stack;

public class LRServiceImpl implements LRService{

    public boolean lr0(List<Token> tokenList, AnalysisTable analysisTable) throws LRException {
        Stack<Integer> stack=new Stack<Integer>();
        int n=0;
        Token a=tokenList.get(n);
        stack.push(0);
        while(true){
            int s=stack.peek();
            String actionParse=analysisTable.action(s,a);
            if(actionParse.charAt(0)=='s'){
                stack.push(Integer.parseInt(actionParse.substring(1)));
                FileHelper.outputRecord("put stack");
            }
            else if(actionParse.charAt(0)=='r'){
                int grammarIndex=Integer.parseInt(actionParse.substring(1));
                Grammar grammar=analysisTable.getGrammarMap().get(grammarIndex);
                Token left=grammar.getLeft();
                int rightTokenNum=grammar.getRight().size();
                for(;rightTokenNum>0;rightTokenNum--){
                    stack.pop();
                }
                int tempStackTop=stack.peek();
                stack.push(analysisTable.goTo(tempStackTop,left));
                String message=left.getSymbol()+"->";
                for(Token token:grammar.getRight()){
                    message=message+token.getSymbol();
                }
                FileHelper.outputRecord(message);
            }
            else if(actionParse.equals("acc")){
                FileHelper.outputRecord("acc");
                break;
            }
            else {
                throw new LRException();
            }
        }
        return true;
    }
}
