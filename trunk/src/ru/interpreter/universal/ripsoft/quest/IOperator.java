
package ru.interpreter.universal.ripsoft.quest;


/**
 *
 * @author ~jo-MA-jo~
 */
public interface IOperator {

    public String[] getNames();
    public void parse(Parser p, Location l, int n_str, int n_stance, int e_stance);
    public boolean parse(Parser p, String str);

}
