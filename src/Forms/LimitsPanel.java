
package Forms;

import Classes.*;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.event.KeyEvent;
import java.util.List;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.*;



public class LimitsPanel extends javax.swing.JPanel {

    private Map<String,Limits> limitsList = new HashMap();
    private Map<String,Shift> shiftList = new HashMap();
    //date format show
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    public JLabel getIdtext() {
        return idtext;
    }
    public void setIdtext(JLabel idtext) {
        this.idtext = idtext;
    }
    
    public LimitsPanel() {
        initComponents();
    }
    
    public void LoadShiftsTables() {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/ShiftList.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             shiftList = (Map<String,Shift>)in.readObject();
             in.close();
             fileIn.close();;    
         }catch(IOException i){
             i.printStackTrace();
             return;
         }catch(ClassNotFoundException c){
             c.printStackTrace();
             return;
         }
    }

    //serializes data
    public void SaveData(){
        //saves employee limits in a data file by id key
        try{
            FileOutputStream fileOut = new  FileOutputStream("MainData/EmployeeLimits/"+idtext.getText()+".ser");
            ObjectOutputStream out = new  ObjectOutputStream(fileOut);
            out.writeObject(limitsList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data saved in MainData/EmployeeLimits/"+idtext.getText()+".ser");
            
        }catch(IOException i){
            i.printStackTrace();
        }
    }

    //load employee limits by id key
    public void LoadData() {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/EmployeeLimits/"+idtext.getText()+".ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             limitsList = (Map<String,Limits>)in.readObject();
             in.close();
             fileIn.close();;    
         }catch(IOException i){
             SaveData();
             createTable();
             i.printStackTrace();
             return;
         }catch(ClassNotFoundException c){
             c.printStackTrace();
             return;
         }
        //paint jtable after load data
            createTable();
    }
    
    public void createTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object rowData[] = new Object[3];
        model.getDataVector().removeAllElements();
        //load all limits in the list 
        limitsList.forEach((String,Limits) ->{
            rowData[2] = sdf.format(Limits.getStartTime().getTime());
            rowData[1] = Limits.getShiftsNames().toString();
            rowData[0] = Limits.getComment();
            //all limit data to a  new row
            model.addRow(rowData);
        });
        
        //makes table RTL
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(176, 255, 210));
        headerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        
        for(int i = 0;i<3;i++){
            jTable1.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
      }
        jTable1.getTableHeader().repaint();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        idtext = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonsave = new javax.swing.JButton();
        jButtondelete = new javax.swing.JButton();
        jButtonselect = new javax.swing.JButton();
        jTextFieldcomment = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldyear = new javax.swing.JTextField();
        jTextFieldmonth = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldday = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListshifts = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        idtext.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        idtext.setForeground(new java.awt.Color(204, 204, 204));
        idtext.setText("תעודת זהות");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("בחירת אילוצים למשתמש:");

        jButtonsave.setBackground(new java.awt.Color(255, 255, 255));
        jButtonsave.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonsave.setText("שמור אילוץ");
        jButtonsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsaveActionPerformed(evt);
            }
        });

        jButtondelete.setBackground(new java.awt.Color(255, 255, 255));
        jButtondelete.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtondelete.setText("מחק אילוץ");
        jButtondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondeleteActionPerformed(evt);
            }
        });

        jButtonselect.setBackground(new java.awt.Color(255, 255, 255));
        jButtonselect.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonselect.setText("בחר אילוץ לעריכה");
        jButtonselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonselectActionPerformed(evt);
            }
        });

        jTextFieldcomment.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextFieldcomment.setText("----");
        jTextFieldcomment.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("הערה:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("תאריך:");

        jTextFieldyear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextFieldmonth.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("/");

        jTextFieldday.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("/");

        jListshifts.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        LoadShiftsTables();
        DefaultListModel sModel = new DefaultListModel();
        for(Object key : shiftList.keySet())
        if(!sModel.contains(shiftList.get(key).getName()))
        sModel.addElement(shiftList.get(key).getName());
        jListshifts.setModel(sModel);
        jListshifts.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jScrollPane2.setViewportView(jListshifts);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("לבחירה של מספר משמרות החזק את הלחצן CTRL ולחץ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonsave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtondelete))
                    .addComponent(jButtonselect, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(idtext)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jTextFieldday, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTextFieldyear, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldcomment, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel3)))
                    .addComponent(jLabel6))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(idtext)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldcomment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonsave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jButtondelete, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonselect, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "הערה", "משמרות", "תאריך"
            }
        ));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtondeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondeleteActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "למחוק את האילוץ?", "מחיקת אילוץ", dialogButton);
        if(dialogResult == 0) {
            int selectedRow = jTable1.getSelectedRow();
            String keyVal = (String) jTable1.getModel().getValueAt(selectedRow, 2);
            limitsList.remove(keyVal);
            SaveData();
            LoadData();
            } 
    }//GEN-LAST:event_jButtondeleteActionPerformed

    private void jButtonsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsaveActionPerformed
        Limits newLimit = new Limits(jTextFieldcomment.getText(),Integer.parseInt(jTextFieldyear.getText()),
                Integer.parseInt(jTextFieldmonth.getText()),Integer.parseInt(jTextFieldday.getText()));
        newLimit.getShiftsNames().clear();
        for(int i = 0; i < jListshifts.getSelectedValuesList().size();i++)
            newLimit.getShiftsNames().add(jListshifts.getSelectedValuesList().get(i));
        String keyVal = sdf.format(newLimit.getStartTime().getTime());
        limitsList.put(keyVal,newLimit);
        SaveData();
        LoadData();
    }//GEN-LAST:event_jButtonsaveActionPerformed

    private void jButtonselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonselectActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        jTextFieldcomment.setText(jTable1.getModel().getValueAt(selectedRow, 0).toString());
        ArrayList splitDate = new ArrayList();
        for(String var: jTable1.getModel().getValueAt(selectedRow, 2).toString().split("/"))
            splitDate.add(var);
        jTextFieldday.setText(splitDate.get(0).toString());
        jTextFieldmonth.setText(splitDate.get(1).toString());
        jTextFieldyear.setText(splitDate.get(2).toString());
    }//GEN-LAST:event_jButtonselectActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DELETE){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "למחוק את האילוץ?", "מחיקת אילוץ", dialogButton);
            if(dialogResult == 0) {
            int selectedRow = jTable1.getSelectedRow();
            String keyVal = (String) jTable1.getModel().getValueAt(selectedRow, 2);
            limitsList.remove(keyVal);
            SaveData();
            LoadData();
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idtext;
    private javax.swing.JButton jButtondelete;
    private javax.swing.JButton jButtonsave;
    private javax.swing.JButton jButtonselect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JList<String> jListshifts;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldcomment;
    private javax.swing.JTextField jTextFieldday;
    private javax.swing.JTextField jTextFieldmonth;
    private javax.swing.JTextField jTextFieldyear;
    // End of variables declaration//GEN-END:variables

}
