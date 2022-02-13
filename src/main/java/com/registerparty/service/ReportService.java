package com.registerparty.service;

import java.io.IOException;
import java.io.OutputStream;

public interface ReportService {
    public void exportReport(String namaLengkap, String nik,
                             String nomorIndukAnggota, String email,
                             int idKecamatan, int idKelurahan, OutputStream outputStream) throws IOException;
}
