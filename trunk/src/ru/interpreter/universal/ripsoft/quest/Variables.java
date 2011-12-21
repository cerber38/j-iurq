
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
        variables.put(name, value);
    }

    public HashMap<String,String> getVariablesHash(){
        return variables;
    }

    public String getVariable(String name){
        return variables.containsKey(name) ? variables.get(name) : null;
    }
  
    /**
     * 
     * @return
     */
    public int getCount(){
        return variables.size();
    }

   

}
