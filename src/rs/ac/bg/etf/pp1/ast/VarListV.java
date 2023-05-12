// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class VarListV extends VarMultipleList {

    private VarMultipleList VarMultipleList;
    private VarSingle VarSingle;

    public VarListV (VarMultipleList VarMultipleList, VarSingle VarSingle) {
        this.VarMultipleList=VarMultipleList;
        if(VarMultipleList!=null) VarMultipleList.setParent(this);
        this.VarSingle=VarSingle;
        if(VarSingle!=null) VarSingle.setParent(this);
    }

    public VarMultipleList getVarMultipleList() {
        return VarMultipleList;
    }

    public void setVarMultipleList(VarMultipleList VarMultipleList) {
        this.VarMultipleList=VarMultipleList;
    }

    public VarSingle getVarSingle() {
        return VarSingle;
    }

    public void setVarSingle(VarSingle VarSingle) {
        this.VarSingle=VarSingle;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarMultipleList!=null) VarMultipleList.accept(visitor);
        if(VarSingle!=null) VarSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarMultipleList!=null) VarMultipleList.traverseTopDown(visitor);
        if(VarSingle!=null) VarSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarMultipleList!=null) VarMultipleList.traverseBottomUp(visitor);
        if(VarSingle!=null) VarSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarListV(\n");

        if(VarMultipleList!=null)
            buffer.append(VarMultipleList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarSingle!=null)
            buffer.append(VarSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarListV]");
        return buffer.toString();
    }
}
