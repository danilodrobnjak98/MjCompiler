// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class MultipleConst extends ConstMultipleList {

    private ConstMultipleList ConstMultipleList;
    private SingleConst SingleConst;

    public MultipleConst (ConstMultipleList ConstMultipleList, SingleConst SingleConst) {
        this.ConstMultipleList=ConstMultipleList;
        if(ConstMultipleList!=null) ConstMultipleList.setParent(this);
        this.SingleConst=SingleConst;
        if(SingleConst!=null) SingleConst.setParent(this);
    }

    public ConstMultipleList getConstMultipleList() {
        return ConstMultipleList;
    }

    public void setConstMultipleList(ConstMultipleList ConstMultipleList) {
        this.ConstMultipleList=ConstMultipleList;
    }

    public SingleConst getSingleConst() {
        return SingleConst;
    }

    public void setSingleConst(SingleConst SingleConst) {
        this.SingleConst=SingleConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstMultipleList!=null) ConstMultipleList.accept(visitor);
        if(SingleConst!=null) SingleConst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstMultipleList!=null) ConstMultipleList.traverseTopDown(visitor);
        if(SingleConst!=null) SingleConst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstMultipleList!=null) ConstMultipleList.traverseBottomUp(visitor);
        if(SingleConst!=null) SingleConst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConst(\n");

        if(ConstMultipleList!=null)
            buffer.append(ConstMultipleList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleConst!=null)
            buffer.append(SingleConst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConst]");
        return buffer.toString();
    }
}
