package com.iswAcademy.Voucherz.util;


import org.springframework.stereotype.Service;

import java.util.UUID;

/*
* Token generator class
*/
@Service
public class TokenGenerator {

    public static String TokenGenerator() {
        UUID token = UUID.randomUUID();
        return token.toString();
    }
}
