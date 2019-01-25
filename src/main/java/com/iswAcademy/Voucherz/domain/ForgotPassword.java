package com.iswAcademy.Voucherz.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class ForgotPassword {

    @NotEmpty
    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
