/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Frames;


import java.awt.GridLayout;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel
{
  private ButtonListener butListener;
  private GridLayout buttonPanelLayout;
  private StartJFrame paramGui;


  public ButtonPanel(StartJFrame paramGui)
  {
      this.paramGui = paramGui;
      buttonPanelLayout = new GridLayout(5, 5, 2, 2);
      setLayout(buttonPanelLayout);
      butListener = new ButtonListener(paramGui);
  }



  public void addButton(int num,String paramBtn){
    this.add(new Button(num, paramBtn, this.butListener));
    validate();
  }


}