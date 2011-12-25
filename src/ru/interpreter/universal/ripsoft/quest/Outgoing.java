
package ru.interpreter.universal.ripsoft.quest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ~jo-MA-jo~
 */
public class Outgoing {
    Parser p;
    private List<String> text = new ArrayList<String>();
    private List<IButton> buttons = new ArrayList<IButton>();

    public Outgoing (Parser p){
        this.p=p;
    }

    protected void clearContent (){
        text.clear();
        buttons.clear();
    }

    protected void clearStrings(){
        text.clear();
    }

    protected void clearButtons (){
        buttons.clear();
    }

    protected void addString (String s){
        text.add(s);
    }
    
    protected void addButton (IButton b){
        buttons.add(b);
     //   System.out.println("Add new Button: "+b.getName());
    }

    public List<String> getText (){
        return text;
    }

    public List<String> getText (boolean clear){
        List<String> out = new ArrayList<String>(text);
        if (clear) text.clear();
        return out;
    }

    public List<String> getCleanText (int max){
//        List<String> clean = new ArrayList<String>();
//        String last ="";
//        int i = 0;
//        for (String s :text) {
//            if (last.isEmpty()||last.equals(" ")||last.equals("\n")||last.equals(" \n"))i++;
//            else i = 0;
//            if (i<=max){
//                clean.add(s);
//            }
//            last =s.trim().replace("\n", "")+"\n";
//        }
        return text;
    }

    public List<IButton> getButtons (){
        return buttons;
    }

    public LinkedHashMap<String,Integer> getInventory (){
        return p.getCore().getInventory().getCleanInventoryHash();
    }



}
