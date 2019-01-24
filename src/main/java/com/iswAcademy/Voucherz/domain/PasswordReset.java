package com.iswAcademy.Voucherz.domain;

import java.util.Date;
import java.util.Objects;

public class PasswordReset {

    private long id;

    private Date expiryDate;

    private User user;

    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PasswordReset{" +
                "id=" + id +
                ", expiryDate=" + expiryDate +
                ", user=" + user +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordReset that = (PasswordReset) o;
        return id == that.id &&
                Objects.equals(expiryDate, that.expiryDate) &&
                Objects.equals(user, that.user) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, expiryDate, user, email);
    }
}
