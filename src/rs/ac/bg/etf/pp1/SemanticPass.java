package rs.ac.bg.etf.pp1;

import java.util.LinkedList;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticPass extends VisitorAdaptor {

	public int printCallCount = 0;
	public int varDeclCount = 0;
	public Obj currentMethod = null;
	public Struct currentType = null;
	public boolean errorDetected = false;
	public int nVars;

	private LinkedList<String> gotoLabele = new LinkedList<String>();

	Logger log = Logger.getLogger(getClass());

	//********************UTILS***********************
	//error log
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	//info log
	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}

	public boolean passed(){

		return !errorDetected;
	}
	//********************VISIT***********************

	public void visit(Program program) {

		nVars = Tab.currentScope.getnVars();
		Tab.chainLocalSymbols(program.getProgName().obj);
		Tab.closeScope();


	}
	public void visit(ProgName progName) {

		progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
		Tab.openScope();

	}

	public void visit(VarSingle varDecl){

		Obj v = Tab.find(varDecl.getVarName());
		if( v == Tab.noObj) {

			Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), currentType);
			report_info("Deklarisana je promenljiva : " + varDecl.getVarName(), varDecl);
			this.varDeclCount++;
		}
		else 
			report_error("Ime : " + varDecl.getVarName() + "je vec deklarisano", null);

	}

	public void visit(Type type){

		Obj typeNode = Tab.find(type.getTypeName());

		if(typeNode == Tab.noObj){

			report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", null);
			currentType = Tab.noType;

		}
		else{

			if(Obj.Type == typeNode.getKind()){

				currentType = typeNode.getType();

			}else{

				report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
				currentType = Tab.noType;

			}
		}
	}

	public void visit (MethodTypeName methodTypeName) {

		currentMethod = Tab.insert(Obj.Meth, methodTypeName.getMethName(), Tab.noType);
		methodTypeName.obj = currentMethod;
		Tab.openScope();
		report_info("Obradjuje se funkcija : " + methodTypeName.getMethName(), methodTypeName);

	}
	private void gotoTask() {

		for(int i = 0;i< gotoLabele.size(); i++) {
			String trenutna = gotoLabele.get(i);
			Obj objekat = Tab.find(trenutna);
			if(objekat == Tab.noObj) 
				report_error("Goto labela nema gde da skoci!", null);

		}

	}
	public void visit (MethodDecl methodDecl) {

		Tab.chainLocalSymbols(currentMethod);
		//obrada goto labela
		gotoTask();
		Tab.closeScope();
		currentMethod = null;
	}

	public void visit (ArrVar arrayDecl) {

		String arrayName = arrayDecl.getArrName();

		if(arrayName == null ) return;

		Obj arr = Tab.find(arrayName);
		if( arr == Tab.noObj) {

			varDeclCount++;
			Obj varNode = Tab.insert(Obj.Var, arrayName, new Struct(Struct.Array, currentType));
			//varNode nema neku ulogu
			//moze i provera da li je varNode null pa da tek onda ide report_info
			report_info("Deklarisana je promenljiva niza : " + arrayName, arrayDecl);
		}
		else 
			report_error("Ime niza : " + arrayName + "je vec definisano!", null);


	}

	public void visit(SingleNumConst num) {

		if(currentType.getKind() == Struct.Int) {

			Obj nC = Tab.insert(Obj.Con, num.getConstName(), currentType);
			nC.setAdr(num.getConstValue());	
			return;
		}

		report_error("Ime : " +  num.getConstName() + " nije konstanta! ", null);
		return;
	}
	public void visit(SingleCharConst ch) {

		if(currentType.getKind() == Struct.Char) {

			Obj nC = Tab.insert(Obj.Con, ch.getCharName(), currentType);
			nC.setAdr(ch.getCharValue());
			return;
		}

		report_error("Ime : " +  ch.getCharName() + " nije konstanta! ", null);
		return;

	}
	public void visit(SingleBoolConst boolConst) {

		int flag = 0;
		if(currentType.getKind() == Struct.Bool) {


			Obj newConst = Tab.insert(Obj.Con, boolConst.getBoolName(), currentType);

			if(boolConst.getBoolValue().equals("true")) { //mora equals jer je string
				flag = 1;
			}
			else {
				flag = 0;
			}

			newConst.setAdr(flag);

		}
		else {
			report_error("Ime : " + boolConst.getBoolName() + "nije konstanta!", null);
		}

	}
	//********************FACTOR***********************

	public void visit(DesignatorFactor designFactor){

		designFactor.struct = designFactor.getDesignator().obj.getType();
	}

	public void visit(NumFactor numFactor){
		numFactor.struct = Tab.intType;
	}

	public void visit(BoolFactor boolFactor){
		boolFactor.struct = BooleanType.boolType; 
	}

	public void visit(CharFactor charFactor){
		charFactor.struct = Tab.charType;
	}

	public void visit(DesignatorINC inc)
	{

		if(inc.getDesignator().obj.getKind()!= Obj.Var && inc.getDesignator().obj.getKind()!= Obj.Elem )
			report_error("Designator nije promenljiva", null);

		if(inc.getDesignator().obj.getType() != Tab.intType)
			report_error("Designator nije tipa int", null);

	}

	public void visit(DesignatorDEC dec)
	{

		if(dec.getDesignator().obj.getKind() != Obj.Var && dec.getDesignator().obj.getKind() != Obj.Elem )
			report_error("Designator mora biti promenljiva ili niz", null);

		if(dec.getDesignator().obj.getType() != Tab.intType)
			report_error("Designator mora biti tipa int", null);
	}

	public void visit(NewFactor newFactor)
	{
		if(newFactor.getExpr().struct == Tab.intType)
			newFactor.struct = new Struct(Struct.Array, currentType);
		else
			report_error("Nije tipa int!", newFactor);

	}

	public void visit(ExprFactor exprFactor)
	{
		exprFactor.struct = exprFactor.getExpr().struct;
	}


	//********************DESIGNATORS***********************

	public void visit(SingleDesignator singleDesignator) {

		String ime = singleDesignator.getName();
		Obj obj = Tab.find(singleDesignator.getName());

		if(obj != Tab.noObj) 
			singleDesignator.obj = obj;
		else
			report_error("Greska ime : " + ime  + "nije deklarisano !" , null);

	}

	public void visit(ArrayDesignator arrayDesignator) {

		Obj obj  = Tab.find(arrayDesignator.getDesignatorArr().getDesignator().obj.getName());

		if(obj == Tab.noObj) 

			report_error("GRESKA ime niza nije definisano !", arrayDesignator);

		else if(obj.getType().getKind() != Struct.Array)

			report_error("GRESKA nije tipa array !", arrayDesignator);

		else 
			arrayDesignator.obj =  new Obj(Obj.Elem, "ArrElem", obj.getType().getElemType());

	}

	public void visit(AssignDesign assign){

		if(assign.getExpr().struct.assignableTo(assign.getDesignator().obj.getType())==false)
		{
			report_error("Greska prilikom dodele", assign);
		}
	}

	public void visit(TermS term){

		term.struct = term.getFactor().struct;
	}

	public void visit(ExprS termExpr){

		termExpr.struct = termExpr.getTerm().struct;

	}

	public void visit(ExprC exprC){
		// skinuta fora sa sajta

		if(exprC.getExpr().struct.equals(exprC.getTerm().struct) && exprC.getExpr().struct == Tab.intType){
			exprC.struct = exprC.getExpr().struct;
		}
		else{
			report_error("Greska na liniji "+ exprC.getLine()+" : nekompatibilni tipovi u izrazu.", null);
			exprC.struct = Tab.noType;
		}

	}

	public void visit(TermC termC){


		if(termC.getTerm().struct.equals(termC.getFactor().struct) && termC.getTerm().struct == Tab.intType){
			termC.struct = termC.getTerm().struct;
		}
		else{
			report_error("Greska na liniji "+ termC.getLine()+" : nekompatibilni tipovi u izrazu.", null);
			termC.struct = Tab.noType;
		}

	}


	//******************Print***************************

	public void visit(PrintStatementNoArg printStmt)
	{
		if(printStmt.getExpr().struct != Tab.intType &&
				printStmt.getExpr().struct != Tab.charType &&
				printStmt.getExpr().struct != BooleanType.boolType
				)
		{
			report_error("Designator mora biti tipa int, char, bool", printStmt);
		}else
		{
			printCallCount++;
		}
	}

	public void visit(PrintStatementWArgs printStmt)
	{
		if(printStmt.getExpr().struct != Tab.intType &&
				printStmt.getExpr().struct != Tab.charType &&
				printStmt.getExpr().struct != BooleanType.boolType
				)
		{
			report_error("Designator mora biti tipa int, char, bool", printStmt);
		}else
		{
			printCallCount++;
		}
	}

	//******************Read***************************

	public void visit(ReadStatement readStmt){

		//<java classname="rs.etf.pp1.mj.runtime.Run" input = "test/ulaz.txt">
		//plus napraviti fajl odakle ce da se cita

		int x = readStmt.getDesignator().obj.getKind();
		if(x!= Obj.Var && x!=Obj.Elem)
		{
			report_error("Designator mora biti promenljiva ili niz", readStmt);
		}

		if(readStmt.getDesignator().obj.getType() != Tab.intType &&
				readStmt.getDesignator().obj.getType() != Tab.charType &&
				readStmt.getDesignator().obj.getType() != BooleanType.boolType
				)
		{
			report_error("Designator mora biti tipa int, char, bool", readStmt);
		}
	}
	//******************LABELA***************************

	private void dodajLabelu(String lbl) {
		this.gotoLabele.add(lbl);
	}
	private void skiniLabelu(GotoLabel gotoLabel) {

		this.gotoLabele.remove(gotoLabel);
	}
	public void visit(LabelTarget labelTarget) {

		// nije definisana ok 
		// mora da bude u okviru scopa
		//dva ista imena ok
		Obj prijem;
		report_info("Procitali smo labelu : " + labelTarget.getName(), labelTarget);
		Obj objekat = Tab.find(labelTarget.getName());

		if(objekat == Tab.noObj)
			prijem = Tab.insert(Obj.Con, labelTarget.getName(), currentType);
		else
			report_error("Labela : " + labelTarget.getName() + " je vec deklarisana"
					, labelTarget);


	}
	public void visit(GotoStatement gotoStatement) {

		//adding elem to list
		String lbl = gotoStatement.getGotoLabela();
		this.dodajLabelu(lbl);

	}




}
