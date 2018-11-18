package lr.util;

import java.util.List;

public class AnalysisTable {
    private List<Token> actionTokenList;
    private List<Token> gotoTokenList;

    private List<Row> rows;

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
            ActionParseState actionParseState;
            if(str.equals("")) {
                actionParseState = new ActionParseState();
            }
            else if(str.equals("acc")){
                actionParseState=new ActionParseState(true);
            }
            else{
                actionParseState=new ActionParseState(str.charAt(0),Integer.parseInt(str.substring(1)));
            }
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

    public ActionParseState action(int state,Token token){
        Row temp=null;
        for(Row row:rows){
            if(row.state==state){
                temp=row;
                break;
            }
        }
        int index=actionTokenList.indexOf(token);
        return temp.actionParseStates.get(index);
    }

    public GotoParseState goTo(int state,Token token){
        Row temp=null;
        for(Row row:rows){
            if(row.state==state){
                temp=row;
                break;
            }
        }
        int index=gotoTokenList.indexOf(token);
        return temp.gotoParseStates.get(index);
    }
}
