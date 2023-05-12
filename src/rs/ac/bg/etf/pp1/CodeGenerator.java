package rs.ac.bg.etf.pp1;

import java.util.LinkedList;

import rs.ac.bg.etf.pp1.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;

	private LinkedList<GotoLabel> gotoLabelaLista = new LinkedList<GotoLabel>();
	private LinkedList<String> methodLista = new LinkedList<String>();
	
	public int getMainPc(){
		return mainPc;
	}


	public LinkedList<GotoLabel> getGotoLabelaLista() {
		return gotoLabelaLista;
	}


	public void setGotoLabelaLista(LinkedList<GotoLabel> gotoLabelaLista) {
		this.gotoLabelaLista = gotoLabelaLista;
	}

	
	//******************UTIL METHOD****************
	
	private void mainSetMethod(MethodTypeName mname) {
		mname.obj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(0);
		Code.put(mname.obj.getLocalSymbols().size());
	}
	private void utilMethod() {
		
		for(int i=0;i<gotoLabelaLista.size();i++) {

			Obj objekat = Tab.find(gotoLabelaLista.get(i).getImeLabele());

			int adresaGoto = gotoLabelaLista.get(i).getIdresa();
			int adresaLabela = objekat.getAdr();

			Code.put2(adresaGoto, adresaLabela - adresaGoto + 1);


		}
	}
	
	//====================================Method

	public void visit(MethodTypeName mname){

		String MethName = mname.getMethName();

		if("main".equalsIgnoreCase(MethName))
			mainPc = Code.pc;

		this.mainSetMethod(mname);
		this.methodLista.add(MethName);

	}

	public void visit(MethodDecl methodDecl)
	{
		this.utilMethod();
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	//====================================Print,Read
	public void visit(PrintStatementNoArg printStmt){
		//skinuta fora sa vezbi
		
		if(printStmt.getExpr().struct.equals(Tab.intType)){
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else{
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
	}

	public void visit(PrintStatementWArgs printWArgs) {
		Code.loadConst(printWArgs.getN2());
		if(printWArgs.getExpr().struct.equals(Tab.charType))
			Code.put(Code.bprint);
		else
			Code.put(Code.print);

	}

	public void visit(ReadStatement readStmt) {
		if(!readStmt.getDesignator().obj.getType().equals(Tab.charType))
			Code.put(Code.read);
		else
			Code.put(Code.bread);
		Code.store(readStmt.getDesignator().obj);
	}
	
	public void visit(DesignatorINC inc){
		if(inc.getDesignator().obj.getKind() ==Obj.Elem)
			Code.put(Code.dup2);
	
		Code.load(inc.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(inc.getDesignator().obj);
	}

	public void visit(DesignatorDEC dec){
		//ista fora kao i sa inc
		if(dec.getDesignator().obj.getKind() ==Obj.Elem)
			Code.put(Code.dup2);
		
		Code.load(dec.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(dec.getDesignator().obj);
	}

	public void visit(DesignatorArr designatorArr){
		Code.load(designatorArr.getDesignator().obj);
	}

	public void visit(AssignDesign assignDesign){
		Code.store(assignDesign.getDesignator().obj);
	}

	

	//====================================Term
	public void visit(TermC termC) { 


		Mulop instanca = termC.getMulop();
		if((instanca instanceof MulOperator)) {
			Code.put(Code.mul);
			return;
		}

		if((instanca instanceof DivOperator)) {
			Code.put(Code.div);
			return;
		}


		Code.put(Code.rem);
	}

	//====================================Expr
	public void visit(ExprS exprS){
		if((exprS.getMinusOption() instanceof Negative))
			Code.put(Code.neg);	
	}

	public void visit(ExprC exprC) {
		if(!(exprC.getAddop() instanceof PlusOperator )) 
			Code.put(Code.sub);
		else 
			Code.put(Code.add);
	}
	//====================================Factor
	public void visit(NumFactor numFactor)
	{
		Obj num = Tab.insert(Obj.Con, "$", numFactor.struct);
		num.setLevel(0);
		num.setAdr(numFactor.getN1());
		Code.load(num);
	}

	public void visit(CharFactor charFactor)
	{
		Obj ch = Tab.insert(Obj.Con, "$", charFactor.struct);
		ch.setLevel(0);
		ch.setAdr(charFactor.getC1());
		Code.load(ch);
	}

	public void visit(BoolFactor boolFactor) {
		Obj b = Tab.insert(Obj.Con, "$", boolFactor.struct);
		b.setLevel(0);
		int x = boolFactor.getB1().equalsIgnoreCase("true") ? 1 : 0;
		b.setAdr(x);
		Code.load(b);
	}

	public void visit(DesignatorFactor dessignFactor){
		Code.load(dessignFactor.getDesignator().obj);
	}

	public void visit(NewFactor newFactor){
		if(newFactor.struct.getKind()==Struct.Array){
			Code.put(Code.newarray);
			if(newFactor.getType().struct == Tab.intType)
				Code.put(1); 
			else
				Code.put(0);
		}
	}

	public void visit(GotoStatement gotoStatement) {
		//LABELA :
		//goto LABELA:
		Obj objekat = Tab.find(gotoStatement.getGotoLabela());
		//				if(objekat != Tab.noObj) {
		//					Code.put(Code.load_2);
		//					Code.put(Code.const_2);
		//					Code.put(Code.jcc);
		//				
		//					Code.put2(objekat.getAdr()-Code.pc+1);
		//					
		//					
		//				}
		if(objekat != Tab.noObj) {

			Code.put(Code.jmp);
			Code.put2(objekat.getAdr()-Code.pc+1);

		}

		//goto LABELA
		//LABELA :

		else {
			Code.put(Code.jmp);				
			this.gotoLabelaLista.add(new GotoLabel(gotoStatement.getGotoLabela(), Code.pc));	

			Code.put2(Code.const_3);

		}



	}
	public void visit(LabelTarget labelTarget) {

		Obj objekat = Tab.insert(Obj.Con, labelTarget.getName(), Tab.noType);
		objekat.setAdr(Code.pc);
		
	}


}
