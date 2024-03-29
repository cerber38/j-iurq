
package ru.interpreter.universal.ripsoft.quest.operators;

import ru.interpreter.universal.ripsoft.quest.ExProc;
import ru.interpreter.universal.ripsoft.quest.IOperator;
import ru.interpreter.universal.ripsoft.quest.Outgoing;
import ru.interpreter.universal.ripsoft.quest.Parser;

/**
 *
 * @author ~jo-MA-jo~
 */
public class End implements IOperator {
    private String[] ops = {"end"};

    public End (){
    }

    public String[] getNames(){
        return ops;
    }

    public void parse(Parser p, int n_str, int n_stance, int e_stance) {
        parse(p, "");
    }

    public boolean parse(Parser p, String str) {
        if (p.getCore().getLastProc()==null){
            if (!p.getCore().getThisLocation().equals("common")){
            Outgoing o = p.getOutgoing();
            o.onOutgoing();
            p.getCore().getOut().onOutgoing(o);
            }
        }else{
            ExProc ep = p.getCore().getLastProc();
            p.getCore().setLocation(ep.name_location);
            p.getCore().delLastProc();
            p.parse(ep.n_str,ep.n_stance);
        }
        p.getCore().setGo(true);
        return true;
      }

}
