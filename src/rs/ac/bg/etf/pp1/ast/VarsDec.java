// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class VarsDec extends ItemDecl {

    private VarDeclist VarDeclist;

    public VarsDec (VarDeclist VarDeclist) {
        this.VarDeclist=VarDeclist;
        if(VarDeclist!=null) VarDeclist.setParent(this);
    }

    public VarDeclist getVarDeclist() {
        return VarDeclist;
    }

    public void setVarDeclist(VarDeclist VarDeclist) {
        this.VarDeclist=VarDeclist;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclist!=null) VarDeclist.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclist!=null) VarDeclist.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclist!=null) VarDeclist.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarsDec(\n");

        if(VarDeclist!=null)
            buffer.append(VarDeclist.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarsDec]");
        return buffer.toString();
    }
}
