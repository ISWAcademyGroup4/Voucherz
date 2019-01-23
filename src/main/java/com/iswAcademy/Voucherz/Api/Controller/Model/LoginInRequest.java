package com.iswAcademy.Voucherz.Api.Controller.Model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class LoginInRequest {
    @Length(min=3, max= 50)
    @NotBlank(message="required")
    private String Email;

    @Length(min=3, max = 50)
    @NotBlank(message="required")
    private String Password;



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
}
