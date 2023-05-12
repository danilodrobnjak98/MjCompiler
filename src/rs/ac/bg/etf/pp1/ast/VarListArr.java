// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class VarListArr extends VarMultipleList {

    private VarMultipleList VarMultipleList;
    private ArrayDecl ArrayDecl;

    public VarListArr (VarMultipleList VarMultipleList, ArrayDecl ArrayDecl) {
        this.VarMultipleList=VarMultipleList;
        if(VarMultipleList!=null) VarMultipleList.setParent(this);
        this.ArrayDecl=ArrayDecl;
        if(ArrayDecl!=null) ArrayDecl.setParent(this);
    }

    public VarMultipleList getVarMultipleList() {
        return VarMultipleList;
    }

    public void setVarMultipleList(VarMultipleList VarMultipleList) {
        this.VarMultipleList=VarMultipleList;
    }

    public ArrayDecl getArrayDecl() {
        return ArrayDecl;
    }

    public void setArrayDecl(ArrayDecl ArrayDecl) {
        this.ArrayDecl=ArrayDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarMultipleList!=null) VarMultipleList.accept(visitor);
        if(ArrayDecl!=null) ArrayDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarMultipleList!=null) VarMultipleList.traverseTopDown(visitor);
        if(ArrayDecl!=null) ArrayDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarMultipleList!=null) VarMultipleList.traverseBottomUp(visitor);
        if(ArrayDecl!=null) ArrayDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarListArr(\n");

        if(VarMultipleList!=null)
            buffer.append(VarMultipleList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ArrayDecl!=null)
            buffer.append(ArrayDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarListArr]");
        return buffer.toString();
    }
}
