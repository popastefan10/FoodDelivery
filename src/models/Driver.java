package models;

import java.time.LocalTime;

public class Driver extends Person {
    private LocalTime startOfWorkingHours;
    private LocalTime endOfWorkingHours;

    public Driver() {
        super();
    }

    public Driver(String email, String phoneNumber, String firstName, String lastName, LocalTime startOfWorkingHours, LocalTime endOfWorkingHours) {
        super(email, phoneNumber, firstName, lastName);
        this.startOfWorkingHours = startOfWorkingHours;
        this.endOfWorkingHours = endOfWorkingHours;
    }

    public LocalTime getStartOfWorkingHours() {
        return startOfWorkingHours;
    }

    public void setStartOfWorkingHours(LocalTime startOfWorkingHours) {
        this.startOfWorkingHours = startOfWorkingHours;
    }

    public LocalTime getEndOfWorkingHours() {
        return endOfWorkingHours;
    }

    public void setEndOfWorkingHours(LocalTime endOfWorkingHours) {
        this.endOfWorkingHours = endOfWorkingHours;
    }
}
