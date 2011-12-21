
package Frames;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;


/**
 *
 * @author ~jo-MA-jo~
 */
public class TextPanel extends JScrollPane{
    private JTextPane textPane=new JTextPane();


      public TextPanel(StartJFrame paramGui) {
        textPane.setBackground(new java.awt.Color(204, 204, 204));
        textPane.setEditable(false);
        textPane.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        setViewportView(textPane);
//
////          setLayout(new BorderLayout(1, 1));
//          textArea= new TextArea();
////          textArea.setFont(new Font("Courier New", 1, 12));
////          textArea.setEditable(false);
////          //textArea.setSize(this.getMaximumSize());
////          setSize(this.getMaximumSize());
////          add(this.textArea, BorderLayout.CENTER);
//        textArea.setBackground(new java.awt.Color(204, 204, 204));
//        textArea.setEditable(false);
//       // textArea.setFont(new java.awt.Font("DialogInput", 1, 12)); // NOI18N
//        textArea.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
//
//        GroupLayout textPanelLayout = new GroupLayout(this);
//        setLayout(textPanelLayout);
//        textPanelLayout.setHorizontalGroup(
//            textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(textArea, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
//        );
//        textPanelLayout.setVerticalGroup(
//            textPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addComponent(textArea, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
//        );
//


      }

    public void addText(String param) {
        try {
            textPane.getDocument().insertString(textPane.getDocument().getLength(), param+"\n---\n", null);
            textPane.setCaretPosition(textPane.getDocument().getLength());
            //  System.out.println(textArea.getHeight());
        } catch (Exception ex) {
         //   Logger.getLogger(TextPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clear() {
        textPane.setText("");
   //     System.out.println("textArea clear");
    }


}
