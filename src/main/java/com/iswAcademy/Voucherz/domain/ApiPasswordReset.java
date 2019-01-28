package com.iswAcademy.Voucherz.domain;

import javax.validation.constraints.NotEmpty;

public class ApiPasswordReset {

    @NotEmpty
    private String password;

    @NotEmpty
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
