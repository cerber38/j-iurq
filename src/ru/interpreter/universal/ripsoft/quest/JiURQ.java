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
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFileChooser;
import ru.interpreter.universal.ripsoft.quest.operators.BuilderOperators;

/**
 *
 * @author ~jo-MA-jo~
 */
public class JiURQ {
    private List<String> listQst = new ArrayList<String>();
    private static LinkedHashMap<String,Location> location = new LinkedHashMap<String,Location>();
    private LinkedHashMap<String[],IOperator> operators = new LinkedHashMap<String[],IOperator>();
    public static int[] currentLocation;
    private static Inventory inventory;
    private static Variables variables;
    private static Parser parser;
    private static IOut out;
    private static IOut_cls out_cls;
    private static List<ExProc> proc = new ArrayList<ExProc>();
    private static String version = "0.1a";


    public JiURQ (IOut out, IOut_cls out_cls){
     operators=BuilderOperators.build();
     parser = new Parser(this);
     inventory = new Inventory();
     variables = new Variables();
     currentLocation = new int[]{0,0};
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

    public Inventory getInventory(){
        return inventory;
    }

    public LinkedHashMap<String[],IOperator> getOperators(){
        return operators;
    }

    public LinkedHashMap<String,Location> getLocation(){
        return location;
    }

    public void strartQest() {
        Location l= location.get(listQst.get(currentLocation[0]));
        parser.parse(l, currentLocation[1]);
    }

    public void stopQest() {
     listQst.clear();
     location.clear();
     inventory.clear();
     variables.clear();
     proc.clear();
     parser.clsOutButton();
     parser.clearOutgoing();
     getOut_cls().cls();
  //   parser = new Parser(this);
     currentLocation = new int[]{0,0};

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
         currentLocation = new int[]{0,0};
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
     * Загружает квест из файла
     * @param fname
     */
    public void loadQest(String fname) {
        try {
           // BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(fname), "windows-1251"));
            String nameLocation ="";
        //    while (r.ready()){
             // String str = r.readLine();
              for (String str :getQest(fname).split("<BR>")){
              if(!str.trim().toLowerCase().startsWith("if")){
                for (String s:str.split("&")){
                    s=s.trim();
                   // System.out.println(s);
                    if (s.indexOf(":")==0){
                        nameLocation = s.trim().substring(1,s.length()).toLowerCase();
                        location.put(nameLocation,new Location(nameLocation));
                        listQst.add(nameLocation);
                 //      System.out.println("Add new location "+nameLocation);
                    }else
                    if  (!s.isEmpty()&&!s.equals(" ")&&!s.equals("\n")/*&&!s.equalsIgnoreCase("end")*/){
                        Location l=location.get(nameLocation);
                        location.put(nameLocation,l.addStringInList(s));
                    }
                }
              }else{
                    if  (!str.isEmpty()&&!str.equals(" ")&&!str.equals("\n")/*&&!s.equalsIgnoreCase("end")*/){
                        Location l=location.get(nameLocation);
                        location.put(nameLocation,l.addStringInList(str));
                    }
              }
            }
          //   r.close();
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
