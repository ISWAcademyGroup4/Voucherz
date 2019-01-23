package com.iswAcademy.Voucherz.controller.model;

import com.iswAcademy.Voucherz.controller.model.UserRegistrationRequest;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UpdateUserRequest {

    private String FirstName;

    private String LastName;

    private String Email;

    private String Password;

    private String PhoneNumber;

    private int CompanySize;


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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public int getCompanySize() {
        return CompanySize;
    }

    public void setCompanySize(int companySize) {
        CompanySize = companySize;
    }
}
