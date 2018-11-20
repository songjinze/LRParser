package lr;

import lr.util.AnalysisTable;
import lr.util.FileHelper;
import lr.util.Token;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LRServiceTest {

    LRService lrService=new LRServiceImpl();
    @org.junit.Test
    public void lr0() {
        File file=new File(this.getClass().getResource("").getFile());
        file=file.getParentFile().getParentFile();
        File testGrammarFile=new File(file.getPath()+"/classes/testGrammarFile");
        File testTableFile=new File(file.getPath()+"/classes/testTableFile");
        AnalysisTable analysisTable=lrService.getAnalysisTableFromFiles(testTableFile,testGrammarFile);
        List<Token> tokenList=new ArrayList<Token>();
        File testTokenListFile=new File(file.getPath()+"/classes/testTokenListFile");
        try{
            BufferedReader bufferedReader=new BufferedReader(new FileReader(testTokenListFile));
            String tokenSymbol=bufferedReader.readLine();
            while(tokenSymbol!=null){
                tokenList.add(new Token(tokenSymbol));
                tokenSymbol=bufferedReader.readLine();
            }
        }catch(IOException e){
            FileHelper.errorRecord(e.getMessage());
        }
        lrService.lr0(tokenList,analysisTable);
    }

    @org.junit.Test
    public void getAnalysisTableFromFiles() {
        String str="##123##";
        String[] temp=str.split("#",5);
        System.out.println(temp.length);
    }
}