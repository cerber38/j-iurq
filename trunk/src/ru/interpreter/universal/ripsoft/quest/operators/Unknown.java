
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
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

    public void parse(Parser p, int n_str, int n_stance, int e_stance) {
        String str = p.getStringQest(n_str, n_stance, e_stance);
        int size = p.getStringQest(n_str).length();
        if (parse(p, str)){
            if (e_stance+1<size)
                 p.parse(n_str, e_stance+1);
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.parse(n_str+1);
        }
    }


    public boolean parse(Parser p, String str) {
        String[] s =str.split("=");

        if (p.getCore().getListLocations().containsKey(str.trim().toLowerCase())){
           // Location l = p.getCore().getLocation().get(str.trim().toLowerCase());
            p.parse(p.getCore().getListLocations().get(str.trim().toLowerCase()));
            return false;
        }else

        if (s.length==2&&s[0].trim().indexOf(" ")==-1){

            String out = "";
            String t=p.replaceMatOperators(s[1].trim());
             System.out.println("parse UNKNOWN : "+str);
            // p.getCore().getVariables().addVariable(s[0].trim(), "0");
             p.parseAllUnknownVariables(str);
           //  t=t.replace(" "+s[0].trim()+" ", " #"+s[0].trim()+"$ ");
            try {
                out = p.getIValue(t).get("out").toString();//getValue(s[1].trim()).get("out").toString();
            } catch (Exception ex) {}
                out = out.isEmpty() ? s[1].trim() : out;
            
                p.getCore().getVariables().addVariable(s[0].trim(), out /*s[1].trim()*/);
         //       System.out.println("Add UNKNOWN variable: "+s[0]+" = "+out);
        }else
            System.out.println("!UNKNOWN! operator : "+str);
        return true;
    }

}
