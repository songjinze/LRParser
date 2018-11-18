package lr.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 规定格式：
 * 每一行分别为
 * 状态:ACTION符号:GOTO符号
 * 每个符号间使用#分割
 *
 */
public class FileHelper {
    public AnalysisTable getAnalysisTableFromFile(File file){
        AnalysisTable analysisTable=new AnalysisTable();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String temp=bufferedReader.readLine();
            String[] symbols=temp.split(":");
            List<Token> actionTokenList=new ArrayList<Token>();
            List<Token> gotoTokenList=new ArrayList<Token>();
            String[] actionTokenSymbols=symbols[1].split("#");
            String[] gotoTokenSymbols=symbols[2].split("#");
            for(String actionTokenSymbol:actionTokenSymbols){
                Token token=new Token(actionTokenSymbol);
                actionTokenList.add(token);
            }
            for(String gotoTokenSymbol:gotoTokenSymbols){
                Token token=new Token(gotoTokenSymbol);
                actionTokenList.add(token);
            }
            analysisTable.initActionTokenList(actionTokenList);
            analysisTable.initGotoTokenList(gotoTokenList);
            while(temp!=null){
                String[]parsers=temp.split(":");
                int state=Integer.parseInt(parsers[0]);
                String[]actionSymbols=parsers[1].split("#");
                String[]gotoSymbols=parsers[2].split("#");
                analysisTable.addRow(state,actionSymbols,gotoSymbols);
                temp=bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(IOException e){
            FileHelper.errorRecord(e.getMessage());
        }
        return analysisTable;
    }

    public static void errorRecord(String message){
        // TODO 添加错误信息记录
    }
}
