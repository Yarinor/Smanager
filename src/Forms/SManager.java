
package Forms;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.util.*;
import javax.swing.*;
import javax.swing.WindowConstants;


public class SManager extends javax.swing.JFrame {
    
    //new panel forms
    private  EmployeesPanel EP = new EmployeesPanel();
    private  ShiftsTable ST = new  ShiftsTable();
    private  MainPanel MP = new  MainPanel();
    private  LimitsPanel LP = new  LimitsPanel();
    private  ShiftsPanel SP = new  ShiftsPanel();
    //form panel name
    private final static String shiftTableTextIdentifier = "ShiftTable";
    private final static String EmployeesPanelTextIdentifier = "EmployeesPanel";
    private final static String MainPanelTextIdentifier = "MainPanel";
    private final static String LimitsPanelTextIdentifier = "LimitsPanel";
    private final static String ShiftsPanelTextIdentifier = "ShiftsPanel";
    
    public JLabel getUsername() {
        return username;
    }
    public void setUsername(JLabel username) {
        this.username = username;
    }
    public JLabel getUserid() {
        return userid;
    }
    public void setUserid(JLabel userid) {
        this.userid = userid;
    }
    public JPanel getjPanelemppanel() {
        return jPanelemppanel;
    }
    public void setjPanelemppanel(JPanel jPanelemppanel) {
        this.jPanelemppanel = jPanelemppanel;
    }
    public JPanel getjPanelshifts() {
        return jPanelshifts;
    }
    public void setjPanelshifts(JPanel jPanelshifts) {
        this.jPanelshifts = jPanelshifts;
    }
    public JPanel getjPanelshiftstable() {
        return jPanelshiftstable;
    }
    public void setjPanelshiftstable(JPanel jPanelshiftstable) {
        this.jPanelshiftstable = jPanelshiftstable;
    }
    public JPanel getjPanellimitsTable() {
        return jPanellimitsTable;
    }
    public void setjPanellimitsTable(JPanel jPanellimitsTable) {
        this.jPanellimitsTable = jPanellimitsTable;
    }
    
    public SManager() {
        initComponents();
        //set new card layout and start with main panel as default page
        LayoutManager CardLayout = new CardLayout() ;
        this.Container.setLayout(CardLayout);
        Container.add(MainPanelTextIdentifier,MP);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Logo = new javax.swing.JLabel();
        properties = new javax.swing.JLabel();
        logoff = new javax.swing.JLabel();
        homeButton = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        userid = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanellimits = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanelshifts = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanelshiftstable = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanelemppanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanellimitsTable = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Container = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shift Manager");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jScrollPane1.setBorder(null);

        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(35, 35, 35));
        jPanel1.setPreferredSize(new java.awt.Dimension(1088, 63));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/logo.png"))); // NOI18N

