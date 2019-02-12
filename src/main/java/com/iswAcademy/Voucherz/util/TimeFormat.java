package com.iswAcademy.Voucherz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormat {
    public static String newtime() {
//    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }
}
