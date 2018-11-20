package lr.util;

class GotoParseState {
    int toStateId;

    public GotoParseState(){
        toStateId=-1;
    }
    public GotoParseState(int toStateId) {
        this.toStateId = toStateId;
    }

    public int getToStateId() {
        return toStateId;
    }
}
