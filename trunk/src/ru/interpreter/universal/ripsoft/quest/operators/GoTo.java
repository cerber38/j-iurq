
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Location;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class GoTo implements IOperator {
    private String[] ops = {"goto"};

    public GoTo (){
    }

    public String[] getNames(){
        return ops;
    }

    public void parse(Parser p, Location l, int n_str, int n_stance, int e_stance) {
        String str = l.location.get(n_str);
        str=str.substring(n_stance, e_stance);
        parse(p, str);

    }

    public boolean parse(Parser p, String str) {
        String s = p.getString(str.substring(str.indexOf(" ")+1, str.length()).trim());
  //      System.out.println("GoTo: "+s);
        p.parse(s);
        return true;
    }

}
