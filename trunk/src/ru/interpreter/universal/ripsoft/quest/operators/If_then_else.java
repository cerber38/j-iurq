
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class If_then_else implements IOperator {
    private String[] ops = {"if","then","else"};

//    private List <String> con = new ArrayList <String>();


    public If_then_else (){

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
         boolean b =false;
         boolean b2 =false;
        String s = str.toLowerCase();
        int _if = s.indexOf("if")+2;
        int _then = s.indexOf("then");
        int _else = s.lastIndexOf("else");
        String condition = str.substring(_if,_then).trim();
        boolean if_then = getBoolean(condition, p);
         if (_else==-1){
            if (if_then){
                String ss = str.substring(_then+4,str.length());
                if (!ss.trim().toLowerCase().startsWith("if")){
                   for(String o : ss.split("&")){
                  if (o.toLowerCase().trim().startsWith("goto")||o.trim().toLowerCase().startsWith("proc")){
                    b2 = false;  
                  }else
                    b2 = true;
  //                 System.out.println("b21 "+b2);
                   action (p,o);
                  }
                }else{
                   action (p,ss);
                }
            }else b2 = true;
                return b2;
        }
        if (!if_then) { b2 = true;
               String ss = str.substring(_else+4,str.length());
                if (!ss.trim().toLowerCase().startsWith("if")){
                   for(String o : ss.split("&")){
                  if (o.toLowerCase().trim().startsWith("goto")||o.trim().toLowerCase().startsWith("proc")){
                    b2 = false;
                  }else
                    b2 = true;
    //              System.out.println("b2 "+b2);
                   action (p,o);
                  }
                }else{
                   action (p,ss);
                   b2 = true;
            }
            return b2;
        }
         else{ b2 = true;
               String ss = str.substring(_then+4,_else);
                if (!ss.trim().toLowerCase().startsWith("if")){
                   for(String o : ss.split("&")){
                  if (o.toLowerCase().trim().startsWith("goto")||o.trim().toLowerCase().startsWith("proc")){
                    b2 = false;
                  }else
                    b2 = true;
     //             System.out.println("b2 "+b2);
                    action (p,o);
                    }
                }else
                   action (p,ss);
             return b2;
        }
      //  return true;
   }

    public boolean getBoolean(String condition, Parser p){
        String temp = condition.replace(" and ", ";");
        temp = temp.replace(" or ", ";");
        temp = temp.replace(" & ", ";");
        for (String s : temp.split(";")){
           condition = condition.replace(s, getBoolean2( s,  p)? "true" : "false");
        }
        return getBoolean2(condition,  p);
    }

    public boolean getBoolean2(String condition, Parser p){
        boolean b =false;
//        String temp = condition;
        boolean not = condition.toLowerCase().trim().startsWith("not");
        try{
          condition = p.parseUnknownVariables(p.getString(condition.trim()));
          condition = condition.replaceAll("not", "").trim();
           if (condition.indexOf("true")==-1&&condition.indexOf("false")==-1&&
                condition.indexOf("*")==-1&&condition.indexOf("/")==-1&&
                condition.indexOf("+")==-1&&condition.indexOf("-")==-1&&
                condition.indexOf("<")==-1&&condition.indexOf("<=")==-1&&
                condition.indexOf(">=")==-1&&condition.indexOf(">")==-1&&
                condition.indexOf("=")==-1){
               if(!p.getCore().getVariables().getVariablesHash().containsKey(condition)&&
                   p.getCore().getInventory().getInventoryHash().containsKey(condition.replace("inv_", ""))){
                   if (condition.startsWith("inv_"))
                       p.getCore().getInventory().addInv(0, condition.replaceAll("inv_", ""));
                   else
                       p.getCore().getVariables().addVariable(condition, "0");
               }


               condition = condition+">0";
            }
//          HashMap<String,String> hm = p.getCore().getVariables().getVariablesHash();
//          LinkedHashMap<String,Integer> lhm = p.getCore().getInventory().getInventoryHash();
//
//            for (String v:hm.keySet()){
//                String s =v.replace(" ", "_");
//                condition = condition.replace(v, s);
//            }
//
//           for (String inv:lhm.keySet()){
//                  String s ="inv_"+inv.replace(" ", "_");
//                  condition = condition.replace("inv_"+inv, s);
//           }
                
            b = (Boolean)p.getIValue(condition).get("out");
  //          System.out.println(b);
        } catch (Exception ex) {
           //     ex.printStackTrace();
//        	System.err.println("Ошибка обработки условия: " + ex.getMessage());
        }
        if(not)b = !b;
 //       System.out.println("getBoolean2 "+condition+" "+b);
        return b ;
    }

    public void action (Parser p,String str) {
        String s = str.trim();
 //       System.out.println("action: "+s);
        p.parse(s);
    }



}


