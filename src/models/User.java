package models;

public abstract class User extends Entity {
    private String email;
    private String phoneNumber;

    public User() {
        super();
    }

    public User(String email, String phoneNumber) {
        super();
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
