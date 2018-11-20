package lr;

import lr.util.AnalysisTable;
import lr.util.Token;

import java.io.File;
import java.util.List;

public interface LRService {
    /**
     * 通过词法单元列表以及语法分析表进行语法分析
     * @param tokenList 词法单元列表
     * @param analysisTable 语法分析表
     * @return
     */
    boolean lr0(List<Token> tokenList, AnalysisTable analysisTable);

    /**
     * 将语法分析表文件转换为对应的类
     * @param tableFile 语法分析表文件
     * @param grammarFile 语法分析表对应的文法
     * @return
     */
    AnalysisTable getAnalysisTableFromFiles(File tableFile,File grammarFile);
}
