package com.iswAcademy.Voucherz.domain;


import java.time.LocalDateTime;

public class User extends BaseEntity{

    private  String FirstName;

    private String LastName;

    private String Email;

    private String Password;

    private String PhoneNumber;

    private int CompanySize;

    private boolean Active;

    private LocalDateTime DateCreated;

    private String Role;

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getCompanySize() {
        return CompanySize;
    }

    public void setCompanySize(int companySize) {
        CompanySize = companySize;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public LocalDateTime getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        DateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "User{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", CompanySize=" + CompanySize +
                ", Active=" + Active +
                ", DateCreated=" + DateCreated +
                ", Role='" + Role + '\'' +
                '}';
    }

}
