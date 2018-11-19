package lr.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisTable {
    private List<Token> actionTokenList;
    private List<Token> gotoTokenList;

    private List<Row> rows;
    private Map<Integer,Grammar> grammarMap;

    public AnalysisTable(){
        actionTokenList=new ArrayList<Token>();
        gotoTokenList=new ArrayList<Token>();
        rows=new ArrayList<Row>();
        grammarMap=new HashMap<Integer, Grammar>();
    }

    public void addGrammar(int index,Grammar grammar){
        grammarMap.put(index,grammar);
    }

    public Map<Integer, Grammar> getGrammarMap() {
        return grammarMap;
    }

    public void initActionTokenList(List<Token> tokens){
        actionTokenList.addAll(tokens);
    }

    public void initGotoTokenList(List<Token> tokens){
        gotoTokenList.addAll(tokens);
    }

    public void addRow(int state,String[] actionSymbols,String[] gotoSymbols){
        Row row=new Row();
        row.state=state;
        for(String str:actionSymbols){
            ActionParseState actionParseState=new ActionParseState(str);
            row.actionParseStates.add(actionParseState);
        }
        for(String str:gotoSymbols){
            GotoParseState gotoParseState;
            if(str.equals("")){
                gotoParseState=new GotoParseState();
            }else{
                gotoParseState=new GotoParseState(Integer.parseInt(str));
            }
            row.gotoParseStates.add(gotoParseState);
        }
    }

    class Row{
        int state;
        List<ActionParseState> actionParseStates;
        List<GotoParseState> gotoParseStates;
    }

    public String action(int state,Token token){
        Row temp=null;
        for(Row row:rows){
            if(row.state==state){
                temp=row;
                break;
            }
        }
        int index=0;
        for(Token token1:actionTokenList){
            if(token1.getSymbol().equals(token.getSymbol())){
                break;
            }
            index++;
        }
        ActionParseState actionParseState=temp.actionParseStates.get(index);
        return actionParseState.getParse();
    }

    public int goTo(int state,Token token){
        Row temp=null;
        for(Row row:rows){
            if(row.state==state){
                temp=row;
                break;
            }
        }
        int index=0;
        for(Token token1:gotoTokenList){
            if(token1.getSymbol().equals(token.getSymbol())){
                break;
            }
            index++;
        }
        return temp.gotoParseStates.get(index).toStateId;
    }
}
