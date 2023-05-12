// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class SimpleVar extends VarMultipleList {

    private VarSingle VarSingle;

    public SimpleVar (VarSingle VarSingle) {
        this.VarSingle=VarSingle;
        if(VarSingle!=null) VarSingle.setParent(this);
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
        if(VarSingle!=null) VarSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarSingle!=null) VarSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarSingle!=null) VarSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SimpleVar(\n");

        if(VarSingle!=null)
            buffer.append(VarSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SimpleVar]");
        return buffer.toString();
    }
}
