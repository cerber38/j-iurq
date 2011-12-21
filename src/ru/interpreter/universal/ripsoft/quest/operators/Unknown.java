
package ru.interpreter.universal.ripsoft.quest.operators;

import bsh.EvalError;
import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Location;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Unknown implements IOperator {

    public Unknown (){
    }

    public String[] getNames() {
        return new String[]{"UNKNOWN"};
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
        String[] s =str.split("=");

        if (p.getCore().getLocation().containsKey(str.trim().toLowerCase())){
            Location l = p.getCore().getLocation().get(str.trim().toLowerCase());
            p.parse(l, 0);
            return false;
        }else

        if (s.length==2&&s[0].trim().indexOf(" ")==-1){
            String out = "";
            String t=s[1].trim();
             p.getCore().getVariables().addVariable(s[0].trim(), "0");
             t=t.replace(s[0].trim(), "#"+s[0].trim()+"$");
            try {
                out = p.getIValue(t).get("out").toString();//getValue(s[1].trim()).get("out").toString();
            } catch (Exception ex) {}
                out = out.isEmpty() ? s[1].trim() : out;
            
                p.getCore().getVariables().addVariable(s[0].trim(), out /*s[1].trim()*/);
 //               System.out.println("Add variable: "+s[0]+" = "+out);
        }else
            System.out.println("!UNKNOWN! operator : "+str);
        return true;
    }

}
