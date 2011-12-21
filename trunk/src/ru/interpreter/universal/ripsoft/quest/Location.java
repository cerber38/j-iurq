
package ru.interpreter.universal.ripsoft.quest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Location {
    public List<String> location;
    public String name = "";
    public int n_str = 0;


    public Location(String name){
        this.location = new ArrayList<String>();
        this.name = name;
    }
    
    public Location(List<String> location){
        this.location=location;
    }

    public Location addStringInList (String str){
        location.add(str);
        return this;
    }

//    public void parse (Core c){
//        parse (c, 0);
//    }
//
//    public void parse (Core c, int n){
//      // for (int i = n_str; i<location.size(); i++){
//         n_str = n;
//         String str =location.get(n_str);
//         for (String[]o: c.getOperators().keySet()){
//             if (operTest(o,str)){
//                 c.getOperators().get(o).parse(c, this, n_str);
//                 break;
//             }
//
//         }
//       // }
//    }
//
//
//
//    public boolean operTest(String[]o,String str) {
//         for(String s : o){
//           if(s.equals("UNKNOWN")) return true;
//              for (String ss: str.trim().split(" ")){
//                if(ss.equalsIgnoreCase(s)) return true;
//             }
//            }
//
//        return false;
//    }


}
