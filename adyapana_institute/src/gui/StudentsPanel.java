/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import models.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author mamet
 */
public class StudentsPanel extends javax.swing.JPanel {

    /**
     * Creates new form registrationsPanel
     */
    public StudentsPanel() {
        initComponents();
        generateStudentNo();
        loadStudentData("");
        loadEnrollmentData("");
        manageUpdateButton.setEnabled(false);
        manageDeleteButton.setEnabled(false);
    }

    private void generateStudentNo() {
        try {
            ResultSet sNo = MySQL.executeSearch("SELECT COUNT(`s_no`) AS sno FROM `student`");
            if (sNo.next()) {
                int usedNo = sNo.getInt("sno");
                int newNo = usedNo + 1;
                regStudentNoField.setText(String.valueOf(newNo));
            } else {
                regStudentNoField.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentData(String query) {
        if (!query.isBlank()) {
            try {
                ResultSet studentData = MySQL.executeSearch("SELECT * FROM `student`" + query);
                DefaultTableModel sTable = (DefaultTableModel) studentDataTable.getModel();
                sTable.setRowCount(0);

                while (studentData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(studentData.getString("s_no"));
                    vector.add(studentData.getString("first_name"));
                    vector.add(studentData.getString("last_name"));
                    vector.add(studentData.getString("email"));
                    vector.add(studentData.getString("dob"));

                    sTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResultSet studentData = MySQL.executeSearch("SELECT * FROM `student`");
                DefaultTableModel sTable = (DefaultTableModel) studentDataTable.getModel();
                sTable.setRowCount(0);

                while (studentData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(studentData.getString("s_no"));
                    vector.add(studentData.getString("first_name"));
                    vector.add(studentData.getString("last_name"));
                    vector.add(studentData.getString("email"));
                    vector.add(studentData.getString("dob"));

                    sTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadEnrollmentData(String email) {
        if (!email.isBlank()) {
            try {
                ResultSet enrollData = MySQL.executeSearch("SELECT * FROM `student_enrollment` "
                        + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                        + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` WHERE `student`.`email`='" + email + "'");
                DefaultTableModel enrollTable = (DefaultTableModel) enrollmentTable.getModel();
                enrollTable.setRowCount(0);

                while (enrollData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(enrollData.getString("student.email"));
                    vector.add(enrollData.getString("student.first_name") + " " + enrollData.getString("student.last_name"));
                    vector.add(enrollData.getString("class.class_no"));
                    vector.add(enrollData.getString("teacher.first_name") + " " + enrollData.getString("teacher.last_name"));
                    vector.add(enrollData.getString("subject.name"));

                    enrollTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResultSet enrollData = MySQL.executeSearch("SELECT * FROM `student_enrollment` "
                        + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                        + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no`");
                DefaultTableModel enrollTable = (DefaultTableModel) enrollmentTable.getModel();
                enrollTable.setRowCount(0);

                while (enrollData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(enrollData.getString("student.email"));
                    vector.add(enrollData.getString("student.first_name") + " " + enrollData.getString("student.last_name"));
                    vector.add(enrollData.getString("class.class_no"));
                    vector.add(enrollData.getString("teacher.first_name") + " " + enrollData.getString("teacher.last_name"));
                    vector.add(enrollData.getString("subject.name"));

                    enrollTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clearManageFields() {
        manageStudentNoField.setText("");
        manageEmailField.setText("");
        manageFirstNameField.setText("");
        manageLastNameField.setText("");
        manageBirthdayField.setDate(null);
        manageAddressField.setText("");
    }

    private void clearRegisterFields() {
        generateStudentNo();
        regEmailField.setText("");
        regFirstNameField.setText("");
        regLastNameField.setText("");
        regBirthdayField.setDate(null);
        regAddressField.setText("");
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
        regStudentNoField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        regFirstNameField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        regLastNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        regEmailField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        regBirthdayField = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        regAddressField = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studentDataTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        manageEmailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        manageStudentNoField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        manageFirstNameField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        manageLastNameField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        manageUpdateButton = new javax.swing.JButton();
        manageDeleteButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        manageAddressField = new javax.swing.JTextArea();
        manageBirthdayField = new com.toedter.calendar.JDateChooser();
        clearButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        enrollmentTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        enrollEmailField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        enrollClassIdField = new javax.swing.JTextField();
        enrollButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        enrollSearchEmail = new javax.swing.JTextField();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Student Number");

        regStudentNoField.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("First Name");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Last Name");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Birthday");

        regBirthdayField.setForeground(new java.awt.Color(255, 255, 255));

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
                        .addComponent(regStudentNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(regEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(regBirthdayField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(regAddressField)
                    .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(regStudentNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(regEmailField)
                    .addComponent(regBirthdayField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regAddressField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Register", jPanel1);

        studentDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student No", "First Name", "Last Name", "Email", "Birthday"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studentDataTable.setShowGrid(false);
        studentDataTable.setShowHorizontalLines(true);
        studentDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studentDataTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studentDataTable);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Student No");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("First Name");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Last Name");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Birthday");

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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageFirstNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageLastNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageBirthdayField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(clearButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(manageEmailField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageUpdateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(manageDeleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manageStudentNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(11, 11, 11)))
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
                            .addComponent(manageStudentNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageFirstNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageLastNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageBirthdayField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(manageUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(manageDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage", jPanel2);

        enrollmentTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Email", "Student Name", "Class ID", "Teacher Name", "Subject"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(enrollmentTable);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Student Email");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Class ID");

        enrollButton.setBackground(new java.awt.Color(0, 102, 0));
        enrollButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        enrollButton.setText("Enroll");
        enrollButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollButtonActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Student Email");

        enrollSearchEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                enrollSearchEmailKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(enrollButton, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(enrollClassIdField)
                    .addComponent(enrollEmailField))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enrollSearchEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(enrollSearchEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enrollEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enrollClassIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(enrollButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Enrollment", jPanel3);

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
        generateStudentNo();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        String sNo = regStudentNoField.getText();
        String fName = regFirstNameField.getText();
        String lName = regLastNameField.getText();
        String email = regEmailField.getText();
        String address = regAddressField.getText();
        Date birthday = regBirthdayField.getDate();

        if (fName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student first name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student last name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (email.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student email", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (address.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student address", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (birthday == null) {
            JOptionPane.showMessageDialog(this, "Enter student birthday", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dob = dateFormat.format(birthday);

            try {
                MySQL.executeIUD("INSERT INTO `student`(`s_no`,`first_name`,`last_name`,`address`,`dob`,`email`) VALUES('" + sNo + "','" + fName + "','" + lName + "','" + address + "','" + dob + "','" + email + "')");
                JOptionPane.showMessageDialog(this, "Student Registered Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearRegisterFields();
                loadStudentData("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String sNo = manageStudentNoField.getText();
        String email = manageEmailField.getText();

        StringBuilder query = new StringBuilder("");

        boolean whereAdded = false;

        if (!sNo.isBlank()) {
            query.append(whereAdded ? " AND " : " WHERE ");
            query.append("`s_no` LIKE '%" + sNo + "%'");
            whereAdded = true;
        }

        if (!email.isBlank()) {
            query.append(whereAdded ? " AND " : " WHERE ");
            query.append("`email` LIKE '%" + email + "%'");
            whereAdded = true;
        }

        loadStudentData(query.toString());
    }//GEN-LAST:event_searchButtonActionPerformed

    private void studentDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studentDataTableMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = studentDataTable.getSelectedRow();
            TableModel studentTable = studentDataTable.getModel();
            String sNo = String.valueOf(studentTable.getValueAt(rowIndex, 0));

            if (sNo != null && !sNo.isBlank()) {
                manageUpdateButton.setEnabled(true);
                manageDeleteButton.setEnabled(true);
                searchButton.setEnabled(false);
                manageStudentNoField.setEditable(false);
                manageEmailField.setEditable(false);

                try {
                    ResultSet result = MySQL.executeSearch("SELECT * FROM `student` WHERE `s_no`='" + sNo + "'");
                    if (result.next()) {
                        manageStudentNoField.setText(sNo);
                        manageEmailField.setText(result.getString("email"));
                        manageFirstNameField.setText(result.getString("first_name"));
                        manageLastNameField.setText(result.getString("last_name"));
                        manageBirthdayField.setDate(result.getDate("dob"));
                        manageAddressField.setText(result.getString("address"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_studentDataTableMouseClicked

    private void manageUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageUpdateButtonActionPerformed
        String sNo = manageStudentNoField.getText();
        String fName = manageFirstNameField.getText();
        String lName = manageLastNameField.getText();
        Date birthday = manageBirthdayField.getDate();
        String address = manageAddressField.getText();

        if (fName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student first name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (lName.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student last name", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (address.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student address", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (birthday == null) {
            JOptionPane.showMessageDialog(this, "Enter student birthday", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dob = dateFormat.format(birthday);

            try {
                MySQL.executeIUD("UPDATE `student` SET `first_name` = '" + fName + "', `last_name` = '" + lName + "', `address` = '" + address + "', `dob` = '" + dob + "' WHERE `s_no`='" + sNo + "'");
                JOptionPane.showMessageDialog(this, "Student Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                manageStudentNoField.setEditable(true);
                manageEmailField.setEditable(true);
                manageDeleteButton.setEnabled(false);
                manageUpdateButton.setEnabled(false);
                searchButton.setEnabled(true);
                clearManageFields();
                loadStudentData("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_manageUpdateButtonActionPerformed

    private void manageDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageDeleteButtonActionPerformed
        String sNo = manageStudentNoField.getText();

        try {
            MySQL.executeIUD("DELETE FROM `student` WHERE `s_no`='" + sNo + "'");
            JOptionPane.showMessageDialog(this, "Student Removed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            manageStudentNoField.setEditable(true);
            manageEmailField.setEditable(true);
            manageDeleteButton.setEnabled(false);
            manageUpdateButton.setEnabled(false);
            searchButton.setEnabled(true);
            clearManageFields();
            loadStudentData("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_manageDeleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        manageStudentNoField.setEditable(true);
        manageEmailField.setEditable(true);
        manageDeleteButton.setEnabled(false);
        manageUpdateButton.setEnabled(false);
        searchButton.setEnabled(true);
        clearManageFields();
        loadStudentData("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void enrollSearchEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enrollSearchEmailKeyTyped
        String email = enrollSearchEmail.getText();
        loadEnrollmentData(email);
    }//GEN-LAST:event_enrollSearchEmailKeyTyped

    private void enrollButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enrollButtonActionPerformed
        String email = enrollEmailField.getText();
        String classId = enrollClassIdField.getText();

        if (email.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student email", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (classId.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter class id", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ResultSet student = MySQL.executeSearch("SELECT * FROM `student` WHERE `email` = '" + email + "'");

                ResultSet classData = MySQL.executeSearch("SELECT * FROM `class` WHERE `class_no` = '" + classId + "'");

                if (!student.next()) {
                    JOptionPane.showMessageDialog(this, "No student found with the email", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!classData.next()) {
                    JOptionPane.showMessageDialog(this, "No class found with the class id", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String sNo = student.getString("s_no");
                    MySQL.executeIUD("INSERT INTO `student_enrollment`(`student_s_no`,`class_class_no`) VALUES('" + sNo + "', '" + classId + "')");
                    JOptionPane.showMessageDialog(this, "Student enrolled successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadEnrollmentData("");
                    enrollClassIdField.setText("");
                    enrollEmailField.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_enrollButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JButton enrollButton;
    private javax.swing.JTextField enrollClassIdField;
    private javax.swing.JTextField enrollEmailField;
    private javax.swing.JTextField enrollSearchEmail;
    private javax.swing.JTable enrollmentTable;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea manageAddressField;
    private com.toedter.calendar.JDateChooser manageBirthdayField;
    private javax.swing.JButton manageDeleteButton;
    private javax.swing.JTextField manageEmailField;
    private javax.swing.JTextField manageFirstNameField;
    private javax.swing.JTextField manageLastNameField;
    private javax.swing.JTextField manageStudentNoField;
    private javax.swing.JButton manageUpdateButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField regAddressField;
    private com.toedter.calendar.JDateChooser regBirthdayField;
    private javax.swing.JTextField regEmailField;
    private javax.swing.JTextField regFirstNameField;
    private javax.swing.JTextField regLastNameField;
    private javax.swing.JTextField regStudentNoField;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTable studentDataTable;
    // End of variables declaration//GEN-END:variables
}
