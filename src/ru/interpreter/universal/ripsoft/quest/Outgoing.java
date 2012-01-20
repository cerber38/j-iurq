
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
    private List<String> first_text = new ArrayList<String>();
    private List<String> text = new ArrayList<String>();
    private List<String> last_text = new ArrayList<String>();
    private List<IButton> buttons = new ArrayList<IButton>();
    private int last_text_size = 0;


    public Outgoing (Parser p){
        this.p=p;
    }

    protected void clearContent (){
        clearStrings();
        clearButtons ();
    }

    protected void clearStrings(){
        first_text.clear();
        text.clear();
        last_text.clear();
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
    
    public void onOutgoing(){
        if (first_text.isEmpty())
            first_text = new ArrayList<String>(text);
       // if (last_text_size < text.size()){
            last_text = new ArrayList<String>();
            for(int i = last_text_size; i<text.size(); i++){
             last_text.add(text.get(i));
            }
            last_text_size = text.size();
     //   }

     }
    
    public List<String> getFirstText (){
        return first_text.isEmpty() ? text : first_text;
    }
    
    public List<String> getText (){
        return text;
    }
    
    public List<String> getLastText (){
        return last_text.isEmpty() ? text : last_text;
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
        List<IButton> btn = new ArrayList<IButton>();
        for(IButton b : buttons) if (!b.isPhantom()) btn.add(b);
        return btn;
    }
    
    public List<IButton> getAllButtons (){
        return buttons;
    }

    public LinkedHashMap<String,Integer> getInventory (){
        return p.getCore().getInventory().getCleanInventoryHash();
    }



}
