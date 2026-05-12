package HospitalManagementPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Department {

    private final String name;
    private final List<Doctor> doctors;

    public Department(String name) {
        this.name = name;
        this.doctors = new ArrayList<Doctor>();
    }

    public String getName() {
        return name;
    }

    public void addDoctor(Doctor doctor) {
        if (doctor != null && !doctors.contains(doctor)) {
            doctors.add(doctor);
        }
    }

    public List<Doctor> getDoctors() {
        return Collections.unmodifiableList(doctors);
    }

    @Override
    public String toString() {
        return name;
    }
}
