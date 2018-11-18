package lr.util;

class ActionParseState {
    char sOrr; //状态转换s或者规约r
    int parseStateId;
    boolean isAcc;

    public ActionParseState(){
        sOrr=0;
        parseStateId=-1;
        isAcc=false;
    }

    public ActionParseState(char sOrr, int parseStateId) {
        this.sOrr = sOrr;
        this.parseStateId = parseStateId;
    }

    public ActionParseState(boolean isAcc) {
        this.isAcc = isAcc;
    }

    public char getsOrr() {
        return sOrr;
    }

    public int getParseStateId() {
        return parseStateId;
    }

    public boolean isAcc() {
        return isAcc;
    }
}
