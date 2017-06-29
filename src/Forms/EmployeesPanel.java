/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class EmployeesPanel extends javax.swing.JPanel {

    private Map<Integer,Employee> empList = new HashMap();
    private Map<String,Limits> limitsList = new HashMap();
    
    public EmployeesPanel() {
        initComponents();
        LoadData();//loads employees data to display in the jtable
    }
    
    
    public void createTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object rowData[] = new Object[7];
        model.getDataVector().removeAllElements();
        //load every emplyee in employee list
        empList.forEach((Integer,Employee) ->{
            rowData[6] = Employee.getFirstName();
            rowData[5] = Employee.getLastName();
            rowData[4] = Employee.getId();
            rowData[3] = Employee.getPhone();
            rowData[2] = Employee.getAddress();
            rowData[1] = Employee.isActive();
            rowData[0] = Employee.getJob();
            //add all data to a new row
            model.addRow(rowData);
        });
        
        //makes table RTL
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(176, 255, 210));
        headerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        
        for(int i = 0;i<7;i++){
            jTable1.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
      }
        jTable1.getTableHeader().repaint();
    }
     
    //serializes data
    public void SaveData(){
        //saves employee list (empList) in a data file
        try{
            FileOutputStream fileOut = new  FileOutputStream("MainData/EmpList.ser");
            ObjectOutputStream out = new  ObjectOutputStream(fileOut);
            out.writeObject(empList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data saved in MainData/EmpList.ser");
            
        }catch(IOException i){
            i.printStackTrace();
        }
    }

       //deserializes data, takes the data in the file and loads it into the EmployeeList
    public void LoadData() {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/EmpList.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             empList = (Map<Integer,Employee>)in.readObject();
             in.close();
             fileIn.close();;    
         }catch(IOException i){
             i.printStackTrace();
             return;
         }catch(ClassNotFoundException c){
             c.printStackTrace();
             return;
         }
        //after load all employees data insert data to jtable
            createTable();
    }
    
    //if employee was deleted, delete his limits file
    public void deleteLimits(int id){
        try{
    		File file = new File("MainData/EmployeeLimits/"+id+".ser");
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    //if new emplyee was added, make limits file
    public void SaveLimits(int id){
        File f = new File("MainData/EmployeeLimits/"+id+".ser");
	if(!f.exists()){
        try{
            FileOutputStream fileOut = new  FileOutputStream("MainData/EmployeeLimits/"+id+".ser");
            ObjectOutputStream out = new  ObjectOutputStream(fileOut);
            out.writeObject(limitsList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data saved in MainData/EmployeeLimits/"+id+".ser");
            
        }catch(IOException i){
            i.printStackTrace();
        }
    }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldphone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxjob = new javax.swing.JComboBox<>();
        jCheckBoxactive = new javax.swing.JCheckBox();
        jButtonsave = new javax.swing.JButton();
        jButtondelete = new javax.swing.JButton();
        jButtonselect = new javax.swing.JButton();
        jTextFieldlastname = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldaddress = new javax.swing.JTextField();
        jButtonclear = new javax.swing.JButton();
        jButtonlimits = new javax.swing.JButton();
        jButtonproperties = new javax.swing.JButton();

        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "תפקיד", "פעיל", "כתובת", "טלפון", "תעודת זהות", "שם משפחה", "שם פרטי"
            }
        ));
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("שם פרטי:");

        jTextFieldname.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jTextFieldname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("תעודת זהות:");

        jTextFieldid.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jTextFieldid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("טלפון:");

        jTextFieldphone.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jTextFieldphone.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("תפקיד:");

        jComboBoxjob.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxjob.setModel(new DefaultComboBoxModel(Employee.jobsList.values()));
        jComboBoxjob.setSelectedIndex(2);
        jComboBoxjob.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        jCheckBoxactive.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jCheckBoxactive.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxactive.setSelected(true);
        jCheckBoxactive.setText("פעיל \\ לא פעיל");

        jButtonsave.setBackground(new java.awt.Color(255, 255, 255));
        jButtonsave.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonsave.setText("שמור עובד");
        jButtonsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsaveActionPerformed(evt);
            }
        });

        jButtondelete.setBackground(new java.awt.Color(255, 255, 255));
        jButtondelete.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtondelete.setText("מחק עובד");
        jButtondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondeleteActionPerformed(evt);
            }
        });

        jButtonselect.setBackground(new java.awt.Color(255, 255, 255));
        jButtonselect.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonselect.setText("בחר עובד לעריכה");
        jButtonselect.setActionCommand("ערוך עובד");
        jButtonselect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonselectActionPerformed(evt);
            }
        });

        jTextFieldlastname.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jTextFieldlastname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("שם משפחה:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("כתובת:");

        jTextFieldaddress.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jTextFieldaddress.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jButtonclear.setBackground(new java.awt.Color(255, 255, 255));
        jButtonclear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonclear.setText("נקה נתונים");
        jButtonclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonclearActionPerformed(evt);
            }
        });

        jButtonlimits.setBackground(new java.awt.Color(255, 255, 255));
        jButtonlimits.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonlimits.setText("ניהול אילוצים");
        jButtonlimits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonlimitsActionPerformed(evt);
            }
        });

        jButtonproperties.setBackground(new java.awt.Color(255, 255, 255));
        jButtonproperties.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonproperties.setText("הגדרות משתמש");
        jButtonproperties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonpropertiesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonlimits)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonselect))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonproperties)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtondelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonclear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxjob, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxactive)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldlastname, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldname, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonsave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldphone, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldid, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxactive)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(jTextFieldlastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBoxjob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonselect)
                            .addComponent(jButtonlimits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jButtonclear, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(1, 14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextFieldid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(jTextFieldaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonsave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtondelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonproperties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsaveActionPerformed
        Employee newEmployee = new Employee(jTextFieldname.getText(),jTextFieldlastname.getText(),Integer.parseInt(jTextFieldid.getText()),jTextFieldphone.getText(),jTextFieldaddress.getText(),jCheckBoxactive.isSelected(),(Employee.jobsList)jComboBoxjob.getSelectedItem());
        empList.put(newEmployee.getId(), newEmployee);
        SaveLimits(newEmployee.getId());
        SaveData();
        LoadData();
    }//GEN-LAST:event_jButtonsaveActionPerformed

    private void jButtondeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondeleteActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "למחוק את העובד?", "מחיקת עובד", dialogButton);
        if(dialogResult == 0) {
            int selectedRow = jTable1.getSelectedRow();
            int keyVal = (int)jTable1.getModel().getValueAt(selectedRow, 4);
            empList.remove(keyVal);
            deleteLimits(keyVal);
            SaveData();
            LoadData();
            }
    }//GEN-LAST:event_jButtondeleteActionPerformed

    private void jButtonselectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonselectActionPerformed
            int selectedRow = jTable1.getSelectedRow();
            jTextFieldname.setText(jTable1.getModel().getValueAt(selectedRow, 6).toString());
            jTextFieldlastname.setText(jTable1.getModel().getValueAt(selectedRow, 5).toString());
            jTextFieldid.setText(jTable1.getModel().getValueAt(selectedRow, 4).toString());
            jTextFieldphone.setText(jTable1.getModel().getValueAt(selectedRow, 3).toString());
            jTextFieldaddress.setText(jTable1.getModel().getValueAt(selectedRow, 2).toString());
            jCheckBoxactive.setSelected((boolean)jTable1.getModel().getValueAt(selectedRow, 1));
            jComboBoxjob.setSelectedItem(jTable1.getModel().getValueAt(selectedRow, 0));
    }//GEN-LAST:event_jButtonselectActionPerformed

    private void jButtonclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonclearActionPerformed
            jTextFieldname.setText("");
            jTextFieldlastname.setText("");
            jTextFieldid.setText("");
            jTextFieldphone.setText("");
            jTextFieldaddress.setText("");
            jCheckBoxactive.setSelected(true);
            jComboBoxjob.setSelectedItem(2);
    }//GEN-LAST:event_jButtonclearActionPerformed

    private void jButtonlimitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonlimitsActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        String keyVal = jTable1.getModel().getValueAt(selectedRow, 4).toString();
        
        LimitsJFrame Limits = new LimitsJFrame();
        Limits.setLocationRelativeTo(null);
        Limits.getLimitsPanel1().getIdtext().setText(keyVal);
        Limits.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Limits.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage());
        Limits.getLimitsPanel1().LoadData();
        Limits.setVisible(true);
    }//GEN-LAST:event_jButtonlimitsActionPerformed

    private void jButtonpropertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonpropertiesActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        String keyVal = jTable1.getModel().getValueAt(selectedRow, 4).toString();
        
        Properties propertiesForm = new Properties();
        propertiesForm.setLocationRelativeTo(null);
        propertiesForm.getIdtext().setText(keyVal);
        propertiesForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        propertiesForm.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage());
        propertiesForm.setVisible(true);
    }//GEN-LAST:event_jButtonpropertiesActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DELETE){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "למחוק את העובד?", "מחיקת עובד", dialogButton);
            if(dialogResult == 0) {
            int selectedRow = jTable1.getSelectedRow();
            int keyVal = (int)jTable1.getModel().getValueAt(selectedRow, 4);
            empList.remove(keyVal);
            deleteLimits(keyVal);
            SaveData();
            LoadData();
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonclear;
    private javax.swing.JButton jButtondelete;
    private javax.swing.JButton jButtonlimits;
    private javax.swing.JButton jButtonproperties;
    private javax.swing.JButton jButtonsave;
    private javax.swing.JButton jButtonselect;
    private javax.swing.JCheckBox jCheckBoxactive;
    private javax.swing.JComboBox<String> jComboBoxjob;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldaddress;
    private javax.swing.JTextField jTextFieldid;
    private javax.swing.JTextField jTextFieldlastname;
    private javax.swing.JTextField jTextFieldname;
    private javax.swing.JTextField jTextFieldphone;
    // End of variables declaration//GEN-END:variables
}
