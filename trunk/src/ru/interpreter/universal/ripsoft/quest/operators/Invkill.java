
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Invkill implements IOperator {
    private String[] ops = {"invkill"};

    public Invkill (){
    }

    public String[] getNames() {
        return ops;
    }

    public void parse(Parser p, int n_str, int n_stance, int e_stance) {
        String inv = p.getStringQest(n_str, n_stance, e_stance);
        int size = p.getStringQest(n_str).length();
        inv=inv.substring(n_stance, e_stance);
         if(inv.indexOf(" ")==-1)
            p.getCore().getInventory().clear();
         else{
             String o = inv.split(" ")[1].trim();
            p.getCore().getInventory().remove(o);
         }
            if (e_stance+1<size)
                 p.parse(n_str, e_stance+1);
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.parse(n_str+1);
     }


    public boolean parse(Parser p, String str) {
        return true;
    }



}
