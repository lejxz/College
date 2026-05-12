package HospitalManagementPackage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Patient Management Form.
 * Covers: register new patients, update personal details, view medical history.
 */
public class PatientForm extends javax.swing.JFrame {

    private final HospitalDataStore store = HospitalDataStore.getInstance();
    private DefaultTableModel patientModel;

    public PatientForm() {
        initComponents();
        setLocationRelativeTo(null);
        initTable();
        refreshTable();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        splitPanel = new javax.swing.JPanel();
        formPanel = new javax.swing.JPanel();
        lblFullName = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        lblDOB = new javax.swing.JLabel();
        txtDOB = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox<String>();
        lblContact = new javax.swing.JLabel();
        txtContact = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        btnPanel = new javax.swing.JPanel();
        btnRegister = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnViewHistory = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        scrollPatients = new javax.swing.JScrollPane();
        tblPatients = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Patient Management");
        setMinimumSize(new java.awt.Dimension(900, 580));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        mainPanel.setLayout(new java.awt.BorderLayout(0, 10));

        // --- Header ---
        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblHeader.setForeground(new java.awt.Color(0, 153, 102));
        lblHeader.setText("Patient Management");
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        // --- Split: form (WEST) + table (CENTER) ---
        splitPanel.setBackground(new java.awt.Color(255, 255, 255));
        splitPanel.setLayout(new java.awt.BorderLayout(10, 0));

        // Form panel (left)
        formPanel.setBackground(new java.awt.Color(255, 255, 255));
        formPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Information"));
        formPanel.setPreferredSize(new java.awt.Dimension(340, 0));
        formPanel.setLayout(new java.awt.GridBagLayout());

        lblFullName.setText("Full Name:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 4, 8);
        formPanel.add(lblFullName, gridBagConstraints);

        txtFullName.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        formPanel.add(txtFullName, gridBagConstraints);

        lblDOB.setText("Date of Birth (YYYY-MM-DD):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 4, 8);
        formPanel.add(lblDOB, gridBagConstraints);

        txtDOB.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        formPanel.add(txtDOB, gridBagConstraints);

        lblGender.setText("Gender:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 4, 8);
        formPanel.add(lblGender, gridBagConstraints);

        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<String>(
                new String[]{"Male", "Female", "Other"}));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        formPanel.add(cmbGender, gridBagConstraints);

        lblContact.setText("Contact Number:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 4, 8);
        formPanel.add(lblContact, gridBagConstraints);

        txtContact.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        formPanel.add(txtContact, gridBagConstraints);

        lblAddress.setText("Address:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 4, 8);
        formPanel.add(lblAddress, gridBagConstraints);

        txtAddress.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 8, 8);
        formPanel.add(txtAddress, gridBagConstraints);

        // Button panel
        btnPanel.setBackground(new java.awt.Color(255, 255, 255));
        btnPanel.setLayout(new java.awt.GridLayout(5, 1, 0, 6));

        btnRegister.setBackground(new java.awt.Color(0, 153, 102));
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("Register Patient");
        btnRegister.setFocusPainted(false);
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });
        btnPanel.add(btnRegister);

        btnUpdate.setBackground(new java.awt.Color(0, 102, 153));
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Selected");
        btnUpdate.setFocusPainted(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        btnPanel.add(btnUpdate);

        btnViewHistory.setBackground(new java.awt.Color(102, 51, 153));
        btnViewHistory.setForeground(new java.awt.Color(255, 255, 255));
        btnViewHistory.setText("View Medical History");
        btnViewHistory.setFocusPainted(false);
        btnViewHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewHistoryActionPerformed(evt);
            }
        });
        btnPanel.add(btnViewHistory);

