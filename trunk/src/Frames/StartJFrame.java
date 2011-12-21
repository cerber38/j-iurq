/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Frames;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import ru.interpreter.universal.ripsoft.quest.JiURQ;
import ru.interpreter.universal.ripsoft.quest.IButton;
import ru.interpreter.universal.ripsoft.quest.IOut;
import ru.interpreter.universal.ripsoft.quest.IOut_cls;
import ru.interpreter.universal.ripsoft.quest.Outgoing;


/**
 *
 * @author Администратор
 */
public class StartJFrame extends JFrame implements IOut, IOut_cls{
  private TextPanel textPanel;
  private InventoryPanel inventoryPanel;
  private ButtonPanel buttonPanel;
  private JiURQ c;
  private Panel panelButtons;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;


  public StartJFrame (){
    initComponents();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void initComponents() {

        inventoryPanel = new InventoryPanel(this);
        textPanel = new TextPanel(this);
        panelButtons = new java.awt.Panel();
        buttonPanel = new ButtonPanel(this);

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationByPlatform(true);

        GroupLayout panelButtonsLayout = new GroupLayout(panelButtons);
        panelButtons.setLayout(panelButtonsLayout);
        panelButtonsLayout.setHorizontalGroup(
            panelButtonsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
             .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelButtonsLayout.setVerticalGroup(
            panelButtonsLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
             .addComponent(buttonPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("Файл");
        jMenuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jMenuItem1.setText("Открыть");
        jMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                c.openDialog();
            }
        });
        jMenu1.add(jMenuItem1);
        jMenuBar1.add(jMenu1);
        jMenu2.setText("?");
//        jMenu2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                System.out.println("About");
//                AboutActionPerformed(evt);
//
//            }
//        });
        jMenuItem2.setText("О программе...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(panelButtons, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inventoryPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(inventoryPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textPanel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void AboutActionPerformed(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                  "<html><center>"
                + "<b><font color=navy>JiURQ</font></b> v"+c.getVersion()
                + "<br><font color=gray size=2>Java interpreter Universal Ripsoft Quest "
                + "<br>-------------------------------------------------------------------</font></center>"
                + "<br><font  size=2 > Redin Pavel (c) 2011 "
                + "<br>e-mail:</font> <font  size=2 color=navy>jomajo.ya@gmail.com </font>"
                + "</html>");
    }

      public JiURQ getCore() {
        return c;

      }
      public void start(String[] args) {
        c =new JiURQ(this,this);
        setTitle("Java interpreter Universal Ripsoft Quest (JiURQ) v"+c.getVersion());
        if (args[0].isEmpty())return;
        c.loadQest(args[0]);
        c.strartQest();

      }

    public static void main(String[] args) {
        StartJFrame sf = new StartJFrame ();
        sf.start(args);
    }


   public void addButton(int num, String paramBtn) {
    this.buttonPanel.addButton(num,paramBtn);
  }

   public void addText(String paramBtn) {
     textPanel.addText(paramBtn);

   }

     public void cls() {
        textPanel.clear();
        buttonPanel.removeAll();
     }

      public void onOutgoing(Outgoing content) {
                    String s="";
        for(String str : content.getText()){
            addText(str);
        }
            int i=-1;
            this.buttonPanel.removeAll();
            this.buttonPanel.repaint();
        for(IButton bt : content.getButtons()){
            i++;
            addButton(i, bt.getName());
        }
        inventoryPanel.refresh(content.getInventory());
        }


}
