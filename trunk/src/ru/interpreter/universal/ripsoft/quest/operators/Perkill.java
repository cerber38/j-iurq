
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Location;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Perkill implements IOperator {
    private String[] ops = {"perkill"};

    public Perkill (){
    }

    public String[] getNames() {
        return ops;
    }

    public void parse(Parser p, Location l, int n_str, int n_stance, int e_stance) {
//        String inv= l.location.get(n_str).toLowerCase().trim();
//         if(inv.indexOf(" ")==-1)
            String str = l.location.get(n_str);
            int size = str.length();
            p.getCore().getVariables().clear();
//         else{
//             String o = inv.split(" ")[1].trim();
//            p.getCore().(o);
//         }

            if (e_stance+1<size)
                 p.parse(l, n_str, e_stance+1);
            else
                if (n_str+1<l.location.size())
                 p.parse(l, n_str+1);

    }


    public boolean parse(Parser p, String str) {
        return true;
    }



}
