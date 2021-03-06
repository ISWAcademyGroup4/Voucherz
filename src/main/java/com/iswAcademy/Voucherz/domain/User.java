package com.iswAcademy.Voucherz.domain;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User extends BaseEntity implements Serializable {

    @ApiModelProperty(notes = "User's Firstname")
    private  String FirstName;

    @ApiModelProperty(notes = "User's Lastname")
    private String LastName;

    @ApiModelProperty(notes = "User's email")
    private String Email;

    @ApiModelProperty(notes = "User's password")
    private String Password;

    @ApiModelProperty(notes="User's phoneNumber")
    private String PhoneNumber;

    @ApiModelProperty(notes = "User's company size")
    private int CompanySize;

    @ApiModelProperty(notes = "User's status")
    private boolean Active;

    @ApiModelProperty(notes = "Date created")
    private String DateCreated;

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

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String dateCreated) {
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
