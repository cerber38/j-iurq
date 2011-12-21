/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.interpreter.universal.ripsoft.quest;

import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Администратор
 */
public class Inventory {
    private LinkedHashMap<String,Integer> inventory;
    private boolean visible;
    public String INVKILL = "invkill";
    public String INV_PLUS = "inv+";
    public String INV_MINUS = "inv-";
    public String INV_ = "inv_";
    public String INV_VISIBLE = "inv_visible";
    public String URQ_INV = "urq_inv";

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
//    /**
//     *
//     * @param location
//     */
//    public void parse (String str /*List<String> location*/){
////        for (String str : location){
//            try{
//                for (String inv : str.split("&")){
//
//                 if (inv.toLowerCase().indexOf(INVKILL)!=-1){
//                     if(inv.toLowerCase().trim().indexOf(" ")==-1)
//                        inventory.clear();
//                     else{
//                         String o = inv.split(" ")[1].trim();
//                         if (inventory.containsKey(o))
//                             inventory.remove(o);
//                     }
//                 } else
//
//                 if (inv.toLowerCase().indexOf(INV_PLUS)!=-1){
//                     String o = inv.substring(inv.indexOf(INV_PLUS)+4,inv.length()).split("=")[1].trim();
//                      if(o.indexOf(",")==-1)
//                          addInv(o);
//                      else{
//                          int count =1;
//                             try{
//                                 count=Integer.parseInt(o.split(",")[0].trim());
//                                }catch (Exception ex){}
//                           addInv(count, o.split(",")[1].trim());
//                          }
//                 }else
//
//                 if (inv.toLowerCase().indexOf(INV_MINUS)!=-1){
//                     String o = inv.substring(inv.indexOf(INV_MINUS)+4,inv.length()).split("=")[1].trim();
//                      if(o.indexOf(",")==-1)
//                          delInv(o);
//                      else{
//                          int count =1;
//                             try{
//                                 count=Integer.parseInt(o.split(",")[0].trim());
//                                }catch (Exception ex){}
//                           delInv(count, o.split(",")[1].trim());
//                          }
//                 }else
//
//                 if (inv.toLowerCase().indexOf(INV_)!=-1){
//                     String o = inv.split("_")[1];
//                      if(o.indexOf("=")!=-1){
//                        int count =1;
//                             try{
//                                 count=Integer.parseInt(o.split("=")[1].trim());
//                                }catch (Exception ex){}
//                        addInv(count, o.split(INV_)[1].split("=")[0].trim());
//                     }
//                 }else
//
//                 if (inv.toLowerCase().indexOf(INV_VISIBLE)!=-1){
//                     String o = inv.split(" ")[1].trim();
//                      if(o.equals("true"))
//                          visible=true;
//                      else
//                          visible=false;
//                 }
//
//                }
//
//            }catch (Exception ex){}
//
////        }
//
//    }

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
  //             System.out.println("inv+ "+count+" "+name);
     }

    /**
     * inv+
     * @param name
     */
    public void addInv(String name){
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
