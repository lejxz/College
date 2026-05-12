package HospitalManagementPackage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Billing and Payment Form.
 * Covers: generate bills, add billing items, mark payment status, view reports.
 */
public class BillingForm extends javax.swing.JFrame {

    private final HospitalDataStore store = HospitalDataStore.getInstance();
    private DefaultTableModel billModel;
    private DefaultTableModel itemModel;
    private Bill activeBill = null;

    public BillingForm() {
        initComponents();
        setLocationRelativeTo(null);
        initTables();
        loadPatients();
        refreshBillTable();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        topPanel = new javax.swing.JPanel();
        billFormPanel = new javax.swing.JPanel();
        lblPatient = new javax.swing.JLabel();
        cmbPatient = new javax.swing.JComboBox<Patient>();
        btnCreateBill = new javax.swing.JButton();
        addItemPanel = new javax.swing.JPanel();
        lblDescription = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        lblAmount = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        btnAddItem = new javax.swing.JButton();
        lblActiveBill = new javax.swing.JLabel();
        btnPanel = new javax.swing.JPanel();
        btnMarkPaid = new javax.swing.JButton();
        btnMarkPartial = new javax.swing.JButton();
        btnViewReport = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();
        billTablePanel = new javax.swing.JPanel();
        scrollBills = new javax.swing.JScrollPane();
        tblBills = new javax.swing.JTable();
        itemTablePanel = new javax.swing.JPanel();
        scrollItems = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Billing and Payment");
        setMinimumSize(new java.awt.Dimension(940, 580));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        mainPanel.setLayout(new java.awt.BorderLayout(0, 10));

        // --- Header ---
        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblHeader.setForeground(new java.awt.Color(102, 0, 153));
        lblHeader.setText("Billing and Payment");
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        // --- Top Panel (bill creation + item addition) ---
        topPanel.setBackground(new java.awt.Color(255, 255, 255));
        topPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        // Bill creation sub-panel
        billFormPanel.setBackground(new java.awt.Color(255, 255, 255));
        billFormPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Create Bill"));
        billFormPanel.setLayout(new java.awt.GridBagLayout());

        lblPatient.setText("Patient:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        billFormPanel.add(lblPatient, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        billFormPanel.add(cmbPatient, gridBagConstraints);

        btnCreateBill.setBackground(new java.awt.Color(102, 0, 153));
        btnCreateBill.setForeground(new java.awt.Color(255, 255, 255));
        btnCreateBill.setText("Create New Bill");
        btnCreateBill.setFocusPainted(false);
        btnCreateBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateBillActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        billFormPanel.add(btnCreateBill, gridBagConstraints);

        lblActiveBill.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblActiveBill.setForeground(new java.awt.Color(100, 100, 100));
        lblActiveBill.setText("No active bill.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 8, 6, 8);
        billFormPanel.add(lblActiveBill, gridBagConstraints);

        topPanel.add(billFormPanel);

        // Item addition sub-panel
        addItemPanel.setBackground(new java.awt.Color(255, 255, 255));
        addItemPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Billing Item"));
        addItemPanel.setLayout(new java.awt.GridBagLayout());

        lblDescription.setText("Description:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        addItemPanel.add(lblDescription, gridBagConstraints);

        txtDescription.setColumns(16);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        addItemPanel.add(txtDescription, gridBagConstraints);

        lblAmount.setText("Amount (PHP):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        addItemPanel.add(lblAmount, gridBagConstraints);

        txtAmount.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        addItemPanel.add(txtAmount, gridBagConstraints);

        btnAddItem.setBackground(new java.awt.Color(0, 153, 102));
        btnAddItem.setForeground(new java.awt.Color(255, 255, 255));
        btnAddItem.setText("Add Item to Bill");
        btnAddItem.setFocusPainted(false);
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(8, 8, 6, 8);
        addItemPanel.add(btnAddItem, gridBagConstraints);

        topPanel.add(addItemPanel);
        mainPanel.add(topPanel, java.awt.BorderLayout.NORTH);

        // --- Center: Bills table + Items table ---
        centerPanel.setBackground(new java.awt.Color(255, 255, 255));
        centerPanel.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        billTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        billTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("All Bills"));
        billTablePanel.setLayout(new java.awt.BorderLayout(0, 6));

        tblBills.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Bill ID", "Patient", "Total (PHP)", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        });
        tblBills.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBills.getSelectionModel().addListSelectionListener(
                new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                        onBillSelected();
                    }
                });
        scrollBills.setViewportView(tblBills);

        // Action buttons row
        btnPanel.setBackground(new java.awt.Color(255, 255, 255));
        btnPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 0));

