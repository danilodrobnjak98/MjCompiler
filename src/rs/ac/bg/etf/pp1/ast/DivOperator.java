// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class DivOperator extends Mulop {

    public DivOperator () {
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
        buffer.append("DivOperator(\n");

        buffer.append(tab);
        buffer.append(") [DivOperator]");
        return buffer.toString();
    }
}
