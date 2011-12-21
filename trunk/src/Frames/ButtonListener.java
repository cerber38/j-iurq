package Frames;

/**
 *
 * @author ~jo-MA-jo~
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import tge.core.Core;

public class ButtonListener
  implements ActionListener
{
  private  StartJFrame sf;
//  private Core core;

  public ButtonListener(StartJFrame paramGui)
  {
    this.sf = paramGui;
  }

  public void actionPerformed(ActionEvent paramActionEvent)
  {
    Button localTGEButton = (Button)paramActionEvent.getSource();
     sf.getCore().ButtonOnClick(localTGEButton.getNum());

//    this.core.last_btn_caption = localTGEButton.getName();
//    this.core.cls();
//    this.core.doCommon();
//    this.core.switchLoc(str);
//    this.core.gotoLocation(str);
  }
}
