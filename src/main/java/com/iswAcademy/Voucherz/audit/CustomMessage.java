package com.iswAcademy.Voucherz.audit;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CustomMessage implements Serializable {

    private String description;
    private String email;
    private String role;
    private String event;
    private String eventdate;

    public CustomMessage() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getDescription() {
        return description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEvent() {
        return event;
    }

    public String getEventdate() {
        return eventdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomMessage{" +
                "description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", event='" + event + '\'' +
                ", eventdate='" + eventdate + '\'' +
                '}';
    }
}
