package api.payload;

public class User {
    private String name;
    private String email;
    private String phone;

// create customer POJO
private String AbbProjectNumber;
    private String Address;
    private String City;
    private String ContactPerson;
    private String CustomerCode;
    private String Email;
    private String Name;
    private String Phone;
    private String PostalCode;
    private String State;

    public String getAbbProjectNumber() { return AbbProjectNumber; }
    public void setAbbProjectNumber(String abbProjectNumber) { AbbProjectNumber = abbProjectNumber; }

    public String getAddress() { return Address; }
    public void setAddress(String address) { Address = address; }

    public String getCity() { return City; }
    public void setCity(String city) { City = city; }

    public String getContactPerson() { return ContactPerson; }
    public void setContactPerson(String contactPerson) { ContactPerson = contactPerson; }

    public String getCustomerCode() { return CustomerCode; }
    public void setCustomerCode(String customerCode) { CustomerCode = customerCode; }

//    public String getEmail() { return Email; }
//    public void setEmail(String email) { Email = email; }
//
//    public String getName() { return Name; }
//    public void setName(String name) { Name = name; }
//
//    public String getPhone() { return Phone; }
//    public void setPhone(String phone) { Phone = phone; }

    public String getPostalCode() { return PostalCode; }
    public void setPostalCode(String postalCode) { PostalCode = postalCode; }

    public String getState() { return State; }
    public void setState(String state) { State = state; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
