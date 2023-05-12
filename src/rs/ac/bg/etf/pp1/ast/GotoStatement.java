// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class GotoStatement extends Statement {

    private String gotoLabela;

    public GotoStatement (String gotoLabela) {
        this.gotoLabela=gotoLabela;
    }

    public String getGotoLabela() {
        return gotoLabela;
    }

    public void setGotoLabela(String gotoLabela) {
        this.gotoLabela=gotoLabela;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GotoStatement(\n");

        buffer.append(" "+tab+gotoLabela);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GotoStatement]");
        return buffer.toString();
    }
}
