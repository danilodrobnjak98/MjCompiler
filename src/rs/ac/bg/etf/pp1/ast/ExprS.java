// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class ExprS extends Expr {

    private MinusOption MinusOption;
    private Term Term;

    public ExprS (MinusOption MinusOption, Term Term) {
        this.MinusOption=MinusOption;
        if(MinusOption!=null) MinusOption.setParent(this);
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
    }

    public MinusOption getMinusOption() {
        return MinusOption;
    }

    public void setMinusOption(MinusOption MinusOption) {
        this.MinusOption=MinusOption;
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MinusOption!=null) MinusOption.accept(visitor);
        if(Term!=null) Term.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MinusOption!=null) MinusOption.traverseTopDown(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MinusOption!=null) MinusOption.traverseBottomUp(visitor);
        if(Term!=null) Term.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprS(\n");

        if(MinusOption!=null)
            buffer.append(MinusOption.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprS]");
        return buffer.toString();
    }
}
