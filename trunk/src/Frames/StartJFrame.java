
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
 * @author ~jo-MA-jo~
 */
public class StartJFrame extends JFrame implements IOut, IOut_cls{
  private TextPanel textPanel;
  private InventoryPanel inventoryPanel;
  private ButtonPanel buttonPanel;
  private JiURQ c;
  private Panel panelButtons;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem restartMenuItem;
    


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

        jMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();
        restartMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       // setResizable(false);
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

        fileMenu.setText("Файл");
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openMenuItem.setText("Открыть");
        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                c.openDialog();
            }
        });
        restartMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        restartMenuItem.setText("Рестарт");
        restartMenuItem.setEnabled(false);
        restartMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                c.restartQest();
            }
        });
        fileMenu.add(openMenuItem);
        fileMenu.add(restartMenuItem);
        jMenuBar.add(fileMenu);
        helpMenu.setText("?");
//        jMenu2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                System.out.println("About");
//                AboutActionPerformed(evt);
//
//            }
//        });
        aboutMenuItem.setText("О программе...");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        jMenuBar.add(helpMenu);

        setJMenuBar(jMenuBar);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(inventoryPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelButtons, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
         String s = "";
        try{
         s = args[0];
        }catch(Exception ex){}
        if (s.isEmpty())return;
        c.loadQest(args[0]);
        c.strartQest();
      //  restartMenuItem.setEnabled(true);

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
                    restartMenuItem.setEnabled(true);
           int size = content.getText().size();
        for(String str : content.getText(true)){
            addText(str);
        }
        if (size>0)
            addText("\n-----\n");
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
