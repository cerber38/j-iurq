/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.interpreter.universal.ripsoft.quest;

/**
 *
 * @author ~jo-MA-jo~
 */
public class ActionInventory {
      private String name;
      private String action;

      public ActionInventory(String _name, String _action){
        this.name = _name;
        this.action = _action;
      }

      public String getName(){
         return name;
      }

      public String getAction(){
         return action;
      }


   }
