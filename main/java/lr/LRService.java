package lr;

import lr.exception.LRException;
import lr.util.AnalysisTable;
import lr.util.Token;

import java.util.List;

public interface LRService {
    /**
     * 通过词法单元列表以及语法分析表进行语法分析
     * @param tokenList 词法单元列表
     * @param analysisTable 语法分析表
     * @return
     */
    boolean lr0(List<Token> tokenList, AnalysisTable analysisTable) throws LRException;
}
