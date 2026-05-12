package HospitalManagementPackage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Appointment Scheduling Form.
 * Covers: book appointments, prevent scheduling conflicts, cancel appointments.
 */
public class AppointmentForm extends javax.swing.JFrame {

    private final HospitalDataStore store = HospitalDataStore.getInstance();
    private DefaultTableModel appointmentModel;

    public AppointmentForm() {
        initComponents();
        setLocationRelativeTo(null);
        initTable();
        loadPatients();
        loadDoctors();
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
        lblDoctor = new javax.swing.JLabel();
        cmbDoctor = new javax.swing.JComboBox<Doctor>();
        lblDate = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        lblTime = new javax.swing.JLabel();
        cmbTime = new javax.swing.JComboBox<String>();
        btnPanel = new javax.swing.JPanel();
        btnBook = new javax.swing.JButton();
        btnComplete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        scrollAppointments = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Appointment Scheduling");
        setMinimumSize(new java.awt.Dimension(900, 560));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        mainPanel.setLayout(new java.awt.BorderLayout(0, 10));

        // --- Header ---
        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 22));
        lblHeader.setForeground(new java.awt.Color(0, 102, 153));
        lblHeader.setText("Appointment Scheduling");
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        // --- Form Panel ---
        formPanel.setBackground(new java.awt.Color(255, 255, 255));
        formPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Book an Appointment"));
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

        lblDoctor.setText("Doctor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(lblDoctor, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(cmbDoctor, gridBagConstraints);

        lblDate.setText("Date (YYYY-MM-DD):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(lblDate, gridBagConstraints);

        txtDate.setColumns(14);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(txtDate, gridBagConstraints);

        lblTime.setText("Time Slot:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(lblTime, gridBagConstraints);

        cmbTime.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[]{
            "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM",
            "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM"
        }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1; gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(6, 8, 6, 8);
        formPanel.add(cmbTime, gridBagConstraints);

        // Buttons
        btnPanel.setBackground(new java.awt.Color(255, 255, 255));
        btnPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 0));

        btnBook.setBackground(new java.awt.Color(0, 153, 102));
        btnBook.setForeground(new java.awt.Color(255, 255, 255));
        btnBook.setText("Book Appointment");
        btnBook.setFocusPainted(false);
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });
        btnPanel.add(btnBook);

        btnComplete.setBackground(new java.awt.Color(0, 102, 153));
        btnComplete.setForeground(new java.awt.Color(255, 255, 255));
        btnComplete.setText("Mark Completed");
        btnComplete.setFocusPainted(false);
        btnComplete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompleteActionPerformed(evt);
            }
        });
        btnPanel.add(btnComplete);

        btnCancel.setBackground(new java.awt.Color(204, 102, 0));
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Cancel Appointment");
        btnCancel.setFocusPainted(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        btnPanel.add(btnCancel);

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
        gridBagConstraints.gridx = 0; gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 8, 6, 8);
        formPanel.add(btnPanel, gridBagConstraints);

        mainPanel.add(formPanel, java.awt.BorderLayout.NORTH);

        // --- Appointments Table ---
        tablePanel.setBackground(new java.awt.Color(255, 255, 255));
        tablePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Appointment Records"));
        tablePanel.setLayout(new java.awt.BorderLayout());

        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Patient", "Doctor", "Date", "Time", "Status"}
        ) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        });
        tblAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollAppointments.setViewportView(tblAppointments);
        tablePanel.add(scrollAppointments, java.awt.BorderLayout.CENTER);

        mainPanel.add(tablePanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {
        Patient patient = (Patient) cmbPatient.getSelectedItem();
        Doctor doctor = (Doctor) cmbDoctor.getSelectedItem();
        String date = txtDate.getText().trim();
        String time = (String) cmbTime.getSelectedItem();

        if (patient == null || doctor == null) {
            showMessage("Select a patient and a doctor.");
            return;
        }
        if (date.isEmpty()) {
            showMessage("Enter a date for the appointment.");
            return;
        }
        if (store.hasConflict(doctor, date, time)) {
            showMessage("Scheduling conflict: Dr. " + doctor.getFullName()
                    + " already has an appointment on " + date + " at " + time + ".");
            return;
        }

        Appointment appointment = new Appointment(patient, doctor, date, time);
        store.addAppointment(appointment);
        refreshTable();
        txtDate.setText("");
        showMessage("Appointment booked.\nID: " + appointment.getAppointmentId());
    }//GEN-LAST:event_btnBookActionPerformed

    private void btnCompleteActionPerformed(java.awt.event.ActionEvent evt) {
        updateSelectedStatus(Appointment.Status.COMPLETED, "Mark selected appointment as completed?");
    }//GEN-LAST:event_btnCompleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
        updateSelectedStatus(Appointment.Status.CANCELLED, "Cancel the selected appointment?");
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void updateSelectedStatus(Appointment.Status newStatus, String confirmMsg) {
        int row = tblAppointments.getSelectedRow();
        if (row < 0) {
            showMessage("Select an appointment from the table first.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, confirmMsg,
                "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String id = String.valueOf(appointmentModel.getValueAt(row, 0));
        for (Appointment a : store.getAppointments()) {
            if (a.getAppointmentId().equals(id)) {
                a.setStatus(newStatus);
                break;
            }
        }
        refreshTable();
    }

    private void initTable() {
        appointmentModel = (DefaultTableModel) tblAppointments.getModel();
    }

    private void loadPatients() {
        DefaultComboBoxModel<Patient> model = new DefaultComboBoxModel<Patient>();
        for (Patient p : store.getPatients()) {
            model.addElement(p);
        }
        cmbPatient.setModel(model);
    }

    private void loadDoctors() {
        DefaultComboBoxModel<Doctor> model = new DefaultComboBoxModel<Doctor>();
        for (Doctor d : store.getDoctors()) {
            model.addElement(d);
        }
        cmbDoctor.setModel(model);
    }

    private void refreshTable() {
        appointmentModel.setRowCount(0);
        for (Appointment a : store.getAppointments()) {
            appointmentModel.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatient().getFullName(),
                "Dr. " + a.getDoctor().getFullName(),
                a.getDate(),
                a.getTime(),
                a.getStatus()
            });
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Appointment Scheduling",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnComplete;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JComboBox<Doctor> cmbDoctor;
    private javax.swing.JComboBox<Patient> cmbPatient;
    private javax.swing.JComboBox<String> cmbTime;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane scrollAppointments;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextField txtDate;
    // End of variables declaration//GEN-END:variables
}
