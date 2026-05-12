package HospitalManagementPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Patient {

    private static int idCounter = 1000;

    private final String patientId;
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String contactNumber;
    private String address;
    private final List<String> medicalHistory;

    public Patient(String fullName, String dateOfBirth, String gender,
            String contactNumber, String address) {
        this.patientId = "P" + (++idCounter);
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.address = address;
        this.medicalHistory = new ArrayList<String>();
    }

    public String getPatientId() {
        return patientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addMedicalHistory(String entry) {
        if (entry != null && !entry.trim().isEmpty()) {
            medicalHistory.add(entry);
        }
    }

    public List<String> getMedicalHistory() {
        return Collections.unmodifiableList(medicalHistory);
    }

    @Override
    public String toString() {
        return patientId + " - " + fullName;
    }
}
