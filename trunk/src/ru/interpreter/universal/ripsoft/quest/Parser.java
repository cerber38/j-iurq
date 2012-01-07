
package ru.interpreter.universal.ripsoft.quest;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Parser {
    private JiURQ c;
    private Outgoing o;
    public LinkedHashMap<String,String> m= new LinkedHashMap<String,String>();
    public LinkedHashMap<String,String> l= new LinkedHashMap<String,String>();


    public Parser(JiURQ c){
        this.c=c;
        o=new Outgoing(this);
        m.put("*", " * ");
        m.put("/", " / ");
        m.put("+", " + ");
        m.put("-", " - ");
        m.put("(", " ( ");
        m.put(")", " ) ");
        l.put("<=", " <= ");
        l.put("<", " < ");
        l.put(">=", " >= ");
        l.put(">", " > ");
        l.put("==", " == ");
        l.put("=", " = ");

        //*, /, +, -, <, <=, >, >=, = (==), <>, and, or, скобки ( )
    }
//    public String parseRnd(String in){
//        String out = in;
//     try{
//        Random r = new Random();
//        int i=out.indexOf("rnd");
//
//        while ((i=out.indexOf("rnd"))>=0){
//            int ii=i+3;
//            String rnd = "";
//            while (isNum(String.valueOf(out.charAt(ii)))){
//                rnd+=String.valueOf(out.charAt(ii));
//                ii=ii+1;
//            }
//            if (!rnd.trim().isEmpty())
//                out = out.substring(0, i)+String.valueOf(r.nextInt(Integer.parseInt(rnd)))+out.substring(i+rnd.length()+3, out.length());
//            else
//                out = out.substring(0, i)+String.valueOf(formatDouble(r.nextDouble(),2))+out.substring(i+3, out.length());
//        }
//       }catch(Exception ex){ return in;}
//        return out;
//    }

//    public String replaceVariableValue(String in){
//        String out = in;
//       HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
//       for (String v:hm.keySet()){
//            out = out.replace("#"+v.replace(" ", "_")+"$", hm.get(v));
//            out = out.replace("#%"+v.replace(" ", "_")+"$", hm.get(v));
//
//            out = out.replace("#"+v+"$", hm.get(v));
//            out = out.replace("#%"+v+"$", hm.get(v));
//       }
//        LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
//           for (String inv:lhm.keySet()){
//            out = out.replace("#inv_"+inv.replace(" ", "_")+"$", String.valueOf(lhm.get(inv)));
//            out = out.replace("#%inv_"+inv.replace(" ", "_")+"$", String.valueOf(lhm.get(inv)));
//
//            out = out.replace("#inv_"+inv+"$", String.valueOf(lhm.get(inv)));
//            out = out.replace("#%inv_"+inv+"$", String.valueOf(lhm.get(inv)));
//           }
//           int n = out.indexOf("#");
//           int k = out.indexOf("$");
//
//        while(k>=0&&n>=0){
//         //             System.out.println("n: "+n+" k: "+k+"  "+out.substring(n, k+1));
//         //             System.out.println(out);
//           String op = out.substring(n, k+1);
//           String operator = op.replace("#", "").replace("%", "").replace("$", "");
//           String inv_operator = "";
//           try{
//             inv_operator = operator.substring(4,operator.length());
//           }catch(Exception ex){}
////        //     System.out.println("inv_operator "+inv_operator);
////           if (lhm.containsKey(inv_operator)){
////             out = out.replace(op,String.valueOf(lhm.get(inv_operator)));
////           }else
////           if (hm.containsKey(operator)){
////             out = out.replace(op, hm.get(operator));
////           }else{
//            out = out.replace(op, " 0 ");
//             if (op.toLowerCase().startsWith("#inv_")||op.toLowerCase().startsWith("#%inv_")){
//               getCore().getInventory().addInv(0,inv_operator);
//          // getCore().getVariables().addVariable(op.replace("#", "").replace("%", "").replace("$", ""), "0");
//             }else
//             getCore().getVariables().addVariable(op.replace("#", "").replace("%", "").replace("$", ""), "0");
//        //   }
//           n = out.indexOf("#");
//           k = out.indexOf("$");
//           if (n==-1)break;
//       //    System.out.println("replaceVariableValue(String in): "+out);
//        }
//
//        return out;
//    }

     public String replaceBadOperators(String in){
          String out = "";
          String condition = in.replaceAll("&", " && ");
          condition = condition.replaceAll("=", " == ");
          condition = condition.replace("<>", " != ");
      //  condition = condition.replace("not", "");
       //    for (String s : condition.trim().split(" ")){
        //       if(!s.isEmpty()&&!s.trim().equals(" "))
                   out+=condition.replace(" and ", " && ").replace(" or ", " || ")+" ";
        //  }
 //         System.out.println("replaceBadOperators(String in): "+out);
          return out;
    }

     public String replaceExtraSpaces(String in){
          String out = "";
           for (String s : in.trim().split(" ")){
               if(!s.isEmpty()&&!s.trim().equals(" "))
                   out+=s.replace("\b", "").replace("\t", "").replace("\f", "").replace("\r", "")+" ";
          }
           if(out.isEmpty()) out = in;
          //out = out.replace("\b", "").replace("\t", "").replace("\f", "").replace("\r", "");
 //         System.out.println("replaceExtraSpaces(String in): "+out);
          return out.trim().replace("! =", "!=");

    }

//    public String parseUnknownVariables(String in){
//     String temp = in.replace("&&", ";");
//            temp = temp.replace("&", ";");
//            temp = temp.replace("||", ";");
//            temp = temp.replace("==", "=");
//            temp = temp.replace("!=", "=");
//            temp = temp.replace("<=", "=");
//            temp = temp.replace(">=", "=");
//            temp = temp.replace(">", "=");
//            temp = temp.replace("<", "=");
//            HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
//            LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
//           String[] t = temp.split(";");
//           for (String ss :t){
//               String[] tt = ss.split("=");
//              for (String s :tt){
//                   String v = s.trim();
//                   if (v.indexOf("true")==-1&&v.indexOf("false")==-1&&
//                       !isNum(v)&&!lhm.containsKey(v.replaceAll("inv_", ""))&&
//                       !hm.containsKey(v)&&
//                       v.indexOf("*")==-1&&
//                       v.indexOf("/")==-1&&
//                       v.indexOf("+")==-1&&
//                       v.indexOf("(")==-1&&
//                       v.indexOf(")")==-1&&
//                       v.indexOf(String.valueOf('"')+"+ ")==-1){
//                       if (v.startsWith("inv_")){
//                        getCore().getInventory().addInv(0, v.replaceAll("inv_", ""));
//  //                      System.out.println("AddNEWInventory: "+v.trim());
//                       }else{
//                        getCore().getVariables().addVariable(v.trim(), "0");
////                        System.out.println("AddNEWVariable: "+v);
//                       }
//                  }
//               }
//           }
//
//           return in;
//    }
    public void addUnknownVar(String[] s){
            String out = "";
            String t=s[1].trim();
             for (String operation : getCore().getMaths().MATH_OPERATIONS.keySet()) {
                 if(t.indexOf(operation)>=0){
                     out = String.valueOf(getCore().getMaths().eval(t));
                     break;
                 }
             }
             if (out.isEmpty()){
                 HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
                 LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
                 try{
                 String ss = s[0].substring(4, s[0].length());
                 if (lhm.containsKey(ss)){
                    getCore().getInventory().addInv(lhm.get(ss), ss);
                //    System.out.println("AddNEWInventory: "+s[0].trim());
                    return;
                 }
                 }catch(Exception ex){}
                 if (hm.containsKey(t)){
                  getCore().getVariables().addVariable(s[0].trim(), hm.get(t));
               //   System.out.println("AddNEWVariable: "+s[0]);
                  return;
                 }

             }

                out = out.isEmpty() ? s[1].trim() : out;

                   if (s[0].toLowerCase().startsWith("inv_")){
                        getCore().getInventory().addInv(Integer.valueOf(out), s[0].substring(4, s[0].length()));
                   //     System.out.println("AddNEWInventory: "+s[0].trim());
                       }else{
                        getCore().getVariables().addVariable(s[0].trim(), out);
                   //     System.out.println("AddNEWVariable: "+s[0]);
                       }
         }

//    public String replaceMatOperators(String in){
//          String out = in;
//        for (String s : m.keySet()){
//            out = out.replace(s, m.get(s));
//        }
//        for (String s : l.keySet()){
//            out = out.replace(s, l.get(s));
//        }
//          return out;
//    }

    public boolean isMath (String symbol){
//        if(getCore().getMaths().isNum(symbol))return true;
        if(symbol.equals("0"))return true;
        if(symbol.equals("1"))return true;
        if(symbol.equals("2"))return true;
        if(symbol.equals("3"))return true;
        if(symbol.equals("4"))return true;
        if(symbol.equals("5"))return true;
        if(symbol.equals("6"))return true;
        if(symbol.equals("7"))return true;
        if(symbol.equals("8"))return true;
        if(symbol.equals("9"))return true;
        if(symbol.equals(" "))return true;
        if(symbol.equals("."))return true;
        if(symbol.equals("+"))return true;
        if(symbol.equals("-"))return true;
        if(symbol.equals("/"))return true;
        if(symbol.equals("*"))return true;
        if(symbol.equals("^"))return true;
        if(symbol.equals("("))return true;
        if(symbol.equals(")"))return true;
        
        return false;
    }

     public String getString(String in,boolean сalculate){
        return сalculate ? getString(getCore().getMaths().replaceVariableValue(getCore().getMaths().parseRnd(in))):
               getCore().getMaths().replaceVariableValue(getString(getCore().getMaths().parseRnd(in)));
     }

    public String getString(String in){
        String temp =getCore().getMaths().parseRnd(in) /*getCore().getMaths().replaceVariableValue(in)*/;

     //   temp = replaceBadOperators(temp);
     //    temp = getCore().getMaths().parseRnd(temp);
      //   String out = temp;
             int n = -1;
             int k = -1;
             int fromIndex=0;
             boolean findNext = true;
        while (findNext) {
             for (String SYMBOL : getCore().getMaths().MATH_SYMBOLS) {
                 int t=temp.indexOf(SYMBOL,fromIndex);
                 k = t;
                 fromIndex=k;
                 if(t>=0){
                   for(int i=t;i>=0; i--){
                           String s = temp.substring(i, i+1);
                       if (isMath(s)){
                           n=i;
                       }else break;
                   }
                   for(int i=t;i<temp.length(); i++){
                           String s = temp.substring(i, i+1);
                       if (isMath(s)){
                           k=i+1;
                       }else break;
                   }
                   if (n>=0){
                    String r = temp.substring(n, k); 
                    System.out.println("r "+r);
                       try{
                       double res = getCore().getMaths().eval(r);
                       long mr =Math.round(res);
                     //  if (res-Math.round(res)==0)
                       temp = temp.substring(0, n)+((res-mr==0)?mr:res)+temp.substring(k);
                       fromIndex=k-r.length();
                       }catch(Exception ex){}
                   }
                     break;
                 }
                 if (fromIndex==-1||fromIndex==temp.length())findNext=false;
              }
         }


//        for (String s : m.keySet()){
//            temp = temp.replace(s, m.get(s));
//        }
//        for (String s : l.keySet()){
//            temp = temp.replace(s, l.get(s));
//        }
//      // temp = replaceExtraSpaces(temp);
//       // parseUnknownVariables(temp);
//        String out = "";
//        String t = "";
//        String[] words = temp.split(" ");
////        for(String w : temp.split(" ")){
//        for(int i = 0; i<words.length; i++){
//            String w = words[i];
//            if (w.isEmpty()) continue;
//          //  System.out.println("test: "+w);
//
//            if (m.containsKey(w)||getCore().getMaths().isNum(w)){
//                t+=w+" ";
//                if (i+1==words.length){
//                   try{
//                   double res = getCore().getMaths().eval(t);
//                   out+= res;
//                   }catch(Exception ex){
//                   out+= t;
//                   }
//                }
//          //     System.out.println("getValue(t): "+t+" out= "+out);
//            }else
//                if (!t.isEmpty()){
//                   try{
//                   double res = getCore().getMaths().eval(t);
//                   out+= res+" "+w+" ";
//                   }catch(Exception ex){
//                   out+= t+" "+w+" ";
//                   }
//               //    out+= getCore().getMaths().eval(t)+" "+w+" ";
//          //  System.out.println("getValue(t): "+t+" out= "+out);
//                t = "";
//              //  System.out.println("out+= getValue(t);: "+out);
//                }else
//                out+= w+" ";
//         }
//        out = out.trim().replace("= =", "==");

    //    System.out.println("getString(String out): "+out);
        return temp;
    }



//    public String getValue(String in){
//    //    System.out.println("getValue: "+in);
//        if (in.indexOf("/ 0")>=0||in.indexOf("/0")>=0)return "0";
//        try{
//            Interpreter bsh = getIValue(in);
//            if (bsh!=null){
//                String out = bsh.get("out").toString();
//              //  System.out.println("bsh.eval: "+out);
//                return out;
//            } else
//                return in;
//        } catch (Exception ex) {
//            return in;
//        }
//    }

//    public Interpreter getIValue(String in){
//        System.out.println("getIValue: "+in);
//        String condition = in;
//        Interpreter bsh = new Interpreter();
//        String out = "";
//        try{
//            HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
//            LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
//
//            for (String v:hm.keySet()){
//                String s =v.replace(" ", "_");
//                condition = condition.replace(v, s);
//               try{
//                   int i = Integer.parseInt(hm.get(v));
//                   bsh.set(s, i);
//                } catch (Exception ex) {
//                   bsh.set(s, hm.get(v));
//                }
//            }
//
//           for (String inv:lhm.keySet()){
//                  String s =inv.replace(" ", "_");
//                  bsh.set(s, lhm.get(inv));
//                  condition = condition.replace(inv, s);
//             //     System.out.println("load inventory: "+s+" = "+lhm.get(inv));
//           }
//
//     //       System.out.println("bsh.eval: "+condition+" ;");
//             bsh.eval("try{\n"
//                    + "out = "+condition+" ;\n"
//                    + "} catch (Exception ex) {\n"
//                    + "}");
//             }catch(Exception exx){
//         //    exx.printStackTrace();
//             return null;
//             }
//        return bsh;
//    }

    public String getStringQest(int n_str){
        return c.getListQest().get(n_str);
    }

    public String getStringQest(int n_str, int n_stance){
        String str = getStringQest(n_str);
        return str.substring(n_stance, str.length());
    }

    public String getStringQest(int n_str, int n_stance, int e_stance){
        String str = getStringQest(n_str);
        return str.substring(n_stance, e_stance);
    }

    public void parse (String str){
         for (String[]o: c.getOperators().keySet()){
             if (operTest(o,str)){
                 c.getOperators().get(o).parse(this,str);
                 break;
             }

         }
    }

    /**
     * парсер
     * @param n_str номер строки
     */
    public void parse (int n_str){
        parse (n_str, 0, 0);
    }

    /**
     * парсер
     * @param n_str номер строки
     * @param n_stance индекс начала текста в строке
     */
    public void parse (int n_str, int n_stance){
        parse (n_str, n_stance, 0);
    }

    /**
     * парсер
     * @param n_str номер строки
     * @param n_stance индекс начала текста в строке
     * @param e_stance индекс конца текста в строке
     */
    public void parse (int n_str, int n_stance, int e_stance){
        String str = getStringQest(n_str);
        if (e_stance==0)e_stance = str.length();
         for (String[]o: c.getOperators().keySet()){
             if (operTest(o,str)){
                 c.getOperators().get(o).parse(this, n_str, n_stance, e_stance);
                 break;
             }

         }
    }

    public boolean operTest(String[]o,String str) {
      //   str = replaceExtraSpaces(str);
         for(String s : o){
           if(s.equals("UNKNOWN")) return true;
//              for (String ss: str.trim().split(" ")){
//                if(ss.equalsIgnoreCase(s)) return true;
//             }
           if(str.trim().toLowerCase().split(" ")[0].equalsIgnoreCase(s))return true;
           if(str.trim().toLowerCase().startsWith("inv_")&&str.trim().toLowerCase().startsWith(s))return true;
            }
        return false;
    }

//   public boolean isNum(String in){
//     try{
//         Integer.valueOf(in.trim());
//        //  System.out.println("isNum: "+in+" true");
//         return true;
//         }catch(Exception ex){
//         try{
//             Double.valueOf(in.trim());
//         //    System.out.println("isNum: "+in+" true");
//             return true;
//             }catch(Exception exx){
//         //   System.out.println("isNum: "+in+" false");
//             return false;
//             }
//         }
//    }

    public JiURQ getCore (){
        return c;
    }

    public Outgoing getOutgoing (){
        return o;
    }

    public void clearOutgoing (){
        o.clearContent();
    }

    public void addOutString (String s){
        o.addString(s);
    }

    public void addOutButton (IButton b){
        o.addButton(b);
    }

    public void clsOutButton (){
        o.clearButtons();
    }

//    double formatDouble(double d, int dz){
//         double dd=Math.pow(10,dz);
//         return Math.round(d*dd)/dd;
//    }


}
