
package ru.interpreter.universal.ripsoft.quest.evaluate;

import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import ru.interpreter.universal.ripsoft.quest.JiURQ;




/**
 *
 * @author ~jo-MA-jo~
 */
public class Maths {

    public static Map<String, Integer> MATH_OPERATIONS;
    public static List<String> MATH_SYMBOLS;
    private static List<String> LOGIC_OPERATORS;
//    private LinkedHashMap<String,String> m= new LinkedHashMap<String,String>();
//    private static List<String> BOOLEAN_OPERATORS;
    private JiURQ jiURQ;



    public Maths(JiURQ jiURQ){
        this.jiURQ = jiURQ;
        MATH_OPERATIONS = new HashMap<String, Integer>();
        MATH_OPERATIONS.put("^", 1);
        MATH_OPERATIONS.put("*", 2);
        MATH_OPERATIONS.put("/", 2);
        MATH_OPERATIONS.put("+", 3);
        MATH_OPERATIONS.put("-", 3);


        MATH_SYMBOLS = new ArrayList<String>();
       // MATH_SYMBOLS.addAll(MATH_OPERATIONS.keySet());
        MATH_SYMBOLS.add("^");
        MATH_SYMBOLS.add("*");
        MATH_SYMBOLS.add("/");
        MATH_SYMBOLS.add("+");
        MATH_SYMBOLS.add("-");
        MATH_SYMBOLS.add("(");
        MATH_SYMBOLS.add(")");
//        MATH_SYMBOLS.add("0");
//        MATH_SYMBOLS.add("1");
//        MATH_SYMBOLS.add("2");
//        MATH_SYMBOLS.add("3");
//        MATH_SYMBOLS.add("4");
//        MATH_SYMBOLS.add("5");
//        MATH_SYMBOLS.add("6");
//        MATH_SYMBOLS.add("7");
//        MATH_SYMBOLS.add("8");
//        MATH_SYMBOLS.add("9");

        LOGIC_OPERATORS = new ArrayList<String>();
        LOGIC_OPERATORS.add("<=");
        LOGIC_OPERATORS.add("<");
        LOGIC_OPERATORS.add(">=");
        LOGIC_OPERATORS.add(">");
        LOGIC_OPERATORS.add("==");
        LOGIC_OPERATORS.add("!=");
        LOGIC_OPERATORS.add("=");
//        LOGIC_OPERATORS.add("not");
//        BOOLEAN_OPERATORS = new ArrayList<String>();
//        BOOLEAN_OPERATORS.add("and");
//        BOOLEAN_OPERATORS.add("&");
//        BOOLEAN_OPERATORS.add("or");


    }
    
    public double eval(String expression){
         return calculate(expression, 2);
    }    
    
    public double eval(String expression, int dz){
         return calculate(expression, dz);
    }

