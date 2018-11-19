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
 * 文法格式：
 * 每一行为一个产生式
 * 例如E->F*id
 * 则写作：
 * E:F#*#id
 */
public class FileHelper {
    public AnalysisTable getAnalysisTableFromFile(File tableFile,File grammarFile){
        AnalysisTable analysisTable=new AnalysisTable();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tableFile));
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
            BufferedReader grammarBufferedReader=new BufferedReader(new FileReader(grammarFile));
            temp=grammarBufferedReader.readLine();
            int count=1;
            while(temp!=null){
                String[]grammarStrs=temp.split(":");
                Token left=new Token(grammarStrs[0]);
                String[] right=grammarStrs[1].split("#");
                List<Token> rightTokens=new ArrayList<Token>();
                for(String str:right){
                    rightTokens.add(new Token(str));
                }
                Grammar grammar=new Grammar(left,rightTokens);
                analysisTable.addGrammar(count,grammar);
                count++;
                temp=grammarBufferedReader.readLine();
            }
            grammarBufferedReader.close();
        }catch(IOException e){
            FileHelper.errorRecord(e.getMessage());
        }
        return analysisTable;
    }

    public static void errorRecord(String message){
        // TODO 添加错误信息记录
    }

    public static void outputRecord(String message){
        // TODO 添加输出信息
    }
}
