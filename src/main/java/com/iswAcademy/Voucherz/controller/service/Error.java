package com.iswAcademy.Voucherz.controller.service;

public class Error {
    private final String field;
    private final String message;


    public Error(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