    public boolean evalBoolean(String expression){
        StringTokenizer tokenizer = new StringTokenizer(expression.trim(), " ");
        int maxtoken = tokenizer.countTokens();
        String bool ="";
        String tmp ="";
        int i=0;
          while (tokenizer.hasMoreTokens()) {
            i++;
            String token = tokenizer.nextToken();
            if (token.equalsIgnoreCase("and")||token.equals("&&")||token.equals("&")){
               boolean b = getBoolean(bool.trim());
               bool ="";
               tmp+= b==true ? " true and":" false and";
            }else
            if (token.equalsIgnoreCase("or")||token.equalsIgnoreCase("||")){
               boolean b = getBoolean(bool.trim());
               bool ="";
               tmp+= b==true ? " true or":" false or";
            }else{
               bool +=" "+token;
            }
            if (i==maxtoken){
               boolean b = getBoolean(bool.trim());
               tmp+= b==true ? " true":" false";
            }
         }
        System.out.println("temp "+tmp.trim());
        tmp = tmp.trim().replace("and true or", "and (true or");
        tmp = tmp.replace("and false or", "and (false or");
        tmp = tmp.replace("or true and", "or true) and");
        tmp = tmp.replace("or false and", "or false) and");
        if(tmp.endsWith("or false")||tmp.endsWith("or true"))tmp +=")";
        if(tmp.startsWith("false or")||tmp.startsWith("true or"))tmp ="("+tmp;
        int left = tmp.indexOf("(");
        int right = tmp.indexOf(")");
         while (left>=0&&right>0) {
            int tr = 0;
            int fe = 0;
             String s = tmp.substring(left+1,right);
             for(String to:s.split(" ")){
                 if (to.equals("true"))tr++;
                 else
                 if (to.equals("false"))fe++;
             }
               tmp = tmp.replace("("+s+")", tr>0 ? "true":"false" );
               left = tmp.indexOf("(");
               right = tmp.indexOf(")");
         }
           System.out.println("temp "+tmp.trim());

        tokenizer = new StringTokenizer(tmp.trim(), " ");
            int tr = 0;
          /*  int fe = 0;*/
          while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if(!token.equals("and")){
                 if (token.equals("true"))tr++;
                 else
                 if (token.equals("false"))return false /*fe++*/;
            }
        }
         return tr>0/*&&fe==0*/;
    }

    public double formatDouble(double d, int dz){
         double dd=Math.pow(10,dz);
         return Math.round(d*dd)/dd;
    }


    private boolean getBoolean(String expression){
        System.out.println("getBoolean "+expression);
        expression = expression.trim();
        boolean not = expression.startsWith("not");
        if (not)expression=expression.substring(3, expression.length());
       for (String operator : LOGIC_OPERATORS) {
                int i = expression.indexOf(operator);
                if (i >= 0){
                Double calLeft = calculate(adaptToRPN (expression.substring(0,i).trim()), 2);
                Double calRight = calculate(adaptToRPN (expression.substring(i+operator.length(),expression.length()).trim()), 2);
                    if(operator.equals("<=")){
                        return not==true ? !(calLeft <= calRight) : calLeft <= calRight;
                    }
                    if(operator.equals("<")){
                        return not==true ? !(calLeft < calRight) : calLeft < calRight;
                    }
                    if(operator.equals(">=")){
                        return not==true ? !(calLeft >= calRight) : calLeft >= calRight;
                    }
                    if(operator.equals(">")){
                        return not==true ? !(calLeft > calRight) : calLeft > calRight;
                    }
                    if(operator.equals("==")||operator.equals("=")){
                        return not==true ? !(calLeft - calRight==0) : calLeft - calRight==0;
                    }
                    if(operator.equals("!=")||operator.equals("<>")){
                        return not==true ? !(calLeft != calRight) : calLeft != calRight;
                    }
                }
        }
         return false;
    }



        public String replaceVariableValue(String in){
                String out = in;
               HashMap<String,String> hm = jiURQ.getVariables().getVariablesHash();
//               for (String v:hm.keySet()){
//                    out = out.replace("#"+v.replace(" ", "_")+"$", hm.get(v));
//                    out = out.replace("#%"+v.replace(" ", "_")+"$", hm.get(v));
//
//                    out = out.replace("#"+v+"$", hm.get(v));
//                    out = out.replace("#%"+v+"$", hm.get(v));
//               }
                LinkedHashMap<String,Integer> lhm = jiURQ.getInventory().getInventoryHash();
//                   for (String inv:lhm.keySet()){
//                    out = out.replace("#inv_"+inv.replace(" ", "_")+"$", String.valueOf(lhm.get(inv)));
//                    out = out.replace("#%inv_"+inv.replace(" ", "_")+"$", String.valueOf(lhm.get(inv)));
//
//                    out = out.replace("#inv_"+inv+"$", String.valueOf(lhm.get(inv)));
//                    out = out.replace("#%inv_"+inv+"$", String.valueOf(lhm.get(inv)));
//                   }
//                   int n = out.indexOf("#");
                   int n = -1;
                   int k = out.indexOf("$");
                   for(int i=k;i>=0; i--){
//                       System.out.println("substring "+out.substring(i, i+1));
                       if (out.substring(i, i+1).equals("#")){
                           n=i;
                           break;
                       }
                           
                   }

//                System.out.println("n "+n);
//                System.out.println("k "+k);
                while(k>=0&&n>=0){
                   String op = out.substring(n, k+1);
                   String operator = op.replace("#", "").replace("%", "").replace("$", "");
                   String inv_operator = "";
                   try{
                     inv_operator = operator.substring(4,operator.length());
                   }catch(Exception ex){}

                 //   out = out.replace(op, " 0 ");
                     if (op.toLowerCase().startsWith("#inv_")||op.toLowerCase().startsWith("#%inv_")){
                         if(lhm.containsKey(inv_operator)){
                             out = out.substring(0, n)+lhm.get(inv_operator)+out.substring(k+1, out.length());
                         }else
                             out = out.replace(op, " 0 ");
                      // jiURQ.getInventory().addInv(0,inv_operator);
                     }else
                         if(hm.containsKey(operator)){
                           out = out.substring(0, n)+hm.get(operator)+out.substring(k+1, out.length());
                         }else
                           out = out.replace(op, " 0 ");
                   //  jiURQ.getVariables().addVariable(op.replace("#", "").replace("%", "").replace("$", ""), "0");
                   n = -1;
                   k = out.indexOf("$");
                   for(int i=k;i>=0; i--){
                       if (out.substring(i, i+1).equals("#")){
                           n=i;
                           break;
                       }
                   }
//                                   System.out.println("n "+n);
//                System.out.println("k "+k);
                   if (n==-1)break;
                }

                return out;
          }

       public String parseRnd(String in){
            String out = in;
         try{
            Random r = new Random();
            int i=out.indexOf("rnd");

            while ((i=out.indexOf("rnd"))>=0){
                int ii=i+3;
                String rnd = "";
                while (isNum(String.valueOf(out.charAt(ii)))){
                    rnd+=String.valueOf(out.charAt(ii));
                    ii=ii+1;
                }
                if (!rnd.trim().isEmpty())
                    out = out.substring(0, i)+String.valueOf(r.nextInt(Integer.parseInt(rnd)))+out.substring(i+rnd.length()+3, out.length());
                else
                    out = out.substring(0, i)+String.valueOf(formatDouble(r.nextDouble(),2))+out.substring(i+3, out.length());
            }
           }catch(Exception ex){ return in;}
            return out;
        }

     private String getVariable(String var){
         System.out.println("getVariable "+var);
            var = var.trim();
            String operator = var.replace("#", "").replace("%", "").replace("$", "");
            HashMap<String,String> hm = jiURQ.getVariables().getVariablesHash();
            LinkedHashMap<String,Integer> lhm = jiURQ.getInventory().getInventoryHash();
            if (hm.containsKey(var))
                 return hm.get(var);
            if (hm.containsKey(operator))
                 return hm.get(operator);
            if (operator.length()>3&&lhm.containsKey(operator.substring(4, operator.length())))
                 return String.valueOf(lhm.get(operator.substring(4, operator.length())));
       //   System.out.println("getVariable "+var+" =0");

         return String.valueOf(jiURQ.getCountLocation(var));

     }
      public String adaptMatOperators(String in){
          String out = in;
        for (String symbols : MATH_SYMBOLS){
            out = out.replace(symbols, " "+symbols+" ");
        }
          return out;
     }

     private String replaceVariables(String expression){
        String out = expression.trim();
        out = parseRnd(out);
        out = adaptMatOperators(out);
      //  String out=temp;
       // temp = temp.replace("  ", " ");
       // temp = temp.replaceAll("  ", " ");
       // parseUnknownVariables(temp);
      //  System.out.println("reVariables "+out);
        String t = "";
        String[] words = out.split(" ");
//        for(String w : temp.split(" ")){
        for(int i = 0; i<words.length; i++){
            String w = words[i];
            if (w.isEmpty()) continue;
            if (!MATH_SYMBOLS.contains(w)&&!isNum(w)){
                System.out.println("w "+w);
              t+=w+" ";
                if (i+1==words.length){
                out=out.replace(t.trim(), getVariable(t));
             //   System.out.println("replaceVariables "+t);
                }
            }else
                if (!t.isEmpty()){
                out=out.replace(t.trim(), getVariable(t));
             //  System.out.println("replaceVariables "+t);
                t = "";
                }
          }


      //  System.out.println("replaceVariables out "+out);
         return out;
     }

     /**
      * RPN, англ. Reverse Polish Notation
      * @param in
      * @return
      */
     private String adaptToRPN (String expression){
        expression = replaceVariables(expression);
        if (expression == null || expression.length() == 0)
            throw new IllegalStateException("Expression isn't specified.");
        // Выходная строка, разбитая на "символы" - операции и операнды..
        List<String> out = new ArrayList<String>();
        // Стек операций.
        Stack<String> stack = new Stack<String>();

        // Удаление пробелов из выражения.
        expression = expression.replaceAll(" ", "");

        // Множество "символов", не являющихся операндами (операции и скобки).
        Set<String> operationSymbols = new HashSet<String>(MATH_OPERATIONS.keySet());
        operationSymbols.add("(");
        operationSymbols.add(")");

        // Индекс, на котором закончился разбор строки на прошлой итерации.
        int index = 0;
        // Признак необходимости поиска следующего элемента.
        boolean findNext = true;
        while (findNext) {
            int nextOperationIndex = expression.length();
            String nextOperation = "";
            // Поиск следующего оператора или скобки.
            for (String operation : operationSymbols) {
                int i = expression.indexOf(operation, index);
                if (i >= 0 && i < nextOperationIndex) {
                    nextOperation = operation;
                    nextOperationIndex = i;
                }
            }
            // Оператор не найден.
            if (nextOperationIndex == expression.length()) {
                findNext = false;
            } else {
                // Если оператору или скобке предшествует операнд, добавляем его в выходную строку.
                if (index != nextOperationIndex) {
                    out.add(expression.substring(index, nextOperationIndex));
                }
                // Обработка операторов и скобок.
                // Открывающая скобка.
                if (nextOperation.equals("(")) {
                    stack.push(nextOperation);
                }
                // Закрывающая скобка.
                else if (nextOperation.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        out.add(stack.pop());
                        if (stack.empty()) {
                            throw new IllegalArgumentException("Unmatched brackets");
                        }
                    }
                    stack.pop();
                }
                // Операция.
                else {
                    while (!stack.empty() && !stack.peek().equals("(") &&
                            (MATH_OPERATIONS.get(nextOperation) >= MATH_OPERATIONS.get(stack.peek()))) {
                        out.add(stack.pop());
                    }
                    stack.push(nextOperation);
                }
                index = nextOperationIndex + nextOperation.length();
            }
        }
        // Добавление в выходную строку операндов после последнего операнда.
        if (index != expression.length()) {
            out.add(expression.substring(index));
        }
        // Пробразование выходного списка к выходной строке.
        while (!stack.empty()) {
            out.add(stack.pop());
        }
        StringBuffer result = new StringBuffer();
        if (!out.isEmpty())
            result.append(out.remove(0));
        while (!out.isEmpty())
            result.append(" ").append(out.remove(0));

        return result.toString();
     }
    /**
     * Вычисляет значение выражения, записанного в инфиксной нотации. Выражение может содержать скобки, числа с
     * плавающей точкой, четыре основных математических операндов.
     *
     * @param expression выражение.
     * @return результат вычисления.
     */
    private Double calculate(String expression, int dz) {
        String rpn = adaptToRPN(expression);
        StringTokenizer tokenizer = new StringTokenizer(rpn, " ");
        Stack<Double> stack = new Stack<Double>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            // Операнд.
            if (!MATH_OPERATIONS.keySet().contains(token)) {
                stack.push(new Double(token));
            } else {
                Double operand2 = stack.pop();
                Double operand1 = stack.empty() ? 0 : stack.pop();
                Double o = operand1;
                if (token.equals("^")) {
                      o=new Double(1);
                      while (operand2>0) {
                        if (operand2%2==0) {
                          operand2 /= 2;
                          operand1 *= operand1; // [ a = (a*a)%n; ]
                          }
                        else {
                          operand2--;
                          o *= operand1; // [ b = (b*a)%n; ]
                          }
                      }
                  stack.push(o);
                } else if (token.equals("*")) {
                    stack.push(operand1*operand2);
                } else if (token.equals("/")) {
                    stack.push(operand1/operand2 );
                } else if (token.equals("+")) {
                    stack.push(operand1+operand2);
                } else if (token.equals("-")) {
                    stack.push(operand1-operand2);
                }
            }
        }
        if (stack.size() != 1)
            throw new IllegalArgumentException("Expression syntax error.");
        return formatDouble(stack.pop(), dz);
    }

   public boolean isNum(String in){
     try{
         Integer.valueOf(in.trim());
     //     System.out.println("isNum: "+in+" true");
         return true;
         }catch(Exception ex){
         try{
             Double.valueOf(in.trim());
         //    System.out.println("isNum: "+in+" true");
             return true;
             }catch(Exception exx){
          //  System.out.println("isNum: "+in+" false");
             return false;
             }
         }
    }

    /**
     * Тестирует методы.
     *
     * @param args список аргументов командной строки.
     */
    public static void main(String[] args) {
        Maths m = new Maths(new JiURQ());
        String expression = "hghjg + 4 * 2 / (5 - bbbb) ^ 2";
      //  String expression = "5.5 ^ 3";
        System.out.println("Инфиксная нотация:         " + expression);
        String rpn = m.adaptToRPN(expression);
        System.out.println("Обратная польская нотация: " + rpn);
        System.out.println("\tРезультат " + m.calculate(expression,2));
    //    expression = "not 556 < 2+1 && not 557<556";
//        boolean b = 556 > 2+1 && 557<556 || 556<557 || 557<558 && 5==5;
//        System.out.println("expression "+expression+" " + m.evalBoolean(expression));
//        System.out.println("expression "+expression+" " + b);

    }




}
