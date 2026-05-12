package HospitalManagementPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bill {

    public enum PaymentStatus {
        UNPAID, PAID, PARTIAL
    }

    private static int idCounter = 0;

    private final String billId;
    private final Patient patient;
    private final List<String[]> items;
    private double totalAmount;
    private PaymentStatus paymentStatus;

    public Bill(Patient patient) {
        this.billId = String.format("BILL%04d", ++idCounter);
        this.patient = patient;
        this.items = new ArrayList<String[]>();
        this.totalAmount = 0.0;
        this.paymentStatus = PaymentStatus.UNPAID;
    }

    public void addItem(String description, double amount) {
        if (description != null && amount >= 0) {
            items.add(new String[]{description, String.format("%.2f", amount)});
            totalAmount += amount;
        }
    }

    public String getBillId() {
        return billId;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<String[]> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String toString() {
        return billId + " - " + patient.getFullName()
                + " - PHP " + String.format("%.2f", totalAmount)
                + " [" + paymentStatus + "]";
    }
}