        btnClear.setBackground(new java.awt.Color(153, 51, 51));
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear Fields");
        btnClear.setFocusPainted(false);
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        btnPanel.add(btnClear);

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 8, 8, 8);
        formPanel.add(btnPanel, gridBagConstraints);

        splitPanel.add(formPanel, java.awt.BorderLayout.WEST);

        // Table panel (right)
        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Registered Patients"));
        tablePanel.setLayout(new java.awt.BorderLayout());

        tblPatients.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Patient ID", "Full Name", "Date of Birth", "Gender", "Contact"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblPatients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblPatients.getSelectionModel().addListSelectionListener(
                new javax.swing.event.ListSelectionListener() {
                    public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                        populateFormFromTable();
                    }
                });
        scrollPatients.setViewportView(tblPatients);
        tablePanel.add(scrollPatients, java.awt.BorderLayout.CENTER);

        splitPanel.add(tablePanel, java.awt.BorderLayout.CENTER);
        mainPanel.add(splitPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {
        String name = txtFullName.getText().trim();
        String dob = txtDOB.getText().trim();
        String gender = (String) cmbGender.getSelectedItem();
        String contact = txtContact.getText().trim();
        String address = txtAddress.getText().trim();

        if (name.isEmpty() || dob.isEmpty() || contact.isEmpty() || address.isEmpty()) {
            showMessage("Please fill in all fields.");
            return;
        }

        Patient patient = new Patient(name, dob, gender, contact, address);
        store.addPatient(patient);
        refreshTable();
        clearFields();
        showMessage("Patient registered successfully.\nID: " + patient.getPatientId());
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblPatients.getSelectedRow();
        if (row < 0) {
            showMessage("Select a patient from the table to update.");
            return;
        }

        String name = txtFullName.getText().trim();
        String dob = txtDOB.getText().trim();
        String gender = (String) cmbGender.getSelectedItem();
        String contact = txtContact.getText().trim();
        String address = txtAddress.getText().trim();

        if (name.isEmpty() || dob.isEmpty() || contact.isEmpty() || address.isEmpty()) {
            showMessage("Please fill in all fields before updating.");
            return;
        }

        String patientId = String.valueOf(patientModel.getValueAt(row, 0));
        for (Patient p : store.getPatients()) {
            if (p.getPatientId().equals(patientId)) {
                p.setFullName(name);
                p.setDateOfBirth(dob);
                p.setGender(gender);
                p.setContactNumber(contact);
                p.setAddress(address);
                break;
            }
        }
        refreshTable();
        clearFields();
        showMessage("Patient record updated successfully.");
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnViewHistoryActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblPatients.getSelectedRow();
        if (row < 0) {
            showMessage("Select a patient to view their medical history.");
            return;
        }

        String patientId = String.valueOf(patientModel.getValueAt(row, 0));
        for (Patient p : store.getPatients()) {
            if (p.getPatientId().equals(patientId)) {
                if (p.getMedicalHistory().isEmpty()) {
                    showMessage("No medical history on record for " + p.getFullName() + ".");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Medical History — ").append(p.getFullName()).append("\n\n");
                for (String entry : p.getMedicalHistory()) {
                    sb.append("• ").append(entry).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString(),
                        "Medical History", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }//GEN-LAST:event_btnViewHistoryActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {
        clearFields();
        tblPatients.clearSelection();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void initTable() {
        patientModel = (DefaultTableModel) tblPatients.getModel();
    }

    private void refreshTable() {
        patientModel.setRowCount(0);
        for (Patient p : store.getPatients()) {
            patientModel.addRow(new Object[]{
                p.getPatientId(), p.getFullName(),
                p.getDateOfBirth(), p.getGender(), p.getContactNumber()
            });
        }
    }

    private void populateFormFromTable() {
        int row = tblPatients.getSelectedRow();
        if (row < 0) return;

        String patientId = String.valueOf(patientModel.getValueAt(row, 0));
        for (Patient p : store.getPatients()) {
            if (p.getPatientId().equals(patientId)) {
                txtFullName.setText(p.getFullName());
                txtDOB.setText(p.getDateOfBirth());
                cmbGender.setSelectedItem(p.getGender());
                txtContact.setText(p.getContactNumber());
                txtAddress.setText(p.getAddress());
                break;
            }
        }
    }

    private void clearFields() {
        txtFullName.setText("");
        txtDOB.setText("");
        cmbGender.setSelectedIndex(0);
        txtContact.setText("");
        txtAddress.setText("");
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Patient Management",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnViewHistory;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblDOB;
    private javax.swing.JLabel lblFullName;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollPatients;
    private javax.swing.JPanel splitPanel;
    private javax.swing.JTable tblPatients;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtDOB;
    private javax.swing.JTextField txtFullName;
    // End of variables declaration//GEN-END:variables
}
