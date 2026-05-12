package HospitalManagementPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton data store for the Hospital Management System.
 * Holds all runtime collections and seeds the initial catalog data.
 * Mirrors the role of EnrollmentCatalog in the enrollment example.
 */
public class HospitalDataStore {

    private static HospitalDataStore instance;

    private final List<Department> departments = new ArrayList<Department>();
    private final List<Doctor> doctors = new ArrayList<Doctor>();
    private final List<Patient> patients = new ArrayList<Patient>();
    private final List<Appointment> appointments = new ArrayList<Appointment>();
    private final List<Room> rooms = new ArrayList<Room>();
    private final List<Bill> bills = new ArrayList<Bill>();

    private HospitalDataStore() {
        seedData();
    }

    public static HospitalDataStore getInstance() {
        if (instance == null) {
            instance = new HospitalDataStore();
        }
        return instance;
    }

    private void seedData() {
        Department cardio = new Department("Cardiology");
        Department ortho = new Department("Orthopedics");
        Department pedia = new Department("Pediatrics");
        Department neuro = new Department("Neurology");
        Department er = new Department("Emergency");
        departments.add(cardio);
        departments.add(ortho);
        departments.add(pedia);
        departments.add(neuro);
        departments.add(er);

        Doctor d1 = new Doctor("Maria Santos", "Cardiologist", cardio, "09171234567");
        Doctor d2 = new Doctor("Jose Reyes", "Orthopedic Surgeon", ortho, "09181234567");
        Doctor d3 = new Doctor("Ana Dela Cruz", "Pediatrician", pedia, "09191234567");
        Doctor d4 = new Doctor("Ricardo Lopez", "Neurologist", neuro, "09201234567");
        Doctor d5 = new Doctor("Lourdes Bautista", "Emergency Physician", er, "09211234567");
        doctors.add(d1);
        doctors.add(d2);
        doctors.add(d3);
        doctors.add(d4);
        doctors.add(d5);

        rooms.add(new Room("101", Room.RoomType.PRIVATE));
        rooms.add(new Room("102", Room.RoomType.PRIVATE));
        rooms.add(new Room("201", Room.RoomType.SEMI_PRIVATE));
        rooms.add(new Room("202", Room.RoomType.SEMI_PRIVATE));
        rooms.add(new Room("301", Room.RoomType.WARD));
        rooms.add(new Room("302", Room.RoomType.WARD));
        rooms.add(new Room("ICU-1", Room.RoomType.ICU));
        rooms.add(new Room("ICU-2", Room.RoomType.ICU));
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void addPatient(Patient patient) {
        if (patient != null && !patients.contains(patient)) {
            patients.add(patient);
        }
    }

    public void addDoctor(Doctor doctor) {
        if (doctor != null && !doctors.contains(doctor)) {
            doctors.add(doctor);
        }
    }

    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
        }
    }

    public void addBill(Bill bill) {
        if (bill != null) {
            bills.add(bill);
        }
    }

    /**
     * Checks whether a doctor already has a scheduled appointment on the
     * same date and time, preventing scheduling conflicts.
     */
    public boolean hasConflict(Doctor doctor, String date, String time) {
        for (Appointment a : appointments) {
            if (a.getDoctor().equals(doctor)
                    && a.getDate().equals(date)
                    && a.getTime().equals(time)
                    && a.getStatus() == Appointment.Status.SCHEDULED) {
                return true;
            }
        }
        return false;
    }
}
