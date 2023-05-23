package models;

public abstract class Person extends User {
    private String firstName;
    private String lastName;

    public Person() {
        super();
    }

    public Person(String email, String phoneNumber, String firstName, String lastName) {
        super(email, phoneNumber);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
