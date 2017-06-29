
package Forms;

import Classes.*;
import java.awt.Color;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;


public class CalendarLimits extends javax.swing.JFrame {
    private int monthPick = 0;
    //todays date as default
    private Calendar startTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH)+monthPick,1);
    private Map<Integer,Employee> empList = new HashMap();
    private Map<Integer,Map<String,Limits>> limitsLists = new HashMap(); //all employees limits lists
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdfMonth = new SimpleDateFormat("MM/yyyy");
    
    public CalendarLimits() {
        initComponents();
        //load all employees list and all employees limits lists
        LoadEmployees();
        LoadEmployeesLimits();
        //show todays date when first enter
        jLabeldate.setText(sdfMonth.format(startTime.getTime()));
        createTable();
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
    }
    
    //load all data limits of all employees
    public void LoadEmployeesLimits() {
        for(Integer key : empList.keySet())
            LoadDataLimits(empList.get(key).getId());
    }
    
    public void LoadDataLimits(int id) {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/EmployeeLimits/"+id+".ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             Map<String,Limits> limitsTemp = new HashMap();
             limitsTemp = (Map<String,Limits>)in.readObject();
             //after load data of employee, put the data in the limits list with employee id as key
             limitsLists.put(id,limitsTemp);
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
        Object rowData[] = new Object[7];
        model.getDataVector().removeAllElements();
        //first put 0 in all row cells
        rowData[0] = 0;
        rowData[1] = 0;
        rowData[2] = 0;
        rowData[3] = 0;
        rowData[4] = 0;
        rowData[5] = 0;
        rowData[6] = 0;
        //date initialized
        int date = 1;
        //limits string initialized
        String limit = "";
        //start from first day of the month
        int firstDay = startTime.get(Calendar.DAY_OF_WEEK)-1;
        //first loop is the numbers of rows
        for(int i = 0 - firstDay; i<startTime.getInstance().getActualMaximum(Calendar.DATE);){
            //second loop insert limits data to every cell by column order
            for(int d = firstDay; d<7;d++){
                //if date is in the month search for limits and add it, else type 0
                if(startTime.getActualMaximum(Calendar.DATE) >= date){
                    //last loop search for limits in this date in every limits list
                    for(Integer key : limitsLists.keySet()){
                    String newDate = sdf.format(new GregorianCalendar(startTime.get(Calendar.YEAR),startTime.get(Calendar.MONTH),date).getTime());
                    //if limit found, add it to limit string
                    if(limitsLists.get(key).containsKey(newDate))
                        limit = limit + empList.get(key).getFirstName()+" "+empList.get(key).getLastName()+"\n"+
                                limitsLists.get(key).get(newDate).getShiftsNames().toString()+"\n";
                    }
                    //add all founded limits data to row of date column
                    rowData[d] = date+"\n"+limit;
                }
                else
                    rowData[d] = 0;
                //go next date
                date++;
                //limits string initialized
                limit = "";                
            }            
            //reset firstDay, next week start from 0
            firstDay = 0;
            //add row data to jtable
            model.addRow(rowData);
            //jump 7 as the days we finished
            i+=7;            
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
        jTable1.getTableHeader().repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabeldate = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("טבלת אילוצים");

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ראשון", "שני", "שלישי", "רביעי", "חמישי", "שישי", "שבת"
            }
        ));
        jTable1.setEnabled(false);
        jTable1.setRowHeight(200);
        jTable1.setRowMargin(2);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(33, 33, 33));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton1.setText("חודש קודם");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButton2.setText("חודש הבא");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("טבלת אילוצים לתאריך:");

        jLabeldate.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabeldate.setForeground(new java.awt.Color(204, 204, 204));
        jLabeldate.setText("תאריך");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jLabeldate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabeldate))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //current date minus 1 month
        monthPick--;
        startTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH)+monthPick,1);
        jLabeldate.setText(sdfMonth.format(startTime.getTime()));
        createTable();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //current date plus 1 month
        monthPick++;
        startTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH)+monthPick,1);
        jLabeldate.setText(sdfMonth.format(startTime.getTime()));
        createTable();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CalendarLimits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalendarLimits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalendarLimits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalendarLimits.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CalendarLimits form = new CalendarLimits();
                form.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage());
                form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                form.pack();
                form.setLocationRelativeTo(null);
                form.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabeldate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
