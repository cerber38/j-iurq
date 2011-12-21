
package ru.interpreter.universal.ripsoft.quest;

import bsh.Interpreter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Parser {
    private JiURQ c;
    private Outgoing o;
    private LinkedHashMap<String,String> m= new LinkedHashMap<String,String>();
    private LinkedHashMap<String,String> l= new LinkedHashMap<String,String>();


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
    public String parseRnd(String in){
        String out = in;
     try{
        Random r = new Random();
        int i=out.indexOf("rnd");

        while ((i=out.indexOf("rnd"))>=0){
            int ii=i+3;
            String rnd = "";
//              do {
//                 rnd+=String.valueOf(out.charAt(ii));
//                 ii++;
//              //   System.out.println("rnd: "+rnd);
//              }while(isNum(String.valueOf(out.charAt(ii))));
            while (isNum(String.valueOf(out.charAt(ii)))){
                rnd+=String.valueOf(out.charAt(ii));
      //          System.out.println("rnd: "+rnd);
                ii=ii+1;
            }
      //      System.out.println("rnd: "+rnd);
            if (!rnd.trim().isEmpty())
             //   out = out.replace("rnd"+rnd, String.valueOf(r.nextInt(Integer.parseInt(rnd))));
                out = out.substring(0, i)+String.valueOf(r.nextInt(Integer.parseInt(rnd)))+out.substring(i+rnd.length()+3, out.length());
            else
              //  out = out.replace("rnd", String.valueOf(r.nextFloat()));
                out = out.substring(0, i)+String.valueOf(formatDouble(r.nextDouble(),2))+out.substring(i+3, out.length());
      //      System.out.println("RNDout: "+out);
        }
       }catch(Exception ex){ return in;}
        return out;
    }

    public String replaceVariableValue(String in){
        String out = in;
       HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
       for (String v:hm.keySet()){
            out = out.replace("#"+v.replace(" ", "_")+"$", hm.get(v));
            out = out.replace("#%"+v.replace(" ", "_")+"$", hm.get(v));

            out = out.replace("#"+v+"$", hm.get(v));
            out = out.replace("#%"+v+"$", hm.get(v));
       }
        LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
           for (String inv:lhm.keySet()){
            out = out.replace("#inv_"+inv.replace(" ", "_")+"$", String.valueOf(lhm.get(inv)));
            out = out.replace("#%inv_"+inv.replace(" ", "_")+"$", String.valueOf(lhm.get(inv)));

            out = out.replace("#inv_"+inv+"$", String.valueOf(lhm.get(inv)));
            out = out.replace("#%inv_"+inv+"$", String.valueOf(lhm.get(inv)));
           }
 //       System.out.println("replaceVariableValue(String in): "+out);
        return out;
    }

     public String replaceBadOperators(String in){
          String out = "";
          String condition = in.replace("&", " && ");
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

    public String parseUnknownVariables(String in){
     String temp = in.replace("&&", ";");
            temp = temp.replace("&", ";");
            temp = temp.replace("||", ";");
            temp = temp.replace("==", "=");
            temp = temp.replace("!=", "=");
            temp = temp.replace("<=", "=");
            temp = temp.replace(">=", "=");
            temp = temp.replace(">", "=");
            temp = temp.replace("<", "=");
            HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
            LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
           String[] t = temp.split(";");
           for (String ss :t){
               String[] tt = ss.split("=");
              for (String s :tt){
                   String v = s.trim();
                   if (v.indexOf("true")==-1&&v.indexOf("false")==-1&&
                       !isNum(v)&&!lhm.containsKey(v.replaceAll("inv_", ""))&&
                       !hm.containsKey(v)&&
                       v.indexOf("*")==-1&&
                       v.indexOf("/")==-1&&
                       v.indexOf("+")==-1&&
                       v.indexOf("(")==-1&&
                       v.indexOf(")")==-1&&
                       v.indexOf(String.valueOf('"')+"+ ")==-1){
                       if (v.startsWith("inv_")){
                        getCore().getInventory().addInv(0, v.replaceAll("inv_", ""));
  //                      System.out.println("AddNEWInventory: "+v.trim());
                       }else{
                        getCore().getVariables().addVariable(v.trim(), "0");
//                        System.out.println("AddNEWVariable: "+v);
                       }
                  }
               }
           }

           return in;
    }

    public String getString(String in){

        String temp = replaceVariableValue(in.trim());

        temp = replaceBadOperators(temp);
        temp = parseRnd(temp);
        for (String s : m.keySet()){
            temp = temp.replace(s, m.get(s));
        }
        for (String s : l.keySet()){
            temp = temp.replace(s, l.get(s));
        }
        temp = replaceExtraSpaces(temp);
       // parseUnknownVariables(temp);
        String out = "";
        String t = "";
        String[] words = temp.split(" ");
//        for(String w : temp.split(" ")){
        for(int i = 0; i<words.length; i++){
            String w = words[i];
            if (w.isEmpty()) continue;
          //  System.out.println("test: "+w);

            if (m.containsKey(w)||isNum(w)){
                t+=w+" ";
                if (i+1==words.length)
                   out+= getValue(t);
             //  System.out.println("if (h.containsKey(w)||isNum(w)): "+t);
            }else
                if (!t.isEmpty()){
                   out+= getValue(t)+" "+w+" ";
                t = "";
              //  System.out.println("out+= getValue(t);: "+out);
                }else
                out+= w+" ";
         }
        out = out.trim().replace("= =", "==");

     //   System.out.println("getString(String test): "+test);
 //       System.out.println("getString(String out): "+out);

        return out;
    }



    public String getValue(String in){
    //    System.out.println("getValue: "+in);
        try{
            Interpreter bsh = getIValue(in);
            if (bsh!=null){
                String out = bsh.get("out").toString();
              //  System.out.println("bsh.eval: "+out);
                return out;
            } else
                return in;
        } catch (Exception ex) {
            return in;
        }
    }

    public Interpreter getIValue(String in){
        String condition = in;
        Interpreter bsh = new Interpreter();
        String out = "";
        try{
            HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
            LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();

            for (String v:hm.keySet()){
                String s =v.replace(" ", "_");
                condition = condition.replace(v, s);
               try{
                   int i = Integer.parseInt(hm.get(v));
                   bsh.set(s, i);
                } catch (Exception ex) {
                   bsh.set(s, hm.get(v));
                }
            }

           for (String inv:lhm.keySet()){
                  String s =inv.replace(" ", "_");
                  bsh.set(s, lhm.get(inv));
                  condition = condition.replace(inv, s);
             //     System.out.println("load inventory: "+s+" = "+lhm.get(inv));
           }

     //       System.out.println("bsh.eval: "+condition+" ;");
             bsh.eval("try{\n"
                    + "out = "+condition+" ;\n"
                    + "} catch (Exception ex) {\n"
                    + "}");
             }catch(Exception exx){
         //    exx.printStackTrace();
             return null;
             }
        return bsh;
    }

    public void parse (String str){
         for (String[]o: c.getOperators().keySet()){
             if (operTest(o,str)){
                 c.getOperators().get(o).parse(this,str);
                 break;
             }

         }
    }

    public void parse (Location l, int n_str){
        parse (l, n_str, 0, l.location.get(n_str).length());
    }

    public void parse (Location l, int n_str, int n_stance){
        parse (l, n_str, n_stance, l.location.get(n_str).length());
    }

    public void parse (Location l, int n_str, int n_stance, int e_stance){
        String str =l.location.get(n_str);
         for (String[]o: c.getOperators().keySet()){
             if (operTest(o,str)){
                 c.getOperators().get(o).parse(this, l, n_str, n_stance, e_stance);
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

   public boolean isNum(String in){
     try{
         Integer.valueOf(in.trim());
         return true;
         }catch(Exception ex){
         try{
             Double.valueOf(in.trim());
             return true;
             }catch(Exception exx){
             return false;
             }
         }
    }

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

    double formatDouble(double d, int dz){
         double dd=Math.pow(10,dz);
         return Math.round(d*dd)/dd;
    }

//
//    public String getClearString(String in){
//         in=parseRnd(in);
//         String out = in;
//     try{
//
//         String out3 = "";
//         String bad = in;
//
//         // for (String ss: in.trim().split(" ")){
//              for (String s: h.keySet()){
//              //  out = out.replace(ss, ss.replace(s, h.get(s)));
//                  out = out.replace(s, h.get(s));
//              }
//           //   out = out.replace("  ", " ");
//       //     }
//      //    System.out.println("Clear out: "+out);
//          bad = out;
//          out = "";
//        //  for (String ss: out.trim().split(" ")){
//            HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
//            LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
//            for (String v:hm.keySet()){
//               bad = bad.replace(v, v.replace(" ", "_"));
//            }
//            for (String inv:lhm.keySet()){
//               bad = bad.replace(inv, inv.replace(" ", "_"));
//            }
//            String test = bad.trim();
//        //    System.out.println("Clear bad: "+bad);
//            boolean b = true;
//            String last ="";
//            String out2 = "";
//            String[] s = bad.trim().split(" ");
//            for (int i = 0; i<s.length; i++){
//                 String ss = s[i];
//                 if (ss.isEmpty()) continue;
//                 if (!s[i].equals("-")&&h.containsKey(s[i])){
//                 if (!s[i].equals("(")&& out2.isEmpty()&&!last.isEmpty())out=last;
//               //  if (!s[i].equals("(")||!s[i].equals(")"))
//                 out2 += out2.isEmpty() ? s[i-1]+" "+s[i] : " "+s[i];
//        //         System.out.println("out: "+out);
//                 }else
//                if (isNum(ss)||
//                        (!hm.containsKey(ss.trim())&&
//                        hm.containsKey(ss.replace("_", " ").replace("#%", "").replace("#", "").replace("$", "").trim()))||
//                        (!lhm.containsKey(ss.trim())&&
//                        lhm.containsKey(ss.replace("inv_", "").replace("_", " ").replace("#%", "").replace("#", "").replace("$", "").trim()))){
//                    out2 += " "+ss;
//                }else
//                     if(!out2.isEmpty()){
//            //              System.out.println("Clear out2: "+out2);
//
//                          out3 = "";
//                      try {
//                          out3 = getValue(out2).get("out").toString();
//                      } catch (Exception ex) {}
//                          out3 = out3.isEmpty() ? out2+" "+ss : out3+" "+ss;
//                          out2 = "";
//                          last = out;
//                          out+= " "+out3;
//                    }else{
//                    last = out;
//                    out+= " "+ss;
//                    }
//            }
//            if (test.equals(out.trim()))return in;
//       } catch (Exception ex) {return in;};
//           // System.out.println("Clear out: "+out);
//        return out.trim();
//    }









//     public Interpreter getValue(String in){
//         return getValue(new Interpreter(),in);
//     }
//
//     public Interpreter getValue(Interpreter bsh, String in){
//       //String out = getVariableValue(in);
//          String condition = getVariableValue(in).replace("&", "&&");
//
//          condition = condition.replace("and", "&&");
//          condition = condition.replace("or", "||");
//          condition = condition.replace("not", "");
//          condition = condition.replace("=", "==");
//          condition = condition.replace("<>", "!=");
//          String temp = getVar(condition);
//
//       try {
//
//            HashMap<String,String> hm = getCore().getVariables().getVariablesHash();
//            LinkedHashMap<String,Integer> lhm = getCore().getInventory().getInventoryHash();
//
//            for (String v:hm.keySet()){
//               try{
//                   int i = Integer.parseInt(hm.get(v));
//                   bsh.set(v, i);
//                } catch (Exception ex) {
//                   bsh.set(v, hm.get(v));
//                }
//            }
//
//           for (String inv:lhm.keySet()){
//                  String s ="inv_"+inv.replace(" ", "_");
//                  bsh.set(s, lhm.get(inv));
//                  condition = condition.replace("inv_"+inv, s);
//             //     System.out.println("load inventory: "+s+" = "+lhm.get(inv));
//
//           }
//           String[] t = temp.split(";");
//           for (String ss :t){
//               String[] tt = ss.split("=");
//              for (String s :tt){
//                   String v = s.trim();
//                   if (!lhm.containsKey(v.replace("inv_", ""))&&!hm.containsKey(v)&&v.indexOf(String.valueOf('"')+"+ ")==-1)
//                      try{
//                        Integer.valueOf(v);
//                      }catch(Exception ex){
////                        bsh.set(v.split(" ")[0].trim(), 0);
////                        getCore().getVariables().addVariable(v.split(" ")[0].trim(), "0");
//                        bsh.set(v, 0);
//                        getCore().getVariables().addVariable(v, "0");
//                        System.out.println("Add: "+v);
//                      }
//               }
//           }
//
//            System.out.println("bsh.eval: out = "+condition+" ;");
//             bsh.eval("try{\n"
//                    + "out = "+condition+" ;\n"
//                    + "} catch (Exception ex) {\n"
//                    + "}");
//
//        } catch (Exception ex1) {
//            return null;
//        }
//        return bsh;
//    }
//
//    private String getVar(String condition){
//          condition = condition.replace("&&", ";");
//          condition = condition.replace("||", ";");
//          condition = condition.replace("==", "=");
//          condition = condition.replace("!=", "=");
//          condition = condition.replace("<=", "=");
//          condition = condition.replace(">=", "=");
//          condition = condition.replace(">", "=");
//          condition = condition.replace("<", "=");
//       return condition;
//    }



   
 

}
