/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.interpreter.universal.ripsoft.quest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFileChooser;
import ru.interpreter.universal.ripsoft.quest.operators.BuilderOperators;

/**
 *
 * @author ~jo-MA-jo~
 */
public class JiURQ {
    private ArrayList<String> listQst = new ArrayList<String>();
    private HashMap<String,Integer> listLocations = new HashMap<String,Integer>();
    private LinkedHashMap<String[],IOperator> operators = new LinkedHashMap<String[],IOperator>();
    public int[] currentQest;
    private Inventory inventory;
    private Variables variables;
    private Activity activity;
    private Parser parser;
    private IOut out;
    private IOut_cls out_cls;
    private List<ExProc> proc = new ArrayList<ExProc>();
    private String version = "0.2b";



    public JiURQ (IOut out, IOut_cls out_cls){
     operators=BuilderOperators.build();
     parser = new Parser(this);
     inventory = new Inventory();
     variables = new Variables();
     activity = new Activity();
     currentQest = new int[]{0,0};
     this.out=out;
     this.out_cls=out_cls;
    }

//    public static class Qest implements IOut{
//
//        public void onOutgoing(Outgoing content) {
//                    String s="";
//        for(String str : content.getText()){
//            s+=str;
//        }
//            s+="\n----------\n";
//            int i=0;
//        for(IButton bt : content.getButtons()){
//            i++;
//            s+=i+" | "+bt.getName()+"\n";
//        }
//        System.out.println(s);
//            //throw new UnsupportedOperationException("Not supported yet.");
//        }
//
//    }

    public void openDialog(){
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileFilter(new MyFilter());
        fileChooser.showOpenDialog(null);
        String path = "";
        try{
        path = fileChooser.getSelectedFile().getPath();
        }catch (Exception ex){}
        if (!path.isEmpty()){
            stopQest();
            loadQest(fileChooser.getSelectedFile().getPath());
            strartQest();
         }
  //  System.out.println(fileChooser.getSelectedFile());
    }

    class MyFilter extends javax.swing.filechooser.FileFilter {
      public boolean accept(File file) {
        String filename = file.getName();
        if (file.isDirectory())
            return true;
        else
            return filename.toLowerCase().endsWith(".qst");
      }
      public String getDescription() {
        return "Файлы квестов (*.qst)";
      }
    }
    public String getVersion(){
        return version;
    }
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//
//        JiURQ с =new JiURQ(new Qest());
//        String fname = args[0];
//        с.loadQest(fname);
//        с.strartQest();
//        // TODO code application logic here
//    }
    public IOut getOut(){
        return out;
    }

    public IOut_cls getOut_cls(){
        return out_cls;
    }

    public void ButtonOnClick(int i){
        parser.getOutgoing().getButtons().get(i).onClick();
    }

    public Variables getVariables(){
        return variables;
    }

    public Activity getActivity(){
        return activity;
    }

    public void ActivityOnClick(String use){
        parser.parse(use);
    }

    public Inventory getInventory(){
        return inventory;
    }

    public LinkedHashMap<String[],IOperator> getOperators(){
        return operators;
    }

    public HashMap<String,Integer> getListLocations(){
        return listLocations;
    }

    public List<String> getListQest(){
        return listQst;
    }

    public void strartQest() {
      //  Location l= location.get(listQst.get(currentQest[0]));
        parser.parse(currentQest[0]);
    }

    public void stopQest() {
     listQst.clear();
     listLocations.clear();
     inventory.clear();
     variables.clear();
     proc.clear();
     parser.clsOutButton();
     parser.clearOutgoing();
     getOut_cls().cls();
     activity.clear();
  //   parser = new Parser(this);
     currentQest = new int[]{0,0};

    }

    public boolean restartQest() {
        if (listQst.size()>0){
         inventory.clear();
         variables.clear();
         proc.clear();
         parser.clsOutButton();
         parser.clearOutgoing();
         getOut_cls().cls();
      //   parser = new Parser(this);
         currentQest = new int[]{0,0};
         strartQest();
         return true;
        }
        return false;
    }

    public void addProc(ExProc prc){
 //       System.out.println("addProc: "+prc.l.name);
        proc.add(prc);
    }

    public void delLastProc(){
        if (!proc.isEmpty())
            proc.remove(proc.size()-1);
 //               System.out.println("delLastProc ");
    }

    public ExProc getLastProc(){
        if (!proc.isEmpty()){
 //           System.out.println("getLastProc() "+proc.get(proc.size()-1).l.name);
            return proc.get(proc.size()-1);
        } else
        return null;
    }

    /**
     * Загружает квест
     * @param fname
     */
    public void loadQest(String fname) {
        try {
           int i=-1;

            String nameLocation ="";
            String[]content =getQest(fname).split("<BR>");
              for (String str :content){
              if(!str.trim().toLowerCase().startsWith("if")){
                for (String s:str.split("&")){
                    s=s.trim();
                   // System.out.println(s);
                    if (s.indexOf(";")==0)continue;
                    if (s.indexOf(":")==0){
                        nameLocation = s.substring(1,s.length()).toLowerCase();
                        listLocations.put(nameLocation, i+1);
                        if (s.toLowerCase().startsWith(":use_")) activity.addUse(s.substring(1,s.length()));
                    }else
                    if  (!s.isEmpty()&&!s.equals(" ")&&!s.equals("\n")/*&&!s.equalsIgnoreCase("end")*/){
                        i++;
                        listQst.add(i,s);
                    }
                }
              }else{
                  if (str.indexOf(";")==0)continue;
                  if (str.indexOf(":")==0){
                        nameLocation = str.substring(1,str.length()).toLowerCase();
                        listLocations.put(nameLocation, i+1);
                        if (str.toLowerCase().startsWith(":use_")) activity.addUse(str.substring(1,str.length()));
                  }else
                    if  (!str.isEmpty()&&!str.equals(" ")&&!str.equals("\n")/*&&!s.equalsIgnoreCase("end")*/){
                        i++;
                        listQst.add(i,str);
                    }
              }
            }
              listQst.trimToSize();
          } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Загружает квест из файла
     * @param fname
     */
    public String getQest(String fname) {
         String out ="";
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(fname), "windows-1251"));

            while (r.ready()){
              String str = r.readLine().trim();
              if (str.startsWith("_"))
                  out +=str.substring(str.indexOf("_")+1, str.length());
              else
                  out +="\n<BR>"+str;
              out = out.replace("#/$", "\n");
            }
      //      System.out.println(out);
             r.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
             return out;
    }


}
