
package ru.interpreter.universal.ripsoft.quest;

import java.util.HashMap;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Variables {
    private HashMap<String,String> variables;


    public Variables(){
        variables = new HashMap<String,String>();

    }

    public Variables clear(){
        variables.clear();
        return this;
    }

    public void remove(String o){
        if (variables.containsKey(o))
            variables.remove(o);
    }

    public void addVariable(String name ,String value){
//        if (variables.containsKey(name)){
//             try{
//                 int i= Integer.valueOf(value);
//                 int ii= Integer.valueOf(variables.get(name));
//                 variables.put(name, String.valueOf(i+ii));
//                }catch(Exception ex){
//                  variables.put(name, value);
//                }
//        }else
        if (value.endsWith(".0"))value=value.substring(0, value.length()-2);
     //    System.out.println("addVariable: "+name+" = "+value);
        variables.put(name, value);
    }

    public HashMap<String,String> getVariablesHash(){
        return variables;
    }

    public String getVariable(String name){
        return variables.containsKey(name) ? variables.get(name) : null;
    }

    public String getCommon(){
        String out ="";
        if(variables.containsKey("common")){
            String val = variables.get("common");
            if (!val.equals("0"))out=val;
        }
        return out;
    }
  
    /**
     * 
     * @return
     */
    public int getCount(){
        return variables.size();
    }

   

}
