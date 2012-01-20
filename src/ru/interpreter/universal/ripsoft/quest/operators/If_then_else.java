
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class If_then_else implements IOperator {
    private String[] ops = {"if","then","else"};

//    private List <String> con = new ArrayList <String>();


    public If_then_else (){

    }

    public String[] getNames() {
        return ops;
    }

   public class wait implements Runnable {

    private Thread th;
    int sleepAmount = 1000;
    Parser p;
    int n_str;
    int n_stance;
    int e_stance;


    public wait(Parser p, int n_str, int n_stance, int e_stance) {
     this.p = p;
     this.n_str=n_str;
     this.n_stance=n_stance;
     this.e_stance=e_stance;
    }



    public void start(){
        th = new Thread(this);
        th.setPriority(Thread.NORM_PRIORITY);
        th.start();
    }

    public synchronized void stop() {
        th = null;
        notify();
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (th == me) {
            if (p.getCore().getGo())parse();
            try {
                th.sleep(sleepAmount);
            } catch (InterruptedException e) { break; }
        }
        th=null;
    }

        public void parse() {
//            System.out.println("!!!!parse wait");
        int size = p.getStringQest(n_str).length();
            if (e_stance+1<size)
                 p.parse(n_str, e_stance+1);
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.parse(n_str+1);
        stop();
       }
    }


    public void parse(Parser p, int n_str, int n_stance, int e_stance) {
        String str = p.getStringQest(n_str, n_stance, e_stance);
        int size = p.getStringQest(n_str).length();
        if (parse(p, str)){
          //  new wait( p,  n_str,  n_stance,  e_stance).start();
            if (e_stance+1<size)
                 p.parse(n_str, e_stance+1);
            else
                if (n_str+1<p.getCore().getListQest().size())
                 p.parse(n_str+1);

        }
    }

    public boolean parse(Parser p, String str) {
         boolean b =false;
         boolean b2 =false;
        String s = str.toLowerCase();
        int _if = s.indexOf("if")+2;
        int _then = s.indexOf("then");
        int _else = s.lastIndexOf("else");
        String condition = str.substring(_if,_then).trim();
        boolean if_then = getBoolean(condition, p);
         if (_else==-1){

            if (if_then){
                String ss = str.substring(_then+4,str.length());
                if (ss.trim().toLowerCase().startsWith("proc"))p.getCore().setGo(false);
                if (!ss.trim().toLowerCase().startsWith("if")){
                   for(String o : ss.split("&")){
                  if (o.toLowerCase().trim().startsWith("goto")/*||o.trim().toLowerCase().startsWith("proc")*/){
                    b2 = false;  
                  }else
                    b2 = true;
  //                 System.out.println("b21 "+b2);
                   if (o.trim().toLowerCase().startsWith("proc"))p.getCore().setGo(false);
                   action (p,o);
                  }
                }else{
                    b2 = true;
                   action (p,ss);
                }
            }else b2 = true;

                 return b2;
        }
        if (!if_then) { b2 = true;
               String ss = str.substring(_else+4,str.length());
                if (!ss.trim().toLowerCase().startsWith("if")){
                   for(String o : ss.split("&")){
                  if (o.toLowerCase().trim().startsWith("goto")/*||o.trim().toLowerCase().startsWith("proc")*/){
                    b2 = false;
                  }else
                    b2 = true;
    //              System.out.println("b2 "+b2);
                    if (o.trim().toLowerCase().startsWith("proc"))p.getCore().setGo(false);
                   action (p,o);
                  }
                }else{
                   action (p,ss);
                   b2 = true;
            }
            return b2;
        }
         else{ b2 = true;
               String ss = str.substring(_then+4,_else);
                if (!ss.trim().toLowerCase().startsWith("if")){
                   for(String o : ss.split("&")){
                  if (o.toLowerCase().trim().startsWith("goto")/*||o.trim().toLowerCase().startsWith("proc")*/){
                    b2 = false;
                  }else
                    b2 = true;
     //             System.out.println("b2 "+b2);
                     if (o.trim().toLowerCase().startsWith("proc"))p.getCore().setGo(false);
                    action (p,o);
                    }
                }else
                   action (p,ss);
             return b2;
        }
      //  return true;
   }

    public boolean getBoolean(String condition, Parser p){

        return p.getCore().getMaths().evalBoolean(condition);
    }


    public void action (Parser p,String str) {
        String s = str.trim();
//        System.out.println("action: "+s);
        p.parse(s);
    }



}


