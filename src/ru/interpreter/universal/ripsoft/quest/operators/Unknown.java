
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Unknown implements IOperator {

    public Unknown (){
    }

    public String[] getNames() {
        return new String[]{"UNKNOWN"};
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
        String[] s =str.split("=");

        if (p.getCore().getListLocations().containsKey(str.trim().toLowerCase())){

           // Location l = p.getCore().getLocation().get(str.trim().toLowerCase());
            p.getCore().addCountLocation(str.trim().toLowerCase());
            p.getCore().setLocation(str.trim().toLowerCase());
            p.parse(p.getCore().getListLocations().get(str.trim().toLowerCase()));

            return false;
        }else

        if (s.length==2&&s[0].trim().indexOf(" ")==-1){
            p.addUnknownVar(s);
//
//            String out = "";
//            String t=s[1].trim();
//             for (String operation : p.getCore().getMaths().MATH_OPERATIONS.keySet()) {
//                 if(t.indexOf(operation)>=0){
//                     out = String.valueOf(p.getCore().getMaths().eval(t));
//                     break;
//                 }
//             }
//
//                out = out.isEmpty() ? s[1].trim() : out;
//                p.getCore().getVariables().addVariable(s[0].trim(),out);
//         //       System.out.println("Add UNKNOWN variable: "+s[0]+" = "+out);
        }else
            System.out.println("!UNKNOWN! operator : "+str);
        p.getCore().setGo(true);
        return true;
    }

}
