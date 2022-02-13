package com.registerparty.controller.rest;

import com.registerparty.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("report/")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/rekap-anggota")
    public void generate(@RequestParam("nama") String nama,
                          @RequestParam("nik") String nik,
                          @RequestParam("nomorIndukAnggota") String nomorIndukAnggota,
                          @RequestParam("email") String email,
                          @RequestParam("idKecamatan") int idKecamatan,
                          @RequestParam("idKelurahan") int idKelurahan, HttpServletResponse response
    ) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=\"rekap-anggota.xlsx\"");
        OutputStream out = response.getOutputStream();

        reportService.exportReport(nama, nik, nomorIndukAnggota, email,
                idKecamatan, idKelurahan, out);
        out.flush();
        out.close();
    }
}
