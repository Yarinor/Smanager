
package Forms;

import Classes.*;
import com.sun.image.codec.jpeg.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.*;




public class ShiftsPanel extends javax.swing.JPanel {

    private Map<Integer,Employee> empList = new HashMap();
    private Map<String,Shift> shiftList = new HashMap();
    private Map<String,ShiftsBoard> shiftsBoardsList = new HashMap();
    private Map<String,Limits> limitsList = new HashMap();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
    private String shiftsBoardChosen = null;
    private String[] days = {"Saturday","Friday","Thursday","Wednesday","Tuesday","Monday","Sunday"};
    private int selectedRow = 0;
    private int selectedCol = 0;
    
    public ShiftsPanel() {
        initComponents();
        if(jComboBoxshiftslist.getItemCount() != 0){
        shiftsBoardChosen = (String )jComboBoxshiftslist.getSelectedItem();
        createTable();
        }
        //JOptionPane.showMessageDialog(null,"");
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
    
    public void LoadDataLimits(int id) {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/EmployeeLimits/"+id+".ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             limitsList = (Map<String,Limits>)in.readObject();
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
    
    public void LoadShiftsBoards() {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/ShiftsBoardsList.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             shiftsBoardsList = (Map<String,ShiftsBoard>)in.readObject();
             in.close();
             fileIn.close();;    
         }catch(IOException i){
             SaveShiftsBoards();
             i.printStackTrace();
             return;
         }catch(ClassNotFoundException c){
             c.printStackTrace();
             return;
         }
    }
    
    public void SaveShiftsBoards(){
        try{
            FileOutputStream fileOut = new  FileOutputStream("MainData/ShiftsBoardsList.ser");
            ObjectOutputStream out = new  ObjectOutputStream(fileOut);
            out.writeObject(shiftsBoardsList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data saved in MainData/ShiftsBoardsList.ser");
            
        }catch(IOException i){
            i.printStackTrace();
        }
    }
    
    public void createTable(){
        LoadShiftsBoards();
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(jTable1.getModel());
        jTable1.setRowSorter(sorter);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int shifts = shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[0]).getShiftList().size(); 
        Object rowData[][] = new Object[shifts][7];
        model.getDataVector().removeAllElements();
        
        for(int s = 0; s<shifts;s++){
            for(int d = 0; d<7;d++){
                String empNames = "";
                for (Integer key : shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().keySet()) {
                        empNames = empNames + shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().get(key).getFirstName()+
                            " "+shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[d]).getShiftList().get(s).getEmployeeList().get(key).getLastName()+"\n";
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
    
    public void saveTableAsPhoto(JTable table,JTableHeader header, String filename) {
       int w = Math.max(table.getWidth(), header.getWidth());
       int h = table.getHeight() + header.getHeight();
       BufferedImage myImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
       Graphics2D g2 = myImage.createGraphics();
       header.paint(g2);
       g2.translate(0, header.getHeight());
       table.paint(g2);
       g2.dispose();
       try {
         OutputStream out = new FileOutputStream(filename);
         JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
         encoder.encode(myImage);
         out.close();
         JOptionPane.showMessageDialog(null,"תמונה נשמרה בהצלחה!");
       } catch (Exception e) {
         JOptionPane.showMessageDialog(null,"תיקיה לא נמצאה!");  
         System.out.println(e); 
       }
     }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldyear = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldmonth = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldday = new javax.swing.JTextField();
        jComboBoxemployees = new javax.swing.JComboBox<>();
        jSpinnershiftstoadd = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonaddshiftsperday = new javax.swing.JButton();
        jButtonaddemp = new javax.swing.JButton();
        jButtondelemp = new javax.swing.JButton();
        jPaneledit = new javax.swing.JPanel();
        jTextFieldendmin = new javax.swing.JTextField();
        jTextFieldendhour = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldstarthour = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldstartmin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldtype = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldname = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButtonsavenew = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxshiftslist = new javax.swing.JComboBox<>();
        jButtonchoose = new javax.swing.JButton();
        jButtondelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxshifts = new javax.swing.JComboBox<>();
        jButtonaddshift = new javax.swing.JButton();
        jCheckBoxchangemanual = new javax.swing.JCheckBox();
        jButtondeleteshiftsperday = new javax.swing.JButton();
        jButtondelshift = new javax.swing.JButton();
        jButtonaddshift1 = new javax.swing.JButton();
        jButtonaddshift2 = new javax.swing.JButton();
        jButtonaddshift3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("תאריך יום ראשון במשמרת:");

        jTextFieldyear.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("/");

        jTextFieldmonth.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("/");

        jTextFieldday.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jComboBoxemployees.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        LoadEmployees();
        for(Object key : empList.keySet())
        if(empList.get(key).isActive())
        jComboBoxemployees.addItem(empList.get(key).getFirstName()+" "+empList.get(key).getLastName()+" - "+empList.get(key).getId());

        //jComboBoxemployees.setModel(new DefaultComboBoxModel(Employee.jobsList.values()));
        jComboBoxemployees.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        jSpinnershiftstoadd.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("רשימת עובדים:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("הוסף כמות משמרות ביום:");

        jButtonaddshiftsperday.setBackground(new java.awt.Color(255, 255, 255));
        jButtonaddshiftsperday.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonaddshiftsperday.setText("הוסף");
        jButtonaddshiftsperday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddshiftsperdayActionPerformed(evt);
            }
        });

        jButtonaddemp.setBackground(new java.awt.Color(255, 255, 255));
        jButtonaddemp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonaddemp.setText("הוסף");
        jButtonaddemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddempActionPerformed(evt);
            }
        });

        jButtondelemp.setBackground(new java.awt.Color(255, 255, 255));
        jButtondelemp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtondelemp.setText("מחק");
        jButtondelemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondelempActionPerformed(evt);
            }
        });

        jPaneledit.setBackground(new java.awt.Color(51, 51, 51));

        jTextFieldendmin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jTextFieldendhour.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText(":");

        jTextFieldstarthour.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText(":");

        jTextFieldstartmin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("שעת התחלה:");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("שעת סיום:");

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("דקות");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("שעות");

        jTextFieldtype.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jTextFieldtype.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("סוג המשמרת:");

        jTextFieldname.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jTextFieldname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("שם המשמרת:");

        javax.swing.GroupLayout jPaneleditLayout = new javax.swing.GroupLayout(jPaneledit);
        jPaneledit.setLayout(jPaneleditLayout);
        jPaneleditLayout.setHorizontalGroup(
            jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPaneleditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneleditLayout.createSequentialGroup()
                        .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPaneleditLayout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11))
                            .addGroup(jPaneleditLayout.createSequentialGroup()
                                .addComponent(jTextFieldstarthour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldstartmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPaneleditLayout.createSequentialGroup()
                        .addComponent(jTextFieldendhour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldendmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneleditLayout.createSequentialGroup()
                        .addComponent(jTextFieldtype, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14))
                    .addGroup(jPaneleditLayout.createSequentialGroup()
                        .addComponent(jTextFieldname, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPaneleditLayout.setVerticalGroup(
            jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPaneleditLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPaneleditLayout.createSequentialGroup()
                        .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldtype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPaneleditLayout.createSequentialGroup()
                        .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldstartmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldstarthour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPaneleditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldendmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldendhour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10))))
                .addContainerGap())
        );

        jButtonsavenew.setBackground(new java.awt.Color(255, 255, 255));
        jButtonsavenew.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonsavenew.setText("שמור");
        jButtonsavenew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsavenewActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("בחר לוח משמרות לעריכה:");

        LoadShiftsBoards();
        for(Object key : shiftsBoardsList.keySet()){
            jComboBoxshiftslist.addItem(sdf.format(shiftsBoardsList.get(key).getDate().getTime()));
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

        jButtondelete.setBackground(new java.awt.Color(255, 255, 255));
        jButtondelete.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtondelete.setText("מחק");
        jButtondelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondeleteActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("תבניות משמרות:");

        LoadShiftsTables();
        for(Object key : shiftList.keySet())
        jComboBoxshifts.addItem(shiftList.get(key).getName()+" "+shiftList.get(key).getPosition());
        jComboBoxshifts.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jComboBoxshifts.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        jButtonaddshift.setBackground(new java.awt.Color(255, 255, 255));
        jButtonaddshift.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonaddshift.setText("עדכן");
        jButtonaddshift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddshiftActionPerformed(evt);
            }
        });

        jCheckBoxchangemanual.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jCheckBoxchangemanual.setForeground(new java.awt.Color(204, 204, 204));
        jCheckBoxchangemanual.setText("עדכן ידני");
        jCheckBoxchangemanual.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        jCheckBoxchangemanual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxchangemanualActionPerformed(evt);
            }
        });
        jPaneledit.setVisible(false);

        jButtondeleteshiftsperday.setBackground(new java.awt.Color(255, 255, 255));
        jButtondeleteshiftsperday.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtondeleteshiftsperday.setText("הסר");
        jButtondeleteshiftsperday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondeleteshiftsperdayActionPerformed(evt);
            }
        });

        jButtondelshift.setBackground(new java.awt.Color(255, 255, 255));
        jButtondelshift.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtondelshift.setText("הסר");
        jButtondelshift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondelshiftActionPerformed(evt);
            }
        });

        jButtonaddshift1.setBackground(new java.awt.Color(255, 255, 255));
        jButtonaddshift1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonaddshift1.setText("עדכן שורה");
        jButtonaddshift1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddshift1ActionPerformed(evt);
            }
        });

        jButtonaddshift2.setBackground(new java.awt.Color(255, 255, 255));
        jButtonaddshift2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonaddshift2.setText("רענון נתונים");
        jButtonaddshift2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddshift2ActionPerformed(evt);
            }
        });

        jButtonaddshift3.setBackground(new java.awt.Color(255, 255, 255));
        jButtonaddshift3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jButtonaddshift3.setText("שמירה כתמונה");
        jButtonaddshift3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaddshift3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtondelshift)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonaddshift1))
                    .addComponent(jButtonaddshift2)
                    .addComponent(jButtonaddshift3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPaneledit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxchangemanual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonaddshift)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxshifts, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonsavenew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldday, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldmonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldyear, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtondelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonchoose)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxshiftslist, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtondelemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonaddemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxemployees, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtondeleteshiftsperday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonaddshiftsperday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinnershiftstoadd, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxshifts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jButtonaddshift, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBoxchangemanual)
                        .addComponent(jButtonaddshift1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtondelshift, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jComboBoxshiftslist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonchoose, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtondelete, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldmonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jTextFieldday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(jButtonsavenew, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jSpinnershiftstoadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonaddshiftsperday, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtondeleteshiftsperday, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxemployees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jButtonaddemp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtondelemp, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPaneledit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonaddshift2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonaddshift3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
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
        jTable1.setInheritsPopupMenu(true);
        jTable1.setRowHeight(80);
        jTable1.setRowMargin(2);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonsavenewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsavenewActionPerformed
        Calendar date =  new GregorianCalendar(Integer.parseInt(jTextFieldyear.getText()),Integer.parseInt(jTextFieldmonth.getText())-1,Integer.parseInt(jTextFieldday.getText()));
        boolean isExist = shiftsBoardsList.containsKey(sdf.format(date.getTime()));
        if(date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && !isExist){   
            shiftsBoardsList.put(sdf.format(date.getTime()),new ShiftsBoard(Integer.parseInt(jTextFieldyear.getText()),Integer.parseInt(jTextFieldmonth.getText())-1,Integer.parseInt(jTextFieldday.getText())));
            SaveShiftsBoards();
            jComboBoxshiftslist.addItem(sdf.format(date.getTime()));
            jComboBoxshiftslist.setSelectedItem(sdf.format(date.getTime()));
            shiftsBoardChosen = (String )jComboBoxshiftslist.getSelectedItem();
            createTable();
        }
        else if(isExist)
            JOptionPane.showMessageDialog(null,"התאריך שנבחר כבר קיים במערכת");
        else
            JOptionPane.showMessageDialog(null,"התאריך שנבחר אינו יום ראשון");
    }//GEN-LAST:event_jButtonsavenewActionPerformed

    private void jButtonchooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonchooseActionPerformed
        shiftsBoardChosen = (String )jComboBoxshiftslist.getSelectedItem();
        createTable();
    }//GEN-LAST:event_jButtonchooseActionPerformed

    private void jButtondeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondeleteActionPerformed
        shiftsBoardsList.remove(jComboBoxshiftslist.getSelectedItem());
        jComboBoxshiftslist.removeItem(jComboBoxshiftslist.getSelectedItem());
        SaveShiftsBoards();
    }//GEN-LAST:event_jButtondeleteActionPerformed

    private void jCheckBoxchangemanualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxchangemanualActionPerformed
        if(jCheckBoxchangemanual.isSelected() == true){
            jPaneledit.setVisible(true);
            if(jTable1.getSelectedRow() >= 0)
                selectedRow = jTable1.getSelectedRow();
            if(jTable1.getSelectedColumn() >= 0)
                selectedCol = jTable1.getSelectedColumn();
            Shift tempShift = new Shift();
            tempShift = shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().get(selectedRow);
            jTextFieldname.setText(tempShift.getName());
            jTextFieldtype.setText(tempShift.getPosition());
            jTextFieldstarthour.setText(String.valueOf(tempShift.getStartTime().get(Calendar.HOUR_OF_DAY)));
            jTextFieldstartmin.setText(String.valueOf(tempShift.getStartTime().get(Calendar.MINUTE)));
            jTextFieldendhour.setText(String.valueOf(tempShift.getEndTime().get(Calendar.HOUR_OF_DAY)));        
            jTextFieldendmin.setText(String.valueOf(tempShift.getEndTime().get(Calendar.MINUTE))); 
        }  
        else
            jPaneledit.setVisible(false);
    }//GEN-LAST:event_jCheckBoxchangemanualActionPerformed

    private void jButtonaddshiftsperdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddshiftsperdayActionPerformed
        for(int i = 0; i < ((Integer)jSpinnershiftstoadd.getValue());i++){
        shiftsBoardsList.get(shiftsBoardChosen).getDaysList().forEach((String,Day) ->{
            Day.getShiftList().add(new Shift());
        });
        }
        
        SaveShiftsBoards();
        createTable();
    }//GEN-LAST:event_jButtonaddshiftsperdayActionPerformed

    private void jButtondeleteshiftsperdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondeleteshiftsperdayActionPerformed
        for(int i = 0; i < ((Integer)jSpinnershiftstoadd.getValue());i++){
        shiftsBoardsList.get(shiftsBoardChosen).getDaysList().forEach((String,Day) ->{
            Day.getShiftList().remove(Day.getShiftList().size()-1);
        });
        }
        
        SaveShiftsBoards();
        createTable();
    }//GEN-LAST:event_jButtondeleteshiftsperdayActionPerformed

    private void jButtonaddshiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddshiftActionPerformed
        if(jCheckBoxchangemanual.isSelected() == true){
            if(jTable1.getSelectedRow() >= 0)
                selectedRow = jTable1.getSelectedRow();
            if(jTable1.getSelectedColumn() >= 0)
                selectedCol = jTable1.getSelectedColumn();
            Shift newShiftInfo = new Shift(jTextFieldname.getText(),jTextFieldtype.getText(),
                Integer.parseInt(jTextFieldstarthour.getText()),Integer.parseInt(jTextFieldstartmin.getText()),
                Integer.parseInt(jTextFieldendhour.getText()),Integer.parseInt(jTextFieldendmin.getText()));
            
            newShiftInfo.setEmployeeList(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().get(selectedRow).getEmployeeList());
            shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().set(selectedRow, newShiftInfo);
            jCheckBoxchangemanual.setSelected(false);
            jPaneledit.setVisible(false);
        }
        else{
            if(jTable1.getSelectedRow() >= 0)
                selectedRow = jTable1.getSelectedRow();
            if(jTable1.getSelectedColumn() >= 0)
                selectedCol = jTable1.getSelectedColumn();
            shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().set(selectedRow, shiftList.get(jComboBoxshifts.getSelectedItem()));
        }
        SaveShiftsBoards();
        createTable();
    }//GEN-LAST:event_jButtonaddshiftActionPerformed

    private void jButtonaddempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddempActionPerformed
        if(jTable1.getSelectedRow() >= 0)
            selectedRow = jTable1.getSelectedRow();
        if(jTable1.getSelectedColumn() >= 0)
            selectedCol = jTable1.getSelectedColumn();
        String empId = jComboBoxemployees.getSelectedItem().toString();
        String[] empId2 = empId.split(" - ");
        LoadDataLimits(Integer.parseInt(empId2[1])); 
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogAns = 0;
        if(limitsList.isEmpty()){
            shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().get(selectedRow).getEmployeeList().put(Integer.parseInt(empId2[1]), empList.get(Integer.parseInt(empId2[1])));
            SaveShiftsBoards();
            createTable();
        }
        else{
        for(Object key : limitsList.keySet()){
        if(sdf.format(limitsList.get(key).getStartTime().getTime()).equals(sdf.format(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getDate().getTime()))
                && limitsList.get(key).getShiftsNames().contains(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().get(selectedRow).getName())){
            dialogAns = JOptionPane.showConfirmDialog(this, "לעובד קיים אילוץ בתאריך ומשמרת זו, האם לשבץ את העובד במשמרת?", "מערכת אילוצים", dialogButton);
            break;
        }
        }
        for(int i = 0; i < shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().size();i++){
            if(shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().get(i).getEmployeeList().containsKey(Integer.parseInt(empId2[1]))){
                dialogAns = JOptionPane.showConfirmDialog(this, "לעובד קיימת משמרת נוספת בתאריך זה, האם לשבץ את העובד במשמרת?", "מערכת אילוצים", dialogButton);
                break;
            }      
        }
        if(dialogAns == 0){
                shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().get(selectedRow).getEmployeeList().put(Integer.parseInt(empId2[1]), empList.get(Integer.parseInt(empId2[1])));
                SaveShiftsBoards();
                createTable();
        }
        }
    }//GEN-LAST:event_jButtonaddempActionPerformed

    private void jButtondelempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondelempActionPerformed
        if(jTable1.getSelectedRow() >= 0)
            selectedRow = jTable1.getSelectedRow();
        if(jTable1.getSelectedColumn() >= 0)
            selectedCol = jTable1.getSelectedColumn();
        String empId = jComboBoxemployees.getSelectedItem().toString();
        String[] empId2 = empId.split(" - ");
        shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().get(selectedRow).getEmployeeList().remove(Integer.parseInt(empId2[1]));
        SaveShiftsBoards();
        createTable();
    }//GEN-LAST:event_jButtondelempActionPerformed

    private void jButtondelshiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondelshiftActionPerformed
            if(jTable1.getSelectedRow() >= 0)
                selectedRow = jTable1.getSelectedRow();
            if(jTable1.getSelectedColumn() >= 0)
                selectedCol = jTable1.getSelectedColumn();
            shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().set(selectedRow, new Shift());
            SaveShiftsBoards();
            createTable();
    }//GEN-LAST:event_jButtondelshiftActionPerformed

    private void jButtonaddshift1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddshift1ActionPerformed
        if(jCheckBoxchangemanual.isSelected() == true){
            if(jTable1.getSelectedRow() >= 0)
                selectedRow = jTable1.getSelectedRow();
            if(jTable1.getSelectedColumn() >= 0)
                selectedCol = jTable1.getSelectedColumn();
            Shift newShiftInfo = new Shift(jTextFieldname.getText(),jTextFieldtype.getText(),
                Integer.parseInt(jTextFieldstarthour.getText()),Integer.parseInt(jTextFieldstartmin.getText()),
                Integer.parseInt(jTextFieldendhour.getText()),Integer.parseInt(jTextFieldendmin.getText()));
            
            for(int i = 0; i< 7;i++){
                shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[i]).getShiftList().set(selectedRow, newShiftInfo);
                SaveShiftsBoards();
                createTable();
            }
            jCheckBoxchangemanual.setSelected(false);
            jPaneledit.setVisible(false);
        }
        else{
            if(jTable1.getSelectedRow() >= 0)
                selectedRow = jTable1.getSelectedRow();
            for(int i = 0; i< 7;i++){
                shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[i]).getShiftList().set(selectedRow, shiftList.get(jComboBoxshifts.getSelectedItem()));
                SaveShiftsBoards();
                createTable();
            }
        }
    }//GEN-LAST:event_jButtonaddshift1ActionPerformed

    private void jButtonaddshift2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddshift2ActionPerformed
        LoadEmployees();
        LoadShiftsTables();
        jComboBoxemployees.removeAllItems();
        jComboBoxshifts.removeAllItems();
        for(Object key : empList.keySet())
            if(empList.get(key).isActive())
                jComboBoxemployees.addItem(empList.get(key).getFirstName()+" "+empList.get(key).getLastName()+" - "+empList.get(key).getId());
        for(Object key : shiftList.keySet())
            jComboBoxshifts.addItem(shiftList.get(key).getName()+" "+shiftList.get(key).getPosition());

    }//GEN-LAST:event_jButtonaddshift2ActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_DELETE){
            if(jTable1.getSelectedRow() >= 0)
                selectedRow = jTable1.getSelectedRow();
            if(jTable1.getSelectedColumn() >= 0)
                selectedCol = jTable1.getSelectedColumn();
            shiftsBoardsList.get(shiftsBoardChosen).getDaysList().get(days[selectedCol]).getShiftList().set(selectedRow, new Shift());
            SaveShiftsBoards();
            createTable();
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jButtonaddshift3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaddshift3ActionPerformed
        JFileChooser address = new JFileChooser();
        address.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        address.showSaveDialog(this);
        String addressName = address.getSelectedFile().toString()+"\\";
        String name = "";
        if(shiftsBoardChosen != null){
        for(String newWord: shiftsBoardChosen.split("/"))
            name = name+newWord;
        //save file in the address chosen with the date name
        saveTableAsPhoto(jTable1,jTable1.getTableHeader(),addressName+name+".jpg");
        }
    }//GEN-LAST:event_jButtonaddshift3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonaddemp;
    private javax.swing.JButton jButtonaddshift;
    private javax.swing.JButton jButtonaddshift1;
    private javax.swing.JButton jButtonaddshift2;
    private javax.swing.JButton jButtonaddshift3;
    private javax.swing.JButton jButtonaddshiftsperday;
    private javax.swing.JButton jButtonchoose;
    private javax.swing.JButton jButtondelemp;
    private javax.swing.JButton jButtondelete;
    private javax.swing.JButton jButtondeleteshiftsperday;
    private javax.swing.JButton jButtondelshift;
    private javax.swing.JButton jButtonsavenew;
    private javax.swing.JCheckBox jCheckBoxchangemanual;
    private javax.swing.JComboBox<String> jComboBoxemployees;
    private javax.swing.JComboBox<String> jComboBoxshifts;
    private javax.swing.JComboBox<String> jComboBoxshiftslist;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPaneledit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnershiftstoadd;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldday;
    private javax.swing.JTextField jTextFieldendhour;
    private javax.swing.JTextField jTextFieldendmin;
    private javax.swing.JTextField jTextFieldmonth;
    private javax.swing.JTextField jTextFieldname;
    private javax.swing.JTextField jTextFieldstarthour;
    private javax.swing.JTextField jTextFieldstartmin;
    private javax.swing.JTextField jTextFieldtype;
    private javax.swing.JTextField jTextFieldyear;
    // End of variables declaration//GEN-END:variables
}
