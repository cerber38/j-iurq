/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.interpreter.universal.ripsoft.quest;

import java.util.ArrayList;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Activity {
   // private LinkedHashMap<String,Integer> use;
    private ArrayList<String> listUse;
    private ArrayList<String> listHideUse;
    private boolean hide_use;

    public Activity(){
           listUse = new ArrayList<String>();
           listHideUse = new ArrayList<String>();
           hide_use=false;
    }

    public void setHide(boolean b){
        hide_use = b;
    }

    public boolean isVisible(){
       // boolean b = visible ? inventory.size()>0 : false;
        return hide_use;
    }

    public Activity clear(){
        listUse.clear();
        listHideUse.clear();
        return this;
    }


    public ArrayList<String> getlistUseHash(){
        return listUse;
    }

    public ArrayList<ActionInventory> getlistInvUse(){
        ArrayList<ActionInventory> list = new ArrayList<ActionInventory>();
      //  if (hide_use)return list;
        for(String s : listUse){
        //    System.out.println("getlistInvUse "+s);
            if(s.toLowerCase().startsWith("use_inv")&&!listHideUse.contains(s)){
                int i = s.lastIndexOf("_");
                String name = i>3?s.substring(i+1):"Осмотреть";
                list.add(new ActionInventory(name,s));
            }
         }
        return list;
    }

    public ArrayList<ActionInventory> getlistUse(String param){
        ArrayList<ActionInventory> list = new ArrayList<ActionInventory>();
      //  if (hide_use)return list;
        for(String s : listUse){
          //   System.out.println("getlistUse "+s);
            if(s.toLowerCase().startsWith("use_"+param.toLowerCase())&&!listHideUse.contains(s)){
                int i = s.lastIndexOf("_");
            //  String name = i>3? s.substring(i+1):"Осмотреть";
                String name = i>3? s.substring(i+1,i+2).equals(s.substring(i+1,i+2).toUpperCase())? s.substring(i+1):"Осмотреть":"Осмотреть";
                list.add(new ActionInventory(name,s));
            }
         }
        return list;
    }

    public void addUse(String label){
        listUse.add(label);
     }



}
