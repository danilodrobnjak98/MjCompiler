// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class SingleVar extends VarDeclist {

    private Type Type;
    private VarMultipleList VarMultipleList;

    public SingleVar (Type Type, VarMultipleList VarMultipleList) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarMultipleList=VarMultipleList;
        if(VarMultipleList!=null) VarMultipleList.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarMultipleList getVarMultipleList() {
        return VarMultipleList;
    }

    public void setVarMultipleList(VarMultipleList VarMultipleList) {
        this.VarMultipleList=VarMultipleList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarMultipleList!=null) VarMultipleList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarMultipleList!=null) VarMultipleList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarMultipleList!=null) VarMultipleList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleVar(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarMultipleList!=null)
            buffer.append(VarMultipleList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleVar]");
        return buffer.toString();
    }
}
