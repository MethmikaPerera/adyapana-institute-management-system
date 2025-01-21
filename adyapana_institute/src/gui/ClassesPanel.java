/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import models.MySQL;

/**
 *
 * @author mamet
 */
public class ClassesPanel extends javax.swing.JPanel {

    /**
     * Creates new form enrollmentsPanel
     */
    public ClassesPanel() {
        initComponents();
        generateClassNo();
        loadDays();
        loadClassData("");
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
    }

    private void generateClassNo() {
        try {
            ResultSet classNo = MySQL.executeSearch("SELECT COUNT(`class_no`) AS classno FROM `class`");
            if (classNo.next()) {
                int usedNo = classNo.getInt("classno");
                int newNo = usedNo + 1;
                regClassNoField.setText(String.valueOf(newNo));
            } else {
                regClassNoField.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDays() {
        try {
            ResultSet days = MySQL.executeSearch("SELECT * FROM `day`");

            Vector<String> daysVector = new Vector<>();
            daysVector.add("Select Day");

            while (days.next()) {
                daysVector.add(days.getString("name"));
            }

            DefaultComboBoxModel daysModel = new DefaultComboBoxModel(daysVector);
            regDaysComboBox.setModel(daysModel);
            manageDaysComboBox.setModel(daysModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadClassData(String classNo) {
        if (!classNo.isBlank()) {
            try {
                ResultSet classData = MySQL.executeSearch("SELECT * FROM `class` "
                        + "INNER JOIN `day` ON `class`.`day_id` = `day`.`id` "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` WHERE `class`.`class_no`='" + classNo + "'");
                DefaultTableModel classTable = (DefaultTableModel) classDataTable.getModel();
                classTable.setRowCount(0);

                while (classData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(classData.getString("class.class_no"));
                    vector.add(classData.getString("subject.name"));
                    vector.add(classData.getString("teacher.first_name") + " " + classData.getString("teacher.last_name"));
                    vector.add(classData.getString("day.name"));
                    vector.add(classData.getString("class.start_time"));
                    vector.add(classData.getString("class.end_time"));

                    classTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResultSet classData = MySQL.executeSearch("SELECT * FROM `class` "
                        + "INNER JOIN `day` ON `class`.`day_id` = `day`.`id` "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no`");
                DefaultTableModel classTable = (DefaultTableModel) classDataTable.getModel();
                classTable.setRowCount(0);

                while (classData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(classData.getString("class.class_no"));
                    vector.add(classData.getString("subject.name"));
                    vector.add(classData.getString("teacher.first_name") + " " + classData.getString("teacher.last_name"));
                    vector.add(classData.getString("day.name"));
                    vector.add(classData.getString("class.start_time"));
                    vector.add(classData.getString("class.end_time"));

                    classTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clearAddFields() {
        generateClassNo();
        regTeacherEmailField.setText("");
        regSubjectIdField.setText("");
        regDaysComboBox.setSelectedIndex(0);
        regStartTimeField.setTime(null);
        regEndTimeField.setTime(null);
    }

    private void clearManageFields() {
        manageClassNoField.setText("");
        manageTeacherEmailField.setText("");
        manageSubjectIdField.setText("");
        manageDaysComboBox.setSelectedIndex(0);
        manageStartTimeField.setTime(null);
        manageEndTimeField.setTime(null);
        manageSearchButton.setEnabled(true);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        manageClassNoField.setEditable(true);
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
        regClassNoField = new javax.swing.JTextField();
        addClassButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        regTeacherEmailField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        regSubjectIdField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        regDaysComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        regStartTimeField = new com.github.lgooddatepicker.components.TimePicker();
        regEndTimeField = new com.github.lgooddatepicker.components.TimePicker();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        classDataTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        manageTeacherEmailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        manageClassNoField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        manageSubjectIdField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        manageSearchButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        manageDaysComboBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        manageStartTimeField = new com.github.lgooddatepicker.components.TimePicker();
        manageEndTimeField = new com.github.lgooddatepicker.components.TimePicker();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Class Number");

        regClassNoField.setEnabled(false);

        addClassButton.setBackground(new java.awt.Color(0, 102, 0));
        addClassButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addClassButton.setText("Add Class");
        addClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassButtonActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Teacher Email");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Subject ID");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Start Time");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("End Time");

        regDaysComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Day");

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
                .addContainerGap(263, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(regDaysComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(regStartTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(regEndTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(addClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(regTeacherEmailField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(regSubjectIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regClassNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)))
                .addGap(260, 260, 260))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(regClassNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regTeacherEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(regSubjectIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jLabel15))
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(regDaysComboBox)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(regStartTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(regEndTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addComponent(addClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add", jPanel1);

        classDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Class No", "Subject Name", "Teacher Name", "Day", "Start Time", "End Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        classDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                classDataTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(classDataTable);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Teacher Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Class No");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Subject Name");

        updateButton.setBackground(new java.awt.Color(0, 0, 153));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(102, 0, 0));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        manageSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search-24px.png"))); // NOI18N
        manageSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageSearchButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Day");

        manageDaysComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Start Time");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("End Time");

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
                            .addComponent(manageDaysComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel10)
                                    .addComponent(manageStartTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 5, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(manageEndTimeField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageTeacherEmailField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageSubjectIdField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                            .addComponent(updateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manageClassNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(manageSearchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(11, 11, 11)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageClassNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(manageSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageTeacherEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageSubjectIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageDaysComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageStartTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageEndTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage", jPanel2);

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
        generateClassNo();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void addClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassButtonActionPerformed
        String email = regTeacherEmailField.getText();
        String subjectId = regSubjectIdField.getText();
        String classNo = regClassNoField.getText();
        int day = regDaysComboBox.getSelectedIndex();
        LocalTime sTime = regStartTimeField.getTime();
        LocalTime eTime = regStartTimeField.getTime();

        String teacherSubId = "";

        if (email.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter teacher email", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (subjectId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter subject number", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (day == 0) {
            JOptionPane.showMessageDialog(this, "Select class day", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (sTime == null) {
            JOptionPane.showMessageDialog(this, "Select start time", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (eTime == null) {
            JOptionPane.showMessageDialog(this, "Select end time", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
            String startTime = sTime.format(format);
            String endTime = eTime.format(format);

            try {
                ResultSet result = MySQL.executeSearch("SELECT * FROM `teacher_has_subject` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no`=`teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no`=`subject`.`sub_no` "
                        + "WHERE `teacher`.`email`='" + email + "' AND `subject`.`sub_no`='" + subjectId + "'");

                if (result.next()) {
                    teacherSubId = result.getString("teacher_has_subject.teacher_subject_id");

                    MySQL.executeIUD("INSERT INTO `class`(`class_no`,`start_time`,`end_time`,`teacher_has_subject_id`,`day_id`) VALUES('" + classNo + "','" + startTime + "','" + endTime + "','" + teacherSubId + "','" + day + "')");
                    JOptionPane.showMessageDialog(this, "Class added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    clearAddFields();
                    loadClassData("");
                } else {
                    JOptionPane.showMessageDialog(this, "Teacher with subject Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_addClassButtonActionPerformed

    private void classDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_classDataTableMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = classDataTable.getSelectedRow();
            TableModel classTable = classDataTable.getModel();
            String classNo = String.valueOf(classTable.getValueAt(rowIndex, 0));

            if (classNo != null && !classNo.isBlank()) {
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
                manageSearchButton.setEnabled(false);
                manageClassNoField.setEditable(false);
                manageTeacherEmailField.setEditable(false);
                manageSubjectIdField.setEditable(false);

                try {
                    ResultSet result = MySQL.executeSearch("SELECT * FROM `class` "
                            + "INNER JOIN `day` ON `class`.`day_id` = `day`.`id` "
                            + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                            + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                            + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` WHERE `class`.`class_no`='" + classNo + "'");
                    if (result.next()) {
                        manageClassNoField.setText(classNo);
                        manageTeacherEmailField.setText(result.getString("teacher.email"));
                        manageSubjectIdField.setText(result.getString("subject.sub_no"));
                        manageDaysComboBox.setSelectedIndex(result.getInt("day.id"));
                        manageStartTimeField.setTime(result.getTime("class.start_time").toLocalTime());
                        manageEndTimeField.setTime(result.getTime("class.end_time").toLocalTime());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_classDataTableMouseClicked

    private void manageSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageSearchButtonActionPerformed
        String classNo = manageClassNoField.getText();
        loadClassData(classNo);
    }//GEN-LAST:event_manageSearchButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        clearManageFields();
        loadClassData("");
    }//GEN-LAST:event_clearButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        String classNo = manageClassNoField.getText();
        int dayId = manageDaysComboBox.getSelectedIndex();
        LocalTime sTime = manageStartTimeField.getTime();
        LocalTime eTime = manageStartTimeField.getTime();

        if (dayId == 0) {
            JOptionPane.showMessageDialog(this, "Select class day", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (sTime == null) {
            JOptionPane.showMessageDialog(this, "Select start time", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (eTime == null) {
            JOptionPane.showMessageDialog(this, "Select end time", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
            String startTime = sTime.format(format);
            String endTime = eTime.format(format);

            try {
                MySQL.executeIUD("UPDATE `class` SET `start_time`='" + startTime + "', `end_time`='" + endTime + "', `day_id`='" + dayId + "' WHERE `class_no`='" + classNo + "'");
                JOptionPane.showMessageDialog(this, "Class updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearManageFields();
                loadClassData("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        String classNo = manageClassNoField.getText();

        try {
            MySQL.executeIUD("DELETE FROM `class` WHERE `class_no` = '" + classNo + "'");
            JOptionPane.showMessageDialog(this, "Class removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearManageFields();
            loadClassData("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClassButton;
    private javax.swing.JTable classDataTable;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField manageClassNoField;
    private javax.swing.JComboBox<String> manageDaysComboBox;
    private com.github.lgooddatepicker.components.TimePicker manageEndTimeField;
    private javax.swing.JButton manageSearchButton;
    private com.github.lgooddatepicker.components.TimePicker manageStartTimeField;
    private javax.swing.JTextField manageSubjectIdField;
    private javax.swing.JTextField manageTeacherEmailField;
    private javax.swing.JButton refreshButton;
    private javax.swing.JTextField regClassNoField;
    private javax.swing.JComboBox<String> regDaysComboBox;
    private com.github.lgooddatepicker.components.TimePicker regEndTimeField;
    private com.github.lgooddatepicker.components.TimePicker regStartTimeField;
    private javax.swing.JTextField regSubjectIdField;
    private javax.swing.JTextField regTeacherEmailField;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
