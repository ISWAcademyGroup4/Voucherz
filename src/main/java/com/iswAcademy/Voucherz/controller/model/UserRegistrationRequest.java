package com.iswAcademy.Voucherz.controller.model;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

public class UserRegistrationRequest extends LoginInRequest {

    @Length(min=3, max =50)
    @NotBlank(message = "required")
    private String FirstName;

    @Length(min=3, max=50)
    @NotBlank(message = "required")
    private String LastName;

    @Length(min=3, max = 20)
    @NotBlank(message="required")
    private String PhoneNumber;

    private int CompanySize;

//    private ArrayList<Role> roles = new ArrayList<>();
//
//    public ArrayList<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(ArrayList<Role> roles) {
//        this.roles = roles;
//    }


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

//    @Override
//    public String toString() {
//        return "UserRegistrationRequest{" +
//                "FirstName='" + FirstName + '\'' +
//                ", LastName='" + LastName + '\'' +
//                ", PhoneNumber='" + PhoneNumber + '\'' +
//                ", CompanySize=" + CompanySize +
//                ", Email=" + getEmail() + '\'' +
//                ", Password=" + getPassword() + '\'' +
//                ", roles=" + roles +
//                '}';
//    }
}
