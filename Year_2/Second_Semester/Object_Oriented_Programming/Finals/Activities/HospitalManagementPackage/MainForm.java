package HospitalManagementPackage;

import javax.swing.ImageIcon;

/**
 * Main dashboard form for the Hospital Management System.
 * Acts as the navigation hub — opens each module form.
 */
public class MainForm extends javax.swing.JFrame {

    public MainForm() {
        initComponents();
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        titlePanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        lblSubtitle = new javax.swing.JLabel();
        menuPanel = new javax.swing.JPanel();
        btnPatients = new javax.swing.JButton();
        btnAppointments = new javax.swing.JButton();
        btnAdmission = new javax.swing.JButton();
        btnBilling = new javax.swing.JButton();
        footerPanel = new javax.swing.JPanel();
        lblFooter = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hospital Management System");
        setMinimumSize(new java.awt.Dimension(720, 480));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 16, 16));
        mainPanel.setLayout(new java.awt.BorderLayout(0, 12));

        // --- Title Panel ---
        titlePanel.setBackground(new java.awt.Color(0, 102, 153));
        titlePanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        titlePanel.setLayout(new java.awt.GridLayout(2, 1, 0, 4));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Hospital Management System");
        titlePanel.add(lblTitle);

        lblSubtitle.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtitle.setForeground(new java.awt.Color(204, 229, 255));
        lblSubtitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSubtitle.setText("Select a module to continue");
        titlePanel.add(lblSubtitle);

        mainPanel.add(titlePanel, java.awt.BorderLayout.PAGE_START);

        // --- Module Button Grid ---
        menuPanel.setBackground(new java.awt.Color(255, 255, 255));
        menuPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 40, 20, 40));
        menuPanel.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        btnPatients.setBackground(new java.awt.Color(0, 153, 102));
        btnPatients.setForeground(new java.awt.Color(255, 255, 255));
        btnPatients.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnPatients.setText("<html><center>Patient<br>Management</center></html>");
        btnPatients.setFocusPainted(false);
        btnPatients.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPatients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatientsActionPerformed(evt);
            }
        });
        menuPanel.add(btnPatients);

        btnAppointments.setBackground(new java.awt.Color(0, 102, 153));
        btnAppointments.setForeground(new java.awt.Color(255, 255, 255));
        btnAppointments.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnAppointments.setText("<html><center>Appointment<br>Scheduling</center></html>");
        btnAppointments.setFocusPainted(false);
        btnAppointments.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAppointments.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAppointmentsActionPerformed(evt);
            }
        });
        menuPanel.add(btnAppointments);

        btnAdmission.setBackground(new java.awt.Color(153, 76, 0));
        btnAdmission.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmission.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnAdmission.setText("<html><center>Admission &amp;<br>Room Management</center></html>");
        btnAdmission.setFocusPainted(false);
        btnAdmission.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmission.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdmissionActionPerformed(evt);
            }
        });
        menuPanel.add(btnAdmission);

        btnBilling.setBackground(new java.awt.Color(102, 0, 153));
        btnBilling.setForeground(new java.awt.Color(255, 255, 255));
        btnBilling.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnBilling.setText("<html><center>Billing &amp;<br>Payment</center></html>");
        btnBilling.setFocusPainted(false);
        btnBilling.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBillingActionPerformed(evt);
            }
        });
        menuPanel.add(btnBilling);

        mainPanel.add(menuPanel, java.awt.BorderLayout.CENTER);

        // --- Footer ---
        footerPanel.setBackground(new java.awt.Color(240, 240, 240));
        footerPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 12, 6, 12));

        lblFooter.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblFooter.setForeground(new java.awt.Color(120, 120, 120));
        lblFooter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFooter.setText("Hospital Management System v1.0");
        footerPanel.add(lblFooter);

        mainPanel.add(footerPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPatientsActionPerformed(java.awt.event.ActionEvent evt) {
        new PatientForm().setVisible(true);
    }//GEN-LAST:event_btnPatientsActionPerformed

    private void btnAppointmentsActionPerformed(java.awt.event.ActionEvent evt) {
        if (HospitalDataStore.getInstance().getPatients().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No patients registered yet. Please register a patient first.",
                    "No Patients", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        new AppointmentForm().setVisible(true);
    }//GEN-LAST:event_btnAppointmentsActionPerformed

    private void btnAdmissionActionPerformed(java.awt.event.ActionEvent evt) {
        if (HospitalDataStore.getInstance().getPatients().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No patients registered yet. Please register a patient first.",
                    "No Patients", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        new AdmissionForm().setVisible(true);
    }//GEN-LAST:event_btnAdmissionActionPerformed

    private void btnBillingActionPerformed(java.awt.event.ActionEvent evt) {
        if (HospitalDataStore.getInstance().getPatients().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "No patients registered yet. Please register a patient first.",
                    "No Patients", javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }
        new BillingForm().setVisible(true);
    }//GEN-LAST:event_btnBillingActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmission;
    private javax.swing.JButton btnAppointments;
    private javax.swing.JButton btnBilling;
    private javax.swing.JButton btnPatients;
    private javax.swing.JLabel lblFooter;
    private javax.swing.JLabel lblSubtitle;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables
}
