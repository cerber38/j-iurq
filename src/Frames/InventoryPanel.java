/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.util.LinkedHashMap;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author Администратор
 */
public class InventoryPanel extends JPanel{
    private List listInventory;


      public InventoryPanel(StartJFrame paramGui) {
         listInventory= new List();
        GroupLayout inventoryPanelLayout = new GroupLayout(this);
        setLayout(inventoryPanelLayout);
        inventoryPanelLayout.setHorizontalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listInventory, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
        );
        inventoryPanelLayout.setVerticalGroup(
            inventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(listInventory, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
        );

     //     setLayout(new BorderLayout(1, 1));
      //    listInventory= new List();
      //    listInventory.setSize(this.getMaximumSize());
      //    add(this.listInventory, BorderLayout.CENTER);

      }

     public void refresh(LinkedHashMap<String,Integer> param) {
        listInventory.removeAll();
         for(String inv : param.keySet()){

             listInventory.add(inv+" "+param.get(inv));
        }
         this.repaint();
      //  System.out.println(textArea.getHeight());

    }



}
