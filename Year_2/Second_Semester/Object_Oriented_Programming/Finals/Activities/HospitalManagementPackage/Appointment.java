package HospitalManagementPackage;

public class Appointment {

    public enum Status {
        SCHEDULED, COMPLETED, CANCELLED
    }

    private static int idCounter = 0;

    private final String appointmentId;
    private final Patient patient;
    private final Doctor doctor;
    private String date;
    private String time;
    private Status status;

    public Appointment(Patient patient, Doctor doctor, String date, String time) {
        this.appointmentId = String.format("APT%03d", ++idCounter);
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
        this.status = Status.SCHEDULED;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return appointmentId + " | " + patient.getFullName()
                + " | Dr. " + doctor.getFullName()
                + " | " + date + " " + time
                + " [" + status + "]";
    }
}
