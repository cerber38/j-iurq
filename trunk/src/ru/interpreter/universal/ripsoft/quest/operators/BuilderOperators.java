package ru.interpreter.universal.ripsoft.quest.operators;

import java.util.LinkedHashMap;
import ru.interpreter.universal.ripsoft.quest.IOperator;


/**
 * 
 * @author ~jo-MA-jo~
 */
public class BuilderOperators  {

	public static LinkedHashMap<String[],IOperator> build() {
               LinkedHashMap<String[],IOperator> op = new LinkedHashMap<String[],IOperator>();
               Invkill invkill = new Invkill();
                op.put(invkill.getNames(), invkill);

               Perkill perkill = new Perkill();
                op.put(perkill.getNames(), perkill);

               Println println = new Println();
                op.put(println.getNames(), println);

               Cls cls = new Cls();
                op.put(cls.getNames(), cls);

               Print print = new Print();
                op.put(print.getNames(), print);

               If_then_else if_then_else = new If_then_else();
                op.put(if_then_else.getNames(), if_then_else);

               Inv_ inv_ = new Inv_();
                op.put(inv_.getNames(), inv_);

               Inv_plus inv_plus = new Inv_plus();
                op.put(inv_plus.getNames(), inv_plus);

               Inv_minus inv_minus = new Inv_minus();
                op.put(inv_minus.getNames(), inv_minus);

               Instr instr = new Instr();
                op.put(instr.getNames(), instr);

               GoTo goTo = new GoTo();
                op.put(goTo.getNames(), goTo);

               Proc proc = new Proc();
                op.put(proc.getNames(), proc);

               Btn btn = new Btn();
                op.put(btn.getNames(), btn);

               End end = new End();
                op.put(end.getNames(), end);

             //*******************************//
               Unknown unknown = new Unknown();
                op.put(unknown.getNames(), unknown);
	      return op;
	}




}
