
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Use_ implements IOperator {
    private String[] ops = {"use_"};

    public Use_ (){
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
      String o = str.substring(0,str.lastIndexOf("_")).trim();
      String v = str.substring(str.lastIndexOf("=")+1).trim();
      if (v.equals("1"))p.getCore().getActivity().setHide(o);
      else p.getCore().getActivity().unHide(o);
      p.getCore().setGo(true);
      return true;
    }



}
