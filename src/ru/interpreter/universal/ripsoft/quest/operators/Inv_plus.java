
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Location;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Inv_plus implements IOperator {
    private String[] ops = {"inv+"};

    public Inv_plus (){
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
      String o = str.substring(str.indexOf("inv+")+4,str.length()).trim();
       if(o.indexOf(",")==-1)
          p.getCore().getInventory().addInv(p.getString(o));
       else{
       //   int count =1;
         try{
              int count = Integer.parseInt(p.getString(o.split(",")[0].trim()));
              p.getCore().getInventory().addInv(count, o.split(",")[1].trim());
         }catch (Exception ex){
           System.out.println("!!!Ошибка добавления в инвентарь: "+str);
         }

         }
      return true;
    }



}
