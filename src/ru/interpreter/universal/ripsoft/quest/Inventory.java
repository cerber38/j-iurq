/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.interpreter.universal.ripsoft.quest;

import java.util.LinkedHashMap;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Inventory {
    private LinkedHashMap<String,Integer> inventory;
    private boolean visible;

    public Inventory(){
           inventory = new LinkedHashMap<String,Integer>();
           visible=false;

    }

    public boolean isVisible(){
        boolean b = visible ? inventory.size()>0 : false;
        return b;
    }

    public Inventory clear(){
        inventory.clear();
        return this;
    }

    public void remove(String o){
        if (inventory.containsKey(o))
            inventory.remove(o);
    }

    public LinkedHashMap<String,Integer> getInventoryHash(){
        return inventory;
    }

    public LinkedHashMap<String,Integer> getCleanInventoryHash(){
        LinkedHashMap<String,Integer> lhm =new LinkedHashMap<String,Integer>();
            for (String inv:inventory.keySet()){
                int i = inventory.get(inv);
                    if (i>0)lhm.put(inv, i);
           }
        return lhm;
    }


    /**
     * urq_inv
     * @return
     */
    public int getCount(){
        return inventory.size();
    }

    /**
     * inv+
     * @param count
     * @param name
     */
    public void addInv(int count, String name){
           int new_count = inventory.containsKey(name) ? inventory.get(name)+count : count;
           if (new_count<0) {
               inventory.remove(name);
  //             System.out.println("remove_inv: "+name);
           }
           else
               inventory.put(name, new_count);
               System.out.println("addInv: "+name+" = "+count);
     }

    /**
     * inv+
     * @param name
     */
    public void addInv(String name){
        System.out.println("addInv: "+name+" = "+1);
           addInv(1, name);
    }

    /**
     * inv-
     * @param count
     * @param name
     */
    public void delInv(int count, String name){
           int new_count = inventory.containsKey(name) ? inventory.get(name)-count : count;
           if (new_count<=0){
               inventory.remove(name);
//               System.out.println("remove_inv: "+name);
           }
           else
               inventory.put(name, new_count);
   //            System.out.println("remove_inv: "+name+" - "+count);
    }

    /**
     * inv-
     * @param name
     */
    public void delInv(String name){
           delInv(1, name);
    }

}