        btnMarkPaid.setBackground(new java.awt.Color(0, 153, 102));
        btnMarkPaid.setForeground(new java.awt.Color(255, 255, 255));
        btnMarkPaid.setText("Mark Paid");
        btnMarkPaid.setFocusPainted(false);
        btnMarkPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkPaidActionPerformed(evt);
            }
        });
        btnPanel.add(btnMarkPaid);

        btnMarkPartial.setBackground(new java.awt.Color(204, 153, 0));
        btnMarkPartial.setForeground(new java.awt.Color(255, 255, 255));
        btnMarkPartial.setText("Partial");
        btnMarkPartial.setFocusPainted(false);
        btnMarkPartial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMarkPartialActionPerformed(evt);
            }
        });
        btnPanel.add(btnMarkPartial);

        btnViewReport.setBackground(new java.awt.Color(0, 102, 153));
        btnViewReport.setForeground(new java.awt.Color(255, 255, 255));
        btnViewReport.setText("Report");
        btnViewReport.setFocusPainted(false);
        btnViewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewReportActionPerformed(evt);
            }
        });
        btnPanel.add(btnViewReport);

        btnBack.setBackground(new java.awt.Color(70, 70, 70));
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        btnPanel.add(btnBack);

        billTablePanel.add(scrollBills, java.awt.BorderLayout.CENTER);
        billTablePanel.add(btnPanel, java.awt.BorderLayout.PAGE_END);
        centerPanel.add(billTablePanel);

        // Bill items table
        itemTablePanel.setBackground(new java.awt.Color(255, 255, 255));
        itemTablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Items (selected bill)"));
        itemTablePanel.setLayout(new java.awt.BorderLayout(0, 6));

        tblItems.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Description", "Amount (PHP)"}
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        });
        scrollItems.setViewportView(tblItems);
        itemTablePanel.add(scrollItems, java.awt.BorderLayout.CENTER);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("Total: PHP 0.00");
        itemTablePanel.add(lblTotal, java.awt.BorderLayout.PAGE_END);

        centerPanel.add(itemTablePanel);
        mainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateBillActionPerformed(java.awt.event.ActionEvent evt) {
        Patient patient = (Patient) cmbPatient.getSelectedItem();
        if (patient == null) {
            showMessage("Select a patient to create a bill.");
            return;
        }
        activeBill = new Bill(patient);
        store.addBill(activeBill);
        refreshBillTable();
        lblActiveBill.setText("Active: " + activeBill.getBillId() + " — " + patient.getFullName());
        showMessage("Bill created: " + activeBill.getBillId());
    }//GEN-LAST:event_btnCreateBillActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {
        if (activeBill == null) {
            showMessage("Create a bill first before adding items.");
            return;
        }
        String description = txtDescription.getText().trim();
        String amountText = txtAmount.getText().trim();

        if (description.isEmpty() || amountText.isEmpty()) {
            showMessage("Enter both a description and an amount.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText);
            if (amount < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showMessage("Enter a valid positive amount.");
            return;
        }

        activeBill.addItem(description, amount);
        refreshBillTable();
        refreshItemTable(activeBill);
        txtDescription.setText("");
        txtAmount.setText("");
    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnMarkPaidActionPerformed(java.awt.event.ActionEvent evt) {
        Bill selected = getSelectedBill();
        if (selected == null) return;
        selected.setPaymentStatus(Bill.PaymentStatus.PAID);
        refreshBillTable();
        showMessage("Bill " + selected.getBillId() + " marked as PAID.");
    }//GEN-LAST:event_btnMarkPaidActionPerformed

    private void btnMarkPartialActionPerformed(java.awt.event.ActionEvent evt) {
        Bill selected = getSelectedBill();
        if (selected == null) return;
        selected.setPaymentStatus(Bill.PaymentStatus.PARTIAL);
        refreshBillTable();
        showMessage("Bill " + selected.getBillId() + " marked as PARTIAL.");
    }//GEN-LAST:event_btnMarkPartialActionPerformed

    private void btnViewReportActionPerformed(java.awt.event.ActionEvent evt) {
        if (store.getBills().isEmpty()) {
            showMessage("No billing records to report.");
            return;
        }
        double totalRevenue = 0;
        int paid = 0, unpaid = 0, partial = 0;
        for (Bill b : store.getBills()) {
            totalRevenue += b.getTotalAmount();
            switch (b.getPaymentStatus()) {
                case PAID: paid++; break;
                case UNPAID: unpaid++; break;
                case PARTIAL: partial++; break;
            }
        }
        String report = "--- Billing Summary Report ---\n\n"
                + "Total Bills:    " + store.getBills().size() + "\n"
                + "  Paid:         " + paid + "\n"
                + "  Partial:      " + partial + "\n"
                + "  Unpaid:       " + unpaid + "\n\n"
                + "Total Revenue:  PHP " + String.format("%.2f", totalRevenue);
        JOptionPane.showMessageDialog(this, report, "Billing Report",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnViewReportActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void onBillSelected() {
        Bill selected = getSelectedBill();
        if (selected != null) {
            refreshItemTable(selected);
        }
    }

    private Bill getSelectedBill() {
        int row = tblBills.getSelectedRow();
        if (row < 0) {
            showMessage("Select a bill from the table first.");
            return null;
        }
        String billId = String.valueOf(billModel.getValueAt(row, 0));
        for (Bill b : store.getBills()) {
            if (b.getBillId().equals(billId)) return b;
        }
        return null;
    }

    private void initTables() {
        billModel = (DefaultTableModel) tblBills.getModel();
        itemModel = (DefaultTableModel) tblItems.getModel();
    }

    private void loadPatients() {
        DefaultComboBoxModel<Patient> model = new DefaultComboBoxModel<Patient>();
        for (Patient p : store.getPatients()) {
            model.addElement(p);
        }
        cmbPatient.setModel(model);
    }

    private void refreshBillTable() {
        billModel.setRowCount(0);
        for (Bill b : store.getBills()) {
            billModel.addRow(new Object[]{
                b.getBillId(),
                b.getPatient().getFullName(),
                String.format("%.2f", b.getTotalAmount()),
                b.getPaymentStatus()
            });
        }
    }

    private void refreshItemTable(Bill bill) {
        itemModel.setRowCount(0);
        for (String[] item : bill.getItems()) {
            itemModel.addRow(new Object[]{item[0], item[1]});
        }
        lblTotal.setText("Total: PHP " + String.format("%.2f", bill.getTotalAmount()));
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Billing",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addItemPanel;
    private javax.swing.JPanel billFormPanel;
    private javax.swing.JPanel billTablePanel;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCreateBill;
    private javax.swing.JButton btnMarkPaid;
    private javax.swing.JButton btnMarkPartial;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton btnViewReport;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JComboBox<Patient> cmbPatient;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JPanel itemTablePanel;
    private javax.swing.JLabel lblActiveBill;
    private javax.swing.JLabel lblAmount;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollBills;
    private javax.swing.JScrollPane scrollItems;
    private javax.swing.JTable tblBills;
    private javax.swing.JTable tblItems;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtDescription;
    // End of variables declaration//GEN-END:variables
}
