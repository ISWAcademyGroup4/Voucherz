package com.iswAcademy.Voucherz.util;


import java.util.UUID;

public  class TokenGenerator {

    public static String TokenGenerator() {
        UUID token = UUID.randomUUID();
        return token.toString();
    }
}
