/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Frames;

import javax.swing.JButton;


public class Button extends JButton
{
  private static final long serialVersionUID = 1L;
  private int num;

  public Button(int num,String paramBtn, ButtonListener paramTGEButtonListener)
  {
//    setColors(paramColor1, paramColor2);
    setText(paramBtn);
    setToolTipText(paramBtn);
//    this.goLoc = paramBtn.getLocation();
//    setPreferredSize(this.getMaximumSize());
    this.num = num;
//    setFocusPainted(false);
    setVisible(true);
  //  setMargin(new Insets(2, 2, 2, 2));
    addActionListener(paramTGEButtonListener);
  }

  public int getNum()
  {
    return this.num;
  }

//  public void setColors(Color paramColor1, Color paramColor2)
//  {
//    setBackground(paramColor1);
//    setForeground(paramColor2);
//  }
}
