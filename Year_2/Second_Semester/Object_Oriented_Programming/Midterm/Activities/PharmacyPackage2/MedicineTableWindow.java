package PharmacyPackage2;

import java.util.List;

public class MedicineTableWindow extends javax.swing.JFrame {

    private final PharmacyForm parentForm;
    private final List<Medicine> medicineList;
    private final javax.swing.table.DefaultTableModel tableModel;

    public MedicineTableWindow(PharmacyForm parentForm, List<Medicine> medicineList) {
        this.parentForm = parentForm;
        this.medicineList = medicineList;
        this.tableModel = new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "#", "Generic Name", "Brand Name", "Description", "Dosage (mg)", "Qty", "Price"
            }
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

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
        medicineTable.getColumnModel().getColumn(5).setPreferredWidth(60);
        medicineTable.getColumnModel().getColumn(6).setPreferredWidth(80);
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
                String.format("%.1f", medicine.getMg()),
                medicine.getQuantity(),
                String.format("%.2f", medicine.getPrice())
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
        btnDelete = new javax.swing.JButton();
        btnSell = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("My Pharmacy - Medicine List");
        setMinimumSize(new java.awt.Dimension(800, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 12, 12, 12));
        mainPanel.setLayout(new java.awt.BorderLayout(0, 8));

        headerPanel.setBackground(new java.awt.Color(255, 255, 255));
        headerPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Title"));

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

        btnDelete.setBackground(new java.awt.Color(180, 50, 50));
        btnDelete.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Medicine");
        btnDelete.setFocusPainted(false);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        footerPanel.add(btnDelete);

        btnSell.setBackground(new java.awt.Color(24, 142, 88));
        btnSell.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSell.setForeground(new java.awt.Color(255, 255, 255));
        btnSell.setText("Sell Medicine");
        btnSell.setFocusPainted(false);
        btnSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSellActionPerformed(evt);
            }
        });
        footerPanel.add(btnSell);

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

    private void btnSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellActionPerformed
        openSellWindow();
    }//GEN-LAST:event_btnSellActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        deleteSelectedMedicine();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void openSellWindow() {
        int selectedRow = medicineTable.getSelectedRow();
        if (selectedRow < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Select a medicine first.", "Selection Required",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        Medicine selectedMedicine = medicineList.get(selectedRow);
        SellMedicineWindow sellWindow = new SellMedicineWindow(this, selectedMedicine);
        sellWindow.setLocationRelativeTo(this);
        sellWindow.setVisible(true);
    }

    private void deleteSelectedMedicine() {
        int selectedRow = medicineTable.getSelectedRow();
        if (selectedRow < 0) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Select a medicine to delete.", "Selection Required",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        Medicine selectedMedicine = medicineList.get(selectedRow);
        String medicineName = selectedMedicine.getBrandName();

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Delete medicine \"" + medicineName + "\"?",
            "Confirm Delete",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.WARNING_MESSAGE);

        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        medicineList.remove(selectedRow);
        refreshTable();

        javax.swing.JOptionPane.showMessageDialog(this,
            "Medicine deleted successfully.", "Deleted",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }

    private void returnToForm() {
        parentForm.setVisible(true);
        dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSell;
    private javax.swing.JPanel footerPanel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTable medicineTable;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
