/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Forms;

import Classes.*;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class MainPanel extends javax.swing.JPanel {

    private Map<Integer,Employee> empList = new HashMap();
    private Map<String,Shift> shiftList = new HashMap();
    private Map<String,ShiftsBoard> shiftsBoardsList = new HashMap();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
    private String shiftsBoardChosen = null;
    
    public MainPanel() {
        initComponents();
        LoadEmployees();
        if(jComboBoxshiftslist.getItemCount() != 0){
            shiftsBoardChosen = (String )jComboBoxshiftslist.getSelectedItem();
            createTable();
        }
    }
    
    public void LoadEmployees() {
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
        createTableEmpPhones();
    }
    
    public void LoadShiftsBoards() {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/ShiftsBoardsList.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             shiftsBoardsList = (Map<String,ShiftsBoard>)in.readObject();
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

    
    public void createTable(){
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String[] days = {"Saturday","Friday","Thursday","Wednesday","Tuesday","Monday","Sunday"};
        int shifts = shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[0]).getShiftList().size(); 
        Object rowData[][] = new Object[shifts][7];
        model.getDataVector().removeAllElements();
        
        for(int s = 0; s<shifts;s++){
            for(int d = 0; d<7;d++){
                String empNames = "";
                for (Integer key : shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().keySet()) {
                    if(empNames.equals(""))
                        empNames = shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().get(key).getFirstName()+
                            " "+shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().get(key).getLastName();
                    else
                        empNames = empNames +"\n"+ shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().get(key).getFirstName()+
                            " "+shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().get(key).getLastName();
                }  
                rowData[s][d] = shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getName()+" "+
                        shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getPosition()+
                        "\n"+sdfTime.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getStartTime().getTime())+" - "+
                        sdfTime.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEndTime().getTime())+"\n"+empNames;
            }
            model.addRow(rowData[s]);
        }
        jTable1.getColumnModel().setColumnMargin(2);
        
        //makes table RTL
        MultiLineCellRenderer renderer = new MultiLineCellRenderer();    
        jTable1.setDefaultRenderer(Object.class, renderer);
        
        //makes table header RTL and paint BG
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(176, 255, 210));
        headerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        for(int i = 0;i<7;i++){
            jTable1.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        jTable1.getColumnModel().getColumn(6).setHeaderValue("ראשון "+sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get("Sunday").getDate().getTime()));
        jTable1.getColumnModel().getColumn(5).setHeaderValue("שני "+sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get("Monday").getDate().getTime()));
        jTable1.getColumnModel().getColumn(4).setHeaderValue("שלישי "+sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get("Tuesday").getDate().getTime()));
        jTable1.getColumnModel().getColumn(3).setHeaderValue("רביעי "+sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get("Wednesday").getDate().getTime()));
        jTable1.getColumnModel().getColumn(2).setHeaderValue("חמישי "+sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get("Thursday").getDate().getTime()));
        jTable1.getColumnModel().getColumn(1).setHeaderValue("שישי "+sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get("Friday").getDate().getTime()));
        jTable1.getColumnModel().getColumn(0).setHeaderValue("שבת "+sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get("Saturday").getDate().getTime()));        
        jTable1.getTableHeader().repaint();
    }
    
    public void createTableEmpPhones(){
          TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable2.getModel());
        jTable2.setRowSorter(sorter);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        Object rowData[] = new Object[2];
        model.getDataVector().removeAllElements();
        empList.forEach((Integer,Employee) ->{
            if(Employee.isActive()){
                rowData[1] = Employee.getFirstName()+" "+Employee.getLastName();
                rowData[0] = Employee.getPhone();
                model.addRow(rowData);
            }
        });
        
        //makes table RTL
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(176, 255, 210));
        headerRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        
        for(int i = 0;i<2;i++){
            jTable2.getColumnModel().getColumn(i).setCellRenderer(rightRenderer);
            jTable2.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
      }
        jTable2.getTableHeader().repaint();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxshiftslist = new javax.swing.JComboBox<>();
        jButtonchoose = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButtonrefresh = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(229, 229, 229));
        jLabel15.setText("בחר לוח משמרות לצפיה:");

        LoadShiftsBoards();
        if(!shiftsBoardsList.isEmpty()){
            for(Object key : shiftsBoardsList.keySet()){
                jComboBoxshiftslist.addItem(sdf.format(shiftsBoardsList.get(key).getDate().getTime()));
            }
        }
        jComboBoxshiftslist.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxshiftslist.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        jButtonchoose.setBackground(new java.awt.Color(255, 255, 255));
        jButtonchoose.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonchoose.setText("בחר");
        jButtonchoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonchooseActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(242, 242, 242));
        jLabel16.setText("ספר טלפונים");

        jButtonrefresh.setBackground(new java.awt.Color(255, 255, 255));
        jButtonrefresh.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonrefresh.setText("רענן לוח");
        jButtonrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonrefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonrefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonchoose)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxshiftslist, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel15)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBoxshiftslist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonchoose, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jButtonrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "יום שבת", "יום שישי", "יום חמישי", "יום רביעי", "יום שלישי", "יום שני", "יום ראשון"
            }
        ));
        jTable1.setEnabled(false);
        jTable1.setInheritsPopupMenu(true);
        jTable1.setRowHeight(80);
        jTable1.setRowMargin(2);
        jScrollPane1.setViewportView(jTable1);

        jTable2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "טלפון", "שם העובד"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 884, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 364, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonchooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonchooseActionPerformed
        shiftsBoardChosen = (String )jComboBoxshiftslist.getSelectedItem();
        createTable();
    }//GEN-LAST:event_jButtonchooseActionPerformed

    private void jButtonrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonrefreshActionPerformed
        LoadShiftsBoards();
        jComboBoxshiftslist.removeAllItems();
        for(Object key : shiftsBoardsList.keySet()){
            jComboBoxshiftslist.addItem(sdf.format(shiftsBoardsList.get(key).getDate().getTime()));
        }
        createTable();
    }//GEN-LAST:event_jButtonrefreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonchoose;
    private javax.swing.JButton jButtonrefresh;
    private javax.swing.JComboBox<String> jComboBoxshiftslist;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
