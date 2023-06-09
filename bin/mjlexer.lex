
package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;


%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext());}
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"goto" 		{ return new_symbol(sym.GOTO, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"read" 		{ return new_symbol(sym.READ, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"true" 		{ return new_symbol(sym.BOOL_CONST, yytext()); }
"false" 	{ return new_symbol(sym.BOOL_CONST, yytext()); }

"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.MUL, yytext()); }
"/" 		{ return new_symbol(sym.DIV, yytext()); }
"%" 		{ return new_symbol(sym.MOD, yytext()); }
"=="		{ return new_symbol(sym.SAME, yytext()); }
"!="		{ return new_symbol(sym.NOTSAME, yytext()); }
">"			{ return new_symbol(sym.G, yytext()); }
">="		{ return new_symbol(sym.GEQ, yytext()); }
"<"			{ return new_symbol(sym.L, yytext()); }
"<="		{ return new_symbol(sym.LEQ, yytext()); }
"++" 		{ return new_symbol(sym.INC, yytext()); }
"--" 		{ return new_symbol(sym.DEC, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
":" 		{ return new_symbol(sym.COLON, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"[" 		{ return new_symbol(sym.LSQUARE, yytext()); }
"]" 		{ return new_symbol(sym.RSQUARE, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }

"//" {yybegin(COMMENT);}
<COMMENT> . {yybegin(COMMENT);}
<COMMENT> "\r\n" { yybegin(YYINITIAL); }


[0-9]+  { return new_symbol(sym.NUM_CONST, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }
("'")[a-z|A-Z|0-9|_|+|-]*("'")      {return new_symbol(sym.CHAR_CONST , new Character(yytext().charAt(1)));}

. { System.err.println("Leksicka greska (" + yytext() + ") na liniji " + (yyline + 1) + " i koloni " + (yycolumn + 1)); }










