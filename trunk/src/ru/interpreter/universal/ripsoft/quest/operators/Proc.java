
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.ExProc;
import ru.interpreter.universal.ripsoft.quest.IOperator;
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

    public void parse(Parser p, int n_str, int n_stance, int e_stance) {
        String str = p.getStringQest(n_str, n_stance, e_stance);
        int size = p.getStringQest(n_str).length();
        if (e_stance+1<size)
                 p.getCore().addProc(new ExProc(n_str, e_stance+1));
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.getCore().addProc(new ExProc(n_str+1, 0));
        parse(p, str);

    }

    public boolean parse(Parser p, String str) {
        String s = str.substring(str.indexOf(" ")+1, str.length()).trim();
        s=p.getString(s);
        System.out.println("Proc: "+s);
        p.parse(s);
        p.getCore().setGo(true);
        return true;
    }

}
