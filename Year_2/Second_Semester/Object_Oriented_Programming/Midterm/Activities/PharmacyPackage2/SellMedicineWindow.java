package PharmacyPackage2;

public class SellMedicineWindow extends javax.swing.JFrame {

    private MedicineTableWindow parentWindow;
    private Medicine medicine;

    public SellMedicineWindow() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public SellMedicineWindow(MedicineTableWindow parentWindow, Medicine medicine) {
        this();
        this.parentWindow = parentWindow;
        this.medicine = medicine;
        fillMedicineDetails();
    }

    private void fillMedicineDetails() {
        if (medicine == null) {
            lblValueGeneric.setText("-");
            lblValueBrand.setText("-");
            lblValueAvailable.setText("0");
            lblValuePrice.setText("0.00");
            return;
        }

        lblValueGeneric.setText(medicine.getGenericName());
        lblValueBrand.setText(medicine.getBrandName());
        lblValueAvailable.setText(String.valueOf(medicine.getQuantity()));
        lblValuePrice.setText(String.format("%.2f", medicine.getPrice()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        lblHeader = new javax.swing.JLabel();
        lblGeneric = new javax.swing.JLabel();
        lblValueGeneric = new javax.swing.JLabel();
        lblBrand = new javax.swing.JLabel();
        lblValueBrand = new javax.swing.JLabel();
        lblAvailable = new javax.swing.JLabel();
        lblValueAvailable = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        lblValuePrice = new javax.swing.JLabel();
        lblQtyToSell = new javax.swing.JLabel();
        txtQtyToSell = new javax.swing.JTextField();
        btnPanel = new javax.swing.JPanel();
        btnSell = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Sell Medicine");

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 14, 14, 14));
        mainPanel.setLayout(new java.awt.GridBagLayout());

        lblHeader.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        lblHeader.setForeground(new java.awt.Color(24, 142, 88));
        lblHeader.setText("Sell Medicine");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 12, 0);
        mainPanel.add(lblHeader, gridBagConstraints);

        lblGeneric.setText("Generic:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 8);
        mainPanel.add(lblGeneric, gridBagConstraints);

        lblValueGeneric.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(lblValueGeneric, gridBagConstraints);

        lblBrand.setText("Brand:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 8);
        mainPanel.add(lblBrand, gridBagConstraints);

        lblValueBrand.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(lblValueBrand, gridBagConstraints);

        lblAvailable.setText("Available Qty:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 8);
        mainPanel.add(lblAvailable, gridBagConstraints);

        lblValueAvailable.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(lblValueAvailable, gridBagConstraints);

        lblPrice.setText("Unit Price:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 4, 8);
        mainPanel.add(lblPrice, gridBagConstraints);

        lblValuePrice.setText("0.00");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        mainPanel.add(lblValuePrice, gridBagConstraints);

        lblQtyToSell.setText("Quantity to Sell:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 4, 8);
        mainPanel.add(lblQtyToSell, gridBagConstraints);

        txtQtyToSell.setColumns(10);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 4, 0);
        mainPanel.add(txtQtyToSell, gridBagConstraints);

        btnPanel.setBackground(new java.awt.Color(255, 255, 255));
        btnPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        btnSell.setBackground(new java.awt.Color(24, 142, 88));
        btnSell.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSell.setForeground(new java.awt.Color(255, 255, 255));
        btnSell.setText("Sell");
        btnSell.setFocusPainted(false);
        btnSell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSellActionPerformed(evt);
            }
        });
        btnPanel.add(btnSell);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        btnPanel.add(btnClose);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        mainPanel.add(btnPanel, gridBagConstraints);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSellActionPerformed
        if (medicine == null) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "No medicine selected.", "Selection Error",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        String qtyText = txtQtyToSell.getText().trim();
        int sellQty;

        try {
            sellQty = Integer.parseInt(qtyText);
            if (sellQty <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Enter a valid positive quantity.", "Input Error",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        double total;
        try {
            total = medicine.sellMedicine(sellQty);
        } catch (IllegalStateException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Not enough stock. Available quantity: " + medicine.getQuantity(),
                "Stock Error",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        } catch (IllegalArgumentException ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Input Error",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        fillMedicineDetails();
        txtQtyToSell.setText("");

        if (parentWindow != null) {
            parentWindow.refreshTable();
        }

        javax.swing.JOptionPane.showMessageDialog(this,
            String.format("Transaction complete. Total price: %.2f", total),
            "Sale Complete",
            javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnSellActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JButton btnSell;
    private javax.swing.JLabel lblAvailable;
    private javax.swing.JLabel lblBrand;
    private javax.swing.JLabel lblGeneric;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblQtyToSell;
    private javax.swing.JLabel lblValueAvailable;
    private javax.swing.JLabel lblValueBrand;
    private javax.swing.JLabel lblValueGeneric;
    private javax.swing.JLabel lblValuePrice;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextField txtQtyToSell;
    // End of variables declaration//GEN-END:variables
}
