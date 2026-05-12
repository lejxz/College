package HospitalManagementPackage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Admission and Room Management Form.
 * Covers: admit patients to rooms, track occupancy, discharge patients.
 */
public class AdmissionForm extends javax.swing.JFrame {

    private final HospitalDataStore store = HospitalDataStore.getInstance();
    private DefaultTableModel roomModel;

    public AdmissionForm() {
        initComponents();
        setLocationRelativeTo(null);
        initTable();
        loadPatients();
        loadAvailableRooms();
        refreshTable();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        formPanel = new javax.swing.JPanel();
        lblPatient = new javax.swing.JLabel();
        cmbPatient = new javax.swing.JComboBox<Patient>();
        lblRoom = new javax.swing.JLabel();
        cmbRoom = new javax.swing.JComboBox<Room>();
        btnPanel = new javax.swing.JPanel();
        btnAdmit = new javax.swing.JButton();
        btnDischarge = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        scrollRooms = new javax.swing.JScrollPane();
        tblRooms = new javax.swing.JTable();
        lblRoomCount = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Admission and Room Management");
        setMinimumSize(new java.awt.Dimension(880, 540));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        mainPanel.setLayout(new java.awt.BorderLayout(0, 10));

        // --- Header ---
        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblHeader.setForeground(new java.awt.Color(153, 76, 0));
        lblHeader.setText("Admission and Room Management");
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        // --- Form ---
        formPanel.setBackground(new java.awt.Color(255, 255, 255));
        formPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Admit Patient"));
        formPanel.setLayout(new java.awt.GridBagLayout());

        lblPatient.setText("Patient:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(lblPatient, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(cmbPatient, gridBagConstraints);

        lblRoom.setText("Available Room:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(lblRoom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(cmbRoom, gridBagConstraints);

        // Buttons
        btnPanel.setBackground(new java.awt.Color(255, 255, 255));
        btnPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        btnAdmit.setBackground(new java.awt.Color(0, 153, 102));
        btnAdmit.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmit.setText("Admit Patient");
        btnAdmit.setFocusPainted(false);
        btnAdmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmitActionPerformed(evt);
            }
        });
        btnPanel.add(btnAdmit);

        btnDischarge.setBackground(new java.awt.Color(204, 102, 0));
        btnDischarge.setForeground(new java.awt.Color(255, 255, 255));
        btnDischarge.setText("Discharge Selected Room");
        btnDischarge.setFocusPainted(false);
        btnDischarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDischargeActionPerformed(evt);
            }
        });
        btnPanel.add(btnDischarge);

        btnRefresh.setBackground(new java.awt.Color(70, 130, 180));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setText("Refresh");
        btnRefresh.setFocusPainted(false);
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });
        btnPanel.add(btnRefresh);

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
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 8, 6, 8);
        formPanel.add(btnPanel, gridBagConstraints);

        mainPanel.add(formPanel, java.awt.BorderLayout.NORTH);

        // --- Room Status Table ---
        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Room Status"));
        tablePanel.setLayout(new java.awt.BorderLayout(0, 6));

        tblRooms.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Room No.", "Type", "Status", "Current Patient", "Patient ID"}
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        });
        tblRooms.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollRooms.setViewportView(tblRooms);
        tablePanel.add(scrollRooms, java.awt.BorderLayout.CENTER);

        lblRoomCount.setFont(new java.awt.Font("Segoe UI", 1, 13));
        lblRoomCount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblRoomCount.setText("Available: 0 / 0");
        tablePanel.add(lblRoomCount, java.awt.BorderLayout.PAGE_END);

        mainPanel.add(tablePanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdmitActionPerformed(java.awt.event.ActionEvent evt) {
        Patient patient = (Patient) cmbPatient.getSelectedItem();
        Room room = (Room) cmbRoom.getSelectedItem();

        if (patient == null) {
            showMessage("Select a patient to admit.");
            return;
        }
        if (room == null) {
            showMessage("No available rooms. All rooms are currently occupied.");
            return;
        }

        for (Room r : store.getRooms()) {
            if (r.isOccupied() && r.getCurrentPatient().equals(patient)) {
                showMessage(patient.getFullName() + " is already admitted in Room " + r.getRoomNumber() + ".");
                return;
            }
        }

        if (room.assign(patient)) {
            patient.addMedicalHistory("Admitted to Room " + room.getRoomNumber()
                    + " [" + room.getType() + "]");
            loadAvailableRooms();
            refreshTable();
            showMessage(patient.getFullName() + " has been admitted to Room " + room.getRoomNumber() + ".");
        } else {
            showMessage("Could not assign room. It may have been taken already.");
        }
    }//GEN-LAST:event_btnAdmitActionPerformed

    private void btnDischargeActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblRooms.getSelectedRow();
        if (row < 0) {
            showMessage("Select an occupied room from the table to discharge.");
            return;
        }

        String status = String.valueOf(roomModel.getValueAt(row, 2));
        if ("AVAILABLE".equals(status)) {
            showMessage("The selected room is already vacant.");
            return;
        }

        String roomNumber = String.valueOf(roomModel.getValueAt(row, 0));
        for (Room r : store.getRooms()) {
            if (r.getRoomNumber().equals(roomNumber) && r.isOccupied()) {
                String patientName = r.getCurrentPatient().getFullName();
                r.getCurrentPatient().addMedicalHistory("Discharged from Room " + r.getRoomNumber());
                r.vacate();
                loadAvailableRooms();
                refreshTable();
                showMessage(patientName + " has been discharged from Room " + roomNumber + ".");
                return;
            }
        }
    }//GEN-LAST:event_btnDischargeActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadPatients();
        loadAvailableRooms();
        refreshTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void initTable() {
        roomModel = (DefaultTableModel) tblRooms.getModel();
    }

    private void loadPatients() {
        DefaultComboBoxModel<Patient> model = new DefaultComboBoxModel<Patient>();
        for (Patient p : store.getPatients()) {
            model.addElement(p);
        }
        cmbPatient.setModel(model);
    }

    private void loadAvailableRooms() {
        DefaultComboBoxModel<Room> model = new DefaultComboBoxModel<Room>();
        for (Room r : store.getRooms()) {
            if (!r.isOccupied()) {
                model.addElement(r);
            }
        }
        cmbRoom.setModel(model);
    }

    private void refreshTable() {
        roomModel.setRowCount(0);
        int available = 0;
        for (Room r : store.getRooms()) {
            String patientName = r.isOccupied() ? r.getCurrentPatient().getFullName() : "-";
            String patientId = r.isOccupied() ? r.getCurrentPatient().getPatientId() : "-";
            roomModel.addRow(new Object[]{
                r.getRoomNumber(), r.getType(),
                r.isOccupied() ? "OCCUPIED" : "AVAILABLE",
                patientName, patientId
            });
            if (!r.isOccupied()) available++;
        }
        lblRoomCount.setText("Available: " + available + " / " + store.getRooms().size());
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Admission Management",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmit;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDischarge;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JComboBox<Patient> cmbPatient;
    private javax.swing.JComboBox<Room> cmbRoom;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblRoom;
    private javax.swing.JLabel lblRoomCount;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollRooms;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTable tblRooms;
    // End of variables declaration//GEN-END:variables
}
