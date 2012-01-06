
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
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

    public void parse(Parser p, int n_str, int n_stance, int e_stance) {
            p.getCore().getVariables().clear();
            int size = p.getStringQest(n_str).length();
            if (e_stance+1<size)
                 p.parse(n_str, e_stance+1);
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.parse(n_str+1);

    }


    public boolean parse(Parser p, String str) {
        p.getCore().setGo(true);
        return true;
    }



}
