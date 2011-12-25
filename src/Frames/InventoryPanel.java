

package Frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.GroupLayout;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import ru.interpreter.universal.ripsoft.quest.ActionInventory;

/**
 *
 * @author ~jo-MA-jo~
 */
public class InventoryPanel extends JPanel implements ActionListener,  PopupMenuListener{
    private JList listInventory;
    private ArrayList<String> inventoryHash = new ArrayList();
    private javax.swing.JPopupMenu popupMenu;
    private StartJFrame sf;
    private LinkedHashMap<String,String> paramHash = new LinkedHashMap<String,String>();


      public InventoryPanel(StartJFrame param) {
       //   ActionListener actionListener = new PopupActionListener();
        //  PopupMenuListener popupMenuListener = this;

          sf = param;
           popupMenu = new JPopupMenu("Title");
           popupMenu.addPopupMenuListener(this);


         listInventory= new javax.swing.JList();
         listInventory.setLayoutOrientation(JList.VERTICAL);
         listInventory.setComponentPopupMenu(popupMenu);

        GroupLayout inventoryPanelLayout = new GroupLayout(this);
        setLayout(inventoryPanelLayout);
        setMaximumSize(this.getMaximumSize());
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listInventory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );
//authorList().getModel().getElementAt(authorList(). locationToIndex(e.getPoint()))
        listInventory.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.isPopupTrigger()){
//                    selectItem(listInventory, e.getX(), e.getY());
//                }
//            }

            @Override
            public void mousePressed(MouseEvent e) {
               listInventory.setSelectedIndex(listInventory.getUI().locationToIndex(listInventory,new Point(e.getX(), e.getY())));//locationToIndex(e.getPoint()))
            }

//            @Override
//            public void mouseReleased(MouseEvent e) {
//                if (e.isPopupTrigger()){
//                    selectItem(listInventory, e.getX(), e.getY());
//                }
//            }
        });

//     //     setLayout(new BorderLayout(1, 1));
//      //    listInventory= new List();
//      //    listInventory.setSize(this.getMaximumSize());
//      //    add(this.listInventory, BorderLayout.CENTER);
//
      }
//    private void selectItem(JList list, int x, int y){
//        int index = list.getUI().locationToIndex(list, new Point(x,y));
//        System.out.println("Selected: "+index);
//        listInventory.setSelectedIndex(index);
//    }

     public void refresh(LinkedHashMap<String,Integer> param) {
         inventoryHash.clear();
         ArrayList<String> data = new ArrayList();
         data.add("Инвентарь");
         inventoryHash.add("Инвентарь");
         for(String inv : param.keySet()){
             int i = param.get(inv);
             data.add(i>1?i+" "+inv:inv);
             inventoryHash.add(inv);
        }
        listInventory.setListData(data.toArray());
        this.repaint();
      //  System.out.println(textArea.getHeight());
    }

 //   class PopupActionListener implements ActionListener {
      public void actionPerformed(ActionEvent actionEvent) {
        int selectedIndex = listInventory.getSelectedIndex();
        sf.getCore().ActivityOnClick(paramHash.get(selectedIndex+"_"+actionEvent.getActionCommand()));
        System.out.println("Selected: " + actionEvent.getActionCommand());
      }
//    }

 //   class MyPopupMenuListener implements PopupMenuListener {
      public void popupMenuCanceled(PopupMenuEvent popupMenuEvent) {
  //      System.out.println("Canceled");
      }

      public void popupMenuWillBecomeInvisible(PopupMenuEvent popupMenuEvent) {

     //   System.out.println("Becoming Invisible");
      }

      public void popupMenuWillBecomeVisible(PopupMenuEvent popupMenuEvent) {
           popupMenu.removeAll();
           int selectedIndex = listInventory.getSelectedIndex();

           System.out.println("listInventory.getSelectedValue() "+selectedIndex);
           ArrayList<ActionInventory> s=new ArrayList();
           if (selectedIndex>=0){
               if(selectedIndex==0){
               s = sf.getCore().getActivity().getlistInvUse();
                   }else
               s = sf.getCore().getActivity().getlistUse(inventoryHash.get(selectedIndex));
          }
           paramHash.clear();
           for(ActionInventory ai :s){
           JMenuItem menuItem = new JMenuItem(ai.getName());
            menuItem.addActionListener(this);
            popupMenu.add(menuItem);
            paramHash.put(selectedIndex+"_"+ai.getName(), ai.getAction());
           }
          if(s.isEmpty()){
            JMenuItem menuItem = new JMenuItem("(пусто...)");
            menuItem.setEnabled(false);
            popupMenu.add(menuItem);
          }
   //      System.out.println("Becoming Visible");
      }


}
