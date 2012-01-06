
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Instr implements IOperator {
    private String[] ops = {"instr"};

    public Instr (){
    }

    public String[] getNames() {
        return ops;
    }

    public void parse(Parser p, int n_str, int n_stance, int e_stance) {
        String str = p.getStringQest(n_str, n_stance, e_stance);
        int size = p.getStringQest(n_str).length();
//        str=str.substring(n_stance, e_stance);
        if (parse(p, str)){
            if (e_stance+1<size)
                 p.parse(n_str, e_stance+1);
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.parse(n_str+1);
        }
    }


    public boolean parse(Parser p, String str) {
         str = str.substring(str.indexOf(" ")+1, str.length());
         String[] s =str.split("=");
         String value = "";
         try{
         value = s[1].trim();
         } catch (Exception ex){}
         p.getCore().getVariables().addVariable(s[0].trim(), value);
     //    System.out.println("Add instr: "+s[0]+" = "+value);
         p.getCore().setGo(true);
        return true;
    }



}
