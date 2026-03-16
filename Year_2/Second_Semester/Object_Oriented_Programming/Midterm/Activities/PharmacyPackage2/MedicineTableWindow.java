package PharmacyPackage2;

import java.util.List;

public class MedicineTableWindow extends javax.swing.JFrame {

    private final PharmacyForm parentForm;
    private final List<Medicine> medicineList;
    private final javax.swing.table.DefaultTableModel tableModel;

    public MedicineTableWindow(PharmacyForm parentForm,
            List<Medicine> medicineList,
            javax.swing.table.DefaultTableModel tableModel) {
        this.parentForm = parentForm;
        this.medicineList = medicineList;
        this.tableModel = tableModel;

        initComponents();
        configureTable();
        refreshTable();
        setLocationRelativeTo(null);
    }

    private void configureTable() {
        medicineTable.setModel(tableModel);
        medicineTable.getColumnModel().getColumn(0).setPreferredWidth(30);
        medicineTable.getColumnModel().getColumn(1).setPreferredWidth(130);
        medicineTable.getColumnModel().getColumn(2).setPreferredWidth(130);
        medicineTable.getColumnModel().getColumn(3).setPreferredWidth(220);
        medicineTable.getColumnModel().getColumn(4).setPreferredWidth(90);
    }

    public final void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < medicineList.size(); i++) {
            Medicine medicine = medicineList.get(i);
            tableModel.addRow(new Object[]{
                i + 1,
                medicine.getGenericName(),
                medicine.getBrandName(),
                medicine.getDescription(),
                String.format("%.1f", medicine.getMg())
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        medicineTable = new javax.swing.JTable();
        footerPanel = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("My Pharmacy - Medicine List");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        mainPanel.setLayout(new java.awt.BorderLayout(0, 8));

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(153, 0, 255));
        lblTitle.setText("Medicine List");
        headerPanel.add(lblTitle);

        mainPanel.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Saved Medicines"));

        medicineTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        medicineTable.setRowHeight(24);
        medicineTable.setSelectionBackground(new java.awt.Color(102, 102, 102));
        scrollPane.setViewportView(medicineTable);

        mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

        footerPanel.setBackground(new java.awt.Color(255, 255, 255));
        footerPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnBack.setBackground(new java.awt.Color(30, 120, 200));
        btnBack.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back to Form");
        btnBack.setFocusPainted(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        footerPanel.add(btnBack);

        mainPanel.add(footerPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        returnToForm();
    }//GEN-LAST:event_btnBackActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        returnToForm();
    }//GEN-LAST:event_formWindowClosing

    private void returnToForm() {
        parentForm.setVisible(true);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable medicineTable;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
