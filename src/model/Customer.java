package model;

public class Customer {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;


    private String creditCardNumber;
    private String creditCardExpiry;
    private String creditCardCVV;

    private Integer addressId; 

    public Customer() {}

    public Customer(int id, String firstName, String lastName, String email, String password,
                    String creditCardNumber, String creditCardExpiry, String creditCardCVV,
                    int addressId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.creditCardNumber = creditCardNumber;
        this.creditCardExpiry = creditCardExpiry;
        this.creditCardCVV = creditCardCVV;
        this.addressId = addressId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCreditCardExpiry() {
        return creditCardExpiry;
    }

    public void setCreditCardExpiry(String creditCardExpiry) {
        this.creditCardExpiry = creditCardExpiry;
    }

    public String getCreditCardCVV() {
        return creditCardCVV;
    }

    public void setCreditCardCVV(String creditCardCVV) {
        this.creditCardCVV = creditCardCVV;
    }

    public Integer getAddressId() { return addressId; }
    
    public void setAddressId(Integer addressId) { this.addressId = addressId; }

}
