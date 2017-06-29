
package Forms;


import Classes.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.Border;


public class SMLogIn extends javax.swing.JFrame {
    private int secure = 0;
    
    private Map<Integer,Employee> empList = new HashMap();
    
    public SMLogIn() {
        initComponents();
        LoadData(); //load all employees data
    }
    
    public void SaveData(){
        //saves employee list (empList) in a data file
        try{
            FileOutputStream fileOut = new  FileOutputStream("MainData/EmpList.ser");
            ObjectOutputStream out = new  ObjectOutputStream(fileOut);
            out.writeObject(empList);
            out.close();
            fileOut.close();
            System.out.println("Serialized data saved in MainData/EmpList.ser");
            LoadData();
            
        }catch(IOException i){
            i.printStackTrace();
        }
    }
    
    public void LoadData() {
        try{
             FileInputStream fileIn = new FileInputStream("MainData/EmpList.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             empList = (Map<Integer,Employee>)in.readObject();
             in.close();
             fileIn.close();;    
         }catch(IOException i){
             empList.put(1, new Employee("מנהל","ראשי",1,"050-0000000","ריק",false,Employee.jobsList.Admin));
             SaveData();
             i.printStackTrace();
             return;
         }catch(ClassNotFoundException c){
             c.printStackTrace();
             return;
         }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shift Manager Log in");
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(25, 25, 25));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/logo.png"))); // NOI18N

        password.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        password.setText("1234");
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        username.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        username.setText("username");
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("שם משתמש:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("סיסמה:");

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("סגור");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setText("הכנס");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("התחבר למערכת בעזרת שם משתמש וסיסמה");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("פותח על ידי תמיר ברני וירין אורנסקי");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Logo)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(54, 54, 54))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addGap(65, 65, 65))
                    .addComponent(jLabel3))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String passCheck = new String(password.getPassword());
        int i = 0;
        //check user and password
        for(Object key : empList.keySet()){
                if((username.getText().equals(Integer.toString(empList.get(key).getId())) && passCheck.equals(empList.get(key).getPassWord()))){
                SManager form = new SManager();
                form.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage()); 
                form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                form.setSize(1000, 800);
                form.setExtendedState(Frame.MAXIMIZED_BOTH);
                form.pack();
                form.getUsername().setText("שלום "+empList.get(key).getFirstName()+" "+empList.get(key).getLastName());
                form.getUserid().setText(Integer.toString(empList.get(key).getId()));
                form.setName(empList.get(key).getFirstName()+" "+empList.get(key).getLastName());
                form.setLocationRelativeTo(null);
                this.dispose();
                if(empList.get(key).getJob().equals(Employee.jobsList.Worker)){
                    form.getjPanelemppanel().setVisible(false);
                    form.getjPanelshifts().setVisible(false);
                    form.getjPanelshiftstable().setVisible(false);
                    form.getjPanellimitsTable().setVisible(false);
                }
                else if(empList.get(key).getJob().equals(Employee.jobsList.Manager))
                    form.getjPanelemppanel().setVisible(false);
                form.setVisible(true);
                i++;
        }
        }
        //tell the user id or password is wrong
        if(i==0){
            JOptionPane.showMessageDialog(this,"שם משתמש או סיסמה לא נכונים");
            //after 5 times user kick from session
            secure++;
            if(secure > 4){
                JOptionPane.showMessageDialog(this,"הכנס מחדש למערכת ונסה שוב");
                this.dispose();
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String passCheck = new String(password.getPassword());
        int i = 0;
        //check user and password
        for(Object key : empList.keySet()){
                if((username.getText().equals(Integer.toString(empList.get(key).getId())) && passCheck.equals(empList.get(key).getPassWord()))){
                SManager form = new SManager();
                form.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage()); 
                form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                form.setSize(1000, 800);
                form.setExtendedState(Frame.MAXIMIZED_BOTH);
                form.pack();
                form.getUsername().setText("שלום "+empList.get(key).getFirstName()+" "+empList.get(key).getLastName());
                form.getUserid().setText(Integer.toString(empList.get(key).getId()));
                form.setName(empList.get(key).getFirstName()+" "+empList.get(key).getLastName());
                form.setLocationRelativeTo(null);
                this.dispose();
                if(empList.get(key).getJob().equals(Employee.jobsList.Worker)){
                    form.getjPanelemppanel().setVisible(false);
                    form.getjPanelshifts().setVisible(false);
                    form.getjPanelshiftstable().setVisible(false);
                    form.getjPanellimitsTable().setVisible(false);
                }
                else if(empList.get(key).getJob().equals(Employee.jobsList.Manager))
                    form.getjPanelemppanel().setVisible(false);
                form.setVisible(true);
                i++;
        }
        }
        //tell the user id or password is wrong
        if(i==0){
            JOptionPane.showMessageDialog(this,"שם משתמש או סיסמה לא נכונים");
            //after 5 times user kick from session
            secure++;
            if(secure > 4){
                JOptionPane.showMessageDialog(this,"הכנס מחדש למערכת ונסה שוב");
                this.dispose();
            }
        }
        }
    }//GEN-LAST:event_usernameKeyPressed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            usernameKeyPressed(evt);
        }
    }//GEN-LAST:event_passwordKeyPressed

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
            java.util.logging.Logger.getLogger(SMLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SMLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SMLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SMLogIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SMLogIn form = new SMLogIn();
                form.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage()); 
                form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                form.setResizable(false);
                form.pack();
                form.setLocationRelativeTo(null);
                form.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
