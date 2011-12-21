
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.ExProc;
import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Location;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Proc implements IOperator {
    private String[] ops = {"proc"};

    public Proc (){
    }

    public String[] getNames(){
        return ops;
    }

    public void parse(Parser p, Location l, int n_str, int n_stance, int e_stance) {
        String str = l.location.get(n_str);
        int size = str.length();
        if (e_stance+1<size)
                 p.getCore().addProc(new ExProc(l, n_str, e_stance+1));
            else
                if (n_str+1<l.location.size())
                 p.getCore().addProc(new ExProc(l, n_str+1, 0));
        str=str.substring(n_stance, e_stance);

        parse(p, str);

    }

    public boolean parse(Parser p, String str) {
        String s = str.substring(str.indexOf(" ")+1, str.length()).trim();
        s=p.getString(s);
 //       System.out.println("Proc: "+s);
        p.parse(s);
        return true;
    }

}
