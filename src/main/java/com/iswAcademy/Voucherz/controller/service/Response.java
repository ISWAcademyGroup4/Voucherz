package com.iswAcademy.Voucherz.controller.service;

import java.util.List;

public class Response {

    private String code;
    private String description;
    List<Error> errors;

    public Response(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Response(String code, String description, List<Error> errors) {
        this.code = code;
        this.description = description;
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
