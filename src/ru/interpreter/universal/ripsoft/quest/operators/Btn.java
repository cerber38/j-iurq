
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IButton;
import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Btn implements IOperator {
    private String[] ops = {"btn"};

    public Btn (){
    }

    public String[] getNames() {
        return ops;
    }

    public void parse(Parser p,int n_str, int n_stance, int e_stance) {
        String str = p.getStringQest(n_str, n_stance, e_stance);
        int size = p.getStringQest(n_str).length();
//        str=str.substring(n_stance, e_stance);
        if (parse(p, str)){
            if (e_stance+1<size)
                 p.parse(n_str, e_stance+1);
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.parse(n_str+1);
        }
    }

    public boolean parse(Parser p, String str) {
    //      System.out.println(" Btn parse: "+str);
        str = str.substring(str.indexOf(" ")+1, str.length());
        String name = str.substring(str.indexOf(",")+1, str.length()).trim();
        str = str.split(",")[0].trim();
        p.addOutButton(new Button(name, p, str));
        return true;
      
    }

    private class Button implements IButton{
        private String name;
        private Parser p;
        private String str;

        public Button(String name, Parser p, String str){
        this.name = name;
        this.p = p;
        this.str = str;
        }

        public String getName() {
            return name;
        }

        public void onClick() {
            p.clearOutgoing();
//            String s = null;
//            try {
//                s = p.getValue(str).get("out").toString();
//            } catch (EvalError ex) {}
//            str = s.isEmpty() ? str : s;
            str =p.getCore().getVariables().getVariablesHash().containsKey(str.trim()) ||
                    p.getCore().getListLocations().containsKey(str.trim())/*p.isNum(str)*/? str: p.getString(str);
            System.out.println("onClick() "+str);
            p.parse(str);
        }

    }

}
