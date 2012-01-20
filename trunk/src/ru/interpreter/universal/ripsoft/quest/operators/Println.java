
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.JiURQ;
import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Println implements IOperator {
    private String[] ops = {"pln", "println"};

    public Println (){
    }

    public String[] getNames() {
        return ops;
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
        if (str.lastIndexOf(" ")<0)str="";
        else{
        str = str.substring(str.indexOf(" ")+1, str.length());
        str=p.getString(str,true);
          //      System.out.println("getClearString(str) "+str);
        }
        p.addOutString(str+"\n");
        p.getCore().setGo(true);
        return true;
    }

}
