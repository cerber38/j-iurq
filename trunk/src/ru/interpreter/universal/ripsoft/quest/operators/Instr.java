
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Location;
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

    public void parse(Parser p, Location l, int n_str, int n_stance, int e_stance) {
        String str =l.location.get(n_str).trim();
        int size = str.length();
        str=str.substring(n_stance, e_stance);
        if (parse(p, str)){
            if (e_stance+1<size)
                 p.parse(l, n_str, e_stance+1);
            else
                if (n_str+1<l.location.size())
                 p.parse(l, n_str+1);
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
        return true;
    }



}