
package Frames;

/**
 *
 * @author ~jo-MA-jo~
 */
import java.awt.GridLayout;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel
{
  private ButtonListener butListener;
  private GridLayout buttonPanelLayout;
  private StartJFrame core;


  public ButtonPanel(StartJFrame core)
  {
      this.core = core;
      buttonPanelLayout = new GridLayout(5, 5, 2, 2);
      setLayout(buttonPanelLayout);
      butListener = new ButtonListener(core);
  }



  public void addButton(int num,String paramBtn, boolean enabled){
    Button b= new Button(num, paramBtn, this.butListener);
    b.setEnabled(enabled);
    this.add(b);
    validate();
  }


}