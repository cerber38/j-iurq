
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Location;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Inv_ implements IOperator {
    private String[] ops = {"inv_"};

    public Inv_ (){
    }

    public String[] getNames() {
        return ops;
    }

    public void parse(Parser p, Location l, int n_str, int n_stance, int e_stance) {
        String str= l.location.get(n_str).trim();
        int size = str.length();
        str=str.substring(n_stance, e_stance);
        if (parse(p, str)){
            if (e_stance+1<size)
                 p.parse(l, n_str, e_stance+1);
            else
                if (n_str+1<l.location.size())
                 p.parse(l, n_str+1);
        }
    }


    public boolean parse(Parser p, String str) {
      String o = str.split("_")[1];
        if(o.indexOf("=")!=-1){
            int count =1;
                 try{
                     count=Integer.parseInt(p.getString(o.split("=")[1].trim()));
                    }catch (Exception ex){}
            p.getCore().getInventory().addInv(count, o.split("=")[0].trim().replace("inv_", ""));
        }
      return true;
    }



}
