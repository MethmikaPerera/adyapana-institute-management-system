/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import models.MySQL;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mamet
 */
public class PaymentsPanel extends javax.swing.JPanel {

    /**
     * Creates new form paymentsPanel
     */
    public PaymentsPanel() {
        initComponents();
        generateInvoiceNo();
        loadDueData("");
        loadPaidData("");
        duePayButton.setEnabled(false);
        paidUpdateButton.setEnabled(false);
        paidPrintButton.setEnabled(false);
    }

    private void generateInvoiceNo() {
        try {
            ResultSet classNo = MySQL.executeSearch("SELECT COUNT(`invoice_id`) AS invoiceno FROM `invoice`");
            if (classNo.next()) {
                int usedNo = classNo.getInt("invoiceno");
                int newNo = usedNo + 1;
                payInvoiceNoField.setText(String.valueOf(newNo));
            } else {
                payInvoiceNoField.setText("1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDueData(String query) {
        if (!query.isBlank()) {
            try {
                ResultSet dueData = MySQL.executeSearch("SELECT * FROM `invoice` "
                        + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                        + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                        + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                        + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                        + "WHERE `payment_status`.`status_id`='1' " + query);
                DefaultTableModel dueTable = (DefaultTableModel) dueDataTable.getModel();
                dueTable.setRowCount(0);

                while (dueData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(dueData.getString("invoice.invoice_id"));
                    vector.add(dueData.getString("student.first_name") + " " + dueData.getString("student.last_name"));
                    vector.add(dueData.getString("class.class_no"));
                    vector.add(dueData.getString("invoice.payment_date"));
                    vector.add(dueData.getString("invoice.value"));
                    vector.add(dueData.getString("invoice.due_amount"));

                    dueTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResultSet dueData = MySQL.executeSearch("SELECT * FROM `invoice` "
                        + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                        + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                        + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                        + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                        + "WHERE `payment_status`.`status_id`='1'");
                DefaultTableModel dueTable = (DefaultTableModel) dueDataTable.getModel();
                dueTable.setRowCount(0);

                while (dueData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(dueData.getString("invoice.invoice_id"));
                    vector.add(dueData.getString("student.first_name") + " " + dueData.getString("student.last_name"));
                    vector.add(dueData.getString("class.class_no"));
                    vector.add(dueData.getString("invoice.payment_date"));
                    vector.add(dueData.getString("invoice.value"));
                    vector.add(dueData.getString("invoice.due_amount"));

                    dueTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void loadPaidData(String query) {
        if (!query.isBlank()) {
            try {
                ResultSet payData = MySQL.executeSearch("SELECT * FROM `invoice` "
                        + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                        + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                        + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                        + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                        + "WHERE `payment_status`.`status_id`='2' " + query);
                DefaultTableModel payTable = (DefaultTableModel) paidDataTable.getModel();
                payTable.setRowCount(0);

                while (payData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(payData.getString("invoice.invoice_id"));
                    vector.add(payData.getString("student.first_name") + " " + payData.getString("student.last_name"));
                    vector.add(payData.getString("class.class_no"));
                    vector.add(payData.getString("invoice.payment_date"));
                    vector.add(payData.getString("invoice.value"));

                    payTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ResultSet payData = MySQL.executeSearch("SELECT * FROM `invoice` "
                        + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                        + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                        + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                        + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                        + "WHERE `payment_status`.`status_id`='2'");
                DefaultTableModel payTable = (DefaultTableModel) paidDataTable.getModel();
                payTable.setRowCount(0);

                while (payData.next()) {
                    Vector<String> vector = new Vector<>();
                    vector.add(payData.getString("invoice.invoice_id"));
                    vector.add(payData.getString("student.first_name") + " " + payData.getString("student.last_name"));
                    vector.add(payData.getString("class.class_no"));
                    vector.add(payData.getString("invoice.payment_date"));
                    vector.add(payData.getString("invoice.value"));

                    payTable.addRow(vector);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clearDueFields() {
        dueSearchButton.setEnabled(true);
        dueClassNoField.setEditable(true);
        dueStudentEmailField.setEditable(true);
        dueInvoiceIdField.setEditable(true);
        dueAmountField.setText("");
        dueClassNoField.setText("");
        duePaidAmountField.setText("");
        dueInvoiceIdField.setText("");
        dueStudentEmailField.setText("");
    }

    private void clearPaidFields() {
        paidSearchButton.setEnabled(true);
        paidInvoiceIdField.setEditable(true);
        paidAmountField.setText("");
        paidClassIdField.setText("");
        paidInvoiceIdField.setText("");
        paidStudentEmailField.setText("");
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
        payInvoiceNoField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        payStudentEmailField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        payClassIdField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        payAmountField = new javax.swing.JTextField();
        payButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dueStudentEmailField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dueClassNoField = new javax.swing.JTextField();
        dueSearchButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        dueDataTable = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        dueInvoiceIdField = new javax.swing.JTextField();
        duePaidAmountField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        dueAmountField = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        duePayButton = new javax.swing.JButton();
        dueClearButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        paidDataTable = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        paidStudentEmailField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        paidClassIdField = new javax.swing.JTextField();
        paidSearchButton = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        paidInvoiceIdField = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        paidAmountField = new javax.swing.JTextField();
        paidUpdateButton = new javax.swing.JButton();
        paidPrintButton = new javax.swing.JButton();
        paidClearButton = new javax.swing.JButton();

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Invoice No");

        payInvoiceNoField.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Student Email");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Class ID");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Amount");

        payButton.setBackground(new java.awt.Color(0, 102, 0));
        payButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        payButton.setText("Pay");
        payButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payButtonActionPerformed(evt);
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
                .addGap(287, 287, 287)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(payButton, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addComponent(payClassIdField)
                    .addComponent(payStudentEmailField)
                    .addComponent(payAmountField, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(payInvoiceNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(refreshButton))
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(payInvoiceNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payStudentEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payClassIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(payAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(payButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(162, 162, 162))
        );

        jTabbedPane1.addTab("Pay", jPanel1);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Student Email");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Class Id");

        dueSearchButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dueSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search-24px.png"))); // NOI18N
        dueSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueSearchButtonActionPerformed(evt);
            }
        });

        dueDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Student Name", "Class ID", "Paid Date", "Paid", "Due"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dueDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dueDataTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(dueDataTable);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Invoice Id");

        duePaidAmountField.setEnabled(false);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Paid Amount");

        dueAmountField.setEnabled(false);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Due Amount");

        duePayButton.setBackground(new java.awt.Color(153, 102, 0));
        duePayButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        duePayButton.setText("Pay Due Amount");
        duePayButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duePayButtonActionPerformed(evt);
            }
        });

        dueClearButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        dueClearButton.setText("Clear");
        dueClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(dueInvoiceIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(dueStudentEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(dueClassNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(dueSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20)
                            .addComponent(duePaidAmountField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jLabel21)
                            .addComponent(dueAmountField, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(duePayButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dueClearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dueClassNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dueStudentEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dueInvoiceIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dueSearchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(duePaidAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dueAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(duePayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dueClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Due", jPanel2);

        paidDataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Student Name", "Class ID", "Payment Date", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        paidDataTable.setShowHorizontalLines(true);
        paidDataTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paidDataTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(paidDataTable);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Student Email");

        paidStudentEmailField.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Class Id");

        paidClassIdField.setEnabled(false);

        paidSearchButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        paidSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/search-24px.png"))); // NOI18N
        paidSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidSearchButtonActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Invoice Id");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Amount");

        paidUpdateButton.setBackground(new java.awt.Color(0, 0, 204));
        paidUpdateButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        paidUpdateButton.setText("Update");
        paidUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidUpdateButtonActionPerformed(evt);
            }
        });

        paidPrintButton.setBackground(new java.awt.Color(102, 102, 0));
        paidPrintButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        paidPrintButton.setText("Print");
        paidPrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidPrintButtonActionPerformed(evt);
            }
        });

        paidClearButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        paidClearButton.setText("Clear");
        paidClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidClearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(paidInvoiceIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(paidSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel8)
                        .addComponent(paidStudentEmailField)
                        .addComponent(jLabel9)
                        .addComponent(paidClassIdField))
                    .addComponent(jLabel19)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(paidClearButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paidPrintButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(paidUpdateButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                        .addComponent(paidAmountField, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(paidSearchButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(paidInvoiceIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paidStudentEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paidClassIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paidAmountField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(paidUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paidPrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paidClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Completed", jPanel3);

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
        generateInvoiceNo();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void payButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payButtonActionPerformed
        String studentEmail = payStudentEmailField.getText();
        String classId = payClassIdField.getText();
        Double amount = Double.parseDouble(payAmountField.getText());
        String invoiceId = payInvoiceNoField.getText();

        if (studentEmail.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter student email", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (classId.isBlank()) {
            JOptionPane.showMessageDialog(this, "Enter class ID", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (amount == null) {
            JOptionPane.showMessageDialog(this, "Enter pay amount", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ResultSet enrollresult = MySQL.executeSearch("SELECT * FROM `student_enrollment` "
                        + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                        + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                        + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                        + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                        + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                        + "WHERE `student`.`email`='" + studentEmail + "' AND `class`.`class_no`='" + classId + "'");

                if (enrollresult.next()) {
                    int enrollId = enrollresult.getInt("student_enrollment.student_enrollment_id");
                    Double subjectPrice = enrollresult.getDouble("subject.price");
                    Double dueAmount = null;
                    int paymentStatus;
                    if (subjectPrice > amount) {
                        dueAmount = subjectPrice - amount;
                        paymentStatus = 1;
                    } else {
                        dueAmount = 0.0;
                        paymentStatus = 2;
                    }

                    Date date = new Date();
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
                    String payDate = dateFormat1.format(date);
                    String runDate = dateFormat2.format(date);

                    MySQL.executeIUD("INSERT INTO `invoice`(`invoice_id`,`payment_date`,`value`,`student_enrollment_id`,`payment_status_id`,`due_amount`) "
                            + "VALUES('" + invoiceId + "','" + payDate + "','" + amount + "','" + enrollId + "','" + paymentStatus + "','" + dueAmount + "')");

                    JOptionPane.showMessageDialog(this, "Payment successfull", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loadDueData("");
                    loadPaidData("");

                    ResultSet result = MySQL.executeSearch("SELECT * FROM `invoice` "
                            + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                            + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                            + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                            + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                            + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                            + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                            + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                            + "WHERE `invoice`.`invoice_id`='" + invoiceId + "'");

                    if (result.next()) {
                        String studentName = result.getString("student.first_name") + " " + result.getString("student.last_name");
                        String sEmail = result.getString("student.email");
                        String teacherName = result.getString("teacher.first_name") + " " + result.getString("teacher.last_name");
                        String subject = result.getString("subject.name");
                        String paidDate = result.getString("invoice.payment_date");
                        String paidValue = result.getString("invoice.value");

                        HashMap<String, Object> parameters = new HashMap<>();
                        parameters.put("Parameter1", runDate);
                        parameters.put("Parameter2", invoiceId);
                        parameters.put("Parameter3", studentName);
                        parameters.put("Parameter4", sEmail);
                        parameters.put("Parameter5", teacherName);
                        parameters.put("Parameter6", subject);
                        parameters.put("Parameter7", paidDate);
                        parameters.put("Parameter8", paidValue);

                        JREmptyDataSource datasource = new JREmptyDataSource();

                        JasperPrint report = JasperFillManager.fillReport("src/reports/invoice.jasper", parameters, datasource);
                        JasperViewer.viewReport(report, false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Enrollment not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_payButtonActionPerformed

    private void dueSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueSearchButtonActionPerformed
        String classNo = dueClassNoField.getText();
        String email = dueStudentEmailField.getText();
        String invoiceId = dueInvoiceIdField.getText();

        StringBuilder query = new StringBuilder("");

        if (!classNo.isBlank()) {
            query.append("AND `class`.`class_no` LIKE '%" + classNo + "%'");
        }

        if (!email.isBlank()) {
            query.append("AND `student`.`email` LIKE '%" + email + "%'");
        }

        if (!invoiceId.isBlank()) {
            query.append("AND `invoice`.`invoice_id` LIKE '%" + invoiceId + "%'");
        }

        loadDueData(query.toString());
    }//GEN-LAST:event_dueSearchButtonActionPerformed

    private void dueDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dueDataTableMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = dueDataTable.getSelectedRow();
            TableModel dueTable = dueDataTable.getModel();
            String invoiceNo = String.valueOf(dueTable.getValueAt(rowIndex, 0));

            if (invoiceNo != null && !invoiceNo.isBlank()) {
                duePayButton.setEnabled(true);
                dueClassNoField.setEditable(false);
                dueStudentEmailField.setEditable(false);
                dueSearchButton.setEnabled(false);
                dueInvoiceIdField.setEditable(false);

                try {
                    ResultSet result = MySQL.executeSearch("SELECT * FROM `invoice` "
                            + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                            + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                            + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                            + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                            + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                            + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                            + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                            + "WHERE `invoice`.`invoice_id`='" + invoiceNo + "' ");
                    if (result.next()) {
                        dueInvoiceIdField.setText(invoiceNo);
                        duePaidAmountField.setText(result.getString("invoice.value"));
                        dueAmountField.setText(result.getString("invoice.due_amount"));
                        dueStudentEmailField.setText(result.getString("student.email"));
                        dueClassNoField.setText(result.getString("class.class_no"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_dueDataTableMouseClicked

    private void dueClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueClearButtonActionPerformed
        clearDueFields();
        loadDueData("");
    }//GEN-LAST:event_dueClearButtonActionPerformed

    private void duePayButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duePayButtonActionPerformed
        String invoiceId = dueInvoiceIdField.getText();
        Double dueAmount = Double.parseDouble(dueAmountField.getText());
        Double paidAmount = Double.parseDouble(duePaidAmountField.getText());

        Date date = new Date();
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        String payDate = dateFormat1.format(date);
        String runDate = dateFormat2.format(date);

        try {
            Double payment = dueAmount + paidAmount;
            MySQL.executeIUD("UPDATE `invoice` SET `payment_date`='" + payDate + "', `value`='" + payment + "', `payment_status_id`='2', `due_amount`='0' WHERE `invoice_id`='" + invoiceId + "'");
            JOptionPane.showMessageDialog(this, "Due Payment successfull", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearDueFields();
            loadDueData("");
            loadPaidData("");

            ResultSet result = MySQL.executeSearch("SELECT * FROM `invoice` "
                    + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                    + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                    + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                    + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                    + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                    + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                    + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                    + "WHERE `invoice`.`invoice_id`='" + invoiceId + "'");

            if (result.next()) {
                String studentName = result.getString("student.first_name") + " " + result.getString("student.last_name");
                String sEmail = result.getString("student.email");
                String teacherName = result.getString("teacher.first_name") + " " + result.getString("teacher.last_name");
                String subject = result.getString("subject.name");
                String paidDate = result.getString("invoice.payment_date");
                String paidValue = String.valueOf(dueAmount);

                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("Parameter1", runDate);
                parameters.put("Parameter2", invoiceId);
                parameters.put("Parameter3", studentName);
                parameters.put("Parameter4", sEmail);
                parameters.put("Parameter5", teacherName);
                parameters.put("Parameter6", subject);
                parameters.put("Parameter7", paidDate);
                parameters.put("Parameter8", paidValue);

                JREmptyDataSource datasource = new JREmptyDataSource();

                JasperPrint report = JasperFillManager.fillReport("src/reports/invoice.jasper", parameters, datasource);
                JasperViewer.viewReport(report, false);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_duePayButtonActionPerformed

    private void paidClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidClearButtonActionPerformed
        clearPaidFields();
        loadPaidData("");
    }//GEN-LAST:event_paidClearButtonActionPerformed

    private void paidSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidSearchButtonActionPerformed
        String invoiceId = paidInvoiceIdField.getText();

        StringBuilder query = new StringBuilder("");

        if (!invoiceId.isBlank()) {
            query.append("AND `invoice`.`invoice_id` LIKE '%" + invoiceId + "%'");
        }

        loadPaidData(query.toString());
    }//GEN-LAST:event_paidSearchButtonActionPerformed

    private void paidDataTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paidDataTableMouseClicked
        if (evt.getClickCount() == 2) {
            int rowIndex = paidDataTable.getSelectedRow();
            TableModel paidTable = paidDataTable.getModel();
            String invoiceNo = String.valueOf(paidTable.getValueAt(rowIndex, 0));

            if (invoiceNo != null && !invoiceNo.isBlank()) {
                paidUpdateButton.setEnabled(true);
                paidPrintButton.setEnabled(true);
                paidClassIdField.setEditable(false);
                paidStudentEmailField.setEditable(false);
                paidSearchButton.setEnabled(false);
                paidInvoiceIdField.setEditable(false);

                try {
                    ResultSet result = MySQL.executeSearch("SELECT * FROM `invoice` "
                            + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                            + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                            + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                            + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                            + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                            + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                            + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                            + "WHERE `invoice`.`invoice_id`='" + invoiceNo + "' ");
                    if (result.next()) {
                        paidInvoiceIdField.setText(invoiceNo);
                        paidAmountField.setText(result.getString("invoice.value"));
                        paidStudentEmailField.setText(result.getString("student.email"));
                        paidClassIdField.setText(result.getString("class.class_no"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_paidDataTableMouseClicked

    private void paidUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidUpdateButtonActionPerformed
        String invoiceId = paidInvoiceIdField.getText();
        Double payment = Double.parseDouble(paidAmountField.getText());

        try {
            MySQL.executeIUD("UPDATE `invoice` SET `value`='" + payment + "' WHERE `invoice_id`='" + invoiceId + "'");
            JOptionPane.showMessageDialog(this, "Payment update successfull", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearPaidFields();
            loadDueData("");
            loadPaidData("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_paidUpdateButtonActionPerformed

    private void paidPrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidPrintButtonActionPerformed
        Date fdate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(fdate);

        String invoiceId = paidInvoiceIdField.getText();
        try {
            ResultSet result = MySQL.executeSearch("SELECT * FROM `invoice` "
                    + "INNER JOIN `student_enrollment` ON `invoice`.`student_enrollment_id` = `student_enrollment`.`student_enrollment_id` "
                    + "INNER JOIN `student` ON `student_enrollment`.`student_s_no` = `student`.`s_no` "
                    + "INNER JOIN `class` ON `student_enrollment`.`class_class_no` = `class`.class_no "
                    + "INNER JOIN `teacher_has_subject` ON `class`.`teacher_has_subject_id` = `teacher_has_subject`.`teacher_subject_id` "
                    + "INNER JOIN `teacher` ON `teacher_has_subject`.`teacher_t_no` = `teacher`.`t_no` "
                    + "INNER JOIN `subject` ON `teacher_has_subject`.`subject_sub_no` = `subject`.`sub_no` "
                    + "INNER JOIN `payment_status` ON `invoice`.`payment_status_id` = `payment_status`.`status_id` "
                    + "WHERE `invoice`.`invoice_id`='" + invoiceId + "'");

            if (result.next()) {
                String studentName = result.getString("student.first_name") + " " + result.getString("student.last_name");
                String studentEmail = result.getString("student.email");
                String teacherName = result.getString("teacher.first_name") + " " + result.getString("teacher.last_name");
                String subject = result.getString("subject.name");
                String paidDate = result.getString("invoice.payment_date");
                String amount = result.getString("invoice.value");

                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("Parameter1", date);
                parameters.put("Parameter2", invoiceId);
                parameters.put("Parameter3", studentName);
                parameters.put("Parameter4", studentEmail);
                parameters.put("Parameter5", teacherName);
                parameters.put("Parameter6", subject);
                parameters.put("Parameter7", paidDate);
                parameters.put("Parameter8", amount);

                JREmptyDataSource datasource = new JREmptyDataSource();

                JasperPrint report = JasperFillManager.fillReport("src/reports/invoice.jasper", parameters, datasource);
                JasperViewer.viewReport(report, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_paidPrintButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField dueAmountField;
    private javax.swing.JTextField dueClassNoField;
    private javax.swing.JButton dueClearButton;
    private javax.swing.JTable dueDataTable;
    private javax.swing.JTextField dueInvoiceIdField;
    private javax.swing.JTextField duePaidAmountField;
    private javax.swing.JButton duePayButton;
    private javax.swing.JButton dueSearchButton;
    private javax.swing.JTextField dueStudentEmailField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField paidAmountField;
    private javax.swing.JTextField paidClassIdField;
    private javax.swing.JButton paidClearButton;
    private javax.swing.JTable paidDataTable;
    private javax.swing.JTextField paidInvoiceIdField;
    private javax.swing.JButton paidPrintButton;
    private javax.swing.JButton paidSearchButton;
    private javax.swing.JTextField paidStudentEmailField;
    private javax.swing.JButton paidUpdateButton;
    private javax.swing.JTextField payAmountField;
    private javax.swing.JButton payButton;
    private javax.swing.JTextField payClassIdField;
    private javax.swing.JTextField payInvoiceNoField;
    private javax.swing.JTextField payStudentEmailField;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables
}
