package com.registerparty.service;

import com.registerparty.model.Anggota;
import com.registerparty.model.Kecamatan;
import com.registerparty.repository.KecamatanRepository;
import com.registerparty.repository.KelurahanRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    KecamatanRepository kecamatanRepository;

    @Autowired
    KelurahanRepository kelurahanRepository;

    @Override
    public void exportReport(String namaLengkap, String nik,
                             String nomorIndukAnggota, String email,
                             int idKecamatan, int idKelurahan, OutputStream outputStream) throws IOException {

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Anggota> query = builder.createQuery(Anggota.class);
            Root<Anggota> root = query.from(Anggota.class);

            List<Predicate> predicates = new ArrayList<>();
            if (namaLengkap != null && !namaLengkap.equals("")) {
                predicates.add(builder.equal(root.get("namaAnggota"), namaLengkap));
            }

            if (nik != null && !nik.equals("")) {
                predicates.add(builder.equal(root.get("nik"), nik));
            }

            if (nomorIndukAnggota != null && !nomorIndukAnggota.equals("")) {
                predicates.add(builder.equal(root.get("nomorIndukAnggota"), nomorIndukAnggota));
            }

            if (email != null && !email.equals("")) {
                predicates.add(builder.equal(root.get("email"), email));
            }

            Kecamatan kecamatan = kecamatanRepository.findById(idKecamatan).orElse(null);
            if (kecamatan != null) {
                predicates.add(builder.equal(root.get("kecamatan"), kecamatan));
                kelurahanRepository.findById(idKelurahan).ifPresent(kelurahan -> predicates.add(builder.equal(root.get("kelurahan"), kelurahan)));
            }

            query.where(predicates.toArray(new Predicate[0]));
            List<Anggota> anggotas = entityManager.createQuery(query.select(root)).getResultList();



            XSSFWorkbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Anggota");
            Row header = sheet.createRow(0);

            CellStyle headerStyle = workbook.createCellStyle();

            XSSFFont headerFont = workbook.createFont();
            headerFont.setFontName("Arial");
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("NIK / KTP");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(1);
            headerCell.setCellValue("Nama Lengkap");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(2);
            headerCell.setCellValue("Nomor Induk Anggota");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(3);
            headerCell.setCellValue("Email");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(4);
            headerCell.setCellValue("Nomor HP");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(5);
            headerCell.setCellValue("Tempat Lahir");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(6);
            headerCell.setCellValue("Tanggal Lahir");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(7);
            headerCell.setCellValue("Jenis Kelamin");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(8);
            headerCell.setCellValue("Pendidikan");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(9);
            headerCell.setCellValue("Pekerjaan");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(10);
            headerCell.setCellValue("Kecamatan");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(10);
            headerCell.setCellValue("Kelurahan");
            headerCell.setCellStyle(headerStyle);

            CellStyle bodyStyle = workbook.createCellStyle();
            XSSFFont bodyFont = workbook.createFont();
            bodyFont.setFontName("Arial");
            bodyFont.setFontHeightInPoints((short) 10);
            bodyStyle.setFont(bodyFont);

            int index = 1;
            for (Anggota anggota : anggotas) {
                Row bodyRow = sheet.createRow(index);

                Cell bodyCell = bodyRow.createCell(0);
                bodyCell.setCellValue(anggota.getNik());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(1);
                bodyCell.setCellValue(anggota.getNamaAnggota());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(2);
                bodyCell.setCellValue(anggota.getNomorIndukAnggota());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(3);
                bodyCell.setCellValue(anggota.getEmail());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(4);
                bodyCell.setCellValue(anggota.getNoHandphone());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(5);
                bodyCell.setCellValue(anggota.getTempatLahir());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(6);
                bodyCell.setCellValue(dateFormat.format(anggota.getTanggalLahir()));
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(7);
                bodyCell.setCellValue(anggota.getJenisKelamin().getNamaJenisKelamin());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(8);
                bodyCell.setCellValue(anggota.getPendidikan().getNamaPendidikan());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(9);
                bodyCell.setCellValue(anggota.getPekerjaan().getNamaPekerjaan());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(10);
                bodyCell.setCellValue(anggota.getKecamatan().getNamaKecamatan());
                bodyCell.setCellStyle(bodyStyle);

                bodyCell = bodyRow.createCell(11);
                bodyCell.setCellValue(anggota.getKelurahan().getNamaKelurahan());
                bodyCell.setCellStyle(bodyStyle);

                index++;

            }

            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);

            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