        properties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/emp2.png"))); // NOI18N
        properties.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                propertiesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                propertiesMouseReleased(evt);
            }
        });

        logoff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/emp3.png"))); // NOI18N
        logoff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logoffMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logoffMouseReleased(evt);
            }
        });

        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/home.png"))); // NOI18N
        homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                homeButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                homeButtonMouseReleased(evt);
            }
        });

        username.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("שלום למשתמש");

        userid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        userid.setForeground(new java.awt.Color(204, 204, 204));
        userid.setText("תעודת זהות");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addComponent(userid)
                .addGap(18, 18, 18)
                .addComponent(username)
                .addGap(18, 18, 18)
                .addComponent(logoff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(properties)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(homeButton)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Logo)
                .addGap(0, 3, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(username)
                        .addComponent(userid))
                    .addComponent(logoff)
                    .addComponent(properties)
                    .addComponent(homeButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setForeground(new java.awt.Color(0, 102, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(210, 562));

        jPanellimits.setBackground(new java.awt.Color(30, 30, 30));
        jPanellimits.setForeground(new java.awt.Color(51, 51, 51));
        jPanellimits.setPreferredSize(new java.awt.Dimension(210, 31));
        jPanellimits.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanellimitsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanellimitsMouseReleased(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ניהול אילוצים");

        javax.swing.GroupLayout jPanellimitsLayout = new javax.swing.GroupLayout(jPanellimits);
        jPanellimits.setLayout(jPanellimitsLayout);
        jPanellimitsLayout.setHorizontalGroup(
            jPanellimitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanellimitsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(60, 60, 60))
        );
        jPanellimitsLayout.setVerticalGroup(
            jPanellimitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanellimitsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(147, 202, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 9, Short.MAX_VALUE)
        );

        jPanelshifts.setBackground(new java.awt.Color(30, 30, 30));
        jPanelshifts.setForeground(new java.awt.Color(51, 51, 51));
        jPanelshifts.setPreferredSize(new java.awt.Dimension(210, 31));
        jPanelshifts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelshiftsMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelshiftsMouseReleased(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ניהול משמרות");

        javax.swing.GroupLayout jPanelshiftsLayout = new javax.swing.GroupLayout(jPanelshifts);
        jPanelshifts.setLayout(jPanelshiftsLayout);
        jPanelshiftsLayout.setHorizontalGroup(
            jPanelshiftsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelshiftsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(57, 57, 57))
        );
        jPanelshiftsLayout.setVerticalGroup(
            jPanelshiftsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelshiftsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelshiftstable.setBackground(new java.awt.Color(30, 30, 30));
        jPanelshiftstable.setForeground(new java.awt.Color(51, 51, 51));
        jPanelshiftstable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelshiftstableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelshiftstableMouseReleased(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("תבנית משמרות");

        javax.swing.GroupLayout jPanelshiftstableLayout = new javax.swing.GroupLayout(jPanelshiftstable);
        jPanelshiftstable.setLayout(jPanelshiftstableLayout);
        jPanelshiftstableLayout.setHorizontalGroup(
            jPanelshiftstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelshiftstableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(50, 50, 50))
        );
        jPanelshiftstableLayout.setVerticalGroup(
            jPanelshiftstableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelshiftstableLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanelemppanel.setBackground(new java.awt.Color(30, 30, 30));
        jPanelemppanel.setForeground(new java.awt.Color(51, 51, 51));
        jPanelemppanel.setPreferredSize(new java.awt.Dimension(210, 31));
        jPanelemppanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelemppanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanelemppanelMouseReleased(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ניהול עובדים");

        javax.swing.GroupLayout jPanelemppanelLayout = new javax.swing.GroupLayout(jPanelemppanel);
        jPanelemppanel.setLayout(jPanelemppanelLayout);
        jPanelemppanelLayout.setHorizontalGroup(
            jPanelemppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelemppanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(62, 62, 62))
        );
        jPanelemppanelLayout.setVerticalGroup(
            jPanelemppanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelemppanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanellimitsTable.setBackground(new java.awt.Color(30, 30, 30));
        jPanellimitsTable.setForeground(new java.awt.Color(51, 51, 51));
        jPanellimitsTable.setPreferredSize(new java.awt.Dimension(210, 31));
        jPanellimitsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanellimitsTableMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanellimitsTableMouseReleased(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("טבלת אילוצים");

        javax.swing.GroupLayout jPanellimitsTableLayout = new javax.swing.GroupLayout(jPanellimitsTable);
        jPanellimitsTable.setLayout(jPanellimitsTableLayout);
        jPanellimitsTableLayout.setHorizontalGroup(
            jPanellimitsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanellimitsTableLayout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(62, 62, 62))
        );
        jPanellimitsTableLayout.setVerticalGroup(
            jPanellimitsTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanellimitsTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/java.jpg"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("פותח על ידי תמיר ברני וירין אורנסקי");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/net.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanellimits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelshifts, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelshiftstable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelemppanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanellimitsTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanellimits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanelshifts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanelshiftstable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanellimitsTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanelemppanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        Container.setMaximumSize(new java.awt.Dimension(2147483647, 32767));

        javax.swing.GroupLayout ContainerLayout = new javax.swing.GroupLayout(Container);
        Container.setLayout(ContainerLayout);
        ContainerLayout.setHorizontalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ContainerLayout.setVerticalGroup(
            ContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1137, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelemppanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelemppanelMouseReleased
        jPanelemppanel.setBackground(new java.awt.Color(30, 30, 30));
        Container.getComponent(0).setVisible(false);
        Container.add(EmployeesPanelTextIdentifier,EP);
        CardLayout cl = (CardLayout)(Container.getLayout());
        cl.show(Container,EmployeesPanelTextIdentifier);
    }//GEN-LAST:event_jPanelemppanelMouseReleased

    private void jPanelemppanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelemppanelMousePressed
        jPanelemppanel.setBackground(Color.DARK_GRAY);
        
    }//GEN-LAST:event_jPanelemppanelMousePressed

    private void jPanelshiftstableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelshiftstableMouseReleased
        jPanelshiftstable.setBackground(new java.awt.Color(30, 30, 30));
        Container.getComponent(0).setVisible(false);
        Container.add(shiftTableTextIdentifier,ST);
        CardLayout cl = (CardLayout)(Container.getLayout());
        cl.show(Container,shiftTableTextIdentifier);
    }//GEN-LAST:event_jPanelshiftstableMouseReleased

    private void jPanelshiftstableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelshiftstableMousePressed
        jPanelshiftstable.setBackground(Color.DARK_GRAY); 
    }//GEN-LAST:event_jPanelshiftstableMousePressed

    private void jPanelshiftsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelshiftsMouseReleased
        jPanelshifts.setBackground(new java.awt.Color(30, 30, 30));
        Container.getComponent(0).setVisible(false);
        Container.add(ShiftsPanelTextIdentifier,SP);
        CardLayout cl = (CardLayout)(Container.getLayout());
        cl.show(Container,ShiftsPanelTextIdentifier);
    }//GEN-LAST:event_jPanelshiftsMouseReleased

    private void jPanelshiftsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelshiftsMousePressed
        jPanelshifts.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_jPanelshiftsMousePressed

    private void jPanellimitsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanellimitsMouseReleased
        jPanellimits.setBackground(new java.awt.Color(30, 30, 30));
        Container.getComponent(0).setVisible(false);
        LP.getIdtext().setText(userid.getText());
        LP.LoadData();
        Container.add(LimitsPanelTextIdentifier,LP);
        CardLayout cl = (CardLayout)(Container.getLayout());
        cl.show(Container,LimitsPanelTextIdentifier);
    }//GEN-LAST:event_jPanellimitsMouseReleased

    private void jPanellimitsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanellimitsMousePressed
        jPanellimits.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_jPanellimitsMousePressed

    private void homeButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMouseReleased
        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/home.png")));
        Container.getComponent(0).setVisible(false);
        Container.add(MainPanelTextIdentifier,MP);
        CardLayout cl = (CardLayout)(Container.getLayout());
        cl.show(Container,MainPanelTextIdentifier);
    }//GEN-LAST:event_homeButtonMouseReleased

    private void homeButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMousePressed
        homeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/homec.png")));
    }//GEN-LAST:event_homeButtonMousePressed

    private void logoffMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoffMouseReleased
        logoff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/emp3.png")));
        this.dispose();
        SMLogIn Form = new SMLogIn();
        Form.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage());
        Form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Form.setResizable(false);
        Form.pack();
        Form.setLocationRelativeTo(null);
        Form.setVisible(true);
    }//GEN-LAST:event_logoffMouseReleased

    private void logoffMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoffMousePressed
        logoff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/emp3c.png")));
    }//GEN-LAST:event_logoffMousePressed

    private void propertiesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propertiesMouseReleased
        properties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/emp2.png")));
        Properties propertiesForm = new Properties();
        propertiesForm.setLocationRelativeTo(null);
        propertiesForm.getIdtext().setText(userid.getText());
        propertiesForm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        propertiesForm.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage());
        propertiesForm.setVisible(true);
    }//GEN-LAST:event_propertiesMouseReleased

    private void propertiesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_propertiesMousePressed
        properties.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Forms/img/emp2c.png")));
    }//GEN-LAST:event_propertiesMousePressed

    private void jPanellimitsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanellimitsTableMousePressed
        jPanellimitsTable.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_jPanellimitsTableMousePressed

    private void jPanellimitsTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanellimitsTableMouseReleased
        jPanellimitsTable.setBackground(new java.awt.Color(30, 30, 30));
        CalendarLimits form = new CalendarLimits();
        form.setLocationRelativeTo(null);
        form.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        form.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage());
        form.setVisible(true);
    }//GEN-LAST:event_jPanellimitsTableMouseReleased

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
            java.util.logging.Logger.getLogger(SManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SManager form = new SManager();
                form.setIconImage(new ImageIcon(getClass().getResource("/Forms/img/logoicon.png")).getImage()); 
                form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                form.setSize(1000, 800);
                form.setExtendedState(Frame.MAXIMIZED_BOTH);
                form.pack();
                form.setLocationRelativeTo(null);
                form.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelemppanel;
    private javax.swing.JPanel jPanelemppanel1;
    private javax.swing.JPanel jPanelemppanel2;
    private javax.swing.JPanel jPanellimits;
    private javax.swing.JPanel jPanellimitsTable;
    private javax.swing.JPanel jPanelshifts;
    private javax.swing.JPanel jPanelshiftstable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logoff;
    private javax.swing.JLabel properties;
    private javax.swing.JLabel userid;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables

    

}
