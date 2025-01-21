/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import models.MySQL;

/**
 *
 * @author mamet
 */
public class TeachersPanel extends javax.swing.JPanel {

    /**
     * Creates new form enrollmentsPanel
     */
    public TeachersPanel() {
        initComponents();
        generateTeacherNo();
        loadTeacherData("");
        loadAssignData("");
        manageUpdateButton.setEnabled(false);
        manageDeleteButton.setEnabled(false);
    }

    private void generateTeacherNo() {
        try {
            ResultSet sNo = MySQL.executeSearch("SELECT COUNT(`t_no`) AS tno FROM `teacher`");
            if (sNo.next()) {
                int usedNo = sNo.getInt("tno");
                int newNo = usedNo + 1;
                regTeacherNoField.setText(String.valueOf(newNo));
            } else {
                regTeacherNoField.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearRegisterFields() {
        generateTeacherNo();
        regEmailField.setText("");
        regFirstNameField.setText("");
        regLastNameField.setText("");
        regAddressField.setText("");
    }
    
    private void clearManageFields() {
        manageTeacherNoField.setText("");
        manageEmailField.setText("");
        manageFirstNameField.setText("");
        manageLastNameField.setText("");
        manageAddressField.setText("");
    }

    private void loadTeacherData(String query) {
        if (!query.isBlank()) {
            try {
                ResultSet teacherData = MySQL.executeSearch("SELECT * FROM `teacher`" + query);
                DefaultTableModel tTable = (DefaultTableModel) teacherDataTable.getModel();
                tTable.setRowCount(0);

                while (teacherData.next()) {
                    Vector<String> tvector = new Vector<>();
                    tvector.add(teacherData.getString("t_no"));
                    tvector.add(teacherData.getString("first_name"));
                    tvector.add(teacherData.getString("last_name"));
                    tvector.add(teacherData.getString("email"));

                    tTable.addRow(tvector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResultSet teacherData = MySQL.executeSearch("SELECT * FROM `teacher`");
                DefaultTableModel tTable = (DefaultTableModel) teacherDataTable.getModel();
                tTable.setRowCount(0);

                while (teacherData.next()) {
                    Vector<String> tvector = new Vector<>();
                    tvector.add(teacherData.getString("t_no"));
                    tvector.add(teacherData.getString("first_name"));
                    tvector.add(teacherData.getString("last_name"));
                    tvector.add(teacherData.getString("email"));

                    tTable.addRow(tvector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadAssignData(String email) {
        if (!email.isBlank()) {
            try {
                ResultSet assignData = MySQL.executeSearch("SELECT * FROM `teacher_has_subject` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no`=`teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no`=`subject`.`sub_no` WHERE `teacher`.`email`='" + email + "'");
                DefaultTableModel tTable = (DefaultTableModel) teacherAssignTable.getModel();
                tTable.setRowCount(0);

                while (assignData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(assignData.getString("teacher.first_name") + " " + assignData.getString("teacher.last_name"));
                    vector.add(assignData.getString("teacher.email"));
                    vector.add(assignData.getString("subject.name"));

                    tTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResultSet assignData = MySQL.executeSearch("SELECT * FROM `teacher_has_subject` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no`=`teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no`=`subject`.`sub_no`");
                DefaultTableModel tTable = (DefaultTableModel) teacherAssignTable.getModel();
                tTable.setRowCount(0);

                while (assignData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(assignData.getString("teacher.first_name") + " " + assignData.getString("teacher.last_name"));
                    vector.add(assignData.getString("teacher.email"));
                    vector.add(assignData.getString("subject.name"));

                    tTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        regTeacherNoField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        regFirstNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        regLastNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        regEmailField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        regAddressField = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        teacherDataTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        manageEmailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        manageTeacherNoField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        manageFirstNameField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        manageLastNameField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        manageUpdateButton = new javax.swing.JButton();
        manageDeleteButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        manageAddressField = new javax.swing.JTextArea();
        clearButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        assignEmailField = new javax.swing.JTextField();
        assignSubjectIdField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        assignButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        teacherAssignTable = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        asignSearchEmail = new javax.swing.JTextField();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Teacher Number");

        regTeacherNoField.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("First Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Last Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Address");

        registerButton.setBackground(new java.awt.Color(0, 102, 0));
        registerButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/refresh-24px.png"))); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regTeacherNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(regFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(regLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(regAddressField)
                    .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(regEmailField))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(regTeacherNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Register", jPanel1);

        teacherDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Teacher No", "First Name", "Last Name", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        teacherDataTable.setShowHorizontalLines(true);
        teacherDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                teacherDataTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(teacherDataTable);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Teacher No");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("First Name");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Last Name");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Address");

        manageUpdateButton.setBackground(new java.awt.Color(0, 0, 153));
        manageUpdateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageUpdateButton.setText("Update");
        manageUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageUpdateButtonActionPerformed(evt);
            }
        });

        manageDeleteButton.setBackground(new java.awt.Color(102, 0, 0));
        manageDeleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        manageDeleteButton.setText("Delete");
        manageDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageDeleteButtonActionPerformed(evt);
            }
        });

        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search-24px.png"))); // NOI18N
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        manageAddressField.setColumns(20);
        manageAddressField.setRows(5);
        jScrollPane2.setViewportView(manageAddressField);

        clearButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addComponent(manageEmailField)
                    .addComponent(jLabel9)
                    .addComponent(manageFirstNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(manageLastNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(jLabel12)
                    .addComponent(manageUpdateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageTeacherNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(manageTeacherNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(manageUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage", jPanel2);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Teacher Email");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Subject ID");

        assignButton.setBackground(new java.awt.Color(0, 102, 0));
        assignButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        assignButton.setText("Assign");
        assignButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignButtonActionPerformed(evt);
            }
        });

        teacherAssignTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Teacher Name", "Teacher Email", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(teacherAssignTable);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Teacher Email");

        asignSearchEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                asignSearchEmailKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addComponent(assignSubjectIdField)
                    .addComponent(assignEmailField)
                    .addComponent(assignButton, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(427, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(asignSearchEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assignEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assignSubjectIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(assignButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(asignSearchEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Assign", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        generateTeacherNo();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String tNo = regTeacherNoField.getText();
        String fName = regFirstNameField.getText();
        String lName = regLastNameField.getText();
        String email = regEmailField.getText();
        String address = regAddressField.getText();

        if (fName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher first name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher last name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (email.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher email", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (address.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher address", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                MySQL.executeIUD("INSERT INTO `teacher`(`t_no`,`first_name`,`last_name`,`address`,`email`) VALUES('" + tNo + "','" + fName + "','" + lName + "','" + address + "','" + email + "')");
                JOptionPane.showMessageDialog(this, "Teacher Registered Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearRegisterFields();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void asignSearchEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_asignSearchEmailKeyTyped
        String email = asignSearchEmail.getText();
        loadAssignData(email);
    }//GEN-LAST:event_asignSearchEmailKeyTyped

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String tNo = manageTeacherNoField.getText();
        String email = manageEmailField.getText();

        StringBuilder query = new StringBuilder("");

        boolean whereAdded = false;

        if (!tNo.isBlank()) {
            query.append(whereAdded ? " AND " : " WHERE ");
            query.append("`s_no` LIKE '%" + tNo + "%'");
            whereAdded = true;
        }

        if (!email.isBlank()) {
            query.append(whereAdded ? " AND " : " WHERE ");
            query.append("`email` LIKE '%" + email + "%'");
            whereAdded = true;
        }

        loadTeacherData(query.toString());
    }//GEN-LAST:event_searchButtonActionPerformed

    private void teacherDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teacherDataTableMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = teacherDataTable.getSelectedRow();
            TableModel teacherTable = teacherDataTable.getModel();
            String tNo = String.valueOf(teacherTable.getValueAt(rowIndex, 0));

            if (tNo != null && !tNo.isBlank()) {
                manageUpdateButton.setEnabled(true);
                manageDeleteButton.setEnabled(true);
                searchButton.setEnabled(false);
                manageTeacherNoField.setEditable(false);
                manageEmailField.setEditable(false);

                try {
                    ResultSet result = MySQL.executeSearch("SELECT * FROM `teacher` WHERE `t_no`='" + tNo + "'");
                    if (result.next()) {
                        manageTeacherNoField.setText(tNo);
                        manageEmailField.setText(result.getString("email"));
                        manageFirstNameField.setText(result.getString("first_name"));
                        manageLastNameField.setText(result.getString("last_name"));
                        manageAddressField.setText(result.getString("address"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_teacherDataTableMouseClicked

    private void manageUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUpdateButtonActionPerformed
        String tNo = manageTeacherNoField.getText();
        String fName = manageFirstNameField.getText();
        String lName = manageLastNameField.getText();
        String address = manageAddressField.getText();

        if (fName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher first name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher last name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (address.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher address", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                MySQL.executeIUD("UPDATE `teacher` SET `first_name` = '" + fName + "', `last_name` = '" + lName + "', `address` = '" + address + "' WHERE `t_no`='" + tNo + "'");
                JOptionPane.showMessageDialog(this, "Teacher Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                manageTeacherNoField.setEditable(true);
                manageEmailField.setEditable(true);
                manageDeleteButton.setEnabled(false);
                manageUpdateButton.setEnabled(false);
                searchButton.setEnabled(true);
                clearManageFields();
                loadTeacherData("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_manageUpdateButtonActionPerformed

    private void manageDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageDeleteButtonActionPerformed
        String tNo = manageTeacherNoField.getText();

        try {
            MySQL.executeIUD("DELETE FROM `teacher` WHERE `t_no`='" + tNo + "'");
            JOptionPane.showMessageDialog(this, "Teacher Removed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            manageTeacherNoField.setEditable(true);
            manageEmailField.setEditable(true);
            manageDeleteButton.setEnabled(false);
            manageUpdateButton.setEnabled(false);
            searchButton.setEnabled(true);
            clearManageFields();
            loadTeacherData("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_manageDeleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        manageTeacherNoField.setEditable(true);
        manageEmailField.setEditable(true);
        manageDeleteButton.setEnabled(false);
        manageUpdateButton.setEnabled(false);
        searchButton.setEnabled(true);
        clearManageFields();
        loadTeacherData("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void assignButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignButtonActionPerformed
        String email = assignEmailField.getText();
        String subId = assignSubjectIdField.getText();

        if (email.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher email", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (subId.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter subject id", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ResultSet teacher = MySQL.executeSearch("SELECT * FROM `teacher` WHERE `email` = '" + email + "'");

                ResultSet subject = MySQL.executeSearch("SELECT * FROM `subject` WHERE `sub_no` = '" + subId + "'");

                if (!teacher.next()) {
                    JOptionPane.showMessageDialog(this, "No teacher found with the email", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!subject.next()) {
                    JOptionPane.showMessageDialog(this, "No subject found with the subject id", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String tNo = teacher.getString("t_no");
                    MySQL.executeIUD("INSERT INTO `teacher_has_subject`(`teacher_t_no`,`subject_sub_no`) VALUES('" + tNo + "', '" + subId + "')");
                    JOptionPane.showMessageDialog(this, "Teacher assigned successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadAssignData("");
                    assignSubjectIdField.setText("");
                    assignEmailField.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_assignButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField asignSearchEmail;
    private javax.swing.JButton assignButton;
    private javax.swing.JTextField assignEmailField;
    private javax.swing.JTextField assignSubjectIdField;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea manageAddressField;
    private javax.swing.JButton manageDeleteButton;
    private javax.swing.JTextField manageEmailField;
    private javax.swing.JTextField manageFirstNameField;
    private javax.swing.JTextField manageLastNameField;
    private javax.swing.JTextField manageTeacherNoField;
    private javax.swing.JButton manageUpdateButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField regAddressField;
    private javax.swing.JTextField regEmailField;
    private javax.swing.JTextField regFirstNameField;
    private javax.swing.JTextField regLastNameField;
    private javax.swing.JTextField regTeacherNoField;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTable teacherAssignTable;
    private javax.swing.JTable teacherDataTable;
    // End of variables declaration//GEN-END:variables
}
