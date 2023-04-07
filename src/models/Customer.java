package models;

public class Customer extends Person {
    private String address;

    public Customer() {
        super();
    }

    public Customer(String email, String phoneNumber, String firstName, String lastName, String address) {
        super(email, phoneNumber, firstName, lastName);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
