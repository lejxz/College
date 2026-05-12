package HospitalManagementPackage;

public class Doctor {

    private static int idCounter = 100;

    private final String doctorId;
    private String fullName;
    private String specialization;
    private final Department department;
    private String contactNumber;
    private boolean available;

    public Doctor(String fullName, String specialization,
            Department department, String contactNumber) {
        this.doctorId = "D" + (++idCounter);
        this.fullName = fullName;
        this.specialization = specialization;
        this.department = department;
        this.contactNumber = contactNumber;
        this.available = true;

        if (department != null) {
            department.addDoctor(this);
        }
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Department getDepartment() {
        return department;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return doctorId + " - Dr. " + fullName + " (" + specialization + ")";
    }
}
