package HospitalManagementPackage;

public class Room {

    public enum RoomType {
        PRIVATE, SEMI_PRIVATE, WARD, ICU
    }

    private final String roomNumber;
    private final RoomType type;
    private boolean occupied;
    private Patient currentPatient;

    public Room(String roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.occupied = false;
        this.currentPatient = null;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public boolean assign(Patient patient) {
        if (occupied || patient == null) {
            return false;
        }
        this.currentPatient = patient;
        this.occupied = true;
        return true;
    }

    public void vacate() {
        this.currentPatient = null;
        this.occupied = false;
    }

    @Override
    public String toString() {
        return roomNumber + " [" + type + "] - "
                + (occupied ? "OCCUPIED" : "AVAILABLE");
    }
}
