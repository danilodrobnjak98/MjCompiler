// generated with ast extension for cup
// version 0.8
// 17/1/2022 1:3:6


package rs.ac.bg.etf.pp1.ast;

public class ItemDeclarations extends ItemDeclList {

    private ItemDeclList ItemDeclList;
    private ItemDecl ItemDecl;

    public ItemDeclarations (ItemDeclList ItemDeclList, ItemDecl ItemDecl) {
        this.ItemDeclList=ItemDeclList;
        if(ItemDeclList!=null) ItemDeclList.setParent(this);
        this.ItemDecl=ItemDecl;
        if(ItemDecl!=null) ItemDecl.setParent(this);
    }

    public ItemDeclList getItemDeclList() {
        return ItemDeclList;
    }

    public void setItemDeclList(ItemDeclList ItemDeclList) {
        this.ItemDeclList=ItemDeclList;
    }

    public ItemDecl getItemDecl() {
        return ItemDecl;
    }

    public void setItemDecl(ItemDecl ItemDecl) {
        this.ItemDecl=ItemDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ItemDeclList!=null) ItemDeclList.accept(visitor);
        if(ItemDecl!=null) ItemDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ItemDeclList!=null) ItemDeclList.traverseTopDown(visitor);
        if(ItemDecl!=null) ItemDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ItemDeclList!=null) ItemDeclList.traverseBottomUp(visitor);
        if(ItemDecl!=null) ItemDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ItemDeclarations(\n");

        if(ItemDeclList!=null)
            buffer.append(ItemDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ItemDecl!=null)
            buffer.append(ItemDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ItemDeclarations]");
        return buffer.toString();
    }
}
