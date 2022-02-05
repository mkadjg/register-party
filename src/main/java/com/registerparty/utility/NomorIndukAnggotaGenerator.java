package com.registerparty.utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NomorIndukAnggotaGenerator {
    public static String generate(Integer noUrut, Date tglLahir) {
        String stringUrut = String.format("%06d", noUrut);
        SimpleDateFormat tglLahirFormat = new SimpleDateFormat("ddMMyy");
        String kodeDaerah = "320408";
        return kodeDaerah + tglLahirFormat.format(tglLahir) + stringUrut;
    }
}
